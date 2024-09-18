package com.loquei.core.application.user.address.create;

public record CreateAddressCommand(
        String postalCode,
        String street,
        String neighborhood,
        String city,
        String state,
        String country,
        long number,
        boolean isMain,
        String userId) {

    public static CreateAddressCommand with(
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            final String userId) {

        return new CreateAddressCommand(postalCode, street, neighborhood, city, state, country, number, isMain, userId);
    }
}
