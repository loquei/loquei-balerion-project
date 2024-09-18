package com.loquei.core.application.category.retrieve.list;

import com.loquei.common.UseCase;
import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;

public abstract class ListCategoriesUseCase extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {}
