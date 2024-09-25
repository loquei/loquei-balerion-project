package com.loquei.core.application.rent.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressId;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class DefaultGetRentByIdUseCase extends GetRendByIdUseCase{

    private final RentGateway rentGateway;

    public DefaultGetRentByIdUseCase(final RentGateway rentGateway) {
        this.rentGateway = requireNonNull(rentGateway);
    }

    @Override
    public GetRentOutput execute(String anIn) {

        final var rentId = RentId.from(anIn);

       return rentGateway.findById(rentId)
               .map(GetRentOutput::from)
               .orElseThrow(notFound(rentId));
    }

    private Supplier<NotFoundException> notFound(final RentId id) {
        return () -> NotFoundException.with(id, Rent.class);
    }
}
