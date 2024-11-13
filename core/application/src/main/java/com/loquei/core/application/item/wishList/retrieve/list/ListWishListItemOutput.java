package com.loquei.core.application.item.wishList.retrieve.list;

import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.image.ItemImage;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.isNull;

public record ListWishListItemOutput(
        String itemId,
        BigDecimal dailyValue,
        String name,
        String description,
        String itemImageId
) {
    public static ListWishListItemOutput from (final Item item,
                                               final ItemImage itemImage){

        return new ListWishListItemOutput(
                item.getId().getValue(),
                item.getDailyValue(),
                item.getName(),
                item.getDescription(),
                isNull(itemImage) ? null : itemImage.getId().getValue()
        );
    }

}
