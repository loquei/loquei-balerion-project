package com.loquei.core.application.rent.retrieve.list;

import com.loquei.core.application.rent.retrieve.get.GetRentOutput;
import com.loquei.core.domain.rent.Rent;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record ListRentOutput(
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

        public static ListRentOutput from(final Rent rent) {
            return new ListRentOutput(
                    rent.getId().getValue(),
                    rent.getLessor().getValue(),
                    rent.getLessee().getValue(),
                    rent.getItem().getValue(),
                    rent.getStartDate(),
                    rent.getEndDate(),
                    rent.getTotalValue(),
                    rent.getStatus().name(),
                    rent.getCancellationReason(),
                    rent.getCreatedAt(),
                    rent.getUpdatedAt());
        }
}
