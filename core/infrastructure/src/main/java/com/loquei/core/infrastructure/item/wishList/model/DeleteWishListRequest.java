package com.loquei.core.infrastructure.item.wishList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeleteWishListRequest(
        @JsonProperty("user_id") String userId,
        @JsonProperty("item_id") String itemId
) {
}
