package com.loquei.core.infrastructure.item.recently.persistence;

import com.loquei.domain.item.ItemId;
import com.loquei.domain.item.recently.RecentlyViewedItem;
import com.loquei.domain.item.recently.RecentlyViewedItemId;
import com.loquei.domain.user.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity(name = "RecentlyViewedItem")
@Table(name = "recently_viewed_items")
public class RecentlyViewedItemJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "viewed_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant viewedAt;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public RecentlyViewedItemJpaEntity() {
    }

    public RecentlyViewedItemJpaEntity(
            final String id,
            final String userId,
            final String itemId,
            final Instant viewedAt,
            final Instant createdAt
    ) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.viewedAt = viewedAt;
        this.createdAt = createdAt;
    }

    public static RecentlyViewedItemJpaEntity from(final RecentlyViewedItem aRecentlyViewedItem) {
        return new RecentlyViewedItemJpaEntity(
                aRecentlyViewedItem.getId().getValue(),
                aRecentlyViewedItem.getUserId().getValue(),
                aRecentlyViewedItem.getItemId().getValue(),
                aRecentlyViewedItem.getViewedAt(),
                aRecentlyViewedItem.getCreatedAt()
        );
    }

    public RecentlyViewedItem toEntity() {
        return RecentlyViewedItem.from(
                RecentlyViewedItemId.from(this.id),
                UserId.from(this.userId),
                ItemId.from(this.itemId),
                this.viewedAt,
                this.createdAt
        );
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Instant getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(Instant viewedAt) {
        this.viewedAt = viewedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
