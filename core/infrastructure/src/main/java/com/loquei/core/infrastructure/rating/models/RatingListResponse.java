package com.loquei.core.infrastructure.rating.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record RatingListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("rater_id") String raterId,
        @JsonProperty("description") String description,
        @JsonProperty("score") Double score,
        @JsonProperty("item_id") String itemId,
        @JsonProperty("updated_at") Instant updatedAt) {}
