package com.loquei.core.application.security.user.update;

import com.loquei.core.domain.security.user.SecurityUser;

public record SecurityUpdateUserOutput(String id, String username, String email) {

    public static SecurityUpdateUserOutput from(final String id, final String username, final String email) {
        return new SecurityUpdateUserOutput(id, username, email);
    }

    public static SecurityUpdateUserOutput from(final SecurityUser user) {
        return new SecurityUpdateUserOutput(user.getId().getValue(), user.getUsername(), user.getEmail());
    }
}
