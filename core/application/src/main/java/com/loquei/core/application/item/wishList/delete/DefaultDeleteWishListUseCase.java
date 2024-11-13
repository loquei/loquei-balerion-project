package com.loquei.core.application.item.wishList.delete;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.wishList.WishListGateway;
import com.loquei.core.domain.user.UserId;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteWishListUseCase extends DeleteWishListUseCase {

    private final WishListGateway wishListGateway;

    public DefaultDeleteWishListUseCase(WishListGateway wishListGateway) {
        this.wishListGateway = requireNonNull(wishListGateway);
    }


    @Override
    public void execute(DeleteWishListCommand anIN) {

        final var itemId= ItemId.from(anIN.itemId());
        final var userId= UserId.from(anIN.userId());

        this.wishListGateway.removeFromWishlist(userId, itemId);
    }
}
