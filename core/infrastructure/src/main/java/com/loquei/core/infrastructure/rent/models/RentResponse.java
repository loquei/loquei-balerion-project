package com.loquei.core.infrastructure.rent.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record RentResponse(
        @JsonProperty("id") String id,
        @JsonProperty("lessor") String lessor,
        @JsonProperty("lessee") String lessee,
        @JsonProperty("item") String item,
        @JsonProperty("start_date") LocalDateTime startDate,
        @JsonProperty("end_date") LocalDateTime endDate,
        @JsonProperty("total_value") BigDecimal totalValue,
        @JsonProperty("status") String status,
        @JsonProperty("cancellation_reason") String cancellationReason,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {}
