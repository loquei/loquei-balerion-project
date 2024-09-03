package com.loquei.core.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateUserRequest(
        @JsonProperty("username") String userName,
        @JsonProperty("personal_name") String personalName,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone,
        @JsonProperty("document") String document,
        @JsonProperty("birth") LocalDate birth) {}
