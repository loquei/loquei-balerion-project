package com.loquei.core.application.wishList.retrieve.list;

import com.loquei.core.domain.wishList.WishList;

import java.time.Instant;

public record WishListRetrieveOutput(
        String id,
        String userId,
        String itemId,
        Instant createdAt
) {

    public static WishListRetrieveOutput from(final WishList wishList){
        return new WishListRetrieveOutput(
                wishList.getId().getValue(),
                wishList.getUserId().getValue(),
                wishList.getItemId().getValue(),
                wishList.getCreatedAt());
    }
}
