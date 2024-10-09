package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.user.address.create.CreateAddressCommand;
import com.loquei.core.application.user.address.create.CreateAddressOutput;
import com.loquei.core.application.user.address.create.CreateAddressUseCase;
import com.loquei.core.application.user.address.delete.DeleteAddressUseCase;
import com.loquei.core.application.user.address.retrieve.get.GetAddressByIdUseCase;
import com.loquei.core.application.user.address.retrieve.list.ListAddressUseCase;
import com.loquei.core.application.user.address.update.UpdateAddressCommand;
import com.loquei.core.application.user.address.update.UpdateAddressOutput;
import com.loquei.core.application.user.address.update.UpdateAddressUseCase;
import com.loquei.core.infrastructure.api.AddressAPI;
import com.loquei.core.infrastructure.user.address.models.AddressResponse;
import com.loquei.core.infrastructure.user.address.models.CreateAddressRequest;
import com.loquei.core.infrastructure.user.address.models.UpdateAddressRequest;
import com.loquei.core.infrastructure.user.address.presenter.AddressApiPressenter;
import java.net.URI;
import java.util.List;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController implements AddressAPI {

    private final CreateAddressUseCase createAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final GetAddressByIdUseCase getAddressByIdUseCase;
    private final ListAddressUseCase listAddressUseCase;

    public AddressController(
            final CreateAddressUseCase createAddressUseCase,
            final UpdateAddressUseCase updateAddressUseCase,
            final DeleteAddressUseCase deleteAddressUseCase,
            final GetAddressByIdUseCase getAddressByIdUseCase,
            final ListAddressUseCase listAddressUseCase) {
        this.createAddressUseCase = createAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.getAddressByIdUseCase = getAddressByIdUseCase;
        this.deleteAddressUseCase = deleteAddressUseCase;
        this.listAddressUseCase = listAddressUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateAddressRequest input) {
        final var command = CreateAddressCommand.with(
                input.postalCode(),
                input.street(),
                input.neighborhood(),
                input.city(),
                input.state(),
                input.country(),
                input.number(),
                input.main(),
                input.userId());
        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateAddressOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/addresses/" + output.id())).body(output);

        return this.createAddressUseCase.execute(command).fold(onError, onSuccess);
    }

    @Override
    public AddressResponse getById(final String id) {
        return AddressApiPressenter.present(getAddressByIdUseCase.execute(id));
    }

    @Override
    public List<AddressResponse> findAddressByUserId(String id) {
        return AddressApiPressenter.present(listAddressUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> update(final String id, final UpdateAddressRequest input) {
        final var command = UpdateAddressCommand.with(
                id,
                input.postalCode(),
                input.street(),
                input.neighborhood(),
                input.city(),
                input.state(),
                input.country(),
                input.number(),
                input.main(),
                input.userId());
        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateAddressOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateAddressUseCase.execute(command).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteAddressUseCase.execute(id);
    }
}
