package com.loquei.core.application.item.retrieve.list;

import com.loquei.common.UseCase;
import com.loquei.common.pagination.Pagination;

public abstract class ListItemsUseCase extends UseCase<ListItemsParams, Pagination<ItemListOutput>> {}
