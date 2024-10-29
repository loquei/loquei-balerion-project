package com.loquei.core.infrastructure.user.image.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImageJpaEntity, String> {

    Optional<UserImageJpaEntity> findByUserId(String userId);

    @Modifying
    @Query("DELETE FROM UserImageJpaEntity i WHERE i.userId = :userId")
    void deleteByUserId(String userId);

}
