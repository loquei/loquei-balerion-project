package com.loquei.core.application.item.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateItemUseCase extends UseCase<CreateItemCommand, Either<Notification, CreateItemOutput>> {}
