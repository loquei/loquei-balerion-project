package com.loquei.core.application.item.retrieve.by.category;

import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ItemListByCategoryOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        String userId,
        List<String> categories,
        Instant updatedAt) {
    public static ItemListByCategoryOutput from(final Item item) {
        return new ItemListByCategoryOutput(
                item.getId().getValue(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                item.getUser().getValue(),
                item.getCategories().stream().map(CategoryId::getValue).toList(),
                item.getUpdatedAt());
    }
}
