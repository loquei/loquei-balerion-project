package com.loquei.core.domain.item.image;

import com.loquei.core.domain.item.ItemId;

import java.util.List;
import java.util.Optional;

public interface ItemImageGateway {

    ItemImage save(final ItemImage itemImage);

    Optional<ItemImage> findById(final ItemImageId itemId);

    List<ItemImage> findByItemId(final ItemId itemId);

    void delete(final ItemImageId itemId);

}
