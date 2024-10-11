package com.loquei.core.application.rent.update.updateRentalDate;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.RentId;
import java.time.LocalDateTime;

public record UpdateRentalDateCommand(RentId rentId, ItemId itemId, LocalDateTime startDate, LocalDateTime endDate) {

    public static UpdateRentalDateCommand with(
            final RentId rentId, final ItemId itemId, final LocalDateTime startDate, final LocalDateTime endDate) {

        return new UpdateRentalDateCommand(rentId, itemId, startDate, endDate);
    }
}
