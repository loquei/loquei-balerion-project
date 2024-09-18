package com.loquei.domain.rating;

import com.loquei.domain.pagination.Pagination;
import com.loquei.domain.pagination.SearchQuery;
import java.util.Optional;

public interface RatingGateway {
    Rating create(Rating aRating);

    void deleteById(RatingId anId);

    Optional<Rating> findById(RatingId anId);

    Rating update(Rating arating);

    Pagination<Rating> findAll(SearchQuery aQuery);
}
