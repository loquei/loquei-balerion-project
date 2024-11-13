package com.loquei.core.infrastructure.item.wishList.persistence;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.item.wishList.WishList;
import com.loquei.core.domain.item.wishList.WishListId;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "WishList")
@Table(name = "wish_list")
public class WishListJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "created_At", nullable = false)
    private Instant createdAt;

    public WishListJpaEntity() {
    }

    public WishListJpaEntity(String id, String userId, String itemId, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static WishListJpaEntity from (WishList wishList){
        return new WishListJpaEntity(
                wishList.getId().getValue(),
                wishList.getUserId().getValue(),
                wishList.getItemId().getValue(),
                wishList.getCreatedAt()
        );
    }

    public WishList toAggregate(){
        return new WishList(
                WishListId.from(getId()),
                UserId.from(getUserId()),
                ItemId.from(getItemId()),
                getCreatedAt());
    }

    public WishList toEntity(){
        return new WishList(
                WishListId.from(this.id),
                UserId.from(this.userId),
                ItemId.from(this.itemId),
                this.createdAt
        );
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemId() {
        return itemId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
