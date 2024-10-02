package com.loquei.core.infrastructure.item.image.persistence;

import com.loquei.core.domain.item.image.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImageJpaEntity, String> {

    List<ItemImageJpaEntity> findAllByItemId(String itemId);
}
