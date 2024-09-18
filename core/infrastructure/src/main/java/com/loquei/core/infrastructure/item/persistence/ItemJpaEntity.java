package com.loquei.core.infrastructure.item.persistence;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Item")
@Table(name = "items")
public class ItemJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "daily_value", nullable = false)
    private BigDecimal dailyValue;

    @Column(name = "max_days", nullable = false)
    private int maxDays;

    @Column(name = "min_days", nullable = false)
    private int minDays;

    @OneToMany(mappedBy = "item", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<ItemCategoryJpaEntity> categories;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public ItemJpaEntity() {}

    public ItemJpaEntity(
            final String id,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final int maxDays,
            final int minDays,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dailyValue = dailyValue;
        this.maxDays = maxDays;
        this.minDays = minDays;
        this.categories = new HashSet<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ItemJpaEntity from(final Item item) {
        final var entity = new ItemJpaEntity(
                item.getId().getValue(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                item.getCreatedAt(),
                item.getUpdatedAt());

        item.getCategories().forEach(entity::addCategory);

        return entity;
    }

    public Item toAggregate() {
        System.out.println(this);
        return Item.with(
                ItemId.from(getId()),
                getName(),
                getDescription(),
                getDailyValue(),
                getMaxDays(),
                getMinDays(),
                getCategoriesIds(),
                getCreatedAt(),
                getUpdatedAt());
    }

    private void addCategory(final CategoryId id) {
        this.categories.add(ItemCategoryJpaEntity.from(id, this));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(BigDecimal dailyValue) {
        this.dailyValue = dailyValue;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public List<CategoryId> getCategoriesIds() {
        return this.categories.stream()
                .map(it -> CategoryId.from(it.getId().getCategoryId()))
                .toList();
    }

    public Set<ItemCategoryJpaEntity> getCategories() {
        return categories;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ItemJpaEntity{" + "id='"
                + id + '\'' + ", name='"
                + name + '\'' + ", description='"
                + description + '\'' + ", dailyValue="
                + dailyValue + ", maxDays="
                + maxDays + ", minDays="
                + minDays + ", categories="
                + categories + ", createdAt="
                + createdAt + ", updatedAt="
                + updatedAt + '}';
    }
}
