package com.loquei.core.application.user.address.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateAddressUseCase
        extends UseCase<CreateAddressCommand, Either<Notification, CreateAddressOutput>> {}
