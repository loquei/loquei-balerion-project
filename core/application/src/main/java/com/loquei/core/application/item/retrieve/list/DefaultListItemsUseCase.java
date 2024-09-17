package com.loquei.core.application.item.retrieve.list;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;

import java.util.Objects;

import static com.loquei.common.utils.BooleanUtils.isTrue;

public class DefaultListItemsUseCase extends ListItemsUseCase {

    private final ItemGateway itemGateway;
    private final UserGateway userGateway;

    public DefaultListItemsUseCase(final ItemGateway itemGateway, final UserGateway userGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Pagination<ItemListOutput> execute(final ListItemsParams params) {
        final var userEmail = params.userEmail();
        final var recentlyViewed = params.recentlyViewed();
        final var aQuery = params.aQuery();

        if (isTrue(recentlyViewed))
            return listRecentlyViewedItems(userEmail, aQuery);

        return this.itemGateway.findAll(aQuery).map(ItemListOutput::from);
    }

    private Pagination<ItemListOutput> listRecentlyViewedItems(final String userEmail, final SearchQuery aQuery) {
        final var user = userGateway.findByEmail(userEmail)
                .orElseThrow(() -> NotFoundException.with(User.class, userEmail));

        return this.itemGateway.findRecentlyViewedItemsByUserId(user.getId(), aQuery).map(ItemListOutput::from);
    }


}
