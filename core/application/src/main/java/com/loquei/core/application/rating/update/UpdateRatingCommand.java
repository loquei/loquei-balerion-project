package com.loquei.core.application.rating.update;

public record UpdateRatingCommand(String id, String description, Double score) {
    public static UpdateRatingCommand with(final String id, final String aDescription, final Double score) {
        return new UpdateRatingCommand(id, aDescription, score);
    }
}
