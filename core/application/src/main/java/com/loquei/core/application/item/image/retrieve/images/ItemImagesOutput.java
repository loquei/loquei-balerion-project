package com.loquei.core.application.item.image.retrieve.images;

import com.loquei.core.domain.item.image.ItemImage;

import java.util.List;

public record ItemImagesOutput(
        List<String> ids
) {
    public static ItemImagesOutput from(final List<ItemImage> itemImages) {
        return new ItemImagesOutput(itemImages.stream().map(itemImage -> itemImage.getId().getValue()).toList());
    }
}
