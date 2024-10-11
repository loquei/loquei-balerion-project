package com.loquei.core.application.rent.retrieve.list;

import com.loquei.common.pagination.SearchQuery;

public record ListRentParams(String userId, SearchQuery aQuery) {

    public static ListRentParams with(final String userId, final SearchQuery aQuery) {
        return new ListRentParams(userId, aQuery);
    }
}
