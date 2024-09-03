package com.loquei.core.infrastructure.security.user.presenter;

import com.loquei.core.application.security.user.create.SecurityCreateUserOutput;
import com.loquei.core.application.security.user.retrieve.SecurityUserOutput;
import com.loquei.core.application.security.user.update.SecurityUpdateUserOutput;
import com.loquei.core.infrastructure.security.user.models.SecurityCreateUserResponse;
import com.loquei.core.infrastructure.security.user.models.SecurityGetUserResponse;
import com.loquei.core.infrastructure.security.user.models.SecurityUpdateUserResponse;

public interface SecurityUserApiPresenter {

    static SecurityGetUserResponse present(final SecurityUserOutput output) {
        return new SecurityGetUserResponse(
                output.id(), output.username(), output.email(), output.createdAt(), output.updatedAt());
    }

    static SecurityCreateUserResponse present(final SecurityCreateUserOutput createUserOutput) {
        return new SecurityCreateUserResponse(
                createUserOutput.id(), createUserOutput.username(), createUserOutput.email());
    }

    static SecurityUpdateUserResponse present(final SecurityUpdateUserOutput updateUserOutput) {
        return new SecurityUpdateUserResponse(
                updateUserOutput.id(), updateUserOutput.username(), updateUserOutput.email());
    }
}
