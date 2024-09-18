package com.loquei.core.application.security.auth.authenticate;

public record SecurityAuthenticateCommand(String email, String authCode) {

    public static SecurityAuthenticateCommand with(final String email, final String authCode) {
        return new SecurityAuthenticateCommand(email, authCode);
    }
}
