package com.loquei.core.domain.item.wishList;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;

import java.util.Optional;

public interface WishListGateway {

    WishList addToWishlist(WishList wishList);

    void removeFromWishlist(WishListId wishListId);

    Boolean existsWishListByUserIdAndItemId(UserId userId, ItemId itemId);

}
