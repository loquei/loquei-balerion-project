package com.loquei.core.application.item.create;

import com.loquei.core.domain.item.Item;
import java.math.BigDecimal;
import java.time.Instant;

public record CreateItemOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        Instant createdAt,
        Instant updatedAt) {

    public static CreateItemOutput from(
            final String id,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final Integer maxDays,
            final Integer minDays,
            final Instant createdAt,
            final Instant updatedAt) {

        return new CreateItemOutput(id, name, description, dailyValue, maxDays, minDays, createdAt, updatedAt);
    }

    public static CreateItemOutput from(final Item item) {
        return new CreateItemOutput(
                item.getId().getValue(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                item.getCreatedAt(),
                item.getUpdatedAt());
    }
}
