package com.loquei.core.infrastructure.item.wishList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ListItemWishListResponse(
        @JsonProperty("item_id") String itemId,
        @JsonProperty("daily_value") BigDecimal dailyValue,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("item_image_path") String itemImagePath
){
}
