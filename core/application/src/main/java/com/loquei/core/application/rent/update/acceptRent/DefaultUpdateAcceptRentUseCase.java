package com.loquei.core.application.rent.update.acceptRent;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.common.event.EventDispatcher;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.RentStatus;
import com.loquei.core.domain.rent.event.RentAcceptedNotificationEvent;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;

import java.util.List;
import java.util.function.Supplier;

public class DefaultUpdateAcceptRentUseCase extends UpdateAcceptRentUseCase {
    private final RentGateway rentGateway;
    private final EventDispatcher eventDispatcher;

    public DefaultUpdateAcceptRentUseCase(final RentGateway rentGateway, final EventDispatcher eventDispatcher) {
        this.rentGateway = requireNonNull(rentGateway);
        this.eventDispatcher = requireNonNull(eventDispatcher);
    }

    @Override
    public Either<Notification, UpdateAcepptRentOutput> execute(UpdateAcceptRentCommand anIn) {

        final var rentId = RentId.from(anIn.rentId());

        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        if (!rent.getStatus().equals(RentStatus.PENDING)) {
            return Left(Notification.create().append(new Error("Only pending rentals can be accepted.")));
        }

        final var notification = Notification.create();

        rent.acceptRent();
        rent.validate(notification);

        if (notification.hasError()) return Left(notification);

        refuseConflictingRentals(rent);

        final var updatedRent = update(rent);

        eventDispatcher.dispatch(RentAcceptedNotificationEvent.with(rentId));

        return updatedRent;
    }

    private void refuseConflictingRentals(final Rent acceptedRent) {
        final var conflictingRentals = rentGateway.findConflictingPendingRentals(
                acceptedRent.getLessor(),
                acceptedRent.getItem(),
                acceptedRent.getStartDate(),
                acceptedRent.getEndDate());

        conflictingRentals.forEach(rent -> {
            rent.refuseRent();
            rentGateway.update(rent);
        });
    }

    private Either<Notification, UpdateAcepptRentOutput> update(Rent rent) {
        return Try(() -> this.rentGateway.update(rent))
                .toEither()
                .bimap(Notification::create, UpdateAcepptRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final RentId anId) {
        return () -> NotFoundException.with(Rent.class, anId);
    }
}
