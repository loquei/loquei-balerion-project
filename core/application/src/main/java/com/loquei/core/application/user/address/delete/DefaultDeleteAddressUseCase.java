package com.loquei.core.application.user.address.delete;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressGateway;
import com.loquei.core.domain.user.address.AddressId;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import java.util.Optional;
import java.util.function.Supplier;

public class DefaultDeleteAddressUseCase extends DeleteAddressUseCase {

    private final AddressGateway addressGateway;

    public DefaultDeleteAddressUseCase(AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
    }

    @Override
    public Optional<Notification> execute(final String anIN) {
        final Address address =
                addressGateway.findById(AddressId.from(anIN)).orElseThrow(notFound(AddressId.from(anIN)));

        final var notification = Notification.create();
        checkMain(address).ifPresent(notification::append);

        if (notification.hasError()) return Optional.of(notification);

        this.addressGateway.delete(AddressId.from(anIN));
        return Optional.empty();
    }

    private Optional<Error> checkMain(Address address) {

        return address.isMain() ? Optional.of(new Error("You cannot delete the main address")) : Optional.empty();
    }

    private Supplier<NotFoundException> notFound(final AddressId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }
}
