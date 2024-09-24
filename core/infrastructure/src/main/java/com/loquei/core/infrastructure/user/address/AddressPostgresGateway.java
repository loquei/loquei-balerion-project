package com.loquei.core.infrastructure.user.address;

import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.address.Address;
import com.loquei.core.domain.user.address.AddressGateway;
import com.loquei.core.domain.user.address.AddressId;
import com.loquei.core.infrastructure.user.address.persistence.AddressJpaEntity;
import com.loquei.core.infrastructure.user.address.persistence.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class AddressPostgresGateway implements AddressGateway {

    private final AddressRepository repository;

    public AddressPostgresGateway(final AddressRepository repository) {
        this.repository = requireNonNull(repository);
    }

    @Override
    public Address create(Address address) {
        final var addressJpa = AddressJpaEntity.from(address);
        return this.repository.save(addressJpa).toAggregate();
    }

    @Override
    public Address update(Address address) {
        final var addressJpa = AddressJpaEntity.from(address);
        return this.repository.save(addressJpa).toAggregate();
    }

    @Override
    public Optional<Address> findById(final AddressId id) {
        return repository.findById(id.getValue()).map(AddressJpaEntity::toAggregate);
    }

    @Override
    public Boolean existsByPostalCodeAndNumber(String postalCode, long number) {
        return repository.existsAddressJpaEntitiesByPostalCodeAndNumber(postalCode, number);
    }

    @Override
    public List<Address> findAddressByUserId(UserId userId) {
        return repository.findAddressJpaEntityByUserId(userId.getValue()).stream()
                .map(AddressJpaEntity::toAggregate)
                .toList();
    }

    @Override
    public void delete(AddressId id) {
        final var idAddress = id.getValue();
        if (repository.existsById(idAddress)) repository.deleteById(idAddress);
    }
}
