package com.loquei.core.domain.rating;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import java.util.Optional;

public interface RatingGateway {
    Rating create(Rating aRating);

    void deleteById(RatingId anId);

    Optional<Rating> findById(RatingId anId);

    Rating update(Rating arating);

    Pagination<Rating> findAll(SearchQuery aQuery);
}
