package com.loquei.core.domain.rent.event;

import com.loquei.common.event.Event;
import com.loquei.core.domain.rent.RentId;

public class RentAcceptedNotificationEvent extends Event {

    private final RentId rentId;

    private RentAcceptedNotificationEvent(final RentId rentId) {
        this.rentId = rentId;
    }

    public static RentAcceptedNotificationEvent with(final RentId rentId) {
        return new RentAcceptedNotificationEvent(rentId);
    }

    public RentId getRentId() {
        return rentId;
    }

}
