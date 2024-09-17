package com.loquei.core.domain.item;

import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

public class ItemValidator extends Validator {

    private final Item item;

    protected ItemValidator(final Item item, final ValidationHandler aHandler) {
        super(aHandler);
        this.item = item;
    }

    @Override
    public void validate() {
        // TODO add validations
    }
}
