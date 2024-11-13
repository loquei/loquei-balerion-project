package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.item.wishList.create.CreateWishListCommand;
import com.loquei.core.application.item.wishList.create.CreateWishListOutput;
import com.loquei.core.application.item.wishList.create.CreateWishListUseCase;
import com.loquei.core.application.item.wishList.delete.DeleteWishListCommand;
import com.loquei.core.application.item.wishList.delete.DeleteWishListUseCase;
import com.loquei.core.infrastructure.api.WishListAPI;
import com.loquei.core.infrastructure.item.wishList.model.CreateWishListRequest;
import com.loquei.core.infrastructure.item.wishList.model.DeleteWishListRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@RestController
public class WishListController implements WishListAPI {

    private final CreateWishListUseCase createWishListUseCase;
    private final DeleteWishListUseCase deleteWishListUseCase;

    public WishListController(
            final CreateWishListUseCase createWishListUseCase,
            final DeleteWishListUseCase deleteWishListUseCase) {
        this.createWishListUseCase = requireNonNull(createWishListUseCase);
        this.deleteWishListUseCase = requireNonNull(deleteWishListUseCase);
    }

    @Override
    public ResponseEntity<?> create(CreateWishListRequest input) {
        final var aCommand = CreateWishListCommand.with(
                input.userId(),
                input.itemId());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateWishListOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/items/" + output.id())).body(output);

        return this.createWishListUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(DeleteWishListRequest input) {
        final var acommand = DeleteWishListCommand.with(
                input.userId(),
                input.itemId()
        );

        deleteWishListUseCase.execute(acommand);
    }


}
