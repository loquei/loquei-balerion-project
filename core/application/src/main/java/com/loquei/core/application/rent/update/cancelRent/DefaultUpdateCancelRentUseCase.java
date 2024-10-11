package com.loquei.core.application.rent.update.cancelRent;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.RentStatus;
import io.vavr.control.Either;
import java.util.function.Supplier;

public class DefaultUpdateCancelRentUseCase extends UpdateCancelRentUseCase {

    private final RentGateway rentGateway;

    public DefaultUpdateCancelRentUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
    }

    @Override
    public Either<Notification, UpdateCancelRentOutput> execute(UpdateCancelRentCommand anIn) {

        final var rentId = RentId.from(anIn.rentId());
        final var cancellationReason = anIn.cancellationReason();

        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        if (!rent.getStatus().equals(RentStatus.IN_PROGRESS)) {
            return Left(Notification.create().append(new Error("Only in progress rentals can be cancelled.")));
        }

        final var notification = Notification.create();

        rent.cancelRent(cancellationReason);
        rent.validate(notification);

        return notification.hasError() ? Left(notification) : update(rent);
    }

    private Either<Notification, UpdateCancelRentOutput> update(Rent rent) {
        return Try(() -> this.rentGateway.update(rent))
                .toEither()
                .bimap(Notification::create, UpdateCancelRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final RentId anId) {
        return () -> NotFoundException.with(Rent.class, anId);
    }
}
