package com.loquei.core.infrastructure.item.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record UpdateItemRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("daily_value") BigDecimal dailyValue,
        @JsonProperty("max_days") Integer maxDays,
        @JsonProperty("min_days") Integer minDays,
        @JsonProperty("categories_id") List<String> categories) {}
