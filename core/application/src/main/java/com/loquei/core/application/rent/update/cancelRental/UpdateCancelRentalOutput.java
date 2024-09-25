package com.loquei.core.application.rent.update.cancelRental;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentStatus;

import java.time.Instant;

public record UpdateCancelRentalOutput(
        String id,
        RentStatus status,
        String cancellationReason,
        Instant updatedAt) {

    public static UpdateCancelRentalOutput from(final Rent rent) {
        return new UpdateCancelRentalOutput(
                rent.getId().getValue(),
                rent.getStatus(),
                rent.getCancellationReason(),
                rent.getUpdatedAt());
    }
}