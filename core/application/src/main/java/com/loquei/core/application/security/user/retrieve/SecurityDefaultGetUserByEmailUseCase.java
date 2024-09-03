package com.loquei.core.application.security.user.retrieve;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class SecurityDefaultGetUserByEmailUseCase extends SecurityGetUserByEmailUseCase {

    private final SecurityUserGateway userGateway;

    public SecurityDefaultGetUserByEmailUseCase(final SecurityUserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public SecurityUserOutput execute(final String anEmail) {
        return this.userGateway
                .findByEmail(anEmail)
                .map(SecurityUserOutput::from)
                .orElseThrow(notFound(anEmail));
    }

    private Supplier<NotFoundException> notFound(final String id) {
        return () -> NotFoundException.with(SecurityUser.class, id);
    }
}
