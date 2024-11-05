package com.loquei.core.application.user.retrieve.get;

import com.loquei.core.domain.user.User;
import java.time.Instant;
import java.time.LocalDate;

public record UserOutput(
        String id,
        String userName,
        String personalName,
        String email,
        String phone,
        String document,
        LocalDate birth,
        Float score,
        Instant createdAt,
        Instant updatedAt) {

    public static UserOutput from(final User user) {
        return new UserOutput(
                user.getId().getValue(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirth(),
                null,
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public static UserOutput from(final User user, final Float score) {
        return new UserOutput(
                user.getId().getValue(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirth(),
                score,
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
