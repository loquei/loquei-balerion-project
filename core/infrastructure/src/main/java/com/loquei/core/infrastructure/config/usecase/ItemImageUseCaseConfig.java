package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.item.image.create.CreateItemImageUseCase;
import com.loquei.core.application.item.image.create.DefaultCreateItemImageUseCase;
import com.loquei.core.application.item.image.delete.DefaultDeleteItemImageUseCase;
import com.loquei.core.application.item.image.delete.DeleteItemImageUseCase;
import com.loquei.core.application.item.image.retrieve.images.DefaultRetrieveItemImagesUseCase;
import com.loquei.core.application.item.image.retrieve.images.RetrieveItemImagesUseCase;
import com.loquei.core.application.item.image.retrieve.view.DefaultViewItemImageUseCase;
import com.loquei.core.application.item.image.retrieve.view.ViewItemImageUseCase;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.image.ItemImageGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemImageUseCaseConfig {

    private final ItemGateway itemGateway;
    private final ItemImageGateway itemImageGateway;

    public ItemImageUseCaseConfig(ItemGateway itemGateway, ItemImageGateway itemImageGateway) {
        this.itemGateway = itemGateway;
        this.itemImageGateway = itemImageGateway;
    }

    @Bean
    public CreateItemImageUseCase createItemImageUseCase() {
        return new DefaultCreateItemImageUseCase(itemGateway, itemImageGateway);
    }

    @Bean
    public RetrieveItemImagesUseCase retrieveItemImagesUseCase() {
        return new DefaultRetrieveItemImagesUseCase(itemImageGateway);
    }

    @Bean
    public ViewItemImageUseCase viewItemImageUseCase() {
        return new DefaultViewItemImageUseCase(itemImageGateway);
    }

    @Bean
    public DeleteItemImageUseCase deleteItemImageUseCase() {
        return new DefaultDeleteItemImageUseCase(itemImageGateway);
    }

}
