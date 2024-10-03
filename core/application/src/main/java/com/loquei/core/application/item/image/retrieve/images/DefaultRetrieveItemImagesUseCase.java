package com.loquei.core.application.item.image.retrieve.images;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImageGateway;

public class DefaultRetrieveItemImagesUseCase extends RetrieveItemImagesUseCase {

    private final ItemImageGateway itemImageGateway;

    public DefaultRetrieveItemImagesUseCase(ItemImageGateway itemImageGateway) {
        this.itemImageGateway = itemImageGateway;
    }

    @Override
    public ItemImagesOutput execute(final String itemId) {
        return ItemImagesOutput.from(itemImageGateway.findByItemId(ItemId.from(itemId)));
    }
}
