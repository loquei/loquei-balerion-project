package com.loquei.core.application.item.retrieve.get;

import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ItemOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        Float score,
        String userId,
        List<String> categories,
        Instant createdAt,
        Instant updatedAt) {

    public static ItemOutput from(
            final String id,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final Integer maxDays,
            final Integer minDays,
            final String userId,
            final List<String> categories,
            final Instant createdAt,
            final Instant updatedAt) {

        return new ItemOutput(
                id, name, description, dailyValue, maxDays, minDays, null, userId, categories, createdAt, updatedAt);
    }

    public static ItemOutput from(final Item item, final Float itemScore) {
        return new ItemOutput(
                item.getId().getValue(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                itemScore,
                item.getUser().getValue(),
                item.getCategories().stream().map(CategoryId::getValue).toList(),
                item.getCreatedAt(),
                item.getUpdatedAt());
    }
}
