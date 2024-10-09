package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.rent.create.CreateRentCommand;
import com.loquei.core.application.rent.create.CreateRentOutput;
import com.loquei.core.application.rent.create.CreateRentUseCase;
import com.loquei.core.application.rent.retrieve.checkavailability.IsItemAvailableForRentCommand;
import com.loquei.core.application.rent.retrieve.checkavailability.IsItemAvailableForRentUseCase;
import com.loquei.core.application.rent.retrieve.get.GetRentByIdUseCase;
import com.loquei.core.application.rent.retrieve.list.ListRentParams;
import com.loquei.core.application.rent.retrieve.list.ListRentUseCase;
import com.loquei.core.application.rent.update.acceptRent.UpdateAcceptRentCommand;
import com.loquei.core.application.rent.update.acceptRent.UpdateAcceptRentUseCase;
import com.loquei.core.application.rent.update.acceptRent.UpdateAcepptRentOutput;
import com.loquei.core.application.rent.update.cancelRent.UpdateCancelRentCommand;
import com.loquei.core.application.rent.update.cancelRent.UpdateCancelRentOutput;
import com.loquei.core.application.rent.update.cancelRent.UpdateCancelRentUseCase;
import com.loquei.core.application.rent.update.refuseRent.UpdateRefuseRentCommand;
import com.loquei.core.application.rent.update.refuseRent.UpdateRefuseRentOutput;
import com.loquei.core.application.rent.update.refuseRent.UpdateRefuseRentUseCase;
import com.loquei.core.application.rent.update.updateRentalDate.UpdateRentalDateCommand;
import com.loquei.core.application.rent.update.updateRentalDate.UpdateRentalDateOutput;
import com.loquei.core.application.rent.update.updateRentalDate.UpdateRentalDateUseCase;
import com.loquei.core.infrastructure.api.RentAPI;
import com.loquei.core.infrastructure.rent.models.*;
import com.loquei.core.infrastructure.rent.presenter.RentApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.function.Function;

@RestController
public class RentController implements RentAPI {

    private final CreateRentUseCase createRentUseCase;
    private final GetRentByIdUseCase getRentByIdUseCase;
    private final ListRentUseCase listRentUseCase;
    private final UpdateAcceptRentUseCase updateAcceptRentUseCase;
    private final UpdateCancelRentUseCase updateCancelRentUseCase;
    private final UpdateRefuseRentUseCase updateRefuseRentUseCase;
    private final UpdateRentalDateUseCase updateRentalDateUseCase;
    private final IsItemAvailableForRentUseCase isItemAvailableForRentUseCase;

    public RentController(CreateRentUseCase createRentUseCase,
                          GetRentByIdUseCase getRentByIdUseCase,
                          ListRentUseCase listRentUseCase,
                          UpdateAcceptRentUseCase updateAcceptRentUseCase,
                          UpdateCancelRentUseCase updateCancelRentUseCase,
                          UpdateRefuseRentUseCase updateRefuseRentUseCase,
                          UpdateRentalDateUseCase updateRentalDateUseCase,
                          IsItemAvailableForRentUseCase isItemAvailableForRentUseCase) {
        this.createRentUseCase = createRentUseCase;
        this.getRentByIdUseCase = getRentByIdUseCase;
        this.listRentUseCase = listRentUseCase;
        this.updateAcceptRentUseCase = updateAcceptRentUseCase;
        this.updateCancelRentUseCase = updateCancelRentUseCase;
        this.updateRefuseRentUseCase = updateRefuseRentUseCase;
        this.updateRentalDateUseCase = updateRentalDateUseCase;
        this.isItemAvailableForRentUseCase = isItemAvailableForRentUseCase;
    }

    @Override
    public ResponseEntity<?> rent(CreateRentRequest input) {
        final var aCommand = CreateRentCommand.with(
                input.lessor(),
                input.lessee(),
                input.item(),
                input.startDate(),
                input.endDate()
        );

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateRentOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/users/" + output.id())).body(output);

        return this.createRentUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<RentListResponse> listAllRentalsByUserId(final String userId) {
        return listRentUseCase
                .execute(userId)
                .map(RentApiPresenter::present);
    }

    @Override
    public RentResponse getById(String id) {
        return RentApiPresenter.present(getRentByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> acceptRent(String id) {
        final var acommand =UpdateAcceptRentCommand.with(id);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateAcepptRentOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return updateAcceptRentUseCase.execute(acommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> refuseRent(String id) {
        final var acommand = UpdateRefuseRentCommand.with(id);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateRefuseRentOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return updateRefuseRentUseCase.execute(acommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> cancelRent(CancelRentRequest input) {
        final var aCommand = UpdateCancelRentCommand.with(
                input.rentId(),
                input.cancellationReason());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCancelRentOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateCancelRentUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> UpdateRentalDate(UpdateRentalDateRequest input) {
        final var aCommand = UpdateRentalDateCommand.with(
                input.rentId(),
                input.itemId(),
                input.startDate(),
                input.endDate());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateRentalDateOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateRentalDateUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> isItemAvailableForRent(String itemId, LocalDateTime startDate, LocalDateTime endDate) {
        final var command = IsItemAvailableForRentCommand.with(itemId, startDate, endDate);
        boolean isAvailable = this.isItemAvailableForRentUseCase.execute(command);

        if (isAvailable) {
            return ResponseEntity.ok("Item is available");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item is not available");
        }
    }


}
