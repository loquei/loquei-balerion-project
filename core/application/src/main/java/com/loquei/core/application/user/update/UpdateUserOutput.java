package com.loquei.core.application.user.update;

import com.loquei.core.domain.user.User;

import java.time.Instant;
import java.time.LocalDate;

public record UpdateUserOutput(
        String id,
        String userName,
        String personalName,
        String email,
        String phone,
        String document,
        LocalDate birth,
        Instant createdAt,
        Instant updatedAt) {

    public static UpdateUserOutput from(final User user) {
        return new UpdateUserOutput(
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
