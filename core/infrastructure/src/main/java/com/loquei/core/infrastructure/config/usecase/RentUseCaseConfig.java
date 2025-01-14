package com.loquei.core.infrastructure.config.usecase;

import com.loquei.common.event.EventDispatcher;
import com.loquei.core.application.rent.create.CreateRentUseCase;
import com.loquei.core.application.rent.create.DefaultCreateRentUseCase;
import com.loquei.core.application.rent.retrieve.checkavailability.DefaultIsItemAvailableForRentUseCase;
import com.loquei.core.application.rent.retrieve.checkavailability.IsItemAvailableForRentUseCase;
import com.loquei.core.application.rent.retrieve.get.DefaultGetRentByIdUseCase;
import com.loquei.core.application.rent.retrieve.get.GetRentByIdUseCase;
import com.loquei.core.application.rent.retrieve.list.DefaultListRentUseCase;
import com.loquei.core.application.rent.retrieve.list.ListRentUseCase;
import com.loquei.core.application.rent.update.UpdateScheduleStatus.DefaultUpdateScheduleStatusUseCase;
import com.loquei.core.application.rent.update.UpdateScheduleStatus.UpdateScheduleStatusUseCase;
import com.loquei.core.application.rent.update.acceptRent.DefaultUpdateAcceptRentUseCase;
import com.loquei.core.application.rent.update.acceptRent.UpdateAcceptRentUseCase;
import com.loquei.core.application.rent.update.cancelRent.DefaultUpdateCancelRentUseCase;
import com.loquei.core.application.rent.update.cancelRent.UpdateCancelRentUseCase;
import com.loquei.core.application.rent.update.refuseRent.DefaultUpdateRefuseRentUseCase;
import com.loquei.core.application.rent.update.refuseRent.UpdateRefuseRentUseCase;
import com.loquei.core.application.rent.update.updateRentalDate.DefaultUpdateRentalDateUseCase;
import com.loquei.core.application.rent.update.updateRentalDate.UpdateRentalDateUseCase;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentUseCaseConfig {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final EmailGateway emailGateway;
    private final EventDispatcher eventDispatcher;

    public RentUseCaseConfig(
            final RentGateway rentGateway, final UserGateway userGateway, final ItemGateway itemGateway, final EmailGateway emailGateway, final EventDispatcher eventDispatcher) {
        this.rentGateway = rentGateway;
        this.userGateway = userGateway;
        this.itemGateway = itemGateway;
        this.emailGateway = emailGateway;
        this.eventDispatcher = eventDispatcher;
    }

    @Bean
    public CreateRentUseCase createRentUseCase() {
        return new DefaultCreateRentUseCase(rentGateway, userGateway, itemGateway, emailGateway, eventDispatcher);
    }

    @Bean
    public UpdateAcceptRentUseCase updateAcceptRentUseCase() {
        return new DefaultUpdateAcceptRentUseCase(rentGateway, eventDispatcher);
    }

    @Bean
    public UpdateCancelRentUseCase updateCancelRentUseCase() {
        return new DefaultUpdateCancelRentUseCase(rentGateway, eventDispatcher);
    }

    @Bean
    public UpdateRefuseRentUseCase updateRefuseRentUseCase() {
        return new DefaultUpdateRefuseRentUseCase(rentGateway, eventDispatcher);
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
    public IsItemAvailableForRentUseCase checkItemAvailabilityUseCase() {
        return new DefaultIsItemAvailableForRentUseCase(rentGateway);
    }

    @Bean
    public UpdateScheduleStatusUseCase updateScheduleStatusUseCase() {
        return new DefaultUpdateScheduleStatusUseCase(rentGateway);
    }
}
