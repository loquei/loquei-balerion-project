package com.loquei.core.application.rent.update.acceptRent;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentStatus;
import java.time.Instant;

public record UpdateAcepptRentOutput(String id, RentStatus status, Instant updatedAt) {

    public static UpdateAcepptRentOutput from(final Rent rent) {
        return new UpdateAcepptRentOutput(rent.getId().getValue(), rent.getStatus(), rent.getUpdatedAt());
    }
}
