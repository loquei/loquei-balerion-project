package com.loquei.core.application.user.address.update;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateAddressUseCase
        extends UseCase<UpdateAddressCommand, Either<Notification, UpdateAddressOutput>> {}
