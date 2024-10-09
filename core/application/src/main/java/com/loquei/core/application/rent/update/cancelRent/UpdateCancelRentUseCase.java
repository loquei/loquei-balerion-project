package com.loquei.core.application.rent.update.cancelRent;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCancelRentUseCase extends UseCase<UpdateCancelRentCommand, Either<Notification, UpdateCancelRentOutput>> {
}
