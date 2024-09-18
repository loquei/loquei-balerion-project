package com.loquei.core.application.rating.update;

import com.loquei.core.domain.rating.Rating;

public record UpdateRatingOutput(String id, String raterId, String description, Double score, String itemId) {
    public static UpdateRatingOutput from(
            final String id, final String raterId, final String description, final Double score, final String itemId) {
        return new UpdateRatingOutput(id, raterId, description, score, itemId);
    }

    public static UpdateRatingOutput from(final Rating aRating) {
        return new UpdateRatingOutput(
                aRating.getId().getValue(),
                aRating.getRater().getValue(),
                aRating.getDescription(),
                aRating.getScore(),
                aRating.getItem().getValue());
    }
}
