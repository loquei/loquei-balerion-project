package com.loquei.core.application.user.image.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserImageUseCase extends UseCase<CreateUserImageCommand, Either<Notification, CreateUserImageOutput>> {
}
