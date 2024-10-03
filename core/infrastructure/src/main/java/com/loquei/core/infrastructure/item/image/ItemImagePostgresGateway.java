package com.loquei.core.infrastructure.item.image;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.domain.item.image.ItemImageGateway;
import com.loquei.core.domain.item.image.ItemImageId;
import com.loquei.core.infrastructure.item.image.persistence.ItemImageJpaEntity;
import com.loquei.core.infrastructure.item.image.persistence.ItemImageRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemImagePostgresGateway implements ItemImageGateway {

    private final ItemImageRepository itemImageRepository;

    public ItemImagePostgresGateway(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public ItemImage save(final ItemImage itemImage) {
        return this.itemImageRepository.save(ItemImageJpaEntity.from(itemImage)).toEntity();
    }

    @Override
    public Optional<ItemImage> findById(final ItemImageId itemId) {
        return this.itemImageRepository.findById(itemId.getValue()).map(ItemImageJpaEntity::toEntity);
    }

    @Override
    public List<ItemImage> findByItemId(final ItemId itemId) {
        return this.itemImageRepository.findAllByItemId(itemId.getValue())
                .stream().map(ItemImageJpaEntity::toEntity).toList();
    }

    @Override
    public void delete(final ItemImageId itemId) {
        final var idValue = itemId.getValue();
        if (this.itemImageRepository.existsById(idValue)) {
            this.itemImageRepository.deleteById(idValue);
        }
    }

    @Override
    public void deleteByItemId(final ItemId itemId) {
        this.itemImageRepository.deleteByItemId(itemId.getValue());
    }

}
