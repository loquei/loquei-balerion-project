package com.loquei.core.application.rent.retrieve.checkavailability;

import java.time.LocalDateTime;

public record IsItemAvailableForRentCommand(String itemId, LocalDateTime startDate, LocalDateTime endDate) {
    public static IsItemAvailableForRentCommand with(final String itemId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return new IsItemAvailableForRentCommand(itemId, startDate, endDate);
    }
}