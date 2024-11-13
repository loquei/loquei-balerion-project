package com.loquei.core.application.item.wishList.create;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateWishListUseCase extends UseCase<CreateWishListCommand, Either<Notification, CreateWishListOutput>> {
}
