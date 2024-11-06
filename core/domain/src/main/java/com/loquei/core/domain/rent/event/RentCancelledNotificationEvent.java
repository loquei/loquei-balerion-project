package com.loquei.core.domain.rent.event;

import com.loquei.common.event.Event;
import com.loquei.core.domain.rent.RentId;

public class RentCancelledNotificationEvent extends Event {

    private final RentId rentId;

    private RentCancelledNotificationEvent(final RentId rentId) {
        this.rentId = rentId;
    }

    public static RentCancelledNotificationEvent with(final RentId rentId) {
        return new RentCancelledNotificationEvent(rentId);
    }

    public RentId getRentId() {
        return rentId;
    }

}
