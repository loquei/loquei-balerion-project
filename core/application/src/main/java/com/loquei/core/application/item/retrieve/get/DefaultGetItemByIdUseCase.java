package com.loquei.core.application.item.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.application.item.retrieve.by.category.ItemListByCategoryOutput;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetItemByIdUseCase extends GetItemByIdUseCase {

    private final ItemGateway itemGateway;

    public DefaultGetItemByIdUseCase(final ItemGateway itemGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public ItemOutput execute(final String anId) {
        final var itemId = ItemId.from(anId);
        return this.itemGateway.findById(itemId).map(this::withScore).orElseThrow(notFound(itemId));
    }

    private Supplier<NotFoundException> notFound(final ItemId id) {
        return () -> NotFoundException.with(Item.class, id);
    }

    private ItemOutput withScore(final Item item) {
        final var score = itemGateway.retrieveItemTotalScore(item.getId());

        return ItemOutput.from(item, score);
    }
}
