package com.loquei.core.application.user.image.retrieve.view;

import com.loquei.core.domain.user.image.UserImage;

public record ViewUserImageOutput(String fileType, byte[] data) {

    public static ViewUserImageOutput from(final UserImage userImage) {
        return new ViewUserImageOutput(userImage.getFileType(), userImage.getData());
    }
}
