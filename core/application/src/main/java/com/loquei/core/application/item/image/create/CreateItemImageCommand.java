package com.loquei.core.application.item.image.create;

import com.loquei.core.domain.item.ItemId;

public record CreateItemImageCommand(
        ItemId itemId,
        String fileName,
        String fileType,
        byte[] data
) {

    public static CreateItemImageCommand with(        final ItemId itemId,
                                                      final String fileName,
                                                      final String fileType,
                                                      final byte[] data) {
        return new CreateItemImageCommand(itemId, fileName, fileType, data);
    }
}
