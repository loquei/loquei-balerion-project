package com.loquei.core.application.item.image.delete;

import com.loquei.core.domain.item.image.ItemImageGateway;
import com.loquei.core.domain.item.image.ItemImageId;

public class DefaultDeleteItemImageUseCase extends DeleteItemImageUseCase {

    private final ItemImageGateway itemImageGateway;

    public DefaultDeleteItemImageUseCase(ItemImageGateway itemImageGateway) {
        this.itemImageGateway = itemImageGateway;
    }

    @Override
    public void execute(final String anIN) {
        this.itemImageGateway.delete(ItemImageId.from(anIN));
    }
}
