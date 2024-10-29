package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.item.image.retrieve.view.DefaultViewItemImageUseCase;
import com.loquei.core.application.user.image.create.CreateUserImageUseCase;
import com.loquei.core.application.user.image.create.DefaultCreateUserImageUseCase;
import com.loquei.core.application.user.image.delete.DefaultDeleteUserImageUseCase;
import com.loquei.core.application.user.image.delete.DeleteUserImageUseCase;
import com.loquei.core.application.user.image.retrieve.view.DefaultViewUserImageUseCase;
import com.loquei.core.application.user.image.retrieve.view.ViewUserImageUseCase;
import com.loquei.core.infrastructure.user.UserPostgresGateway;
import com.loquei.core.infrastructure.user.image.UserImagePostgresGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserImageUseCaseConfig {

    private final UserPostgresGateway userPostgresGateway;
    private final UserImagePostgresGateway userImagePostgresGateway;

    public UserImageUseCaseConfig(
            final UserPostgresGateway userPostgresGateway,
            final UserImagePostgresGateway userImagePostgresGateway
    ) {
        this.userPostgresGateway = userPostgresGateway;
        this.userImagePostgresGateway = userImagePostgresGateway;
    }

    @Bean
    public CreateUserImageUseCase createUserImageUseCase() {
        return new DefaultCreateUserImageUseCase(userPostgresGateway, userImagePostgresGateway);
    }

    @Bean
    public ViewUserImageUseCase viewUserImageUseCase() {
        return new DefaultViewUserImageUseCase(userImagePostgresGateway);
    }

    @Bean
    public DeleteUserImageUseCase deleteUserImageUseCase() {
        return new DefaultDeleteUserImageUseCase(userImagePostgresGateway);
    }

}
