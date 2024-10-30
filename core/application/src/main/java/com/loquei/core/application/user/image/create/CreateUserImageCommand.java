package com.loquei.core.application.user.image.create;

import com.loquei.core.domain.user.UserId;

public record CreateUserImageCommand(UserId userId, String fileName, String fileType, byte[] data) {

    public static CreateUserImageCommand with(
            final UserId userId, final String fileName, final String fileType, final byte[] data) {
        return new CreateUserImageCommand(userId, fileName, fileType, data);
    }
}
