package com.loquei.core.application.security.auth.generate;

import static com.loquei.common.utils.TimeUtils.minutesToMillis;
import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.email.Email;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.security.auth.SecurityAuth;
import com.loquei.core.domain.security.auth.SecurityAuthGateway;
import com.loquei.core.domain.security.token.SecurityTokenService;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import java.util.concurrent.CompletableFuture;

public class SecurityDefaultGenerateAuthCodeUseCase extends SecurityGenerateAuthCodeUseCase {

    private static final long EXPIRATION_TIME = minutesToMillis(5);

    private final SecurityUserGateway userGateway;
    private final SecurityAuthGateway authGateway;
    private final EmailGateway emailGateway;
    private final SecurityTokenService tokenService;

    public SecurityDefaultGenerateAuthCodeUseCase(
            final SecurityUserGateway userGateway,
            final SecurityAuthGateway authGateway,
            final EmailGateway emailGateway,
            final SecurityTokenService tokenService) {
        this.userGateway = requireNonNull(userGateway);
        this.authGateway = requireNonNull(authGateway);
        this.emailGateway = requireNonNull(emailGateway);
        this.tokenService = requireNonNull(tokenService);
    }

    @Override
    public String execute(final String userEmail) {
        final var user = this.userGateway
                .findByEmail(userEmail)
                .orElseThrow(() -> NotFoundException.with(SecurityUser.class, userEmail));

        final var authToken = this.tokenService.generateToken(user.getEmail(), EXPIRATION_TIME);

        final var auth = SecurityAuth.newAuth(user.getEmail(), authToken);

        CompletableFuture.runAsync(() -> this.authGateway.create(auth));

        final var email = buildEmail(user, auth);

        CompletableFuture.runAsync(() -> this.emailGateway.send(email));

        return authToken;
    }

    private Email buildEmail(final SecurityUser user, final SecurityAuth auth) {
        final var email = user.getEmail();
        final var emailSubject = buildEmailSubject();
        final var emailBody = buildEmailBody(user, auth);
        return Email.newEmail(email, emailSubject, emailBody);
    }

    private String buildEmailSubject() {
        return "Autenticação Plataforma Loquei!";
    }

    private String buildEmailBody(final SecurityUser user, final SecurityAuth auth) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Olá ").append(user.getUsername()).append(", somos a Loquei!\n\n");
        sb.append("Para se autenticar no nosso App, basta utilizar o código abaixo:\n");
        sb.append(auth.getAuthCode().getValue());
        sb.append("\n\n");
        sb.append("Atenciosamente,\n");
        sb.append("Equipe de Segurança, Loquei.");

        return sb.toString();
    }
}
