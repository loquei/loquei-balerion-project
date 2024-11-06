package com.loquei.core.domain.rent.event;

import com.loquei.common.event.Event;
import com.loquei.core.domain.rent.RentId;

public class RentRefusedNotificationEvent extends Event {

    private final RentId rentId;

    private RentRefusedNotificationEvent(final RentId rentId) {
        this.rentId = rentId;
    }

    public static RentRefusedNotificationEvent with(final RentId rentId) {
        return new RentRefusedNotificationEvent(rentId);
    }

    public RentId getRentId() {
        return rentId;
    }

}
