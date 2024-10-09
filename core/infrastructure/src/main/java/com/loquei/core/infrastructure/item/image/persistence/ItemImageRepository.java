package com.loquei.core.infrastructure.item.image.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImageJpaEntity, String> {

    List<ItemImageJpaEntity> findAllByItemId(String itemId);

    void deleteByItemId(String itemId);
}
