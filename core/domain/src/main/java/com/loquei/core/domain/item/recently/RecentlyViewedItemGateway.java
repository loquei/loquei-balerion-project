package com.loquei.core.domain.item.recently;

import com.loquei.core.domain.user.UserId;

import java.util.List;

public interface RecentlyViewedItemGateway {

    RecentlyViewedItem create(RecentlyViewedItem item);

    void deleteById(RecentlyViewedItemId id);

    List<RecentlyViewedItem> findAllByUserId(UserId userId);

}
