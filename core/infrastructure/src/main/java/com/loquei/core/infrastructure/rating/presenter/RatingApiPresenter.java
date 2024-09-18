package com.loquei.core.infrastructure.rating.presenter;

import com.loquei.core.application.rating.retrieve.get.RatingOutput;
import com.loquei.core.application.rating.retrieve.list.RatingListOutput;
import com.loquei.core.infrastructure.rating.models.RatingListResponse;
import com.loquei.core.infrastructure.rating.models.RatingResponse;

public interface RatingApiPresenter {

    static RatingResponse present(final RatingOutput output) {
        return new RatingResponse(
                output.id(),
                output.raterId(),
                output.description(),
                output.score(),
                output.itemId(),
                output.createdAt(),
                output.updatedAt());
    }

    static RatingListResponse present(final RatingListOutput output) {
        return new RatingListResponse(
                output.id(),
                output.raterId(),
                output.description(),
                output.score(),
                output.itemId(),
                output.updatedAt());
    }
}
