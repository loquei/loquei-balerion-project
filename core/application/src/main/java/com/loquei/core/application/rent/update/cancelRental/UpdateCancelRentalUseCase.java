package com.loquei.core.application.rent.update.cancelRental;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCancelRentalUseCase extends UseCase<UpdateCancelRentalCommand, Either<Notification, UpdateCancelRentalOutput>> {
}
