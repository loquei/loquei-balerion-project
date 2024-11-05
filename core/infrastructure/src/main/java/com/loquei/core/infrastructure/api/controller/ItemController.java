package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.item.create.CreateItemCommand;
import com.loquei.core.application.item.create.CreateItemOutput;
import com.loquei.core.application.item.create.CreateItemUseCase;
import com.loquei.core.application.item.delete.DeleteItemUseCase;
import com.loquei.core.application.item.retrieve.by.category.ListItemsByCategoryParams;
import com.loquei.core.application.item.retrieve.by.category.ListItemsByCategoryUseCase;
import com.loquei.core.application.item.retrieve.get.GetItemByIdUseCase;
import com.loquei.core.application.item.retrieve.list.ListItemsParams;
import com.loquei.core.application.item.retrieve.list.ListItemsUseCase;
import com.loquei.core.application.item.update.UpdateItemCommand;
import com.loquei.core.application.item.update.UpdateItemOutput;
import com.loquei.core.application.item.update.UpdateItemUseCase;
import com.loquei.core.infrastructure.api.ItemAPI;
import com.loquei.core.infrastructure.item.models.CreateItemRequest;
import com.loquei.core.infrastructure.item.models.ItemListResponse;
import com.loquei.core.infrastructure.item.models.ItemResponse;
import com.loquei.core.infrastructure.item.models.UpdateItemRequest;
import com.loquei.core.infrastructure.item.presenter.ItemApiPresenter;
import java.net.URI;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController implements ItemAPI {

    private final CreateItemUseCase createItemUseCase;
    private final UpdateItemUseCase updateItemUseCase;
    private final DeleteItemUseCase deleteItemUseCase;
    private final GetItemByIdUseCase getItemByIdUseCase;
    private final ListItemsUseCase listItemsUseCase;
    private final ListItemsByCategoryUseCase listItemsByCategoryUseCase;

    public ItemController(
            final CreateItemUseCase createItemUseCase,
            final UpdateItemUseCase updateItemUseCase,
            final DeleteItemUseCase deleteItemUseCase,
            final GetItemByIdUseCase getItemByIdUseCase,
            final ListItemsUseCase listItemsUseCase,
            final ListItemsByCategoryUseCase listItemsByCategoryUseCase) {
        this.createItemUseCase = createItemUseCase;
        this.updateItemUseCase = updateItemUseCase;
        this.deleteItemUseCase = deleteItemUseCase;
        this.getItemByIdUseCase = getItemByIdUseCase;
        this.listItemsUseCase = listItemsUseCase;
        this.listItemsByCategoryUseCase = listItemsByCategoryUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateItemRequest input) {
        final var aCommand = CreateItemCommand.with(
                input.name(),
                input.description(),
                input.dailyValue(),
                input.maxDays(),
                input.minDays(),
                input.userId(),
                input.categories());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateItemOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/items/" + output.id())).body(output);

        return this.createItemUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<ItemListResponse> list(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction,
            final Boolean recentlyViewed,
            final String userEmail,
            final String ownerEmail) {

        final var query = new SearchQuery(page, perPage, search, sort, direction);
        return listItemsUseCase
                .execute(ListItemsParams.with(userEmail, recentlyViewed, ownerEmail, query))
                .map(ItemApiPresenter::present);
    }

    @Override
    public Pagination<ItemListResponse> listByCategory(
            final String categoryId,
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction) {
        final var query = new SearchQuery(page, perPage, search, sort, direction);
        return listItemsByCategoryUseCase
                .execute(ListItemsByCategoryParams.with(categoryId, query))
                .map(ItemApiPresenter::present);
    }

    @Override
    public ItemResponse getById(final String id) {
        return ItemApiPresenter.present(this.getItemByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> update(final String id, final UpdateItemRequest input) {
        final var aCommand = UpdateItemCommand.with(
                id,
                input.name(),
                input.description(),
                input.dailyValue(),
                input.maxDays(),
                input.minDays(),
                input.categories());

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateItemOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateItemUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteItemUseCase.execute(id);
    }
}
