package com.loquei.core.application.rent.update.refuseRent;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.RentStatus;
import io.vavr.control.Either;

import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultUpdateRefuseRentUseCase extends UpdateRefuseRentUseCase {
    private final RentGateway rentGateway;

    public DefaultUpdateRefuseRentUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
    }

    @Override
    public Either<Notification, UpdateRefuseRentOutput> execute(UpdateRefuseRentCommand anIn) {

        final var rentId = RentId.from(anIn.rentId());

        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        if (!rent.getStatus().equals(RentStatus.ACCEPTED) && !rent.getStatus().equals(RentStatus.PENDING)) {
            return Left(Notification.create().append(new Error("Only accepted and pending rentals can be refused.")));
        }

        final var notification = Notification.create();

        rent.refuseRental();
        rent.validate(notification);

        return notification.hasError() ? Left(notification) : update(rent);
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
