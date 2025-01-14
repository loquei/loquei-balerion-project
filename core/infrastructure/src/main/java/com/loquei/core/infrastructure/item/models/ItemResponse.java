package com.loquei.core.infrastructure.item.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ItemResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("daily_value") BigDecimal dailyValue,
        @JsonProperty("max_days") Integer maxDays,
        @JsonProperty("min_days") Integer minDays,
        @JsonProperty("score") Float score,
        @JsonProperty("user_id") String userId,
        @JsonProperty("categories_ids") List<String> categoriesIds,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {}
