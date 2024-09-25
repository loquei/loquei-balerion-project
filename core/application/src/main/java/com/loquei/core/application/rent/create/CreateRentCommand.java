package com.loquei.core.application.rent.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateRentCommand(
        String lessorId,
        String lesseeId,
        String itemId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalValue) {

    public static CreateRentCommand with(
            final String lessorId,
            final String lesseeId,
            final String itemId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final BigDecimal totalValue) {

        return new CreateRentCommand(lessorId, lesseeId, itemId, startDate, endDate, totalValue);
    }
}
