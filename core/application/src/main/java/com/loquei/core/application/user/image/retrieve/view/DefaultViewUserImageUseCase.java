package com.loquei.core.application.user.image.retrieve.view;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.image.UserImage;
import com.loquei.core.domain.user.image.UserImageGateway;

public class DefaultViewUserImageUseCase extends ViewUserImageUseCase {

    private final UserImageGateway userImageGateway;

    public DefaultViewUserImageUseCase(final UserImageGateway userImageGateway) {
        this.userImageGateway = userImageGateway;
    }

    @Override
    public ViewUserImageOutput execute(final String anIn) {
        final var userId = UserId.from(anIn);

        final var userImage = userImageGateway
                .findByUserId(userId)
                .orElseThrow(() -> NotFoundException.with(userId, UserImage.class));

        return ViewUserImageOutput.from(userImage);
    }
}
