package com.loquei.core.application.security.user.delete;

import static java.util.Objects.requireNonNull;

import com.loquei.core.domain.security.user.SecurityUserGateway;

public class SecurityDefaultDeleteUserUseCase extends SecurityDeleteUserUseCase {

    private final SecurityUserGateway userGateway;

    public SecurityDefaultDeleteUserUseCase(final SecurityUserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public void execute(final String email) {
        this.userGateway.deleteByEmail(email);
    }
}
