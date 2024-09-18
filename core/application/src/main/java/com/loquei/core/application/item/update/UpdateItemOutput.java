package com.loquei.core.application.item.update;

import com.loquei.core.domain.item.Item;
import java.math.BigDecimal;
import java.time.Instant;

public record UpdateItemOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        Instant createdAt,
        Instant updatedAt) {

    public static UpdateItemOutput from(
            final String id,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final Integer maxDays,
            final Integer minDays,
            final Instant createdAt,
            final Instant updatedAt) {

        return new UpdateItemOutput(id, name, description, dailyValue, maxDays, minDays, createdAt, updatedAt);
    }

    public static UpdateItemOutput from(final Item item) {
        return new UpdateItemOutput(
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
