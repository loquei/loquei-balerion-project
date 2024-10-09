package com.loquei.core.application.item.image.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateItemImageUseCase
        extends UseCase<CreateItemImageCommand, Either<Notification, CreateItemImageOutput>> {}
