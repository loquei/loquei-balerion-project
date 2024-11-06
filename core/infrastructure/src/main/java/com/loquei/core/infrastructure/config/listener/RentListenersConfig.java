package com.loquei.core.infrastructure.config.listener;

import com.loquei.core.application.rent.update.acceptRent.listener.RentAcceptedNotificationListener;
import com.loquei.core.application.rent.update.cancelRent.listener.RentCancelledNotificationListener;
import com.loquei.core.application.rent.update.refuseRent.listener.RentRefusedNotificationListener;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentListenersConfig {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final EmailGateway emailGateway;

    public RentListenersConfig(
            final RentGateway rentGateway,
            final UserGateway userGateway,
            final ItemGateway itemGateway,
            final EmailGateway emailGateway
    ) {
        this.rentGateway = rentGateway;
        this.userGateway = userGateway;
        this.itemGateway = itemGateway;
        this.emailGateway = emailGateway;
    }

    @Bean
    public RentAcceptedNotificationListener rentAcceptedNotificationListener() {
        return new RentAcceptedNotificationListener(rentGateway, userGateway, itemGateway, emailGateway);
    }

    @Bean
    public RentCancelledNotificationListener rentCancelledNotificationListener() {
        return new RentCancelledNotificationListener(rentGateway, userGateway, itemGateway, emailGateway);
    }

    @Bean
    public RentRefusedNotificationListener rentRefusedNotificationListener() {
        return new RentRefusedNotificationListener(rentGateway, userGateway, itemGateway, emailGateway);
    }

}
