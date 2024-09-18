package com.loquei.core.application.rating.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.AggregateRoot;
import com.loquei.common.Identifier;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rating.Rating;
import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultCreateRatingUseCase extends CreateRatingUseCase {

    private final ItemGateway itemGateway;

    private final UserGateway userGateway;
    private final RatingGateway ratingGateway;

    public DefaultCreateRatingUseCase(
            final ItemGateway itemGateway, final UserGateway userGateway, final RatingGateway ratingGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.userGateway = Objects.requireNonNull(userGateway);
        this.ratingGateway = Objects.requireNonNull(ratingGateway);
    }

    @Override
    public Either<Notification, CreateRatingOutput> execute(final CreateRatingCommand aCommand) {
        final var raterId = UserId.from(aCommand.raterId());
        final var description = aCommand.description();
        final var score = aCommand.score();
        final var itemId = ItemId.from(aCommand.itemId());

        final var rater = this.userGateway.findById(raterId).orElseThrow(notFound(User.class, raterId));

        final var item = this.itemGateway.findById(itemId).orElseThrow(notFound(Item.class, itemId));

        final var notification = Notification.create();
        final var rating = Rating.newRating(rater.getId(), description, score, item.getId());
        rating.validate(notification);

        return notification.hasError() ? Left(notification) : create(rating);
    }

    private Either<Notification, CreateRatingOutput> create(Rating aRating) {
        return Try(() -> this.ratingGateway.create(aRating))
                .toEither()
                .bimap(Notification::create, CreateRatingOutput::from);
    }

    private Supplier<NotFoundException> notFound(
            final Class<? extends AggregateRoot<? extends Identifier>> clazz, final Identifier anId) {
        return () -> NotFoundException.with(clazz, anId);
    }
}
