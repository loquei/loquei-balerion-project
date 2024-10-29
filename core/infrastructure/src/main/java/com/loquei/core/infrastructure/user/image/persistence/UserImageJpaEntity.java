package com.loquei.core.infrastructure.user.image.persistence;

import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.image.UserImage;
import com.loquei.core.domain.user.image.UserImageId;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "user_image")
public class UserImageJpaEntity {

    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public UserImageJpaEntity() {}

    public UserImageJpaEntity(
            String id, String userId, String fileName, String fileType, byte[] data, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createdAt = createdAt;
    }

    public static UserImageJpaEntity from(
            String id, String itemId, String fileName, String fileType, byte[] data, Instant createdAt) {
        return new UserImageJpaEntity(id, itemId, fileName, fileType, data, createdAt);
    }

    public static UserImageJpaEntity from(UserImage userImage) {
        return new UserImageJpaEntity(
                userImage.getId().getValue(),
                userImage.getUserId().getValue(),
                userImage.getFileName(),
                userImage.getFileType(),
                userImage.getData(),
                userImage.getCreatedAt());
    }

    public UserImage toEntity() {
        return UserImage.from(
                UserImageId.from(this.id),
                UserId.from(this.userId),
                this.fileName,
                this.fileType,
                this.data,
                this.createdAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
