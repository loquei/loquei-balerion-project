package com.loquei.core.application.user.retrieve.list;

import com.loquei.core.domain.user.User;
import java.time.Instant;

public record UserListOutput(
        String id, String userName, String personalName, String email, String phone, Instant createdAt) {

    public static UserListOutput from(final User user) {
        return new UserListOutput(
                user.getId().getValue(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedAt());
    }
}
