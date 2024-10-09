package com.loquei.core.application.rent.update.updateRentalDate;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateRentalDateUseCase extends UseCase<UpdateRentalDateCommand, Either<Notification, UpdateRentalDateOutput>> {
}
