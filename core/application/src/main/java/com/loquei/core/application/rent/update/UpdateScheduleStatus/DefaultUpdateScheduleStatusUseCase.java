package com.loquei.core.application.rent.update.UpdateScheduleStatus;

import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;

import java.time.Instant;
import java.util.List;

public class DefaultUpdateScheduleStatusUseCase implements UpdateScheduleStatusUseCase{

    private final RentGateway rentGateway;

    public DefaultUpdateScheduleStatusUseCase(RentGateway rentGateway) {
        this.rentGateway = rentGateway;
    }

    @Override
    public void execute() {

        List<Rent> rents = rentGateway.findAll();

        rents.forEach(rent -> {
            rent.updateStatusBasedOnDate();
            rentGateway.rent(rent);
        });
    }

}
