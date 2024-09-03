package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.user.create.CreateUserCommand;
import com.loquei.core.application.user.create.CreateUserOutput;
import com.loquei.core.application.user.create.CreateUserUseCase;
import com.loquei.core.application.user.delete.DeleteUserUseCase;
import com.loquei.core.application.user.retrieve.get.GetUserByIdUseCase;
import com.loquei.core.application.user.retrieve.list.ListUsersUseCase;
import com.loquei.core.application.user.update.UpdateUserCommand;
import com.loquei.core.application.user.update.UpdateUserOutput;
import com.loquei.core.application.user.update.UpdateUserUseCase;
import com.loquei.core.infrastructure.api.UserAPI;
import com.loquei.core.infrastructure.user.models.CreateUserRequest;
import com.loquei.core.infrastructure.user.models.UpdateUserRequest;
import com.loquei.core.infrastructure.user.models.UserListResponse;
import com.loquei.core.infrastructure.user.models.UserResponse;
import com.loquei.core.infrastructure.user.presenter.UserApiPresenter;
import java.net.URI;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(
            final CreateUserUseCase createUserUseCase,
            final UpdateUserUseCase updateUserUseCase,
            final DeleteUserUseCase deleteUserUseCase,
            final ListUsersUseCase listUsersUseCase,
            final GetUserByIdUseCase getUserByIdUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateUserRequest input) {
        final var aCommand = CreateUserCommand.with(
                input.userName(), input.personalName(), input.email(), input.phone(), input.document(), input.birth());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateUserOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/users/" + output.id())).body(output);

        return this.createUserUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<UserListResponse> list(
            final String search, final int page, final int perPage, final String sort, final String direction) {
        return listUsersUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(UserApiPresenter::present);
    }

    @Override
    public UserResponse getById(final String id) {
        return UserApiPresenter.present(this.getUserByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> update(final String id, final UpdateUserRequest input) {
        final var aCommand = UpdateUserCommand.with(
                id,
                input.userName(),
                input.personalName(),
                input.email(),
                input.phone(),
                input.document(),
                input.birth());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateUserOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateUserUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteUserUseCase.execute(id);
    }
}
