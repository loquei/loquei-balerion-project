package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.item.wishList.create.CreateWishListUseCase;
import com.loquei.core.application.item.wishList.create.DefaultCreateWishListUseCase;
import com.loquei.core.application.item.wishList.delete.DefaultDeleteWishListUseCase;
import com.loquei.core.application.item.wishList.delete.DeleteWishListUseCase;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.wishList.WishListGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WishListUseCaseConfig {

    private final WishListGateway wishListGateway;
    private final ItemGateway itemGateway;
    private final UserGateway userGateway;

    public WishListUseCaseConfig(WishListGateway wishListGateway, ItemGateway itemGateway, UserGateway userGateway) {
        this.wishListGateway = wishListGateway;
        this.itemGateway = itemGateway;
        this.userGateway = userGateway;
    }

    @Bean
    public CreateWishListUseCase createWishListUseCase(){
        return new DefaultCreateWishListUseCase(itemGateway, userGateway, wishListGateway);
    }

    @Bean
    public DeleteWishListUseCase deleteWishListUseCase(){
        return new DefaultDeleteWishListUseCase(wishListGateway);
    }
}
