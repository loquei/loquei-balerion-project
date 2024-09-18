package com.loquei.core.domain.category;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryId> {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryId anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean isActive) {
        final var id = CategoryId.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : InstantUtils.now();
        return new Category(id, aName, aDescription, isActive, now, now, deletedAt);
    }

    public static Category with(final Category aCategory) {
        return with(
                aCategory.id,
                aCategory.name,
                aCategory.description,
                aCategory.active,
                aCategory.createdAt,
                aCategory.updatedAt,
                aCategory.deletedAt);
    }

    public static Category with(
            final CategoryId anId,
            final String name,
            final String description,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        return new Category(anId, name, description, active, createdAt, updatedAt, deletedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate() {
        if (Objects.isNull(this.deletedAt)) this.deletedAt = InstantUtils.now();

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Category update(final String aName, final String aDescription, final boolean isActive) {
        if (isActive) activate();
        else deactivate();

        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
