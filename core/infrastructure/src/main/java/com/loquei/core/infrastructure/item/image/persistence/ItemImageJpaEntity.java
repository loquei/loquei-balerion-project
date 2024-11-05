package com.loquei.core.infrastructure.item.image.persistence;

import com.loquei.common.utils.Base64Utils;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.domain.item.image.ItemImageId;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "item_image")
public class ItemImageJpaEntity {

    @Id
    private String id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private String data;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public ItemImageJpaEntity() {}

    public ItemImageJpaEntity(
            String id, String itemId, String fileName, String fileType, byte[] data, Instant createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = Base64Utils.encode(data);
        this.createdAt = createdAt;
    }

    public static ItemImageJpaEntity from(
            String id, String itemId, String fileName, String fileType, byte[] data, Instant createdAt) {
        return new ItemImageJpaEntity(id, itemId, fileName, fileType, data, createdAt);
    }

    public static ItemImageJpaEntity from(ItemImage itemImage) {
        return new ItemImageJpaEntity(
                itemImage.getId().getValue(),
                itemImage.getItemId().getValue(),
                itemImage.getFileName(),
                itemImage.getFileType(),
                itemImage.getData(),
                itemImage.getCreatedAt());
    }

    public ItemImage toEntity() {
        return ItemImage.from(
                ItemImageId.from(this.id),
                ItemId.from(this.itemId),
                this.fileName,
                this.fileType,
                Base64Utils.decode(this.data),
                this.createdAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return Base64Utils.decode(data);
    }

    public void setData(byte[] data) {
        this.data = Base64Utils.encode(data);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
