package com.loquei.core.infrastructure.item.image.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemImageRepository extends JpaRepository<ItemImageJpaEntity, String> {

    List<ItemImageJpaEntity> findAllByItemId(String itemId);

    @Modifying
    @Query("DELETE FROM ItemImageJpaEntity i WHERE i.itemId = :itemId")
    void deleteByItemId(String itemId);

    @Query("""
            SELECT img
            FROM ItemImageJpaEntity img
            WHERE img.itemId = :itemId
            ORDER BY img.createdAt ASC
            LIMIT 1
            """)
    Optional<ItemImageJpaEntity> findMainImageByItemId(@Param("itemId") String itemId);
}
