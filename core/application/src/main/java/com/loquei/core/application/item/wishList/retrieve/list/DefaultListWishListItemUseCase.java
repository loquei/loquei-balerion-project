package com.loquei.core.application.item.wishList.retrieve.list;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.domain.item.image.ItemImageGateway;
import com.loquei.core.domain.user.UserId;

import static java.util.Objects.requireNonNull;

public class DefaultListWishListItemUseCase extends ListWishListItemUseCase{

    private final ItemGateway itemGateway;
    private final ItemImageGateway itemImageGateway;

    public DefaultListWishListItemUseCase(final ItemGateway itemGateway,
                                          final ItemImageGateway itemImageGateway) {
        this.itemGateway = requireNonNull(itemGateway);
        this.itemImageGateway = requireNonNull(itemImageGateway);
    }

    @Override
    public Pagination<ListWishListItemOutput> execute(ListWishListParams params) {

        final var userId = UserId.from(params.userId());

        final var itemsPagination = itemGateway.findItemsFromWishList(userId, params.aQuery());

        return itemsWithImages(itemsPagination);

    }

    private Pagination<ListWishListItemOutput> itemsWithImages(Pagination<Item> items){
        return items.map(item -> ListWishListItemOutput.from(
                item,
                retrieveItemImage(item.getId())
        ));
    }

    private ItemImage retrieveItemImage(ItemId itemId){
        return itemImageGateway.findMainImageByItemId(itemId).orElse(null);
    }
}
