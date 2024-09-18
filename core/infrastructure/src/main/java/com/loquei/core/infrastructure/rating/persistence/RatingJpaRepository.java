package com.loquei.core.infrastructure.rating.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingJpaRepository extends JpaRepository<RatingJpaEntity, String> {
    Page<RatingJpaEntity> findAll(Specification<RatingJpaEntity> whereClause, Pageable page);
}
