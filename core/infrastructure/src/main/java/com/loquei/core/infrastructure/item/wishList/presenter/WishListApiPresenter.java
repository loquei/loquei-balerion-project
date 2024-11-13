package com.loquei.core.infrastructure.item.wishList.presenter;

import com.loquei.core.application.item.wishList.retrieve.list.ListWishListItemOutput;
import com.loquei.core.infrastructure.item.wishList.model.ListItemWishListResponse;

import java.net.URI;

public interface WishListApiPresenter {

    static ListItemWishListResponse present (ListWishListItemOutput output, String baseImagePath){
        return new ListItemWishListResponse(
                output.itemId(),
                output.dailyValue(),
                output.name(),
                output.description(),
                URI.create(baseImagePath + output.itemImageId()).toString()
        );
    }
}
