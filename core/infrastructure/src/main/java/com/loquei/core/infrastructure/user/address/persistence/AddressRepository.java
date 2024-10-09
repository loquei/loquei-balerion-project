package com.loquei.core.infrastructure.user.address.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, String> {

    List<AddressJpaEntity> findAddressJpaEntityByUserId(String userId);

    Boolean existsAddressJpaEntitiesByPostalCodeAndNumber(String postalCode, long number);
}
