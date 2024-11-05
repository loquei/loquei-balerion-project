package com.loquei.core.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;

public record UserResponse(
        @JsonProperty("id") String id,
        @JsonProperty("username") String userName,
        @JsonProperty("personal_name") String personalName,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone,
        @JsonProperty("document") String document,
        @JsonProperty("birth") LocalDate birth,
        @JsonProperty("score") Float score,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {}
