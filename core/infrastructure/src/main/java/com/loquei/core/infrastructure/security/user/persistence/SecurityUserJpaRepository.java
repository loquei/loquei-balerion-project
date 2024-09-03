package com.loquei.core.infrastructure.security.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserJpaRepository extends JpaRepository<SecurityUserJpaEntity, String> {
    Optional<SecurityUserJpaEntity> findByEmail(String email);
}
