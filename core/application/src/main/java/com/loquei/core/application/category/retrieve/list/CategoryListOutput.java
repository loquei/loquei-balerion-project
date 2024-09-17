package com.loquei.core.application.category.retrieve.list;

import com.loquei.core.domain.category.Category;
import com.loquei.core.domain.category.CategoryId;
import java.time.Instant;

public record CategoryListOutput(
        CategoryId id, String name, String description, boolean isActive, Instant createdAt, Instant deletedAt) {
    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt());
    }
}
