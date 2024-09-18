package com.loquei.core.application.rating.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.rating.Rating;
import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.rating.RatingId;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetRatingByIdUseCase extends GetRatingByIdUseCase {

    private final RatingGateway ratingGateway;

    public DefaultGetRatingByIdUseCase(final RatingGateway ratingGateway) {
        this.ratingGateway = Objects.requireNonNull(ratingGateway);
    }

    @Override
    public RatingOutput execute(final String anId) {
        final var aRatingId = RatingId.from(anId);
        return this.ratingGateway.findById(aRatingId).map(RatingOutput::from).orElseThrow(notFound(aRatingId));
    }

    private Supplier<NotFoundException> notFound(final RatingId anId) {
        return () -> NotFoundException.with(Rating.class, anId);
    }
}
