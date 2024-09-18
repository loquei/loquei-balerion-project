package com.loquei.core.infrastructure.rating.persistence;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rating.Rating;
import com.loquei.core.domain.rating.RatingId;
import com.loquei.core.domain.user.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity(name = "Rating")
@Table(name = "ratings")
public class RatingJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "rater_id", nullable = false)
    private String raterId;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "score")
    private Double score;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public RatingJpaEntity() {}

    public RatingJpaEntity(
            final String id,
            final String raterId,
            final String description,
            final Double score,
            final String itemId,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.raterId = raterId;
        this.description = description;
        this.score = score;
        this.itemId = itemId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static RatingJpaEntity from(final Rating aRating) {
        return new RatingJpaEntity(
                aRating.getId().getValue(),
                aRating.getRater().getValue(),
                aRating.getDescription(),
                aRating.getScore(),
                aRating.getItem().getValue(),
                aRating.getCreatedAt(),
                aRating.getUpdatedAt());
    }

    public Rating toAggregate() {
        return Rating.with(
                RatingId.from(getId()),
                UserId.from(getRaterId()),
                getDescription(),
                getScore(),
                ItemId.from(getItemId()),
                getCreatedAt(),
                getUpdatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
