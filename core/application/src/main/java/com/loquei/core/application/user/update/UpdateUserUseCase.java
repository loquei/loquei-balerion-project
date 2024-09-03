package com.loquei.core.application.user.update;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {}
