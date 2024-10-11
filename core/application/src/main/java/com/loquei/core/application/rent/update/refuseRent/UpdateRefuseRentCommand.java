package com.loquei.core.application.rent.update.refuseRent;

public record UpdateRefuseRentCommand(String rentId) {

    public static UpdateRefuseRentCommand with(final String rentId) {

        return new UpdateRefuseRentCommand(rentId);
    }
}
