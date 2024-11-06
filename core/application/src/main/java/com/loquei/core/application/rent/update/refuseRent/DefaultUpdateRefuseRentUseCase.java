package com.loquei.core.application.rent.update.refuseRent;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.common.event.EventDispatcher;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.RentStatus;
import com.loquei.core.domain.rent.event.RentRefusedNotificationEvent;
import io.vavr.control.Either;
import java.util.function.Supplier;

public class DefaultUpdateRefuseRentUseCase extends UpdateRefuseRentUseCase {
    private final RentGateway rentGateway;
    private final EventDispatcher eventDispatcher;

    public DefaultUpdateRefuseRentUseCase(final RentGateway rentGateway, final EventDispatcher eventDispatcher) {
        this.rentGateway = requireNonNull(rentGateway);
        this.eventDispatcher = requireNonNull(eventDispatcher);
    }

    @Override
    public Either<Notification, UpdateRefuseRentOutput> execute(UpdateRefuseRentCommand anIn) {

        final var rentId = RentId.from(anIn.rentId());

        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        if (!rent.getStatus().equals(RentStatus.ACCEPTED) && !rent.getStatus().equals(RentStatus.PENDING)) {
            return Left(Notification.create().append(new Error("Only accepted and pending rentals can be refused.")));
        }

        final var notification = Notification.create();

        rent.refuseRent();
        rent.validate(notification);

        if (notification.hasError()) return Left(notification);

        final var updatedRetrun = update(rent);

        eventDispatcher.dispatch(RentRefusedNotificationEvent.with(rentId));

        return updatedRetrun;
    }

    private Either<Notification, UpdateRefuseRentOutput> update(Rent rent) {
        return Try(() -> this.rentGateway.update(rent))
                .toEither()
                .bimap(Notification::create, UpdateRefuseRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final RentId anId) {
        return () -> NotFoundException.with(Rent.class, anId);
    }
}
