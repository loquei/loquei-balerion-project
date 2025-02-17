package com.loquei.core.infrastructure.item.recently;

import com.loquei.core.domain.item.recently.RecentlyViewedItem;
import com.loquei.core.domain.item.recently.RecentlyViewedItemGateway;
import com.loquei.core.domain.item.recently.RecentlyViewedItemId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.item.recently.persistence.RecentlyViewedItemJpaEntity;
import com.loquei.core.infrastructure.item.recently.persistence.RecentlyViewedItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecentlyViewedItemPostgresGateway implements RecentlyViewedItemGateway {

    private final RecentlyViewedItemRepository repository;

    public RecentlyViewedItemPostgresGateway(final RecentlyViewedItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecentlyViewedItem create(final RecentlyViewedItem item) {
        return this.repository.save(RecentlyViewedItemJpaEntity.from(item)).toEntity();
    }

    @Override
    public void deleteById(RecentlyViewedItemId id) {
        final var idValue = id.getValue();
        if (this.repository.existsById(idValue)) {
            this.repository.deleteById(idValue);
        }
    }

    @Override
    public List<RecentlyViewedItem> findAllByUserId(UserId userId) {
        return repository.findAllByUserId(userId.getValue()).stream()
                .map(RecentlyViewedItemJpaEntity::toEntity)
                .toList();
    }

}
