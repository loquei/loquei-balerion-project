package com.loquei.core.application.item.delete;

import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImageGateway;
import java.util.Objects;

public class DefaultDeleteItemUseCase extends DeleteItemUseCase {

    private final ItemGateway itemGateway;
    private final ItemImageGateway itemImageGateway;

    public DefaultDeleteItemUseCase(final ItemGateway anItemGateway, final ItemImageGateway anItemImageGateway) {
        this.itemGateway = Objects.requireNonNull(anItemGateway);
        this.itemImageGateway = Objects.requireNonNull(anItemImageGateway);
    }

    @Override
    public void execute(final String anId) {
        final var itemId = ItemId.from(anId);
        this.itemImageGateway.deleteByItemId(itemId);
        this.itemGateway.delete(itemId);
    }
}
