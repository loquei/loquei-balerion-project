package com.loquei.core.infrastructure.security.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityAuthJpaRepository extends JpaRepository<SecurityAuthJpaEntity, String> {

    Optional<SecurityAuthJpaEntity> findFirstByEmailOrderByCreatedAtDesc(String email);
}
