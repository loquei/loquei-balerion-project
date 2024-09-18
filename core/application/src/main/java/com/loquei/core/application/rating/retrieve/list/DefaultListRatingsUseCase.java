package com.loquei.core.application.rating.retrieve.list;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.rating.RatingGateway;
import java.util.Objects;

public class DefaultListRatingsUseCase extends ListRatingsUseCase {

    private final RatingGateway ratingGateway;

    public DefaultListRatingsUseCase(final RatingGateway ratingGateway) {
        this.ratingGateway = Objects.requireNonNull(ratingGateway);
    }

    @Override
    public Pagination<RatingListOutput> execute(SearchQuery aQuery) {
        return this.ratingGateway.findAll(aQuery).map(RatingListOutput::from);
    }
}
