package com.loquei.core.infrastructure.config.event;

import com.loquei.common.event.EventDispatcher;
import com.loquei.core.application.rent.update.acceptRent.listener.RentAcceptedNotificationListener;
import com.loquei.core.domain.email.event.EmailEvent;
import com.loquei.core.domain.rent.event.RentAcceptedNotificationEvent;
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

    public EventConfiguration(
            // Dispatcher
            final EventDispatcher eventDispatcher,
            // Listeners
            final EmailEventListener emailEventListener,
            final RentAcceptedNotificationListener rentAcceptedNotificationListener
    ) {
        this.eventDispatcher = eventDispatcher;
        this.emailEventListener = emailEventListener;
        this.rentAcceptedNotificationListener = rentAcceptedNotificationListener;
    }

    @Bean
    public EventDispatcher eventDispatcher() {

        // Register Email Event Listener
        eventDispatcher.registerListener(EmailEvent.class, emailEventListener);

        // Register RentAcceptedNotificationEvent Listener
        eventDispatcher.registerListener(RentAcceptedNotificationEvent.class, rentAcceptedNotificationListener);

        // Return Event Dispatcher With Registered Listeners
        return eventDispatcher;
    }

}
