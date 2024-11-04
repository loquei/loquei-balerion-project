package com.loquei.core.application.item.retrieve.by.category;

import com.loquei.common.pagination.SearchQuery;

public record ListItemsByCategoryParams(String categoryId, SearchQuery aQuery) {

    public static ListItemsByCategoryParams with(
            final String categoryId,
            final SearchQuery aQuery
    ) {
        return new ListItemsByCategoryParams(categoryId, aQuery);
    }
}
