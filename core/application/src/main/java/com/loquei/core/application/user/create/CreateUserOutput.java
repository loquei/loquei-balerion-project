package com.loquei.core.application.user.create;

import com.loquei.core.domain.user.User;

import java.time.Instant;
import java.time.LocalDate;

public record CreateUserOutput(
        String id,
        String userName,
        String personalName,
        String email,
        String phone,
        String document,
        LocalDate birth,
        Instant createdAt,
        Instant updatedAt) {

    public static CreateUserOutput from(
            final String id,
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth,
            final Instant createdAt,
            final Instant updatedAt) {
        return new CreateUserOutput(id, userName, personalName, email, phone, document, birth, createdAt, updatedAt);
    }

    public static CreateUserOutput from(final User user) {
        return new CreateUserOutput(
                user.getId().getValue(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirth(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
