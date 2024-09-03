package com.loquei.core.infrastructure.security.api.controller;

import com.loquei.core.application.security.auth.authenticate.SecurityAuthenticateCommand;
import com.loquei.core.application.security.auth.authenticate.SecurityAuthenticateUseCase;
import com.loquei.core.application.security.auth.generate.SecurityGenerateAuthCodeUseCase;
import com.loquei.core.infrastructure.security.api.SecurityAuthAPI;
import com.loquei.core.infrastructure.security.auth.models.SecurityAuthenticateRequest;
import com.loquei.core.infrastructure.security.auth.models.SecurityAuthenticateResponse;
import com.loquei.core.infrastructure.security.auth.models.SecurityGenerateAuthCodeRequest;
import com.loquei.core.infrastructure.security.auth.models.SecurityGenerateAuthCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityAuthController implements SecurityAuthAPI {

    private final SecurityGenerateAuthCodeUseCase authRequestUseCase;
    private final SecurityAuthenticateUseCase generateAuthUseCase;

    @Override
    public SecurityGenerateAuthCodeResponse generateAuthCode(final SecurityGenerateAuthCodeRequest requestBody) {
        final var email = requestBody.email();
        final var token = authRequestUseCase.execute(email);
        return new SecurityGenerateAuthCodeResponse(token);
    }

    @Override
    public SecurityAuthenticateResponse authenticate(final SecurityAuthenticateRequest requestBody) {
        final var command = SecurityAuthenticateCommand.with(requestBody.email(), requestBody.code());
        final var token = this.generateAuthUseCase.execute(command);
        return new SecurityAuthenticateResponse(token);
    }
}
