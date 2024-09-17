package com.loquei.core.domain.item;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.category.CategoryId;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item extends AggregateRoot<ItemId> {

    private String name;
    private String description;
    private BigDecimal dailyValue;
    private int maxDays;
    private int minDays;
    private List<CategoryId> categories;
    private final Instant createdAt;
    private Instant updatedAt;

    private Item(
            final ItemId anId,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final int maxDays,
            final int minDays,
            final List<CategoryId> categories,
            final Instant createdAt,
            final Instant updatedAt) {
        super(anId);
        this.name = name;
        this.description = description;
        this.dailyValue = dailyValue;
        this.maxDays = maxDays;
        this.minDays = minDays;
        this.categories = categories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Item newItem(
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final int maxDays,
            final int minDays) {
        final var id = ItemId.unique();
        final var now = InstantUtils.now();
        return new Item(id, name, description, dailyValue, maxDays, minDays, new ArrayList<>(), now, now);
    }

    public static Item with(
            final ItemId anId,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final int maxDays,
            final int minDays,
            final List<CategoryId> categories,
            final Instant createdAt,
            final Instant updatedAt) {
        return new Item(anId, name, description, dailyValue, maxDays, minDays, categories, createdAt, updatedAt);
    }

    public static Item with(final Item item) {
        return new Item(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                new ArrayList<>(item.getCategories()),
                item.getCreatedAt(),
                item.getUpdatedAt());
    }

    public Item update(
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final int maxDays,
            final int minDays,
            final List<CategoryId> categories) {
        this.name = name;
        this.description = description;
        this.dailyValue = dailyValue;
        this.maxDays = maxDays;
        this.minDays = minDays;

        this.categories = new ArrayList<>(categories != null ? categories : Collections.emptyList());

        this.updatedAt = InstantUtils.now();

        return this;
    }

    public Item addCategory(final CategoryId categoryId) {
        if (categoryId == null) return this;
        this.categories.add(categoryId);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Item addCategories(final List<CategoryId> categories) {
        if (categories == null || categories.isEmpty()) return this;

        this.categories.addAll(categories);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        new ItemValidator(this, aHandler).validate();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDailyValue() {
        return dailyValue;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public int getMinDays() {
        return minDays;
    }

    public List<CategoryId> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
