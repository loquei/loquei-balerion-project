package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.item.create.CreateItemUseCase;
import com.loquei.core.application.item.create.DefaultCreateItemUseCase;
import com.loquei.core.application.item.delete.DefaultDeleteItemUseCase;
import com.loquei.core.application.item.delete.DeleteItemUseCase;
import com.loquei.core.application.item.retrieve.by.category.DefaultListItemsByCategoryUseCase;
import com.loquei.core.application.item.retrieve.by.category.ListItemsByCategoryUseCase;
import com.loquei.core.application.item.retrieve.get.DefaultGetItemByIdUseCase;
import com.loquei.core.application.item.retrieve.get.GetItemByIdUseCase;
import com.loquei.core.application.item.retrieve.list.DefaultListItemsUseCase;
import com.loquei.core.application.item.retrieve.list.ListItemsUseCase;
import com.loquei.core.application.item.update.DefaultUpdateItemUseCase;
import com.loquei.core.application.item.update.UpdateItemUseCase;
import com.loquei.core.application.item.wishList.retrieve.list.DefaultListWishListItemUseCase;
import com.loquei.core.application.item.wishList.retrieve.list.ListWishListItemUseCase;
import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.image.ItemImageGateway;
import com.loquei.core.domain.item.recently.RecentlyViewedItem;
import com.loquei.core.domain.item.recently.RecentlyViewedItemGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemUseCaseConfig {

    private final ItemGateway itemGateway;
    private final ItemImageGateway itemImageGateway;
    private final CategoryGateway categoryGateway;
    private final UserGateway userGateway;
    private final RecentlyViewedItemGateway recentlyViewedItemGateway;

    public ItemUseCaseConfig(
            final ItemGateway itemGateway,
            final ItemImageGateway itemImageGateway,
            final CategoryGateway categoryGateway,
            final UserGateway userGateway,
            final RecentlyViewedItemGateway recentlyViewedItemGateway) {
        this.itemGateway = itemGateway;
        this.itemImageGateway = itemImageGateway;
        this.categoryGateway = categoryGateway;
        this.userGateway = userGateway;
        this.recentlyViewedItemGateway = recentlyViewedItemGateway;
    }

    @Bean
    public CreateItemUseCase createItemUseCase() {
        return new DefaultCreateItemUseCase(itemGateway, categoryGateway);
    }

    @Bean
    public UpdateItemUseCase updateItemUseCase() {
        return new DefaultUpdateItemUseCase(itemGateway, categoryGateway);
    }

    @Bean
    public DeleteItemUseCase deleteItemUseCase() {
        return new DefaultDeleteItemUseCase(itemGateway, itemImageGateway);
    }

    @Bean
    public GetItemByIdUseCase getItemByIdUseCase() {
        return new DefaultGetItemByIdUseCase(itemGateway, userGateway, recentlyViewedItemGateway);
    }

    @Bean
    public ListItemsUseCase listItemsUseCase() {
        return new DefaultListItemsUseCase(itemGateway, userGateway);
    }

    @Bean
    public ListItemsByCategoryUseCase listItemsByCategoryParams() {
        return new DefaultListItemsByCategoryUseCase(itemGateway, categoryGateway);
    }

    @Bean
    public ListWishListItemUseCase listWishListItemUseCase(){
        return new DefaultListWishListItemUseCase(itemGateway, itemImageGateway);
    }
}
