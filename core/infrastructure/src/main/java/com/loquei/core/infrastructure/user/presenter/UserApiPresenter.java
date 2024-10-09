package com.loquei.core.infrastructure.user.presenter;

import com.loquei.core.application.user.retrieve.get.UserOutput;
import com.loquei.core.application.user.retrieve.list.UserListOutput;
import com.loquei.core.infrastructure.user.models.UserListResponse;
import com.loquei.core.infrastructure.user.models.UserResponse;

public interface UserApiPresenter {
    static UserListResponse present(final UserListOutput output) {
        return new UserListResponse(
                output.id(),
                output.userName(),
                output.personalName(),
                output.email(),
                output.phone());
    }

    static UserResponse present(final UserOutput output) {
        return new UserResponse(
                output.id(),
                output.userName(),
                output.personalName(),
                output.email(),
                output.phone(),
                output.document(),
                output.birth(),
                output.createdAt(),
                output.updatedAt());
    }
}
