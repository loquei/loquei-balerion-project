package com.loquei.core.application.rent.create;

import com.loquei.core.domain.rent.Rent;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record CreateRentOutput(
        String id,
        String lessorId,
        String lesseeId,
        String itemId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalValue,
        String status,
        String cancellationReason,
        Instant createdAt,
        Instant updatedAt) {

    public static CreateRentOutput from(
            final String id,
            final String lessorId,
            final String lesseeId,
            final String itemId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final BigDecimal totalValue,
            final String status,
            final String cancellationReason,
            final Instant createdAt,
            final Instant updatedAt) {

        return new CreateRentOutput(
                id,
                lessorId,
                lesseeId,
                itemId,
                startDate,
                endDate,
                totalValue,
                status,
                cancellationReason,
                createdAt,
                updatedAt);
    }

    public static CreateRentOutput from(final Rent rent) {
        return new CreateRentOutput(
                rent.getId().getValue(),
                rent.getLessor().getId().getValue(),
                rent.getLessee().getId().getValue(),
                rent.getItem().getId().getValue(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getTotalValue(),
                rent.getStatus().name(),
                rent.getCancellationReason(),
                rent.getCreatedAt(),
                rent.getUpdatedAt());
    }
}
