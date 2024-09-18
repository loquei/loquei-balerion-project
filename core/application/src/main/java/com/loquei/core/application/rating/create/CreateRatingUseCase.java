package com.loquei.core.application.rating.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateRatingUseCase
        extends UseCase<CreateRatingCommand, Either<Notification, CreateRatingOutput>> {}
