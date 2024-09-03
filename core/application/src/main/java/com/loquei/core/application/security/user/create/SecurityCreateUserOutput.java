package com.loquei.core.application.security.user.create;

import com.loquei.core.domain.security.user.SecurityUser;

public record SecurityCreateUserOutput(String id, String username, String email) {

    public static SecurityCreateUserOutput from(final String id, final String username, final String email) {
        return new SecurityCreateUserOutput(id, username, email);
    }

    public static SecurityCreateUserOutput from(final SecurityUser user) {
        return new SecurityCreateUserOutput(user.getId().getValue(), user.getUsername(), user.getEmail());
    }
}
