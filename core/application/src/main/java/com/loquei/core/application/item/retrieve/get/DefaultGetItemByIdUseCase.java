package com.loquei.core.application.item.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.recently.RecentlyViewedItem;
import com.loquei.core.domain.item.recently.RecentlyViewedItemGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetItemByIdUseCase extends GetItemByIdUseCase {

    private final ItemGateway itemGateway;
    private final UserGateway userGateway;
    private final RecentlyViewedItemGateway recentlyViewedItemGateway;

    public DefaultGetItemByIdUseCase(
            final ItemGateway itemGateway,
            final UserGateway userGateway,
            final RecentlyViewedItemGateway recentlyViewedItemGateway
    ) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.userGateway = userGateway;
        this.recentlyViewedItemGateway = recentlyViewedItemGateway;
    }

    @Override
    public ItemOutput execute(final GetItemCommand command) {
        final var itemId = ItemId.from(command.itemId());
        final var userEmail = command.userEmail();

        return this.itemGateway.findById(itemId).map(item -> build(item, userEmail))
                .orElseThrow(notFound(itemId));
    }

    private Supplier<NotFoundException> notFound(final ItemId id) {
        return () -> NotFoundException.with(Item.class, id);
    }

    private ItemOutput build(final Item item, final String userEmail) {
        final var score = itemGateway.retrieveItemTotalScore(item.getId());

        persistItemVisualization(item, userEmail);

        return ItemOutput.from(item, score);
    }

    private void persistItemVisualization(final Item item, final String userEmail) {
        final var user = userGateway.findByEmail(userEmail)
                .orElseThrow(notFound(userEmail));

        final var recentlyItems = recentlyViewedItemGateway.findAllByUserId(user.getId());

        if (recentlyItems.stream().anyMatch(recentlyViewedItem -> recentlyViewedItem.getItemId().equals(item.getId()))) {
            final var recentlyOpt = recentlyItems.stream()
                    .filter(recentlyViewedItem -> recentlyViewedItem.getItemId().equals(item.getId()))
                    .findFirst();

            if (recentlyOpt.isPresent()) {
                final var recently = recentlyOpt.get();

                recentlyViewedItemGateway.deleteById(recently.getId());
            }
        }

        if (recentlyItems.size() >= 15) {
            recentlyItems.sort(Comparator.comparing(RecentlyViewedItem::getViewedAt).reversed());
            recentlyItems.removeFirst();
        }


        final var recentlyViewedItem = RecentlyViewedItem.newRecentlyViewedItem(user.getId(), item.getId());

        recentlyViewedItemGateway.create(recentlyViewedItem);
    }

    private Supplier<NotFoundException> notFound(final String email) {
        return () -> NotFoundException.with(User.class, email);
    }
}
