package com.loquei.core.infrastructure.rent.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CancelRentRequest(
        @JsonProperty("id") String id, @JsonProperty("cancellation_reason") String cancellationReason) {}
