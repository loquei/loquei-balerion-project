package com.loquei.core.application.category.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.category.Category;
import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.category.CategoryId;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anId) {
        final var aCategoryId = CategoryId.from(anId);
        return this.categoryGateway
                .findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(aCategoryId));
    }

    private Supplier<NotFoundException> notFound(final CategoryId anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
