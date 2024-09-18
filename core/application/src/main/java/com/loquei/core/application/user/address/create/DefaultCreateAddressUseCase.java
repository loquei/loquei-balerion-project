package com.loquei.core.application.user.address.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.core.application.user.address.update.UpdateAddressOutput;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressGateway;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class DefaultCreateAddressUseCase extends CreateAddressUseCase {

    private final AddressGateway addressGateway;
    private final UserGateway userGateway;

    public DefaultCreateAddressUseCase(final AddressGateway addressGateway, final UserGateway userGateway) {
        this.addressGateway = requireNonNull(addressGateway);
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
   public Either<Notification, CreateAddressOutput> execute(final CreateAddressCommand anIn) {
        final var userId = UserId.from(anIn.userId());
        final var postalCode = anIn.postalCode();
        final var street = anIn.street();
        final var neighborhood = anIn.neighborhood();
        final var city = anIn.city();
        final var state = anIn.state();
        final var country = anIn.country();
        final var number = anIn.number();
        final var isMain = true;
        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        final var notification = Notification.create();

        duplicationError(postalCode, number).ifPresent(notification::append);

        final var address = Address.newAddress(
                postalCode, street, neighborhood, city, state, country, number, isMain, user.getId());
        address.validate(notification);

        return notification.hasError() ? Left(notification) : create(user.getId(), address);
    }

    private Optional<Error> duplicationError(final String postalCode, final long number) {
        final var exists = addressGateway.existsByPostalCodeAndNumber(postalCode, number);

        return exists ? Optional.of(new Error("duplicated address")) : Optional.empty();
    }

    private Either<Notification, CreateAddressOutput> create(final UserId userId, Address address) {
        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));
        updateAllAddressesUnmakeMain(user);
        return Try(() -> this.addressGateway.create(address))
                .toEither()
                .bimap(Notification::create, CreateAddressOutput::from);
    }

    private Either<Notification, UpdateAddressOutput> update(Address address) {
        return Try(() -> this.addressGateway.update(address))
                .toEither()
                .bimap(Notification::create, UpdateAddressOutput::from);
    }

    public Either<Notification, UpdateAddressOutput> updateMainToFalse(final Address address) {
        address.unmakeMain();

        final var notification = Notification.create();
        address.validate(notification);
        return notification.hasError() ? Left(notification) : update(address);
    }

    public void updateAllAddressesUnmakeMain(final User user) {
        final List<Address> addresses = addressGateway.findAddressByUserId(user.getId());
        for (final Address address : addresses) {
            if (address.isMain()) {
                updateMainToFalse(address);
            }
        }
    }

    private Supplier<NotFoundException> notFound(final UserId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }
}
