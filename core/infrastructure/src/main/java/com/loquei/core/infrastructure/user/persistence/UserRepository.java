package com.loquei.core.infrastructure.user.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {

    Page<UserJpaEntity> findAll(Specification<UserJpaEntity> whereClause, Pageable page);

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByDocument(String document);

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByPhone(String phone);

}
