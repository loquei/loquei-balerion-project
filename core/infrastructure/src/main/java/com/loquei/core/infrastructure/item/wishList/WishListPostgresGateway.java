package com.loquei.core.infrastructure.item.wishList;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.wishList.WishList;
import com.loquei.core.domain.item.wishList.WishListGateway;
import com.loquei.core.domain.item.wishList.WishListId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.item.wishList.persistence.WishListJpaEntity;
import com.loquei.core.infrastructure.item.wishList.persistence.WishListRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class WishListPostgresGateway implements WishListGateway {

    private final WishListRepository wishListRepository;

    public WishListPostgresGateway(WishListRepository wishListRepository) {
        this.wishListRepository = requireNonNull(wishListRepository);
    }

    @Override
    public WishList addToWishlist(WishList wishList) {
        return wishListRepository.save(WishListJpaEntity.from(wishList)).toEntity();
    }

    @Override
    public void removeFromWishlist(WishListId wishListId) {
        wishListRepository.deleteById(wishListId.getValue());
    }

    @Override
    public Boolean existsWishListByUserIdAndItemId(UserId userId, ItemId itemId) {
        return wishListRepository.existsWishListByUserIdAndItemId(userId.getValue(),
                itemId.getValue());
    }
}
