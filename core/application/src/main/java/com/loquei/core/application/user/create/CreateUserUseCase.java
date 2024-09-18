package com.loquei.core.application.user.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserUseCase extends UseCase<CreateUserCommand, Either<Notification, CreateUserOutput>> {}