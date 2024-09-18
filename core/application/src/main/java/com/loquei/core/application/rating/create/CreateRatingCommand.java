package com.loquei.core.application.rating.create;

public record CreateRatingCommand(String raterId, String description, Double score, String itemId) {
    public static CreateRatingCommand with(
            final String raterId, final String aDescription, final Double score, final String itemId) {
        return new CreateRatingCommand(raterId, aDescription, score, itemId);
    }
}
