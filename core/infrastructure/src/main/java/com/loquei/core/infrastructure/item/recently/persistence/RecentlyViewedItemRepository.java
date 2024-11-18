package com.loquei.core.infrastructure.item.recently.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentlyViewedItemRepository extends JpaRepository<RecentlyViewedItemJpaEntity, String> {

    List<RecentlyViewedItemJpaEntity> findAllByUserId(String userId);

}
