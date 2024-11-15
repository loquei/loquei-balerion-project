package com.loquei.core.infrastructure.user.persistence;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {

    @Query(
            nativeQuery = true,
            value =
                    """
                        SELECT
                            ROUND(CAST(AVG(r.score) AS numeric), 1)
                        FROM ratings r
                        WHERE r.item_id IN (
                                SELECT i.id
                                FROM items i
                                WHERE user_id = :userId
                            )
                        GROUP BY item_id
                    """)
    Float retrieveUserTotalScore(@Param("userId") String userId);

    @Query(
            nativeQuery = true,
            value =
                    """
                        SELECT
                            COUNT(*)
                        FROM ratings r
                        WHERE r.item_id IN (
                            SELECT i.id
                            FROM items i
                            WHERE user_id = :userId
                        )
                    """
    )
    Integer retrieveUserRatingCount(@Param("userId") String userId);

    @Query(
            nativeQuery = true,
            value =
                    """
                        SELECT
                            COUNT(*)
                        FROM rentals
                        WHERE lessor_id = :userId
                    """
    )
    Integer retrieveUserRentalsCount(@Param("userId") String userId);

    Page<UserJpaEntity> findAll(Specification<UserJpaEntity> whereClause, Pageable page);

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByDocument(String document);

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByPhone(String phone);
}
