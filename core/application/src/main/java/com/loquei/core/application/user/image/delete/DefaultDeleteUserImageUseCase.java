package com.loquei.core.application.user.image.delete;

import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.image.UserImageGateway;

public class DefaultDeleteUserImageUseCase extends DeleteUserImageUseCase {

    private final UserImageGateway userImageGateway;

    public DefaultDeleteUserImageUseCase(final UserImageGateway userImageGateway) {
        this.userImageGateway = userImageGateway;
    }

    @Override
    public void execute(final String anIN) {
        final var userId = UserId.from(anIN);
        userImageGateway.deleteByUserId(userId);
    }
}
