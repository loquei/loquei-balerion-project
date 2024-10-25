package com.loquei.core.infrastructure.item.image.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemImageRepository extends JpaRepository<ItemImageJpaEntity, String> {

    List<ItemImageJpaEntity> findAllByItemId(String itemId);

    @Modifying
    @Query("DELETE FROM ItemImageJpaEntity i WHERE i.itemId = :itemId")
    void deleteByItemId(String itemId);
}
