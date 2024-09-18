package com.loquei.domain.rating;

import com.loquei.domain.AggregateRoot;
import com.loquei.domain.item.ItemId;
import com.loquei.domain.user.UserId;
import com.loquei.domain.utils.InstantUtils;
import com.loquei.domain.validation.ValidationHandler;
import java.time.Instant;
import java.util.Objects;

public class Rating extends AggregateRoot<RatingId> {
    private UserId rater;
    private String description;
    private Double score;
    private ItemId item;
    private Instant createdAt;
    private Instant updatedAt;

    private Rating(
            final RatingId anId,
            final UserId aName,
            final String aDescription,
            final Double score,
            final ItemId item,
            final Instant aCreationDate,
            final Instant aUpdateDate) {
        super(anId);
        this.rater = aName;
        this.description = aDescription;
        this.score = score;
        this.item = item;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
    }

    public static Rating newRating(
            final UserId raterId, final String aDescription, final Double aScore, final ItemId item) {
        final var id = RatingId.unique();
        final var now = InstantUtils.now();
        return new Rating(id, raterId, aDescription, aScore, item, now, now);
    }

    public static Rating with(final Rating aRating) {
        return with(
                aRating.id,
                aRating.rater,
                aRating.description,
                aRating.score,
                aRating.item,
                aRating.createdAt,
                aRating.updatedAt);
    }

    public static Rating with(
            final RatingId anId,
            final UserId rater,
            final String description,
            final Double score,
            final ItemId item,
            final Instant createdAt,
            final Instant updatedAt) {
        return new Rating(anId, rater, description, score, item, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new RatingValidator(this, handler).validate();
    }

    public Rating update(final String aDescription, final Double score) {
        this.description = aDescription;
        this.score = score;
        this.updatedAt = Instant.now();
        return this;
    }

    public UserId getRater() {
        return rater;
    }

    public String getDescription() {
        return description;
    }

    public Double getScore() {
        return score;
    }

    public ItemId getItem() {
        return item;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
