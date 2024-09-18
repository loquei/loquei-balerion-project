package com.loquei.core.application.user.retrieve.list;

import com.loquei.common.UseCase;
import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;

public abstract class ListUsersUseCase extends UseCase<SearchQuery, Pagination<UserListOutput>> {}
