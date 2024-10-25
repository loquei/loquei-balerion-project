package com.loquei.core.application.item.retrieve.list;

import com.loquei.common.pagination.SearchQuery;

public record ListItemsParams(String userEmail, Boolean recentlyViewed, String ownerEmail, SearchQuery aQuery) {

    public static ListItemsParams with(
            final String userEmail, final Boolean recentlyViewed, final String ownerEmail, final SearchQuery aQuery) {
        return new ListItemsParams(userEmail, recentlyViewed, ownerEmail, aQuery);
    }
}
