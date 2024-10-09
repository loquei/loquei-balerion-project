package com.loquei.core.application.rent.update.updateRentalDate;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public record UpdateRentalDateOutput(
        String rentId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        RentStatus status,
        Instant updatedAt) {

    public static UpdateRentalDateOutput from(final Rent rent) {
        return new UpdateRentalDateOutput(
                rent.getId().getValue(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getStatus(),
                rent.getUpdatedAt());
    }
}