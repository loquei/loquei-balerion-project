package com.loquei.core.application.item.update;

import java.math.BigDecimal;
import java.util.List;

public record UpdateItemCommand(
        String id,
        String name,
        String description,
        BigDecimal dailyValue,
        Integer maxDays,
        Integer minDays,
        List<String> categories) {
    public static UpdateItemCommand with(
            final String id,
            final String name,
            final String description,
            final BigDecimal dailyValue,
            final Integer maxDays,
            final Integer minDays,
            final List<String> categories) {
        return new UpdateItemCommand(id, name, description, dailyValue, maxDays, minDays, categories);
    }
}
