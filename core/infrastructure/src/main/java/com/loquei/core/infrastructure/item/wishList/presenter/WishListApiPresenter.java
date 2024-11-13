package com.loquei.core.infrastructure.item.wishList.presenter;

import com.loquei.core.application.item.wishList.retrieve.list.ListWishListItemOutput;
import com.loquei.core.infrastructure.item.wishList.model.ListItemWishListResponse;

public interface WishListApiPresenter {

    static ListItemWishListResponse present (ListWishListItemOutput output){
        return new ListItemWishListResponse(
                output.itemId(),
                output.dailyValue(),
                output.name(),
                output.description(),
                output.itemImageId()
        );
    }
}
