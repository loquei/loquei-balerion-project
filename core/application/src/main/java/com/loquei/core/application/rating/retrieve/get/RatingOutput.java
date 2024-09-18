package com.loquei.core.application.rating.retrieve.get;

import com.loquei.core.domain.rating.Rating;
import java.time.Instant;

public record RatingOutput(
        String id,
        String raterId,
        String description,
        Double score,
        String itemId,
        Instant createdAt,
        Instant updatedAt) {
    public static RatingOutput from(final Rating aRating) {
        return new RatingOutput(
                aRating.getId().getValue(),
                aRating.getRater().getValue(),
                aRating.getDescription(),
                aRating.getScore(),
                aRating.getItem().getValue(),
                aRating.getCreatedAt(),
                aRating.getUpdatedAt());
    }
}
