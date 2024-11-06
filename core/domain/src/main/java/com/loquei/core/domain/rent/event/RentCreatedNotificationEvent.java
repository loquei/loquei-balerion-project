package com.loquei.core.domain.rent.event;

import com.loquei.common.event.Event;
import com.loquei.core.domain.rent.RentId;

public class RentCreatedNotificationEvent extends Event {

    private final RentId rentId;

    private RentCreatedNotificationEvent(final RentId rentId) {
        this.rentId = rentId;
    }

    public static RentCreatedNotificationEvent with(final RentId rentId) {
        return new RentCreatedNotificationEvent(rentId);
    }

    public RentId getRentId() {
        return rentId;
    }

}
