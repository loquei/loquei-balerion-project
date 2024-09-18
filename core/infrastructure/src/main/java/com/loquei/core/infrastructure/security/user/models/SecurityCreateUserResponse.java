package com.loquei.core.infrastructure.security.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SecurityCreateUserResponse(
        @JsonProperty("id") String id,
        @JsonProperty("username") String username,
        @JsonProperty("email") String email) {}
