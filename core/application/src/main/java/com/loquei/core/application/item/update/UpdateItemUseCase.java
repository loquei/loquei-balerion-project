package com.loquei.core.application.item.update;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateItemUseCase extends UseCase<UpdateItemCommand, Either<Notification, UpdateItemOutput>> {}
