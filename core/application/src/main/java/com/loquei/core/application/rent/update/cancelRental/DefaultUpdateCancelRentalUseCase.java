package com.loquei.core.application.rent.update.cancelRental;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.user.address.update.UpdateAddressOutput;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.Address;
import io.vavr.control.Either;

import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultUpdateCancelRentalUseCase extends UpdateCancelRentalUseCase{

    private final RentGateway rentGateway;

    public DefaultUpdateCancelRentalUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
    }

    @Override
    public Either<Notification, UpdateCancelRentalOutput> execute(UpdateCancelRentalCommand anIn) {

        final var rentId = RentId.from(anIn.rentId());
        final var cancellationReason = anIn.cancellationReason();

        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        final var notification = Notification.create();

        rent.cancelRental(cancellationReason);
        rent.validate(notification);

        return notification.hasError() ? Left(notification) : update(rent);
    }

    private Either<Notification, UpdateCancelRentalOutput> update(Rent rent) {
        return Try(() -> this.rentGateway.cancelRental(rent))
                .toEither()
                .bimap(Notification::create, UpdateCancelRentalOutput::from);
    }

    private Supplier<NotFoundException> notFound(final RentId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }
}
