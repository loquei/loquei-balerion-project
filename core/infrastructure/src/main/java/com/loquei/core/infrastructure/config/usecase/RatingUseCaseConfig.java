package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.rating.create.CreateRatingUseCase;
import com.loquei.core.application.rating.create.DefaultCreateRatingUseCase;
import com.loquei.core.application.rating.delete.DefaultDeleteRatingUseCase;
import com.loquei.core.application.rating.delete.DeleteRatingUseCase;
import com.loquei.core.application.rating.retrieve.get.DefaultGetRatingByIdUseCase;
import com.loquei.core.application.rating.retrieve.get.GetRatingByIdUseCase;
import com.loquei.core.application.rating.retrieve.list.DefaultListRatingsUseCase;
import com.loquei.core.application.rating.retrieve.list.ListRatingsUseCase;
import com.loquei.core.application.rating.update.DefaultUpdateRatingUseCase;
import com.loquei.core.application.rating.update.UpdateRatingUseCase;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.rating.RatingGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingUseCaseConfig {

    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final RatingGateway ratingGateway;

    public RatingUseCaseConfig(
            final UserGateway userGateway, final ItemGateway itemGateway, final RatingGateway ratingGateway) {
        this.userGateway = userGateway;
        this.itemGateway = itemGateway;
        this.ratingGateway = ratingGateway;
    }

    @Bean
    public CreateRatingUseCase createRatingUseCase() {
        return new DefaultCreateRatingUseCase(itemGateway, userGateway, ratingGateway);
    }

    @Bean
    public UpdateRatingUseCase updateRatingUseCase() {
        return new DefaultUpdateRatingUseCase(ratingGateway);
    }

    @Bean
    public DeleteRatingUseCase deleteRatingUseCase() {
        return new DefaultDeleteRatingUseCase(ratingGateway);
    }

    @Bean
    public GetRatingByIdUseCase getRatingByIdUseCase() {
        return new DefaultGetRatingByIdUseCase(ratingGateway);
    }

    @Bean
    public ListRatingsUseCase listRatingsUseCase() {
        return new DefaultListRatingsUseCase(ratingGateway);
    }
}
