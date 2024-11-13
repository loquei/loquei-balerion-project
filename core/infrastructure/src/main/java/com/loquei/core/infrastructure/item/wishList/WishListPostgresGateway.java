package com.loquei.core.infrastructure.item.wishList;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.wishList.WishList;
import com.loquei.core.domain.item.wishList.WishListGateway;
import com.loquei.core.domain.item.wishList.WishListId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.item.wishList.persistence.WishListJpaEntity;
import com.loquei.core.infrastructure.item.wishList.persistence.WishListRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void removeFromWishlist(UserId userId, ItemId itemId) {
        wishListRepository.deleteByUserIdAndItemId(userId.getValue(), itemId.getValue());
    }


    @Override
    public Boolean existsWishListByUserIdAndItemId(UserId userId, ItemId itemId) {
        return wishListRepository.existsWishListByUserIdAndItemId(userId.getValue(),
                itemId.getValue());
    }
}
