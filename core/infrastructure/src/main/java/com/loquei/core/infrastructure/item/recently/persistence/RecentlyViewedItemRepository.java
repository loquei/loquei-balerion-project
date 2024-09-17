package com.loquei.core.infrastructure.item.recently.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentlyViewedItemRepository extends JpaRepository<RecentlyViewedItemJpaEntity, String> {}
