package com.loquei.core.application.item.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.domain.exceptions.NotFoundException;
import com.loquei.domain.item.Item;
import com.loquei.domain.item.ItemGateway;
import com.loquei.domain.item.ItemId;

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
        return this.itemGateway.findById(itemId).map(ItemOutput::from).orElseThrow(notFound(itemId));
    }

    private Supplier<NotFoundException> notFound(final ItemId id) {
        return () -> NotFoundException.with(Item.class, id);
    }
}
