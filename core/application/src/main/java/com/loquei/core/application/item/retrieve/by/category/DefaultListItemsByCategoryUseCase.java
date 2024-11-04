package com.loquei.core.application.item.retrieve.by.category;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.category.Category;
import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.ItemGateway;

import java.util.function.Supplier;

public class DefaultListItemsByCategoryUseCase extends ListItemsByCategoryUseCase {

    private final ItemGateway itemGateway;
    private final CategoryGateway categoryGateway;

    public DefaultListItemsByCategoryUseCase(
            final ItemGateway itemGateway,
            final CategoryGateway categoryGateway
    ) {
        this.itemGateway = itemGateway;
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Pagination<ItemListByCategoryOutput> execute(final ListItemsByCategoryParams params) {
        final var categoryId = CategoryId.from(params.categoryId());
        final var query = params.aQuery();

        final var category = categoryGateway.findById(categoryId)
                .orElseThrow(categoryNotFound(categoryId));

        return this.itemGateway
                .findAllByCategory(category.getId(), query)
                .map(ItemListByCategoryOutput::from);
    }

    private Supplier<NotFoundException> categoryNotFound(final CategoryId id) {
        return () -> NotFoundException.with(Category.class, id);
    }
}
