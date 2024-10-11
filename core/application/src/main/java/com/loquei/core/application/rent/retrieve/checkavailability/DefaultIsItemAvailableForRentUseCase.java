package com.loquei.core.application.rent.retrieve.checkavailability;

import static java.util.Objects.requireNonNull;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.RentGateway;

public class DefaultIsItemAvailableForRentUseCase extends IsItemAvailableForRentUseCase {

    private final RentGateway rentGateway;

    public DefaultIsItemAvailableForRentUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
    }

    @Override
    public Boolean execute(IsItemAvailableForRentCommand command) {
        // Extrai os valores do comando
        final var itemId = command.itemId();
        final var startDate = command.startDate();
        final var endDate = command.endDate();

        // Verifica a disponibilidade usando o RentGateway
        return rentGateway.isItemAvailableForRent(ItemId.from(itemId), startDate, endDate);
    }
}
