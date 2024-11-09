package com.loquei.core.domain.wishList;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

import java.util.Objects;

public class WishListValidator extends Validator {

    private final WishList wishList;

    public WishListValidator(ValidationHandler aHandler, WishList wishList) {
        super(aHandler);
        this.wishList = wishList;
    }

    public void validate() {
        validateUserId();
        validateItemId();
    }

    private void validateUserId() {
        if (Objects.isNull(wishList.getUserId())) {
            this.validationHandler().append(new Error("'userId' must not be null or empty"));
        }
    }

    private void validateItemId() {
        if (Objects.isNull(wishList.getItemId())) {
            this.validationHandler().append(new Error("'itemId' must not be null or empty"));
        }
    }

}
