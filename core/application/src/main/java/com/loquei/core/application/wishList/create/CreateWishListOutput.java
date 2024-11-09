package com.loquei.core.application.wishList.create;

import com.loquei.core.domain.wishList.WishList;

import java.time.Instant;

public record CreateWishListOutput(
        String userId,
        String itemId,
        Instant createdAt) {

    public static CreateWishListOutput from(
            final String userId,
            final String itemId,
            final Instant createdAt){
        return new CreateWishListOutput(userId, itemId, createdAt);
    }

    public static CreateWishListOutput from (final WishList wishList){
        return new CreateWishListOutput(
                wishList.getUserId().getValue(),
                wishList.getItemId().getValue(),
                wishList.getCreatedAt()
                );
    }
}
