package com.loquei.core.infrastructure.security.auth.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityAuthJpaRepository extends JpaRepository<SecurityAuthJpaEntity, String> {

    Optional<SecurityAuthJpaEntity> findFirstByEmailOrderByCreatedAtDesc(String email);
}
