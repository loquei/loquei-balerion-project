package com.loquei.core.application.security.auth.authenticate;

import static com.loquei.common.utils.TimeUtils.hoursToMillis;
import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.AuthException;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.utils.InstantUtils;
import com.loquei.core.domain.security.auth.SecurityAuth;
import com.loquei.core.domain.security.auth.SecurityAuthCode;
import com.loquei.core.domain.security.auth.SecurityAuthGateway;
import com.loquei.core.domain.security.token.SecurityTokenService;
import java.util.Objects;

public class SecurityDefaultAuthenticateUseCase extends SecurityAuthenticateUseCase {

    private static final long EXPIRATION_TIME = hoursToMillis(24);

    private final SecurityAuthGateway authGateway;
    private final SecurityTokenService tokenService;

    public SecurityDefaultAuthenticateUseCase(
            final SecurityAuthGateway authGateway, final SecurityTokenService tokenService) {
        this.authGateway = requireNonNull(authGateway);
        this.tokenService = requireNonNull(tokenService);
    }

    @Override
    public String execute(final SecurityAuthenticateCommand aCommand) {
        final var email = aCommand.email();
        final var authCode = SecurityAuthCode.from(aCommand.authCode());

        final var auth = this.authGateway
                .findMostRecentByEmail(email)
                .orElseThrow(() -> NotFoundException.with(SecurityAuth.class, email));

        if (auth.getExpiresAt().isBefore(InstantUtils.now())) {
            throw AuthException.with(email);
        }

        if (!Objects.equals(auth.getAuthCode().getValue(), authCode.getValue())) {
            throw AuthException.with(email);
        }

        this.authGateway.delete(auth.getId());

        return this.tokenService.generateToken(email, EXPIRATION_TIME);
    }
}
