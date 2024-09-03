package com.loquei.core.application.security.user.create;

import com.loquei.common.exceptions.NotificationException;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;

import static java.util.Objects.requireNonNull;

public class SecurityDefaultCreateUserUseCase extends SecurityCreateUserUseCase {

    private final SecurityUserGateway userGateway;

    public SecurityDefaultCreateUserUseCase(final SecurityUserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public SecurityCreateUserOutput execute(final SecurityCreateUserCommand aCommand) {
        final var username = aCommand.username();
        final var email = aCommand.email();

        final var notification = Notification.create();
        final var user = SecurityUser.newUser(username, email);
        user.validate(notification);

        if (notification.hasError()) notify(notification);

        return SecurityCreateUserOutput.from(this.userGateway.create(user));
    }

    private void notify(final Notification notification) {
        throw new NotificationException("could not create an security aggregate user", notification);
    }
}
