package com.loquei.core.domain.wishList;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.user.UserId;

public interface WishListGateway {

    WishList addToWishlist(WishList wishList);

    void removeFromWishlist(WishListId wishListId);

    Pagination<WishList> findAllByUserId(UserId userId);

}
