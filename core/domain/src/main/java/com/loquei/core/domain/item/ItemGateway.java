package com.loquei.core.domain.item;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.user.UserId;
import java.util.Optional;

public interface ItemGateway {
    Item create(Item item);

    Item update(Item item);

    Optional<Item> findById(ItemId itemId);

    Pagination<Item> findAll(SearchQuery searchQuery);

    Pagination<Item> findRecentlyViewedItemsByUserId(UserId userId, SearchQuery query);

    void delete(ItemId itemId);
}
