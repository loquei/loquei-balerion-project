package com.loquei.core.infrastructure.user.address.persistence;

import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity(name = "Address")
@Table(name = "addresses")
public class AddressJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "number", nullable = false)
    private long number;

    @Column(name = "main", nullable = false)
    private boolean main;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public AddressJpaEntity() {}

    public AddressJpaEntity(
            final String anId,
            final String postalCode,
            final String street,
            final String neighborhood,
            final String city,
            final String state,
            final String country,
            final long number,
            final boolean main,
            final String userId,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = anId;
        this.postalCode = postalCode;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.main = main;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AddressJpaEntity from(final Address address) {
        return new AddressJpaEntity(
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

    public Address toAggregate() {
        return Address.with(
                AddressId.from(getId()),
                getPostalCode(),
                getStreet(),
                getNeighborhood(),
                getCity(),
                getState(),
                getCountry(),
                getNumber(),
                getMain(),
                UserId.from(getUserId()),
                getCreatedAt(),
                getUpdatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public boolean getMain() {
        return main;
    }
}
