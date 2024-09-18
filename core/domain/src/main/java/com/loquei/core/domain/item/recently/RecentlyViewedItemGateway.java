package com.loquei.core.domain.item.recently;

public interface RecentlyViewedItemGateway {

    RecentlyViewedItem create(RecentlyViewedItem item);

    void deleteById(RecentlyViewedItemId id);
}
