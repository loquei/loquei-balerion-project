package com.loquei.core.application.security.user.retrieve;

import com.loquei.core.domain.security.user.SecurityUser;
import java.time.Instant;

public record SecurityUserOutput(String id, String username, String email, Instant createdAt, Instant updatedAt) {
    public static SecurityUserOutput from(
            final String id,
            final String username,
            final String email,
            final Instant createdAt,
            final Instant updatedAt) {
        return new SecurityUserOutput(id, username, email, createdAt, updatedAt);
    }

    public static SecurityUserOutput from(final SecurityUser user) {
        return new SecurityUserOutput(
                user.getId().getValue(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
