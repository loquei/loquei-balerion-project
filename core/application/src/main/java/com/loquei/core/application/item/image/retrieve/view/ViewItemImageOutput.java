package com.loquei.core.application.item.image.retrieve.view;

import com.loquei.core.domain.item.image.ItemImage;

public record ViewItemImageOutput(
        String fileType,
        byte[] data
) {

    public static ViewItemImageOutput from(final ItemImage itemImage) {
        return new ViewItemImageOutput(itemImage.getFileType(), itemImage.getData());
    }
}
