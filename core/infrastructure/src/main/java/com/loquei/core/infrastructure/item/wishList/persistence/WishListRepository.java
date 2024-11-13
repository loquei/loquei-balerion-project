package com.loquei.core.infrastructure.item.wishList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListJpaEntity, String> {


    @Modifying
    void deleteByUserIdAndItemId(String userId, String itemId);

    Boolean existsWishListByUserIdAndItemId(String userId, String itemId);
}


