package com.loquei.core.application.rating.create;

import com.loquei.core.domain.rating.Rating;

public record CreateRatingOutput(String id, String raterId, String description, Double score, String itemId) {
    public static CreateRatingOutput from(
            final String id, final String raterId, final String description, final Double score, final String itemId) {
        return new CreateRatingOutput(id, raterId, description, score, itemId);
    }

    public static CreateRatingOutput from(final Rating aRating) {
        return new CreateRatingOutput(
                aRating.getId().getValue(),
                aRating.getRater().getValue(),
                aRating.getDescription(),
                aRating.getScore(),
                aRating.getItem().getValue());
    }
}
