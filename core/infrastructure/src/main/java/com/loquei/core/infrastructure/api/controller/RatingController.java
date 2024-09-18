package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.rating.create.CreateRatingCommand;
import com.loquei.core.application.rating.create.CreateRatingOutput;
import com.loquei.core.application.rating.create.CreateRatingUseCase;
import com.loquei.core.application.rating.delete.DeleteRatingUseCase;
import com.loquei.core.application.rating.retrieve.get.GetRatingByIdUseCase;
import com.loquei.core.application.rating.retrieve.list.ListRatingsUseCase;
import com.loquei.core.application.rating.update.UpdateRatingCommand;
import com.loquei.core.application.rating.update.UpdateRatingOutput;
import com.loquei.core.application.rating.update.UpdateRatingUseCase;
import com.loquei.core.infrastructure.api.RatingAPI;
import com.loquei.core.infrastructure.rating.models.CreateRatingRequest;
import com.loquei.core.infrastructure.rating.models.RatingListResponse;
import com.loquei.core.infrastructure.rating.models.RatingResponse;
import com.loquei.core.infrastructure.rating.models.UpdateRatingRequest;
import com.loquei.core.infrastructure.rating.presenter.RatingApiPresenter;
import java.net.URI;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController implements RatingAPI {

    private final CreateRatingUseCase createRatingUseCase;
    private final UpdateRatingUseCase updateRatingUseCase;
    private final DeleteRatingUseCase deleteRatingUseCase;
    private final GetRatingByIdUseCase getRatingByIdUseCase;
    private final ListRatingsUseCase listRatingsUseCase;

    public RatingController(
            final CreateRatingUseCase createRatingUseCase,
            final UpdateRatingUseCase updateRatingUseCase,
            final DeleteRatingUseCase deleteRatingUseCase,
            final GetRatingByIdUseCase getRatingByIdUseCase,
            final ListRatingsUseCase listRatingsUseCase) {
        this.createRatingUseCase = createRatingUseCase;
        this.updateRatingUseCase = updateRatingUseCase;
        this.deleteRatingUseCase = deleteRatingUseCase;
        this.getRatingByIdUseCase = getRatingByIdUseCase;
        this.listRatingsUseCase = listRatingsUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateRatingRequest input) {
        final var aCommand =
                CreateRatingCommand.with(input.raterId(), input.description(), input.score(), input.itemId());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateRatingOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/ratings/" + output.id())).body(output);

        return this.createRatingUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<RatingListResponse> list(
            final String search, final int page, final int perPage, final String sort, final String direction) {
        return listRatingsUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(RatingApiPresenter::present);
    }

    @Override
    public RatingResponse getById(final String id) {
        return RatingApiPresenter.present(this.getRatingByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateRatingRequest input) {
        final var aCommand = UpdateRatingCommand.with(id, input.description(), input.score());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateRatingOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateRatingUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(String id) {
        this.deleteRatingUseCase.execute(id);
    }
}
