package com.loquei.core.domain.category;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
    Category create(Category aCategory);

    void deleteById(CategoryId anId);

    Optional<Category> findById(CategoryId anId);

    Category update(Category aCategory);

    Pagination<Category> findAll(SearchQuery aQuery);

    List<CategoryId> existsByIds(Iterable<CategoryId> ids);
}
