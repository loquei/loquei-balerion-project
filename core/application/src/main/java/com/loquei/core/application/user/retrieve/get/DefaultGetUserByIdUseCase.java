package com.loquei.core.application.user.retrieve.get;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import java.util.function.Supplier;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    public DefaultGetUserByIdUseCase(final UserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public UserOutput execute(final String anId) {
        final var userId = UserId.from(anId);
        return this.userGateway.findById(userId).map(this::build).orElseThrow(notFound(userId));
    }

    private Supplier<NotFoundException> notFound(final UserId id) {
        return () -> NotFoundException.with(User.class, id);
    }

    private UserOutput build(final User user) {
        final var score = this.userGateway.retrieveUserTotalScore(user.getId());
        final var feedbackCount = this.userGateway.retrieveUserRatingCount(user.getId());
        final var rentalsCount = this.userGateway.retrieveUserRentalsCount(user.getId());

        return UserOutput.from(user, score, feedbackCount, rentalsCount);
    }
}
