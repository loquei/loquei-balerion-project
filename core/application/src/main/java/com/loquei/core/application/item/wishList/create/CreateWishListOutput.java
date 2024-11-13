package com.loquei.core.application.item.wishList.create;

import com.loquei.core.domain.item.wishList.WishList;

import java.time.Instant;

public record CreateWishListOutput(
        String id,
        String userId,
        String itemId,
        Instant createdAt) {

    public static CreateWishListOutput from(
            final String id,
            final String userId,
            final String itemId,
            final Instant createdAt){
        return new CreateWishListOutput(id, userId, itemId, createdAt);
    }

    public static CreateWishListOutput from (final WishList wishList){
        return new CreateWishListOutput(
                wishList.getId().getValue(),
                wishList.getUserId().getValue(),
                wishList.getItemId().getValue(),
                wishList.getCreatedAt()
                );
    }
}
