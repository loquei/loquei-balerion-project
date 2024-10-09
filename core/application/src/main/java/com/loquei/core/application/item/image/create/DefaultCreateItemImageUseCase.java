package com.loquei.core.application.item.image.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.domain.item.image.ItemImageGateway;
import io.vavr.control.Either;

public class DefaultCreateItemImageUseCase extends CreateItemImageUseCase {

    private final ItemGateway itemGateway;
    private final ItemImageGateway itemImageGateway;

    public DefaultCreateItemImageUseCase(final ItemGateway itemGateway, final ItemImageGateway itemImageGateway) {
        this.itemGateway = itemGateway;
        this.itemImageGateway = itemImageGateway;
    }

    @Override
    public Either<Notification, CreateItemImageOutput> execute(final CreateItemImageCommand aCommand) {
        final var itemId = aCommand.itemId();
        final var fileName = aCommand.fileName();
        final var fileType = aCommand.fileType();
        final var data = aCommand.data();

        final var notification = Notification.create();

        if (itemGateway.findById(itemId).isEmpty()) {
            notification.append(new Error("item not found"));
        }

        final var itemImage = ItemImage.newItemImage(itemId, fileName, fileType, data);
        itemImage.validate(notification);

        return notification.hasError() ? Left(notification) : create(itemImage);
    }

    private Either<Notification, CreateItemImageOutput> create(final ItemImage itemImage) {
        return Try(() -> this.itemImageGateway.save(itemImage))
                .toEither()
                .bimap(Notification::create, CreateItemImageOutput::from);
    }
}
