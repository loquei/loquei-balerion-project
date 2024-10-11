package com.loquei.core.application.rent.update.refuseRent;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentStatus;
import java.time.Instant;

public record UpdateRefuseRentOutput(String id, RentStatus status, Instant updatedAt) {

    public static UpdateRefuseRentOutput from(final Rent rent) {
        return new UpdateRefuseRentOutput(rent.getId().getValue(), rent.getStatus(), rent.getUpdatedAt());
    }
}
