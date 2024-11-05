package com.loquei.core.infrastructure.item;

import static com.loquei.core.infrastructure.utils.SpecificationUtils.like;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.item.persistence.ItemJpaEntity;
import com.loquei.core.infrastructure.item.persistence.ItemRepository;
import jakarta.persistence.criteria.JoinType;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ItemPostgresGateway implements ItemGateway {

    private final ItemRepository itemRespository;

    public ItemPostgresGateway(final ItemRepository itemRespository) {
        this.itemRespository = itemRespository;
    }

    @Override
    public Item create(final Item item) {
        return save(item);
    }

    @Override
    public Item update(final Item item) {
        return save(item);
    }

    @Override
    public Optional<Item> findById(final ItemId itemId) {
        return this.itemRespository.findById(itemId.getValue()).map(ItemJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Item> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.itemRespository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ItemJpaEntity::toAggregate).toList());
    }

    @Override
    public Pagination<Item> findRecentlyViewedItemsByUserId(final UserId userId, final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.itemRespository.findRecentlyViewedItemsByUserIdWithSpec(userId.getValue(), specifications, page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ItemJpaEntity::toAggregate).toList());
    }

    @Override
    public Pagination<Item> findByOwnerId(final UserId userId, final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(Specification.where(null))
                .and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId.getValue()));

        final var pageResult = this.itemRespository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ItemJpaEntity::toAggregate).toList());
    }

    @Override
    public void delete(final ItemId itemId) {
        final var idValue = itemId.getValue();
        if (this.itemRespository.existsById(idValue)) {
            this.itemRespository.deleteById(idValue);
        }
    }

    @Override
    public Pagination<Item> findAllByCategory(final CategoryId categoryId, final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(), aQuery.perPage(), Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(Specification.where(null))
                .and((root, query, criteriaBuilder) -> {
                    query.distinct(true);
                    final var join = root.join("categories", JoinType.INNER);
                    return criteriaBuilder.equal(join.get("id").get("categoryId"), categoryId.getValue());
                });

        final var pageResult = this.itemRespository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ItemJpaEntity::toAggregate).toList());
    }

    @Override
    public Float retrieveItemTotalScore(final ItemId itemId) {
        return itemRespository.retrieveItemTotalScore(itemId.getValue());
    }

    private Item save(final Item item) {
        return this.itemRespository.save(ItemJpaEntity.from(item)).toAggregate();
    }

    private Specification<ItemJpaEntity> assembleSpecification(final String str) {
        final Specification<ItemJpaEntity> nameLike = like("name", str);
        final Specification<ItemJpaEntity> desciprtionLike = like("description", str);
        return nameLike.or(desciprtionLike);
    }
}
