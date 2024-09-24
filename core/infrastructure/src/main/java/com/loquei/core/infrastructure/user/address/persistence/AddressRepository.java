package com.loquei.core.infrastructure.user.address.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, String> {

    List<AddressJpaEntity> findAddressJpaEntityByUserId(String userId);

    Boolean existsAddressJpaEntitiesByPostalCodeAndNumber(String postalCode, long number);
}
