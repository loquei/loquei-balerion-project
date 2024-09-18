package com.loquei.core.domain.user.address;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.exceptions.NotificationException;
import com.loquei.common.validation.Validator;

import java.util.Objects;

public class AddressValidator extends Validator {

    private static final Integer POSTAL_CODE_LENGTH = 8;
    private static final String POSTAL_CODE_REGEX = "^[0-9]{8}$";
    private static final Integer STREET_MAX_LENGTH = 255;
    private static final Integer STREET_MIN_LENGTH = 3;
    private static final Integer NEIGHBORHOOD_MAX_LENGTH = 255;
    private static final Integer NEIGHBORHOOD_MIN_LENGTH = 3;
    private static final Integer CITY_MAX_LENGTH = 255;
    private static final Integer CITY_MIN_LENGTH = 2;
    private static final Integer STATE_LENGTH = 2;
    private static final Integer COUNTRY_MAX_LENGTH = 255;
    private static final Integer COUNTRY_MIN_LENGTH = 2;

    private final Address address;

    public AddressValidator(final Address address, final ValidationHandler aHandler) {
        super(aHandler);
        this.address = address;
    }

    @Override
    public void validate() {
        this.checkPostalCodeConstraints();
        this.checkStreetConstraints();
        this.checkNeighborhoodConstraints();
        this.checkCityConstraints();
        this.checkStateConstraints();
        this.checkCountryConstraints();
        this.checkNumberConstraints();
    }

    public void checkPostalCodeConstraints() {
        if (Objects.isNull(this.address.getPostalCode())) {
            this.validationHandler().append(new Error("'cep' should not be null"));
            return;
        }
        if (this.address.getPostalCode().isBlank()
                || this.address.getPostalCode().isEmpty()) {
            this.validationHandler().append(new Error("'cep' should not be empty"));
            return;
        }
        if (!this.address.getPostalCode().matches(POSTAL_CODE_REGEX)) {
            this.validationHandler().append(new Error("'cep' deve estar no formato '12345678'"));
            return;
        }
        final String numericPostalCode = this.address.getPostalCode().replace("-", "");
        if (numericPostalCode.length() != POSTAL_CODE_LENGTH) {
            this.validationHandler().append(new Error("'cep' must have exactly 8 digits"));
        }
    }

    private void checkStreetConstraints() {
        final var street = this.address.getStreet();
        if (Objects.isNull(street)) {
            this.validationHandler().append(new Error("'street' should not be null"));
            return;
        }
        if (street.isBlank() || street.isEmpty()) {
            this.validationHandler().append(new Error("'street' should not be empty"));
            return;
        }
        final int length = street.trim().length();
        if (length > STREET_MAX_LENGTH || length < STREET_MIN_LENGTH) {
            this.validationHandler().append(new Error("'street' must be between 3 and 255 characters"));
        }
    }

    private void checkNeighborhoodConstraints() {
        final var neighborhood = this.address.getNeighborhood();
        if (Objects.isNull(neighborhood)) {
            this.validationHandler().append(new Error("'neighborhood' should not be null"));
            return;
        }
        if (neighborhood.isBlank() || neighborhood.isEmpty()) {
            this.validationHandler().append(new Error("'neighborhood' should not be empty"));
            return;
        }
        final int length = neighborhood.trim().length();
        if (length > NEIGHBORHOOD_MAX_LENGTH || length < NEIGHBORHOOD_MIN_LENGTH) {
            this.validationHandler().append(new Error("'neighborhood' must be between 3 and 255 characters"));
        }
    }

    private void checkCityConstraints() {
        final var city = this.address.getCity();
        if (Objects.isNull(city)) {
            this.validationHandler().append(new Error("'city' should not be null"));
            return;
        }
        if (city.isBlank() || city.isEmpty()) {
            this.validationHandler().append(new Error("'city' should not be empty"));
            return;
        }
        final int length = city.trim().length();
        if (length > CITY_MAX_LENGTH || length < CITY_MIN_LENGTH) {
            this.validationHandler().append(new Error("'city' must be between 2 and 255 characters"));
        }
    }

    private void checkStateConstraints() {
        final var state = this.address.getState();
        if (Objects.isNull(state)) {
            this.validationHandler().append(new Error("'state' should not be null"));
            return;
        }
        if (state.isBlank() || state.isEmpty()) {
            this.validationHandler().append(new Error("'state' should not be empty"));
            return;
        }
        final int length = state.trim().length();
        if (length != STATE_LENGTH) {
            this.validationHandler().append(new Error("'state' must be exactly 2 characters"));
            return;
        }
        try {
            AddressState.valueOf(state.trim().toUpperCase());
        } catch (NotificationException e) {
            this.validationHandler().append(new Error("'state' must be a valid Brazilian state abbreviation, ex: SP"));
        }
    }

    private void checkCountryConstraints() {
        final var country = this.address.getCountry();
        if (Objects.isNull(country)) { //
            this.validationHandler().append(new Error("'country' should not be null"));
            return;
        }
        if (country.isBlank() || country.isEmpty()) {
            this.validationHandler().append(new Error("'country' should not be empty"));
            return;
        }
        final int length = country.trim().length();
        if (length > COUNTRY_MAX_LENGTH || length < COUNTRY_MIN_LENGTH) {
            this.validationHandler().append(new Error("'country' must be between 2 and 255 characters"));
        }
    }

    private void checkNumberConstraints() {
        final long number = this.address.getNumber();
        if (number <= 0) {
            this.validationHandler().append(new Error("'number' must be greater than zero"));
        }
    }
}
