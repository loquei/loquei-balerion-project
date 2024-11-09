package com.loquei.core.application.wishList.delete;

import com.loquei.core.domain.wishList.WishListGateway;
import com.loquei.core.domain.wishList.WishListId;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteWishListUseCase extends DeleteWishListUseCase {

    private final WishListGateway wishListGateway;

    public DefaultDeleteWishListUseCase(WishListGateway wishListGateway) {
        this.wishListGateway = requireNonNull(wishListGateway);
    }

    @Override
    public void execute(String anIN) {
        this.wishListGateway.removeFromWishlist(WishListId.from(anIN));
    }
}
