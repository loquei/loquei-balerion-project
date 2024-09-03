package com.loquei.core.infrastructure.security.auth;

import com.loquei.core.domain.security.auth.SecurityAuth;
import com.loquei.core.domain.security.auth.SecurityAuthGateway;
import com.loquei.core.domain.security.auth.SecurityAuthId;
import com.loquei.core.infrastructure.security.auth.persistence.SecurityAuthJpaEntity;
import com.loquei.core.infrastructure.security.auth.persistence.SecurityAuthJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthPostgresGateway implements SecurityAuthGateway {

    private final SecurityAuthJpaRepository authJpaRepository;

    @Override
    public SecurityAuth create(final SecurityAuth auth) {
        return this.authJpaRepository.save(SecurityAuthJpaEntity.from(auth)).toAggregate();
    }

    @Override
    public Optional<SecurityAuth> findMostRecentByEmail(final String email) {
        return this.authJpaRepository
                .findFirstByEmailOrderByCreatedAtDesc(email)
                .map(SecurityAuthJpaEntity::toAggregate);
    }

    @Override
    public void delete(final SecurityAuthId authId) {
        final var idValue = authId.getValue();
        if (this.authJpaRepository.existsById(idValue)) {
            this.authJpaRepository.deleteById(idValue);
        }
    }
}
