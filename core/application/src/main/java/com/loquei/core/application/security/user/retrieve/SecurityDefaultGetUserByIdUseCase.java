package com.loquei.core.application.security.user.retrieve;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.domain.security.user.SecurityUserId;
import java.util.function.Supplier;

public class SecurityDefaultGetUserByIdUseCase extends SecurityGetUserByIdUseCase {

    private final SecurityUserGateway userGateway;

    public SecurityDefaultGetUserByIdUseCase(final SecurityUserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public SecurityUserOutput execute(final String anId) {
        final var userId = SecurityUserId.from(anId);
        return this.userGateway.findById(userId).map(SecurityUserOutput::from).orElseThrow(notFound(userId));
    }

    private Supplier<NotFoundException> notFound(final SecurityUserId id) {
        return () -> NotFoundException.with(SecurityUser.class, id);
    }
}
