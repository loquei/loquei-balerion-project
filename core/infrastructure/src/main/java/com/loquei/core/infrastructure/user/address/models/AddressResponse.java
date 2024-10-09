package com.loquei.core.infrastructure.user.address.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loquei.core.application.user.address.retrieve.list.AddressListOutput;
import java.time.Instant;

public record AddressResponse(
        @JsonProperty("id") String id,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("street") String street,
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("country") String country,
        @JsonProperty("number") long number,
        @JsonProperty("main") boolean main,
        @JsonProperty("user_id") String userId,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {

    public static AddressResponse from(final AddressListOutput address) {
        return new AddressResponse(
                address.id(),
                address.postalCode(),
                address.street(),
                address.neighborhood(),
                address.city(),
                address.state(),
                address.country(),
                address.number(),
                address.isMain(),
                address.userId(),
                address.createdAt(),
                address.updatedAt());
    }
}
