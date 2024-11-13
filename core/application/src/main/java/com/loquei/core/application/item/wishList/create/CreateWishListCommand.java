package com.loquei.core.application.item.wishList.create;

public record CreateWishListCommand
        (String userId,
         String itemId) {

    public static CreateWishListCommand with(
            final String userId,
            final String itemId){
        return  new CreateWishListCommand(userId,itemId);
    }
}
