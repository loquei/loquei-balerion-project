package com.loquei.core.domain.user.address;

import com.loquei.common.Entity;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.user.UserId;
import java.time.Instant;

public class Address extends Entity<AddressId> {
    private String postalCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private long number;
    private boolean main;
    private final UserId userId;
    private final Instant createdAt;
    private Instant updatedAt;

    public Address(
            final AddressId anId,
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            final UserId userId,
            final Instant createdAt,
            final Instant updatedAt) {

        super(anId);
        this.postalCode = postalCode;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.main = isMain;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Address newAddress(
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            final UserId userId) {

        final var id = AddressId.unique();
        final var now = InstantUtils.now();

        return new Address(
                id, postalCode, street, neighborhood, city, state, country, number, isMain, userId, now, now);
    }

    public static Address with(
            final AddressId anId,
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain,
            final UserId userId,
            final Instant createdAt,
            final Instant updatedAt) {

        return new Address(
                anId,
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

    public static Address with(final Address address) {

        return new Address(
                address.getId(),
                address.getPostalCode(),
                address.getStreet(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getNumber(),
                address.isMain(),
                address.getUserId(),
                address.getCreatedAt(),
                address.getUpdatedAt());
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new AddressValidator(this, aHandler).validate();
    }

    public Address update(
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean isMain) {

        this.postalCode = postalCode;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.main = isMain;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Address makeMain() {
        this.updatedAt = InstantUtils.now();
        this.main = true;
        return this;
    }

    public Address unmakeMain() {
        this.updatedAt = InstantUtils.now();
        this.main = false;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public long getNumber() {
        return number;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public boolean isMain() {
        return main;
    }

    public UserId getUserId() {
        return userId;
    }
}
