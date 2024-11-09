package com.loquei.core.domain.wishList;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;

import java.time.Instant;

public class WishList extends AggregateRoot<WishListId> {

    private final UserId userId;
    private final ItemId itemId;
    private final Instant createdAt;

    public WishList(final WishListId anId,
                    final UserId userId,
                    final ItemId itemId,
                    final Instant createdAt) {
        super(anId);
        this.userId = userId;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static WishList with(final WishList wishList){
        return new WishList(wishList.getId(), wishList.getUserId(), wishList.getItemId(), wishList.getCreatedAt());
    }

    public static WishList newWishList(final UserId userId,
                                       final ItemId itemId) {
        final var id = WishListId.unique();
        final var now = InstantUtils.now();

        return new WishList(id, userId, itemId, now);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

    }

    public UserId getUserId() {
        return userId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
