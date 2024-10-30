package com.loquei.core.application.user.image.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.image.UserImage;
import com.loquei.core.domain.user.image.UserImageGateway;
import io.vavr.control.Either;

public class DefaultCreateUserImageUseCase extends CreateUserImageUseCase {

    private final UserGateway userGateway;
    private final UserImageGateway userImageGateway;

    public DefaultCreateUserImageUseCase(final UserGateway userGateway, final UserImageGateway userImageGateway) {
        this.userGateway = userGateway;
        this.userImageGateway = userImageGateway;
    }

    @Override
    public Either<Notification, CreateUserImageOutput> execute(final CreateUserImageCommand aCommand) {
        final var userId = aCommand.userId();
        final var fileName = aCommand.fileName();
        final var fileType = aCommand.fileType();
        final var data = aCommand.data();

        final var notification = Notification.create();

        if (userGateway.findById(userId).isEmpty()) {
            notification.append(new Error("user not found"));
        }

        if (userImageGateway.findByUserId(userId).isPresent()) {
            notification.append(new Error("user image already exists"));
        }

        final var userImage = UserImage.newUserImage(userId, fileName, fileType, data);
        userImage.validate(notification);

        return notification.hasError() ? Left(notification) : create(userImage);
    }

    private Either<Notification, CreateUserImageOutput> create(final UserImage userImage) {
        return Try(() -> this.userImageGateway.save(userImage))
                .toEither()
                .bimap(Notification::create, CreateUserImageOutput::from);
    }
}
