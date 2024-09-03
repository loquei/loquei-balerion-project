package com.loquei.core.infrastructure.security.user;

import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.domain.security.user.SecurityUserId;
import com.loquei.core.infrastructure.security.user.persistence.SecurityUserJpaEntity;
import com.loquei.core.infrastructure.security.user.persistence.SecurityUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUserPostgresGateway implements SecurityUserGateway {

    private final SecurityUserJpaRepository userJpaRepository;

    @Override
    public SecurityUser create(final SecurityUser user) {
        return this.save(user);
    }

    @Override
    public SecurityUser update(SecurityUser user) {
        return this.save(user);
    }

    @Override
    public Optional<SecurityUser> findById(final SecurityUserId id) {
        return this.userJpaRepository.findById(id.getValue()).map(SecurityUserJpaEntity::toAggregate);
    }

    @Override
    public Optional<SecurityUser> findByEmail(final String email) {
        return this.userJpaRepository.findByEmail(email).map(SecurityUserJpaEntity::toAggregate);
    }

    @Override
    public void delete(SecurityUserId id) {
        final var idValue = id.getValue();
        if (this.userJpaRepository.existsById(idValue)) this.userJpaRepository.deleteById(idValue);
    }

    private SecurityUser save(final SecurityUser user) {
        return this.userJpaRepository.save(SecurityUserJpaEntity.from(user)).toAggregate();
    }
}
