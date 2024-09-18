package com.loquei.core.application.rating.delete;

import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.rating.RatingId;
import java.util.Objects;

public class DefaultDeleteRatingUseCase extends DeleteRatingUseCase {

    private final RatingGateway ratingGateway;

    public DefaultDeleteRatingUseCase(final RatingGateway ratingGateway) {
        this.ratingGateway = Objects.requireNonNull(ratingGateway);
    }

    @Override
    public void execute(final String andId) {
        this.ratingGateway.deleteById(RatingId.from(andId));
    }
}
