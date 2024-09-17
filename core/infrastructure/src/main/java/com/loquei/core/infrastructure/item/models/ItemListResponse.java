package com.loquei.core.infrastructure.item.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;

public record ItemListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("daily_value") BigDecimal dailyValue,
        @JsonProperty("max_days") Integer maxDays,
        @JsonProperty("min_days") Integer minDays,
        @JsonProperty("updated_at") Instant updatedAt) {}
