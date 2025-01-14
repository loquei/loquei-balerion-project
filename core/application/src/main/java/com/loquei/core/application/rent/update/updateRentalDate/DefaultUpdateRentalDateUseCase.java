package com.loquei.core.application.rent.update.updateRentalDate;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.*;
import io.vavr.control.Either;
import java.util.function.Supplier;

public class DefaultUpdateRentalDateUseCase extends UpdateRentalDateUseCase {

    private final RentGateway rentGateway;
    private final ItemGateway itemGateway;

    public DefaultUpdateRentalDateUseCase(final RentGateway rentGateway, final ItemGateway itemGateway) {
        this.rentGateway = requireNonNull(rentGateway);
        this.itemGateway = requireNonNull(itemGateway);
    }

    @Override
    public Either<Notification, UpdateRentalDateOutput> execute(UpdateRentalDateCommand anIn) {
        final var rentId = anIn.rentId();
        final var itemId = anIn.itemId();
        final var startDate = anIn.startDate();
        final var endDate = anIn.endDate();

        final var item = itemGateway.findById(itemId).orElseThrow(notFound(itemId));
        final var rent = rentGateway.findById(rentId).orElseThrow(notFound(rentId));

        final var totalValue = RentCalculator.calculateTotalValue(startDate, endDate, item);

        if (!rent.getStatus().equals(RentStatus.PENDING)) {
            return Left(Notification.create().append(new Error("Only pending rentals can change date.")));
        }

        final var notification = Notification.create();

        rent.updateRentalDate(startDate, endDate, totalValue);
        rent.validate(notification);

        return notification.hasError() ? Left(notification) : update(rent);
    }

    private Either<Notification, UpdateRentalDateOutput> update(Rent rent) {
        return Try(() -> this.rentGateway.update(rent))
                .toEither()
                .bimap(Notification::create, UpdateRentalDateOutput::from);
    }

    private Supplier<NotFoundException> notFound(final RentId anId) {
        return () -> NotFoundException.with(Rent.class, anId);
    }

    private Supplier<NotFoundException> notFound(final ItemId anId) {
        return () -> NotFoundException.with(Item.class, anId);
    }
}
