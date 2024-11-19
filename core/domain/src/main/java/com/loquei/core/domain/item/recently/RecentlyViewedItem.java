package com.loquei.core.domain.item.recently;

import com.loquei.common.Entity;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;
import java.time.Instant;

public class RecentlyViewedItem extends Entity<RecentlyViewedItemId> {

    private final UserId userId;
    private final ItemId itemId;
    private final Instant viewedAt;
    private final Instant createdAt;

    private RecentlyViewedItem(
            final RecentlyViewedItemId anId,
            final UserId userId,
            final ItemId itemId,
            final Instant viewedAt,
            final Instant createdAt) {
        super(anId);
        this.userId = userId;
        this.itemId = itemId;
        this.viewedAt = viewedAt;
        this.createdAt = createdAt;
    }

    public static RecentlyViewedItem newRecentlyViewedItem(final UserId userId, final ItemId itemId) {
        final var id = RecentlyViewedItemId.unique();
        final var now = InstantUtils.now();
        return new RecentlyViewedItem(id, userId, itemId, now, now);
    }

    public static RecentlyViewedItem from(
            final RecentlyViewedItemId id,
            final UserId userId,
            final ItemId itemId,
            final Instant viewedAt,
            final Instant createdAt) {
        return new RecentlyViewedItem(id, userId, itemId, viewedAt, createdAt);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        // TODO: Implement validation
    }

    public UserId getUserId() {
        return userId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public Instant getViewedAt() {
        return viewedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
