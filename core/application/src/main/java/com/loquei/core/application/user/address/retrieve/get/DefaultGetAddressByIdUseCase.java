package com.loquei.core.application.user.address.retrieve.get;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressGateway;
import com.loquei.core.domain.user.address.AddressId;
import java.util.function.Supplier;

public class DefaultGetAddressByIdUseCase extends GetAddressByIdUseCase {

    private final AddressGateway addressGateway;

    public DefaultGetAddressByIdUseCase(AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
    }

    @Override
    public GetAddressOutput execute(final String anIn) {
        final var addressId = AddressId.from(anIn);
        return this.addressGateway
                .findById(addressId)
                .map(GetAddressOutput::from)
                .orElseThrow(notFound(addressId));
    }

    private Supplier<NotFoundException> notFound(final AddressId id) {
        return () -> NotFoundException.with(id, Address.class);
    }
}
