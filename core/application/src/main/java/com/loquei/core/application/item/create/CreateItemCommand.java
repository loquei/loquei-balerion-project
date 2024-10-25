package com.loquei.core.application.item.create;

import java.math.BigDecimal;
import java.util.List;

public record CreateItemCommand(
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        String userId,
        List<String> categories) {

    public static CreateItemCommand with(
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final Integer maxDays,
            final Integer minDays,
            final String userId,
            final List<String> categories) {
        return new CreateItemCommand(name, description, dailyValue, maxDays, minDays, userId, categories);
    }
}
