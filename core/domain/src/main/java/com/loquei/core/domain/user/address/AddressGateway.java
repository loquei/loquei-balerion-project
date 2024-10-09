package com.loquei.core.domain.user.address;

import com.loquei.core.domain.user.UserId;
import java.util.List;
import java.util.Optional;

public interface AddressGateway {

    Address create(Address address);

    Address update(Address address);

    Optional<Address> findById(AddressId Id);

    Boolean existsByPostalCodeAndNumber(String postalCode, long number);

    List<Address> findAddressByUserId(UserId userId);

    void delete(AddressId id);
}
