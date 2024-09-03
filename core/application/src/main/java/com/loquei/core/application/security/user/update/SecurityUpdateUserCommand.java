package com.loquei.core.application.security.user.update;

public record SecurityUpdateUserCommand(String id, String username, String email) {
    public static SecurityUpdateUserCommand with(final String id, final String username, final String email) {
        return new SecurityUpdateUserCommand(id, username, email);
    }
}
