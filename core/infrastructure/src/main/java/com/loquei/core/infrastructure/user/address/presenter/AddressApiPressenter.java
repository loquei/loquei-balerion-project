package com.loquei.core.infrastructure.user.address.presenter;

import com.loquei.core.application.user.address.retrieve.get.GetAddressOutput;
import com.loquei.core.application.user.address.retrieve.list.AddressListOutput;
import com.loquei.core.infrastructure.user.address.models.AddressResponse;

import java.util.List;

public interface AddressApiPressenter {

    static AddressResponse present(final GetAddressOutput output) {
        return new AddressResponse(
                output.id(),
                output.postalCode(),
                output.street(),
                output.neighborhood(),
                output.city(),
                output.state(),
                output.country(),
                output.number(),
                output.main(),
                output.userId(),
                output.createdAt(),
                output.updatedAt());
    }

    static List<AddressResponse> present(final List<AddressListOutput> output) {
        return output.stream().map(AddressResponse::from).toList();
    }
}
