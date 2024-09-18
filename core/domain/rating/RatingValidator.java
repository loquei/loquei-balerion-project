package com.loquei.domain.rating;

import com.loquei.domain.validation.ValidationHandler;
import com.loquei.domain.validation.Validator;

public class RatingValidator extends Validator {
    private final Rating rating;

    protected RatingValidator(final Rating rating, final ValidationHandler aHandler) {
        super(aHandler);
        this.rating = rating;
    }

    @Override
    public void validate() {}
}
