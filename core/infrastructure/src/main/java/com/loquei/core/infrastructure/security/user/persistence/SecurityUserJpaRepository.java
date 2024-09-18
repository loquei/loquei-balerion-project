package com.loquei.core.infrastructure.security.user.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserJpaRepository extends JpaRepository<SecurityUserJpaEntity, String> {
    Optional<SecurityUserJpaEntity> findByEmail(String email);
}
