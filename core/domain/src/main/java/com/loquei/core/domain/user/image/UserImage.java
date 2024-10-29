package com.loquei.core.domain.user.image;

import com.loquei.common.Entity;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.user.UserId;

import java.time.Instant;

public class UserImage extends Entity<UserImageId> {

    private final UserId userId;
    private final String fileName;
    private final String fileType;
    private final byte[] data;
    private final Instant createdAt;

    private UserImage(
            final UserImageId anId,
            final UserId userId,
            final String fileName,
            final String fileType,
            final byte[] data,
            final Instant createdAt) {
        super(anId);
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createdAt = createdAt;
    }

    public static UserImage newUserImage(
            final UserId userId, final String fileName, final String fileType, final byte[] data) {
        final var id = UserImageId.unique();
        final var now = InstantUtils.now();
        return new UserImage(id, userId, fileName, fileType, data, now);
    }

    public static UserImage from(
            final UserImageId anId,
            final UserId userId,
            final String fileName,
            final String fileType,
            final byte[] data,
            final Instant createdAt) {
        return new UserImage(anId, userId, fileName, fileType, data, createdAt);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        // TODO: Implement validation
    }

    public UserId getUserId() {
        return userId;
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
