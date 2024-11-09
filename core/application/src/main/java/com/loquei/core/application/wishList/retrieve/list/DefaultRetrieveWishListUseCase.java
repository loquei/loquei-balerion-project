package com.loquei.core.application.wishList.retrieve.list;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.wishList.WishListGateway;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class DefaultRetrieveWishListUseCase extends WishListRetrieveUseCase{

    private final WishListGateway wishListGateway;
    private final UserGateway userGateway;

    public DefaultRetrieveWishListUseCase(final WishListGateway wishListGateway,
                                          final UserGateway userGateway) {
        this.wishListGateway = requireNonNull(wishListGateway);
        this.userGateway = requireNonNull(userGateway);

    }

    @Override
    public Pagination<WishListRetrieveOutput> execute(String anIn) {

        final var userId = UserId.from(anIn);

        final var user = userGateway.findById(userId).orElseThrow(notFound(userId));

        return wishListGateway.findAllByUserId(user.getId()).map(WishListRetrieveOutput::from);
    }

    private Supplier<NotFoundException> notFound(final UserId id) {
        return () -> NotFoundException.with(id, User.class);
    }
}
