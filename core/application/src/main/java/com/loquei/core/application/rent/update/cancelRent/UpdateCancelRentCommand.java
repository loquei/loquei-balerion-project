package com.loquei.core.application.rent.update.cancelRent;

public record UpdateCancelRentCommand(
        String rentId,
        String cancellationReason) {

    public static UpdateCancelRentCommand with(
            final String rentId,
            final String cancellationReason) {

        return new UpdateCancelRentCommand(rentId, cancellationReason);
    }
}
