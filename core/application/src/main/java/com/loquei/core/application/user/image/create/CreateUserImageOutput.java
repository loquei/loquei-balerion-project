package com.loquei.core.application.user.image.create;

import com.loquei.core.domain.user.image.UserImage;

public record CreateUserImageOutput(String id) {

    public static CreateUserImageOutput from(final UserImage userImage) {
        return new CreateUserImageOutput(userImage.getId().getValue());
    }

}
