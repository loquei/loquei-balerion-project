package com.loquei.core.application.user.retrieve.list;

import static java.util.Objects.requireNonNull;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.user.UserGateway;

public class DefaultListUsersUseCase extends ListUsersUseCase {

    private final UserGateway userGateway;

    public DefaultListUsersUseCase(final UserGateway userGateway) {
        this.userGateway = requireNonNull(userGateway);
    }

    @Override
    public Pagination<UserListOutput> execute(final SearchQuery aQuery) {
        return this.userGateway.findAll(aQuery).map(UserListOutput::from);
    }
}
