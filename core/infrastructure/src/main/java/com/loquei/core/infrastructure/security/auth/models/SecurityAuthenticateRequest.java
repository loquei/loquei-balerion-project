package com.loquei.core.infrastructure.security.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SecurityAuthenticateRequest(@JsonProperty("email") String email, @JsonProperty("code") String code) {}
