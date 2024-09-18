package com.loquei.core.infrastructure.rating;

import static com.loquei.core.infrastructure.utils.SpecificationUtils.like;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.rating.Rating;
import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.rating.RatingId;
import com.loquei.core.infrastructure.rating.persistence.RatingJpaEntity;
import com.loquei.core.infrastructure.rating.persistence.RatingJpaRepository;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RatingPostgresGateway implements RatingGateway {

    private final RatingJpaRepository repository;

    public RatingPostgresGateway(final RatingJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rating create(final Rating aRating) {
        return save(aRating);
    }

    @Override
    public void deleteById(final RatingId anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) this.repository.deleteById(anIdValue);
    }

    @Override
    public Optional<Rating> findById(final RatingId anId) {
        return this.repository.findById(anId.getValue()).map(RatingJpaEntity::toAggregate);
    }

    @Override
    public Rating update(final Rating aRating) {
        return save(aRating);
    }

    @Override
    public Pagination<Rating> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(RatingJpaEntity::toAggregate).toList());
    }

    private Rating save(final Rating rating) {
        return this.repository.save(RatingJpaEntity.from(rating)).toAggregate();
    }

    private Specification<RatingJpaEntity> assembleSpecification(final String str) {
        return like("description", str);
    }
}
