package com.loquei.core.application.user.delete;

import com.loquei.core.domain.security.SecurityCoreUserGateway;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteUserUseCase extends DeleteUserUseCase {

    private final UserGateway userGateway;
    private final SecurityCoreUserGateway securityUserGateway;

    public DefaultDeleteUserUseCase(final UserGateway userGateway, final SecurityCoreUserGateway securityUserGateway) {
        this.userGateway = requireNonNull(userGateway);
        this.securityUserGateway = requireNonNull(securityUserGateway);
    }

    @Override
    public void execute(final String anId) {
        this.securityUserGateway.deleteById(anId);
        this.userGateway.delete(UserId.from(anId));
    }
}
