package com.loquei.core.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record UserListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("username") String userName,
        @JsonProperty("personal_name") String personalName,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone) {}
