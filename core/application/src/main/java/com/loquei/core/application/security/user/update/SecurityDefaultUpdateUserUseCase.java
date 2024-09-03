package com.loquei.core.application.security.user.update;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.exceptions.NotificationException;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.domain.security.user.SecurityUserId;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class SecurityDefaultUpdateUserUseCase extends SecurityUpdateUserUseCase {

    private final SecurityUserGateway userGateway;

    public SecurityDefaultUpdateUserUseCase(final SecurityUserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public SecurityUpdateUserOutput execute(final SecurityUpdateUserCommand aCommand) {
        final var userId = SecurityUserId.from(aCommand.id());
        final var username = aCommand.username();
        final var email = aCommand.email();

        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        final var notification = Notification.create();
        user.update(username, email);
        user.validate(notification);

        if (notification.hasError()) notify(notification);

        return SecurityUpdateUserOutput.from(this.userGateway.update(user));
    }

    private Supplier<NotFoundException> notFound(final SecurityUserId id) {
        return () -> NotFoundException.with(SecurityUser.class, id);
    }

    private void notify(final Notification notification) {
        throw new NotificationException("could not create an security aggregate user", notification);
    }
}
