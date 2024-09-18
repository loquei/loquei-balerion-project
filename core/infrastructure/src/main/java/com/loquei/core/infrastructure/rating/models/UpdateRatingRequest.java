package com.loquei.core.infrastructure.rating.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateRatingRequest(
        @JsonProperty("description") String description, @JsonProperty("score") Double score) {}
