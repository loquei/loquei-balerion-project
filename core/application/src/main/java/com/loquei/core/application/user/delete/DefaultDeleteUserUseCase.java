package com.loquei.core.application.user.delete;

import static java.util.Objects.requireNonNull;

import com.loquei.core.domain.security.SecurityCoreUserGateway;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;

public class DefaultDeleteUserUseCase extends DeleteUserUseCase {

    private final UserGateway userGateway;
    private final SecurityCoreUserGateway securityUserGateway;

    public DefaultDeleteUserUseCase(final UserGateway userGateway, final SecurityCoreUserGateway securityUserGateway) {
        this.userGateway = requireNonNull(userGateway);
        this.securityUserGateway = requireNonNull(securityUserGateway);
    }

    @Override
    public void execute(final String anId) {
        final var user = this.userGateway.findById(UserId.from(anId)).orElse(null);

        if (user == null) return;

        this.securityUserGateway.deleteByEmail(user.getEmail());
        this.userGateway.delete(UserId.from(anId));
    }
}
