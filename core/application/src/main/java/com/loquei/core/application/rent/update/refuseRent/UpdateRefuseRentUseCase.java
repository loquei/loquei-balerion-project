package com.loquei.core.application.rent.update.refuseRent;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateRefuseRentUseCase extends UseCase<UpdateRefuseRentCommand, Either<Notification, UpdateRefuseRentOutput>> {
}
