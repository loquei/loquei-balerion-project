package com.loquei.core.domain.security.user;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;

import java.time.Instant;

public class SecurityUser extends AggregateRoot<SecurityUserId> {

    private String username;
    private String email;
    private final Instant createdAt;
    private Instant updatedAt;

    private SecurityUser(
            final SecurityUserId anId,
            final String username,
            final String email,
            final Instant createdAt,
            final Instant updatedAt) {
        super(anId);
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static SecurityUser newUser(final String username, final String email) {
        final var id = SecurityUserId.unique();
        final var now = InstantUtils.now();
        return new SecurityUser(id, username, email, now, now);
    }

    public static SecurityUser with(
            final SecurityUserId anId,
            final String username,
            final String email,
            final Instant createdAt,
            final Instant updatedAt) {
        return new SecurityUser(anId, username, email, createdAt, updatedAt);
    }

    public static SecurityUser with(final SecurityUser user) {
        return new SecurityUser(
                user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public SecurityUser update(final String username, final String email) {
        this.username = username;
        this.email = email;
        this.updatedAt = InstantUtils.now();

        return this;
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        new SecurityUserValidator(this, aHandler).validate();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
