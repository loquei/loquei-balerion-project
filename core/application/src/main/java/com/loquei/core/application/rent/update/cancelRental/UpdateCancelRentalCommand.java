package com.loquei.core.application.rent.update.cancelRental;


import com.loquei.core.domain.rent.RentStatus;

public record UpdateCancelRentalCommand(
        String rentId,
        String cancellationReason) {

    public static UpdateCancelRentalCommand with(
            final String rentId,
            final String cancellationReason) {

        return new UpdateCancelRentalCommand(rentId, cancellationReason);
    }
}
