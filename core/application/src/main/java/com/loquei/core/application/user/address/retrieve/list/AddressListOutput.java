package com.loquei.core.application.user.address.retrieve.list;

import com.loquei.core.domain.user.address.Address;
import java.time.Instant;

public record AddressListOutput(
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

    public static AddressListOutput from(final Address address) {
        return new AddressListOutput(
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
