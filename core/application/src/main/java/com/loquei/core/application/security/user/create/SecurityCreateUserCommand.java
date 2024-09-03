package com.loquei.core.application.security.user.create;

public record SecurityCreateUserCommand(String username, String email) {
    public static SecurityCreateUserCommand with(final String username, final String email) {
        return new SecurityCreateUserCommand(username, email);
    }
}
