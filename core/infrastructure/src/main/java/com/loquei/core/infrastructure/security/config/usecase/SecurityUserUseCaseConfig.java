package com.loquei.core.infrastructure.security.config.usecase;

import com.loquei.core.application.security.user.create.SecurityCreateUserUseCase;
import com.loquei.core.application.security.user.create.SecurityDefaultCreateUserUseCase;
import com.loquei.core.application.security.user.delete.SecurityDefaultDeleteUserUseCase;
import com.loquei.core.application.security.user.delete.SecurityDeleteUserUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityDefaultGetUserByEmailUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityDefaultGetUserByIdUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityGetUserByEmailUseCase;
import com.loquei.core.application.security.user.retrieve.SecurityGetUserByIdUseCase;
import com.loquei.core.application.security.user.update.SecurityDefaultUpdateUserUseCase;
import com.loquei.core.application.security.user.update.SecurityUpdateUserUseCase;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityUserUseCaseConfig {

    private final SecurityUserGateway userGateway;

    @Bean
    public SecurityCreateUserUseCase securityCreateUserUseCase() {
        return new SecurityDefaultCreateUserUseCase(userGateway);
    }

    @Bean
    public SecurityUpdateUserUseCase securityUpdateUserUseCase() {
        return new SecurityDefaultUpdateUserUseCase(userGateway);
    }

    @Bean
    public SecurityDeleteUserUseCase securityDeleteUserUseCase() {
        return new SecurityDefaultDeleteUserUseCase(userGateway);
    }

    @Bean
    public SecurityGetUserByIdUseCase securityGetUserByIdUseCase() {
        return new SecurityDefaultGetUserByIdUseCase(userGateway);
    }

    @Bean
    public SecurityGetUserByEmailUseCase securityGetUserByEmailUseCase() {
        return new SecurityDefaultGetUserByEmailUseCase(userGateway);
    }
}
