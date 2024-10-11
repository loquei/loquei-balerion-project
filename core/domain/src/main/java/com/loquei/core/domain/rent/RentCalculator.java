package com.loquei.core.domain.rent;

import com.loquei.core.domain.item.Item;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public final class RentCalculator {

    private RentCalculator() {}

    public static BigDecimal calculateTotalValue(
            final LocalDateTime startDate, final LocalDateTime endDate, final Item item) {
        long daysBetween = Duration.between(startDate, endDate).toDays();

        if (daysBetween < 1) {
            daysBetween = 1;
        }
        return item.getDailyValue().multiply(BigDecimal.valueOf(daysBetween));
    }
}
