package com.loquei.core.domain.security.auth;


import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;

import java.time.Instant;

public class SecurityAuth extends AggregateRoot<SecurityAuthId> {

    private String email;
    private SecurityAuthCode authCode;
    private String authToken;
    private Instant expiresAt;
    private Instant createdAt;

    private SecurityAuth(
            final SecurityAuthId anId,
            final String email,
            final SecurityAuthCode authCode,
            final String authToken,
            final Instant expiresAt,
            final Instant createdAt) {
        super(anId);
        this.email = email;
        this.authCode = authCode;
        this.authToken = authToken;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    public static SecurityAuth newAuth(final String email, final String authToken) {
        final var id = SecurityAuthId.unique();
        final var authCode = SecurityAuthCode.generate();
        final var now = InstantUtils.now();
        final var expires = now.plusSeconds(60 * 10);
        return new SecurityAuth(id, email, authCode, authToken, expires, now);
    }

    public static SecurityAuth with(
            final SecurityAuthId anId,
            final String email,
            final SecurityAuthCode authCode,
            final String authToken,
            final Instant expiresAt,
            final Instant createdAt) {
        return new SecurityAuth(anId, email, authCode, authToken, expiresAt, createdAt);
    }

    public static SecurityAuth with(final SecurityAuth auth) {
        return new SecurityAuth(
                auth.getId(),
                auth.getEmail(),
                auth.getAuthCode(),
                auth.getAuthToken(),
                auth.getExpiresAt(),
                auth.getCreatedAt());
    }

    @Override
    public void validate(final ValidationHandler aHandler) {}

    public String getEmail() {
        return email;
    }

    public SecurityAuthCode getAuthCode() {
        return authCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
