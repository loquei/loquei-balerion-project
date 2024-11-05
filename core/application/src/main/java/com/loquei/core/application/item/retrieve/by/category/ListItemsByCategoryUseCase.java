package com.loquei.core.application.item.retrieve.by.category;

import com.loquei.common.UseCase;
import com.loquei.common.pagination.Pagination;

public abstract class ListItemsByCategoryUseCase
        extends UseCase<ListItemsByCategoryParams, Pagination<ItemListByCategoryOutput>> {}
