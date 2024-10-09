package com.loquei.core.application.item.image.create;

import com.loquei.core.domain.item.image.ItemImage;

public record CreateItemImageOutput(String id) {

    public static CreateItemImageOutput from(final ItemImage itemImage) {
        return new CreateItemImageOutput(itemImage.getId().getValue());
    }
}
