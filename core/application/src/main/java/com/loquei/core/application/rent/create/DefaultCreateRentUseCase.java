package com.loquei.core.application.rent.create;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentCalculator;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultCreateRentUseCase extends CreateRentUseCase {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final EmailGateway emailGateway;

    public DefaultCreateRentUseCase(
            final RentGateway rentGateway,
            final UserGateway userGateway,
            final ItemGateway itemGateway,
            final EmailGateway emailGateway
    ) {
        this.rentGateway = requireNonNull(rentGateway);
        this.userGateway = requireNonNull(userGateway);
        this.itemGateway = requireNonNull(itemGateway);
        this.emailGateway = requireNonNull(emailGateway);
    }

    @Override
    public Either<Notification, CreateRentOutput> execute(CreateRentCommand anIn) {
        final var notification = Notification.create();

        final var lessorId = UserId.from(anIn.lessorId());
        final var lesseeId = UserId.from(anIn.lesseeId());
        final var itemId = ItemId.from(anIn.itemId());
        final var startDate = anIn.startDate();
        final var endDate = anIn.endDate();

        final var lessor = userGateway.findById(lessorId).orElseThrow(notFound(lessorId));
        final var lessee = userGateway.findById(lesseeId).orElseThrow(notFound(lesseeId));
        final var item = itemGateway.findById(itemId).orElseThrow(notFound(itemId));

        checkRentDurationCompatibility(item, startDate, endDate).ifPresent(notification::append);

        final var totalValue = RentCalculator.calculateTotalValue(startDate, endDate, item);

        isItemAvailableForRent(item.getId(), startDate, endDate).ifPresent(notification::append);

        final var rent = Rent.newRent(lessor.getId(), lessee.getId(), item.getId(), startDate, endDate, totalValue);

        rent.validate(notification);

        if (notification.hasError()) return Left(notification);

        final var createdReturn = create(rent);

        sendEmails(lessor, lessee, item, rent);

        return createdReturn;
    }

    private void sendEmails(final User lessor, final User lessee, final Item item, final Rent rent) {
        sendLessorEmail(lessor, item, rent);
        sendLesseeEmail(lessee, item, rent);
    }

    private void sendLessorEmail(final User lessor, final Item item, final Rent rent) {
        final var lessorEmail = SendLessorRentRequestEmailHelper.buildLessorEmail(lessor, item, rent);
        CompletableFuture.runAsync(() -> this.emailGateway.send(lessorEmail));
    }

    private void sendLesseeEmail(final User lessee, final Item item, final Rent rent) {
        final var lesseeEmail = SendLesseeRentRequestEmailHelper.buildLesseeEmail(lessee, item, rent);
        CompletableFuture.runAsync(() -> this.emailGateway.send(lesseeEmail));
    }

    private Optional<Error> isItemAvailableForRent(ItemId itemId, LocalDateTime startDate, LocalDateTime endDate) {
        final var isAvailable = rentGateway.isItemAvailableForRent(itemId, startDate, endDate);

        return !isAvailable ? Optional.of(new Error("item is not available")) : Optional.empty();
    }

    private Optional<Error> checkRentDurationCompatibility(Item item, LocalDateTime startDate, LocalDateTime endDate) {

        long rentalDays = ChronoUnit.DAYS.between(startDate, endDate);

        if (rentalDays < item.getMinDays()) {
            return Optional.of(new Error(
                    "The rental duration is shorter than the allowed minimum of " + item.getMinDays() + " days."));
        }

        if (rentalDays > item.getMaxDays()) {
            return Optional.of(
                    new Error("The rental duration exceeds the allowed maximum of " + item.getMaxDays() + " days."));
        }

        return Optional.empty();
    }

    private Either<Notification, CreateRentOutput> create(Rent rent) {
        return Try(() -> this.rentGateway.rent(rent)).toEither().bimap(Notification::create, CreateRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final UserId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }

    private Supplier<NotFoundException> notFound(final ItemId anId) {
        return () -> NotFoundException.with(Item.class, anId);
    }
}
