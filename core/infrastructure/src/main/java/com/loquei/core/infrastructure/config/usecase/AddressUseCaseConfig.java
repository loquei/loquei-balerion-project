package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.user.address.create.CreateAddressUseCase;
import com.loquei.core.application.user.address.create.DefaultCreateAddressUseCase;
import com.loquei.core.application.user.address.delete.DefaultDeleteAddressUseCase;
import com.loquei.core.application.user.address.delete.DeleteAddressUseCase;
import com.loquei.core.application.user.address.retrieve.get.DefaultGetAddressByIdUseCase;
import com.loquei.core.application.user.address.retrieve.get.GetAddressByIdUseCase;
import com.loquei.core.application.user.address.retrieve.list.DefaultListAddressUseCase;
import com.loquei.core.application.user.address.retrieve.list.ListAddressUseCase;
import com.loquei.core.application.user.address.update.DefaultUpdateAddressUseCase;
import com.loquei.core.application.user.address.update.UpdateAddressUseCase;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.address.AddressGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressUseCaseConfig {

    private final AddressGateway addressGateway;
    private final UserGateway userGateway;

    public AddressUseCaseConfig(final UserGateway userGateway, final AddressGateway gateway) {
        this.addressGateway = gateway;
        this.userGateway = userGateway;
    }

    @Bean
    public UpdateAddressUseCase updateAddressUseCase() {
        return new DefaultUpdateAddressUseCase(addressGateway, userGateway);
    }

    @Bean
    public DeleteAddressUseCase deleteAddressUseCase() {
        return new DefaultDeleteAddressUseCase(addressGateway);
    }

    @Bean
    public GetAddressByIdUseCase getAddressByIdUseCase() {
        return new DefaultGetAddressByIdUseCase(addressGateway);
    }

    @Bean
    public CreateAddressUseCase createAddressUseCase() {
        return new DefaultCreateAddressUseCase(addressGateway, userGateway);
    }

    @Bean
    public ListAddressUseCase listAddressUseCase() {
        return new DefaultListAddressUseCase(userGateway, addressGateway);
    }
}
