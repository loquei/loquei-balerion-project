package com.loquei.core.application.rent.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateRentUseCase extends UseCase<CreateRentCommand, Either<Notification, CreateRentOutput>>{

}

