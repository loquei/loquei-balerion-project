package com.loquei.core.infrastructure.security.api.controller;

import static com.loquei.core.infrastructure.security.user.presenter.SecurityUserApiPresenter.present;

import com.loquei.core.application.security.user.create.SecurityCreateUserCommand;
import com.loquei.core.application.security.user.create.SecurityCreateUserUseCase;
import com.loquei.core.application.security.user.delete.SecurityDeleteUserUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityGetUserByEmailUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityGetUserByIdUseCase;
import com.loquei.core.application.security.user.update.SecurityUpdateUserCommand;
import com.loquei.core.application.security.user.update.SecurityUpdateUserUseCase;
import com.loquei.core.infrastructure.security.api.SecurityUserAPI;
import com.loquei.core.infrastructure.security.user.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityUserController implements SecurityUserAPI {

    private final SecurityCreateUserUseCase securityCreateUserUseCase;
    private final SecurityUpdateUserUseCase securityUpdateUserUseCase;
    private final SecurityDeleteUserUseCase securityDeleteUserUseCase;
    private final SecurityGetUserByIdUseCase securityGetUserByIdUseCase;
    private final SecurityGetUserByEmailUseCase securityGetUserByEmailUseCase;

    @Override
    public SecurityCreateUserResponse create(final SecurityCreateUserRequest input) {
        final var command = SecurityCreateUserCommand.with(input.username(), input.email());

        return present(this.securityCreateUserUseCase.execute(command));
    }

    @Override
    public SecurityGetUserResponse getById(final String id) {
        return present(this.securityGetUserByIdUseCase.execute(id));
    }

    @Override
    public SecurityGetUserResponse getByEmail(final String email) {
        return present(this.securityGetUserByEmailUseCase.execute(email));
    }

    @Override
    public SecurityUpdateUserResponse update(final String id, final SecurityUpdateUserRequest input) {
        final var command = SecurityUpdateUserCommand.with(id, input.username(), input.email());
        return present(this.securityUpdateUserUseCase.execute(command));
    }

    @Override
    public void deleteById(final String id) {
        this.securityDeleteUserUseCase.execute(id);
    }
}
