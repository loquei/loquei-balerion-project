package com.loquei.core.domain.item.image;

import com.loquei.common.Entity;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.item.ItemId;
import java.time.Instant;

public class ItemImage extends Entity<ItemImageId> {

    private final ItemId itemId;
    private final String fileName;
    private final String fileType;
    private final byte[] data;
    private final Instant createdAt;

    private ItemImage(
            final ItemImageId anId,
            final ItemId itemId,
            final String fileName,
            final String fileType,
            final byte[] data,
            final Instant createdAt) {
        super(anId);
        this.itemId = itemId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createdAt = createdAt;
    }

    public static ItemImage newItemImage(
            final ItemId itemId, final String fileName, final String fileType, final byte[] data) {
        final var id = ItemImageId.unique();
        final var now = InstantUtils.now();
        return new ItemImage(id, itemId, fileName, fileType, data, now);
    }

    public static ItemImage from(
            final ItemImageId anId,
            final ItemId itemId,
            final String fileName,
            final String fileType,
            final byte[] data,
            final Instant createdAt) {
        return new ItemImage(anId, itemId, fileName, fileType, data, createdAt);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        // TODO: Implement validation
    }

    public ItemId getItemId() {
        return itemId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getData() {
        return data;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
