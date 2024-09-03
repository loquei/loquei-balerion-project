package com.loquei.core.application.user.create;

import com.loquei.common.validation.Error;
import com.loquei.core.domain.security.SecurityCoreUser;
import com.loquei.core.domain.security.SecurityCoreUserGateway;
import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.common.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import static java.util.Objects.requireNonNull;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final UserGateway userGateway;
    private final SecurityCoreUserGateway securityUserGateway;

    public DefaultCreateUserUseCase(final UserGateway userGateway, final SecurityCoreUserGateway securityUserGateway) {
        this.userGateway = requireNonNull(userGateway);
        this.securityUserGateway = requireNonNull(securityUserGateway);
    }

    @Override
    public Either<Notification, CreateUserOutput> execute(final CreateUserCommand aCommand) {
        final var username = aCommand.userName();
        final var personalName = aCommand.personalName();
        final var email = aCommand.email();
        final var phone = aCommand.phone();
        final var document = aCommand.document();
        final var birth = aCommand.birth();

        final var notification = Notification.create();
        final var user = User.newUser(username, personalName, email, phone, document, birth);
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

        return notification.hasError() ? Left(notification) : create(user);
    }

    private Either<Notification, CreateUserOutput> create(final User user) {
        return Try(() -> this.createUserWithSecurity(user))
                .toEither()
                .bimap(Notification::create, CreateUserOutput::from);
    }

    private User createUserWithSecurity(final User user) {
        final var securityUser = new SecurityCoreUser(user.getUserName(), user.getEmail());

        this.securityUserGateway.create(securityUser);

        return this.userGateway.create(user);
    }
}
