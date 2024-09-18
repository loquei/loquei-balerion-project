package com.loquei.core.infrastructure.security.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SecurityAuthenticateResponse(@JsonProperty("token") String token) {}
