package com.loquei.core.application.user.address.update;

public record UpdateAddressCommand(
        String id,
        String postalCode,
        String street,
        String neighborhood,
        String city,
        String state,
        String country,
        long number,
        boolean isMain,
        String userId) {

    public static UpdateAddressCommand with(
            final String id,
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            String userId) {

        return new UpdateAddressCommand(
                id, postalCode, street, neighborhood, city, state, country, number, isMain, userId);
    }
}
