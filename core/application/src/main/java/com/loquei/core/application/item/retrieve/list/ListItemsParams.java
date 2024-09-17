package com.loquei.core.application.item.retrieve.list;

import com.loquei.common.pagination.SearchQuery;

public record ListItemsParams(String userEmail, Boolean recentlyViewed, SearchQuery aQuery) {

    public static ListItemsParams with(final String userEmail, final Boolean recentlyViewed, final SearchQuery aQuery) {
        return new ListItemsParams(userEmail, recentlyViewed, aQuery);
    }
}
