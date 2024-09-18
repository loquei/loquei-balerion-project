package com.loquei.core.application.rating.update;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.Identifier;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.rating.Rating;
import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.rating.RatingId;
import io.vavr.control.Either;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateRatingUseCase extends UpdateRatingUseCase {

    private final RatingGateway ratingGateway;

    public DefaultUpdateRatingUseCase(final RatingGateway ratingGateway) {
        this.ratingGateway = Objects.requireNonNull(ratingGateway);
    }

    @Override
    public Either<Notification, UpdateRatingOutput> execute(final UpdateRatingCommand aCommand) {
        final var id = RatingId.from(aCommand.id());
        final var description = aCommand.description();
        final var score = aCommand.score();

        final var rating = this.ratingGateway.findById(id).orElseThrow(notFound(id));

        final var notification = Notification.create();
        rating.update(description, score).validate(notification);

        return notification.hasError() ? Left(notification) : create(rating);
    }

    private Either<Notification, UpdateRatingOutput> create(Rating aRating) {
        return Try(() -> this.ratingGateway.update(aRating))
                .toEither()
                .bimap(Notification::create, UpdateRatingOutput::from);
    }

    private Supplier<NotFoundException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Rating.class, anId);
    }
}
