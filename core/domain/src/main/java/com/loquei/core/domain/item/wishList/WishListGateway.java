package com.loquei.core.domain.item.wishList;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;

public interface WishListGateway {

    WishList addToWishlist(WishList wishList);

    void removeFromWishlist(UserId userId, ItemId itemId);

    Boolean existsWishListByUserIdAndItemId(UserId userId, ItemId itemId);

}
