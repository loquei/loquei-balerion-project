package com.loquei.core.application.item.retrieve.list;

import com.loquei.core.domain.item.Item;

import java.math.BigDecimal;
import java.time.Instant;

public record ItemListOutput(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        Instant updatedAt) {
    public static ItemListOutput from(final Item item) {
        return new ItemListOutput(
                item.getId().getValue(),
                item.getName(),
                item.getDescription(),
                item.getDailyValue(),
                item.getMaxDays(),
                item.getMinDays(),
                item.getUpdatedAt());
    }
}
