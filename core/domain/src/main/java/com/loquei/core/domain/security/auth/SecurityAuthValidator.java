package com.loquei.core.domain.security.auth;

import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

public class SecurityAuthValidator extends Validator {

    private final SecurityAuth auth;

    protected SecurityAuthValidator(final SecurityAuth auth, final ValidationHandler aHandler) {
        super(aHandler);
        this.auth = auth;
    }

    @Override
    public void validate() {
        // TODO implement validations
    }
}
