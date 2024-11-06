package com.loquei.core.infrastructure.config.event;

import com.loquei.common.event.EventDispatcher;
import com.loquei.core.application.rent.update.acceptRent.listener.RentAcceptedNotificationListener;
import com.loquei.core.application.rent.update.cancelRent.listener.RentCancelledNotificationListener;
import com.loquei.core.domain.email.event.EmailEvent;
import com.loquei.core.domain.rent.event.RentAcceptedNotificationEvent;
import com.loquei.core.domain.rent.event.RentCancelledNotificationEvent;
import com.loquei.core.infrastructure.email.event.EmailEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {

    // Dispatcher
    private final EventDispatcher eventDispatcher;

    // Listeners
    private final EmailEventListener emailEventListener;
    private final RentAcceptedNotificationListener rentAcceptedNotificationListener;
    private final RentCancelledNotificationListener rentCancelledNotificationListener;

    public EventConfiguration(
            // Dispatcher
            final EventDispatcher eventDispatcher,
            // Listeners
            final EmailEventListener emailEventListener,
            final RentAcceptedNotificationListener rentAcceptedNotificationListener,
            final RentCancelledNotificationListener rentCancelledNotificationListener
    ) {
        this.eventDispatcher = eventDispatcher;
        this.emailEventListener = emailEventListener;
        this.rentAcceptedNotificationListener = rentAcceptedNotificationListener;
        this.rentCancelledNotificationListener = rentCancelledNotificationListener;
    }

    @Bean
    public EventDispatcher eventDispatcher() {

        // Register Email Event Listener
        eventDispatcher.registerListener(EmailEvent.class, emailEventListener);

        // Register RentAcceptedNotificationEvent Listener
        eventDispatcher.registerListener(RentAcceptedNotificationEvent.class, rentAcceptedNotificationListener);

        // Register RentCancelledNotificationEvent Listener
        eventDispatcher.registerListener(RentCancelledNotificationEvent.class, rentCancelledNotificationListener);

        // Return Event Dispatcher With Registered Listeners
        return eventDispatcher;
    }

}
