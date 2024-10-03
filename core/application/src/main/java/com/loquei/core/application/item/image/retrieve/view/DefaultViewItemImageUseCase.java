package com.loquei.core.application.item.image.retrieve.view;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.domain.item.image.ItemImageGateway;
import com.loquei.core.domain.item.image.ItemImageId;

public class DefaultViewItemImageUseCase extends ViewItemImageUseCase {

    private final ItemImageGateway itemImageGateway;

    public DefaultViewItemImageUseCase(ItemImageGateway itemImageGateway) {
        this.itemImageGateway = itemImageGateway;
    }

    @Override
    public ViewItemImageOutput execute(final String anIn) {
        final var itemImageId = ItemImageId.from(anIn);

        final var itemImage = itemImageGateway.findById(itemImageId)
                .orElseThrow(() -> NotFoundException.with(itemImageId, ItemImage.class));

        return ViewItemImageOutput.from(itemImage);
    }
}
