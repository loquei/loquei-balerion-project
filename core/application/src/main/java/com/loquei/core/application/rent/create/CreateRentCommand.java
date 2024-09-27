package com.loquei.core.application.rent.create;

import java.time.LocalDateTime;

public record CreateRentCommand(
        String lessorId,
        String lesseeId,
        String itemId,
        LocalDateTime startDate,
        LocalDateTime endDate) {

    public static CreateRentCommand with(
            final String lessorId,
            final String lesseeId,
            final String itemId,
            final LocalDateTime startDate,
            final LocalDateTime endDate) {

        return new CreateRentCommand(lessorId, lesseeId, itemId, startDate, endDate);
    }
}
