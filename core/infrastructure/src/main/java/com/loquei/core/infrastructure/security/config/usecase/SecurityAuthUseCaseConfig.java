package com.loquei.core.infrastructure.security.config.usecase;

import com.loquei.core.application.security.auth.authenticate.SecurityAuthenticateUseCase;
import com.loquei.core.application.security.auth.authenticate.SecurityDefaultAuthenticateUseCase;
import com.loquei.core.application.security.auth.generate.SecurityDefaultGenerateAuthCodeUseCase;
import com.loquei.core.application.security.auth.generate.SecurityGenerateAuthCodeUseCase;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.security.auth.SecurityAuthGateway;
import com.loquei.core.domain.security.token.SecurityTokenService;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityAuthUseCaseConfig {

    private final SecurityUserGateway userGateway;
    private final SecurityAuthGateway authGateway;
    private final SecurityTokenService tokenService;
    private final EmailGateway emailGateway;

    @Bean
    public SecurityGenerateAuthCodeUseCase generateAuthCodeUseCase() {
        return new SecurityDefaultGenerateAuthCodeUseCase(userGateway, authGateway, emailGateway, tokenService);
    }

    @Bean
    public SecurityAuthenticateUseCase authenticateUseCase() {
        return new SecurityDefaultAuthenticateUseCase(authGateway, tokenService);
    }
}
