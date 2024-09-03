package com.loquei.common.exceptions;

import com.loquei.common.validation.Error;

import java.util.Collections;
import java.util.List;

public class AuthException extends DomainException {

    protected AuthException(String aMessage, List<Error> someErrors) {
        super(aMessage, someErrors);
    }

    public static AuthException with(final String email) {
        final var anError = "cant authenticate user with email %s".formatted(email);
        return new AuthException(anError, Collections.emptyList());
    }
}
