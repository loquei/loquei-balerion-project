package com.loquei.core.application.category.delete;

import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.category.CategoryId;
import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public void execute(String anId) {
        this.categoryGateway.deleteById(CategoryId.from(anId));
    }
}
