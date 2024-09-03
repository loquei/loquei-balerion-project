package com.loquei.core.application.user.update;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.security.SecurityCoreUser;
import com.loquei.core.domain.security.SecurityCoreUserGateway;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;

import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultUpdateUserUseCase extends UpdateUserUseCase {

    private final UserGateway userGateway;
    private final SecurityCoreUserGateway securityUserGateway;

    public DefaultUpdateUserUseCase(final UserGateway userGateway, final SecurityCoreUserGateway securityUserGateway) {
        this.userGateway = requireNonNull(userGateway);
        this.securityUserGateway = requireNonNull(securityUserGateway);
    }

    @Override
    public Either<Notification, UpdateUserOutput> execute(final UpdateUserCommand aCommand) {
        final var id = UserId.from(aCommand.id());
        final var username = aCommand.userName();
        final var personalName = aCommand.personalName();
        final var email = aCommand.email();
        final var phone = aCommand.phone();
        final var document = aCommand.document();
        final var birth = aCommand.birth();

        final var user = this.userGateway.findById(id).orElseThrow(notFound(id));

        final var notification = Notification.create();
        user.update(username, personalName, email, phone, document, birth);
        user.validate(notification);

        if (this.userGateway.findByEmail(email).isPresent()) {
            notification.append(new Error("Email already exists"));
        }

        if (this.userGateway.findByDocument(document).isPresent()) {
            notification.append(new Error("Document already exists"));
        }

        if (this.userGateway.findByUsername(username).isPresent()) {
            notification.append(new Error("Username already exists"));
        }

        if (this.userGateway.findByPhone(phone).isPresent()) {
            notification.append(new Error("Phone already exists"));
        }

        return notification.hasError() ? Left(notification) : update(user);
    }

    private Either<Notification, UpdateUserOutput> update(final User user) {
        return Try(() -> this.updateUserWithSecurity(user))
                .toEither()
                .bimap(Notification::create, UpdateUserOutput::from);
    }

    private Supplier<NotFoundException> notFound(final UserId id) {
        return () -> NotFoundException.with(User.class, id);
    }

    private User updateUserWithSecurity(final User user) {
        final var securityUser = new SecurityCoreUser(user.getUserName(), user.getEmail());

        this.securityUserGateway.update(user.getId().getValue(), securityUser);

        return this.userGateway.update(user);
    }
}
