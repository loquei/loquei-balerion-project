package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.rent.create.CreateRentUseCase;
import com.loquei.core.application.rent.create.DefaultCreateRentUseCase;
import com.loquei.core.application.rent.retrieve.checkavailability.DefaultIsItemAvailableForRentUseCase;
import com.loquei.core.application.rent.retrieve.get.DefaultGetRentByIdUseCase;
import com.loquei.core.application.rent.retrieve.get.GetRentByIdUseCase;
import com.loquei.core.application.rent.retrieve.list.DefaultListRentUseCase;
import com.loquei.core.application.rent.retrieve.list.ListRentUseCase;
import com.loquei.core.application.rent.update.acceptRent.DefaultUpdateAcceptRentUseCase;
import com.loquei.core.application.rent.update.acceptRent.UpdateAcceptRentUseCase;
import com.loquei.core.application.rent.update.cancelRent.DefaultUpdateCancelRentUseCase;
import com.loquei.core.application.rent.update.cancelRent.UpdateCancelRentUseCase;
import com.loquei.core.application.rent.update.refuseRent.DefaultUpdateRefuseRentUseCase;
import com.loquei.core.application.rent.update.refuseRent.UpdateRefuseRentUseCase;
import com.loquei.core.application.rent.update.updateRentalDate.DefaultUpdateRentalDateUseCase;
import com.loquei.core.application.rent.update.updateRentalDate.UpdateRentalDateUseCase;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;

public class RentUseCaseConfig {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;

    public RentUseCaseConfig(
            final RentGateway rentGateway, final UserGateway userGateway, final ItemGateway itemGateway) {
        this.rentGateway = rentGateway;
        this.userGateway = userGateway;
        this.itemGateway = itemGateway;
    }

    @Bean
    public CreateRentUseCase createRentUseCase() {
        return new DefaultCreateRentUseCase(rentGateway, userGateway, itemGateway);
    }

    @Bean
    public UpdateAcceptRentUseCase updateAcceptRentUseCase() {
        return new DefaultUpdateAcceptRentUseCase(rentGateway);
    }

    @Bean
    public UpdateCancelRentUseCase updateCancelRentUseCase() {
        return new DefaultUpdateCancelRentUseCase(rentGateway);
    }

    @Bean
    public UpdateRefuseRentUseCase updateRefuseRentUseCase() {
        return new DefaultUpdateRefuseRentUseCase(rentGateway);
    }

    @Bean
    public UpdateRentalDateUseCase updateRentalDateUseCase() {
        return new DefaultUpdateRentalDateUseCase(rentGateway, itemGateway);
    }

    @Bean
    public GetRentByIdUseCase getRentByIdUseCase() {
        return new DefaultGetRentByIdUseCase(rentGateway);
    }

    @Bean
    public ListRentUseCase listRentUseCase() {
        return new DefaultListRentUseCase(rentGateway, userGateway);
    }

    @Bean
    public DefaultIsItemAvailableForRentUseCase checkItemAvailabilityUseCase() {
        return new DefaultIsItemAvailableForRentUseCase(rentGateway);
    }
}