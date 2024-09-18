package com.loquei.core.application.rating.update;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateRatingUseCase
        extends UseCase<UpdateRatingCommand, Either<Notification, UpdateRatingOutput>> {}
