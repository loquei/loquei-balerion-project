package com.loquei.core.application.rent.update.cancelRent;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentStatus;

import java.time.Instant;

public record UpdateCancelRentOutput(
        String id,
        RentStatus status,
        String cancellationReason,
        Instant updatedAt) {

    public static UpdateCancelRentOutput from(final Rent rent) {
        return new UpdateCancelRentOutput(
                rent.getId().getValue(),
                rent.getStatus(),
                rent.getCancellationReason(),
                rent.getUpdatedAt());
    }
}