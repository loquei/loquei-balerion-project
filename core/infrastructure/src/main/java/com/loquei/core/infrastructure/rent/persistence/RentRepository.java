package com.loquei.core.infrastructure.rent.persistence;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentRepository extends JpaRepository<RentJpaEntity, String> {

    @Query("SELECT r FROM Rent r WHERE r.lessorId = :userId OR r.lesseeId = :userId")
    Page<RentJpaEntity> findAllByUserId(@Param("userId") String userId, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN false ELSE true END "
            + "FROM Rent r WHERE r.itemId = :itemId AND (r.status = 'ACCEPTED' OR r.status = 'ACTIVE') "
            + "AND (r.startDate <= :endDate AND r.endDate >= :startDate)")
    boolean isItemAvailableForRent(
            @Param("itemId") String itemId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
