package com.loquei.core.infrastructure.security.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record SecurityGetUserResponse(
        @JsonProperty("id") String id,
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {}
