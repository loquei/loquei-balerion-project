package com.loquei.core.infrastructure.rating.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateRatingRequest(
        @JsonProperty("rater_id") String raterId,
        @JsonProperty("description") String description,
        @JsonProperty("score") Double score,
        @JsonProperty("item_id") String itemId) {}
