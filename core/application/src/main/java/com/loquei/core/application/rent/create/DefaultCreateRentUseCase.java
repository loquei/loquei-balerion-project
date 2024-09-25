package com.loquei.core.application.rent.create;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultCreateRentUseCase extends CreateRentUseCase{

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;

    public DefaultCreateRentUseCase(final RentGateway rentGateway, final UserGateway userGateway, final ItemGateway itemGateway) {
        this.rentGateway = requireNonNull(rentGateway);
        this.userGateway = requireNonNull(userGateway);
        this.itemGateway = requireNonNull(itemGateway);
    }

    @Override
    public Either<Notification, CreateRentOutput> execute(CreateRentCommand anIn) {
        final var notification = Notification.create();

        final var lessorId = UserId.from(anIn.lessorId());
        final var lesseeId =  UserId.from(anIn.lesseeId());
        final var itemId = ItemId.from(anIn.itemId());
        final var startDate = anIn.startDate();
        final var endDate = anIn.endDate();
        final var totalValue = anIn.totalValue();

        final var lessor = userGateway.findById(lessorId).orElseThrow(notFound(lessorId));
        final var lessee = userGateway.findById(lesseeId).orElseThrow(notFound(lesseeId));
        final var item = itemGateway.findById(itemId).orElseThrow(notFound(itemId));

        isItemAvailableForRent(item.getId(), startDate, endDate).ifPresent(notification::append);

        final var rent = Rent.newRent(lessor.getId(), lessee.getId(), item.getId(), startDate, endDate, totalValue);

        rent.validate(notification);

        return notification.hasError() ? Left(notification) : create(rent);

    }

    private Optional<Error> isItemAvailableForRent(ItemId itemId, LocalDateTime startDate, LocalDateTime endDate){
        final var exists = rentGateway.isItemAvailableForRent(itemId, startDate, endDate);

        return exists ? Optional.of(new Error("item is not available")) : Optional.empty();
    }

    private Either<Notification, CreateRentOutput> create(Rent rent){
        return Try(() -> this.rentGateway.rent(rent))
                .toEither()
                .bimap(Notification::create, CreateRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final UserId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }

    private Supplier<NotFoundException> notFound(final ItemId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }
}
