package com.loquei.core.application.rent.update.acceptRent;

public record UpdateAcceptRentCommand(String rentId) {

    public static UpdateAcceptRentCommand with(final String rentId) {

        return new UpdateAcceptRentCommand(rentId);
    }
}
