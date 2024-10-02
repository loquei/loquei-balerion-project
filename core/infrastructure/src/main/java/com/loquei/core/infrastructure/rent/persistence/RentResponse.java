package com.loquei.core.infrastructure.rent.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record RentResponse(
        @JsonProperty("id") String id,
        @JsonProperty("lessor") String lessorId,
        @JsonProperty("lesse")String lesseeId,
        @JsonProperty("item")String itemId,
        @JsonProperty("startDate")LocalDateTime startDate,
        @JsonProperty("endDate")LocalDateTime endDate,
        @JsonProperty("totalValue")BigDecimal totalValue,
        @JsonProperty("status") String status,
        @JsonProperty("cancellationReason") String cancellationReason,
        @JsonProperty("createdAt") Instant createdAt,
        @JsonProperty("updatedAt") Instant updatedAt
) {
}
