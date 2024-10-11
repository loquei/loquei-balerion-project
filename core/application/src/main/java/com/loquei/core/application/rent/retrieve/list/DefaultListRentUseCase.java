package com.loquei.core.application.rent.retrieve.list;

import static java.util.Objects.requireNonNull;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import java.util.function.Supplier;

public class DefaultListRentUseCase extends ListRentUseCase {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;

    public DefaultListRentUseCase(final RentGateway rentGateway, final UserGateway userGateway) {
        this.rentGateway = requireNonNull(rentGateway);
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public Pagination<ListRentOutput> execute(String anIn) {

        final var userId = UserId.from(anIn);

        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        return rentGateway.findAllByUserId(user.getId()).map(ListRentOutput::from);
    }

    private Supplier<NotFoundException> notFound(final UserId id) {
        return () -> NotFoundException.with(id, User.class);
    }
}
