package com.loquei.core.infrastructure.item.persistence;

import com.loquei.core.infrastructure.item.recently.persistence.RecentlyViewedItemJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<ItemJpaEntity, String> {

    Page<ItemJpaEntity> findAll(Specification<ItemJpaEntity> whereClause, Pageable page);

    @Query(
            value = "SELECT item " + "FROM Item item "
                    + "INNER JOIN RecentlyViewedItem recently ON item.id = recently.itemId "
                    + "WHERE recently.userId = :userId "
                    + "ORDER BY recently.viewedAt DESC",
            countQuery = "SELECT count(item) " + "FROM Item item "
                    + "INNER JOIN RecentlyViewedItem recently ON item.id = recently.itemId "
                    + "WHERE recently.userId = :userId")
    Page<ItemJpaEntity> findRecentlyViewedItemsByUserIdWithSpec(@Param("userId") String userId, Pageable pageable);

    default Page<ItemJpaEntity> findRecentlyViewedItemsByUserIdWithSpec(
            String userId, Specification<ItemJpaEntity> spec, Pageable pageable) {
        Specification<ItemJpaEntity> userSpec = (root, query, criteriaBuilder) -> {
            var subquery = query.subquery(String.class);
            var subRoot = subquery.from(RecentlyViewedItemJpaEntity.class);
            subquery.select(subRoot.get("itemId")).where(criteriaBuilder.equal(subRoot.get("userId"), userId));
            query.orderBy(criteriaBuilder.desc(subRoot.get("viewedAt")));
            return criteriaBuilder.in(root.get("id")).value(subquery);
        };
        return findAll(Specification.where(userSpec).and(spec), pageable);
    }
}