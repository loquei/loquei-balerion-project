package com.loquei.core.application.item.delete;

import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import java.util.Objects;

public class DefaultDeleteItemUseCase extends DeleteItemUseCase {

    private final ItemGateway itemGateway;

    public DefaultDeleteItemUseCase(final ItemGateway itemGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public void execute(final String anId) {
        this.itemGateway.delete(ItemId.from(anId));
    }
}
