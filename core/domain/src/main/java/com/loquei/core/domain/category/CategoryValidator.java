package com.loquei.core.domain.category;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;
import java.util.Objects;

public class CategoryValidator extends Validator {
    private static final Integer NAME_MAX_LENGTH = 255;
    private static final Integer NAME_MIN_LENGTH = 3;
    private final Category category;

    protected CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (Objects.isNull(name)) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
