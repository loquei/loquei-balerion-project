package com.loquei.core.infrastructure.item.wishList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListJpaEntity, String> {


    Boolean existsWishListByUserIdAndItemId(String userId, String itemId);
}


