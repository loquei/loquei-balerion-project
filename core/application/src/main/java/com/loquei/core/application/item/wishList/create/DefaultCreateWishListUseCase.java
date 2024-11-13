package com.loquei.core.application.item.wishList.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.AggregateRoot;
import com.loquei.common.Identifier;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.item.wishList.WishList;
import com.loquei.core.domain.item.wishList.WishListGateway;

import io.vavr.control.Either;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class DefaultCreateWishListUseCase extends CreateWishListUseCase {

    private final ItemGateway itemGateway;
    private final UserGateway userGateway;
    private final WishListGateway wishlistGateway;

    public DefaultCreateWishListUseCase(
            final ItemGateway itemGateway,
            final UserGateway userGateway,
            final WishListGateway wishlistGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.userGateway = Objects.requireNonNull(userGateway);
        this.wishlistGateway = Objects.requireNonNull(wishlistGateway);
    }

    @Override
    public Either<Notification, CreateWishListOutput> execute(final CreateWishListCommand aCommand) {
        final var userId = UserId.from(aCommand.userId());
        final var itemId = ItemId.from(aCommand.itemId());

        final var user = this.userGateway.findById(userId).orElseThrow(notFound(User.class, userId));
        final var item = this.itemGateway.findById(itemId).orElseThrow(notFound(Item.class, itemId));


        final var notification = Notification.create();

        final var wishlist = WishList.newWishList(user.getId(), item.getId());

        ifExists(userId, itemId).ifPresent(notification::append);

        wishlist.validate(notification);

        return notification.hasError() ? Left(notification) : create(wishlist);
    }

    private Either<Notification, CreateWishListOutput> create(WishList aWishlist) {
        return Try(() -> this.wishlistGateway.addToWishlist(aWishlist))
                .toEither()
                .bimap(Notification::create, CreateWishListOutput::from);
    }


    private Optional<Error> ifExists(UserId userId, ItemId itemId) {
        return !wishlistGateway.existsWishListByUserIdAndItemId(userId, itemId) ? Optional.empty() :
                Optional.of(new Error( "This item has already been added to your wish list"));
    }


    private Supplier<NotFoundException> notFound(
            final Class<? extends AggregateRoot<? extends Identifier>> clazz, final Identifier anId) {
        return () -> NotFoundException.with(clazz, anId);
    }
}
