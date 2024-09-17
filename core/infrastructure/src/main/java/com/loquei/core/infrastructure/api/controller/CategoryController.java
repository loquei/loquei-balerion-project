package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.category.create.CreateCategoryCommand;
import com.loquei.core.application.category.create.CreateCategoryOutput;
import com.loquei.core.application.category.create.CreateCategoryUseCase;
import com.loquei.core.application.category.delete.DeleteCategoryUseCase;
import com.loquei.core.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.loquei.core.application.category.retrieve.list.ListCategoriesUseCase;
import com.loquei.core.application.category.update.UpdateCategoryCommand;
import com.loquei.core.application.category.update.UpdateCategoryOutput;
import com.loquei.core.application.category.update.UpdateCategoryUseCase;
import com.loquei.core.infrastructure.api.CategoryAPI;
import com.loquei.core.infrastructure.category.models.CategoryListResponse;
import com.loquei.core.infrastructure.category.models.CategoryResponse;
import com.loquei.core.infrastructure.category.models.CreateCategoryRequest;
import com.loquei.core.infrastructure.category.models.UpdateCategoryRequest;
import com.loquei.core.infrastructure.category.presenter.CategoryApiPresenter;
import java.net.URI;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final ListCategoriesUseCase listCategoriesUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoriesUseCase = Objects.requireNonNull(listCategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(), input.description(), Objects.nonNull(input.active()) ? input.active() : true);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<CategoryListResponse> list(
            final String search, final int page, final int perPage, final String sort, final String direction) {
        return listCategoriesUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CategoryApiPresenter::present);
    }

    @Override
    public CategoryResponse getById(final String anId) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(anId));
    }

    @Override
    public ResponseEntity<?> updateById(final String anId, final UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(
                anId, input.name(), input.description(), Objects.nonNull(input.active()) ? input.active() : true);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteCategoryUseCase.execute(anId);
    }
}
