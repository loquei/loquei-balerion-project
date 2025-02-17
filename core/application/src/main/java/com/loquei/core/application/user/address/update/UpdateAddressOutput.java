package com.loquei.core.application.user.address.update;

import com.loquei.core.domain.user.address.Address;
import java.time.Instant;

public record UpdateAddressOutput(
        String id,
        String postalCode,
        String street,
        String neighborhood,
        String city,
        String state,
        String country,
        long number,
        boolean isMain,
        String userId,
        Instant createdAt,
        Instant updatedAt) {

    public static UpdateAddressOutput from(
            final String id,
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            final String userId,
            final Instant createdAt,
            final Instant updatedAt) {

        return new UpdateAddressOutput(
                id,
                postalCode,
                street,
                neighborhood,
                city,
                state,
                country,
                number,
                isMain,
                userId,
                createdAt,
                updatedAt);
    }

    public static UpdateAddressOutput from(final Address address) {
        return new UpdateAddressOutput(
                address.getId().getValue(),
                address.getPostalCode(),
                address.getStreet(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getNumber(),
                address.isMain(),
                address.getUserId().getValue(),
                address.getCreatedAt(),
                address.getUpdatedAt());
    }
}
