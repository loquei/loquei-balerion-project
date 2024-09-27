package com.loquei.core.application.rent.update.acceptRent;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateAcceptRentUseCase extends UseCase<UpdateAcceptRentCommand, Either<Notification, UpdateAcepptRentOutput>> {
}
