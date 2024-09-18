package com.loquei.core.application.rating.retrieve.list;

import com.loquei.core.domain.rating.Rating;
import java.time.Instant;

public record RatingListOutput(
        String id, String raterId, String description, Double score, String itemId, Instant updatedAt) {
    public static RatingListOutput from(final Rating aRating) {
        return new RatingListOutput(
                aRating.getId().getValue(),
                aRating.getRater().getValue(),
                aRating.getDescription(),
                aRating.getScore(),
                aRating.getItem().getValue(),
                aRating.getUpdatedAt());
    }
}
