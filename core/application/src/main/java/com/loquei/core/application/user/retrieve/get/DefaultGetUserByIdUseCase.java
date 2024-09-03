package com.loquei.core.application.user.retrieve.get;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    public DefaultGetUserByIdUseCase(final UserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public UserOutput execute(final String anId) {
        final var userId = UserId.from(anId);
        return this.userGateway.findById(userId).map(UserOutput::from).orElseThrow(notFound(userId));
    }

    private Supplier<NotFoundException> notFound(final UserId id) {
        return () -> NotFoundException.with(User.class, id);
    }
}
