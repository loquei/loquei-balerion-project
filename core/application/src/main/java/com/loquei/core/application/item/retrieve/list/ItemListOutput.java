package com.loquei.core.application.item.retrieve.list;

import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ItemListOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        String userId,
        List<String> categories,
        Instant updatedAt) {
    public static ItemListOutput from(final Item item) {
        return new ItemListOutput(
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
