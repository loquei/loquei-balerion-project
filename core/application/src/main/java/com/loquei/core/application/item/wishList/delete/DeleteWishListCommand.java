package com.loquei.core.application.item.wishList.delete;

import com.loquei.core.application.item.wishList.create.CreateWishListCommand;

public record DeleteWishListCommand(
        String itemId,
        String userId
) {

    public static DeleteWishListCommand with(
            final String userId,
            final String itemId){
        return  new DeleteWishListCommand(userId,itemId);
    }
}
