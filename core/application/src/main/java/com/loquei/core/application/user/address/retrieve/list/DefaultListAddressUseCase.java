package com.loquei.core.application.user.address.retrieve.list;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.AddressGateway;
import java.util.List;
import java.util.function.Supplier;

public class DefaultListAddressUseCase extends ListAddressUseCase {

    private final AddressGateway addressGateway;
    private final UserGateway userGateway;

    public DefaultListAddressUseCase(final UserGateway userGateway, final AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public List<AddressListOutput> execute(final String aIn) {
        final var userId = UserId.from(aIn);

        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        return this.addressGateway.findAddressByUserId(user.getId()).stream()
                .map(AddressListOutput::from)
                .toList();
    }

    private Supplier<NotFoundException> notFound(final UserId anId) {
        return () -> NotFoundException.with(User.class, anId);
    }
}
