package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.user.create.CreateUserUseCase;
import com.loquei.core.application.user.create.DefaultCreateUserUseCase;
import com.loquei.core.application.user.delete.DefaultDeleteUserUseCase;
import com.loquei.core.application.user.delete.DeleteUserUseCase;
import com.loquei.core.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import com.loquei.core.application.user.retrieve.get.GetUserByIdUseCase;
import com.loquei.core.application.user.retrieve.list.DefaultListUsersUseCase;
import com.loquei.core.application.user.retrieve.list.ListUsersUseCase;
import com.loquei.core.application.user.update.DefaultUpdateUserUseCase;
import com.loquei.core.application.user.update.UpdateUserUseCase;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.infrastructure.security.SecurityUserFacadeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {

    private final UserGateway userGateway;
    private final SecurityUserFacadeGateway securityUserFacadeGateway;

    public UserUseCaseConfig(final UserGateway userGateway, final SecurityUserFacadeGateway securityUserFacadeGateway) {
        this.userGateway = userGateway;
        this.securityUserFacadeGateway = securityUserFacadeGateway;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new DefaultCreateUserUseCase(userGateway, securityUserFacadeGateway);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase() {
        return new DefaultUpdateUserUseCase(userGateway, securityUserFacadeGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase() {
        return new DefaultDeleteUserUseCase(userGateway, securityUserFacadeGateway);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase() {
        return new DefaultListUsersUseCase(userGateway);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase() {
        return new DefaultGetUserByIdUseCase(userGateway);
    }
}
