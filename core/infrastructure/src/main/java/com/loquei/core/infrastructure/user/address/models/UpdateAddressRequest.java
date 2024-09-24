package com.loquei.core.infrastructure.user.address.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAddressRequest(
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("street") String street,
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("country") String country,
        @JsonProperty("number") long number,
        @JsonProperty("main") boolean main,
        @JsonProperty("user_id") String userId) {}
