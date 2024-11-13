package com.loquei.core.application.item.wishList.retrieve.list;

import com.loquei.common.pagination.SearchQuery;

public record ListWishListParams(
        String userId,
        SearchQuery aQuery
) {

    public static ListWishListParams with (final String userId, final SearchQuery aQuery){
        return new ListWishListParams(userId, aQuery);
    }
}
