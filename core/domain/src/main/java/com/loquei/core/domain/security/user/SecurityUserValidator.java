package com.loquei.core.domain.security.user;

import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

public class SecurityUserValidator extends Validator {

    private final SecurityUser user;

    public SecurityUserValidator(final SecurityUser user, final ValidationHandler aHandler) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        // TODO Implement this method
    }
}
