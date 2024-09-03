package com.loquei.core.infrastructure.user;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.user.persistence.UserJpaEntity;
import com.loquei.core.infrastructure.user.persistence.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.loquei.core.infrastructure.utils.SpecificationUtils.like;

@Component
public class UserPostgresGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User user) {
        return save(user);
    }

    @Override
    public User update(final User user) {
        return save(user);
    }

    @Override
    public Optional<User> findById(final UserId id) {
        return this.userRepository.findById(id.getValue()).map(UserJpaEntity::toAggregate);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return this.userRepository.findByEmail(email).map(UserJpaEntity::toAggregate);
    }

    @Override
    public Optional<User> findByDocument(final String document) {
        return this.userRepository.findByDocument(document).map(UserJpaEntity::toAggregate);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return this.userRepository.findByUsername(username).map(UserJpaEntity::toAggregate);
    }

    @Override
    public Optional<User> findByPhone(final String phone) {
        return this.userRepository.findByPhone(phone).map(UserJpaEntity::toAggregate);
    }

    @Override
    public Pagination<User> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.userRepository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(UserJpaEntity::toAggregate).toList());
    }

    @Override
    public void delete(final UserId id) {
        final var idValue = id.getValue();
        if (this.userRepository.existsById(idValue)) {
            this.userRepository.deleteById(idValue);
        }
    }

    private User save(final User user) {
        return this.userRepository.save(UserJpaEntity.from(user)).toAggregate();
    }

    private Specification<UserJpaEntity> assembleSpecification(final String str) {
        final Specification<UserJpaEntity> usernameLike = like("username", str);
        final Specification<UserJpaEntity> personalNameLike = like("personalName", str);
        return usernameLike.or(personalNameLike);
    }
}
