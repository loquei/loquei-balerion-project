package com.loquei.core.application.rent.update.acceptRent;

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

public class DefaultUpdateAcceptRentUseCase extends UpdateAcceptRentUseCase {
    private final RentGateway rentGateway;

    public DefaultUpdateAcceptRentUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
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

        return notification.hasError() ? Left(notification) : update(rent);
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
