package com.loquei.core.application.rating.retrieve.list;

import com.loquei.common.UseCase;
import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;

public abstract class ListRatingsUseCase extends UseCase<SearchQuery, Pagination<RatingListOutput>> {}
