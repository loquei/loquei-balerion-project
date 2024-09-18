package com.loquei.core.application.user.address.update;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressGateway;
import com.loquei.core.domain.user.address.AddressId;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;
import java.util.List;
import java.util.function.Supplier;

public class DefaultUpdateAddressUseCase extends UpdateAddressUseCase {

    private final AddressGateway addressGateway;
    private final UserGateway userGateway;

    public DefaultUpdateAddressUseCase(final AddressGateway addressGateway, final UserGateway userGateway) {
        this.addressGateway = requireNonNull(addressGateway);
        this.userGateway = requireNonNull(userGateway);
    }

    private Either<Notification, UpdateAddressOutput> update(Address address) {
        return Try(() -> this.addressGateway.update(address))
                .toEither()
                .bimap(Notification::create, UpdateAddressOutput::from);
    }

    private Supplier<NotFoundException> notFound(final AddressId id) {
        return () -> NotFoundException.with(User.class, id);
    }

    @Override
    public Either<Notification, UpdateAddressOutput> execute(final UpdateAddressCommand anIn) {
        final var id = AddressId.from(anIn.id());
        final var postalCode = anIn.postalCode();
        final var street = anIn.street();
        final var neighborhood = anIn.neighborhood();
        final var city = anIn.city();
        final var state = anIn.state();
        final var country = anIn.country();
        final var number = anIn.number();
        final var isMain = anIn.isMain();
        final var userId = UserId.from(anIn.userId());

        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        if (isMain) {
            updateAllAddressesUnmakeMain(user);
        }

        final var address = this.addressGateway.findById(id).orElseThrow(notFound(id));

        final var notification = Notification.create();
        address.update(postalCode, street, neighborhood, city, state, country, number, isMain);
        address.validate(notification);

        return notification.hasError() ? Left(notification) : update(address);
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
