package com.loquei.core.infrastructure.security;

import static java.util.Objects.requireNonNull;

import com.loquei.core.domain.security.SecurityCoreUser;
import com.loquei.core.domain.security.SecurityCoreUserGateway;
import com.loquei.core.infrastructure.security.facade.SecurityUserFacade;
import com.loquei.core.infrastructure.security.user.models.SecurityCreateUserRequest;
import com.loquei.core.infrastructure.security.user.models.SecurityUpdateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserFacadeGateway implements SecurityCoreUserGateway {

    private final SecurityUserFacade securityUserFacade;

    public SecurityUserFacadeGateway(final SecurityUserFacade securityUserFacade) {
        this.securityUserFacade = requireNonNull(securityUserFacade);
    }

    @Override
    public SecurityCoreUser create(final SecurityCoreUser input) {
        final var request = new SecurityCreateUserRequest(input.username(), input.email());

        final var response = securityUserFacade.create(request);

        return new SecurityCoreUser(response.username(), response.email());
    }

    @Override
    public SecurityCoreUser getById(final String id) {
        final var response = securityUserFacade.getById(id);

        return new SecurityCoreUser(response.username(), response.email());
    }

    @Override
    public SecurityCoreUser getByEmail(final String email) {
        final var response = securityUserFacade.getByEmail(email);

        return new SecurityCoreUser(response.username(), response.email());
    }

    @Override
    public SecurityCoreUser update(final String id, final SecurityCoreUser input) {
        final var request = new SecurityUpdateUserRequest(input.username(), input.email());

        final var response = securityUserFacade.update(id, request);

        return new SecurityCoreUser(response.username(), response.email());
    }

    @Override
    public void deleteByEmail(final String email) {
        this.securityUserFacade.deleteByEmail(email);
    }
}
