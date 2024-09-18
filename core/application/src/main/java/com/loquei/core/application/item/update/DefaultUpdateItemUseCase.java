package com.loquei.core.application.item.update;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.exceptions.NotFoundException;
import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DefaultUpdateItemUseCase extends UpdateItemUseCase {

    private final ItemGateway itemGateway;
    private final CategoryGateway categoryGateway;

    public DefaultUpdateItemUseCase(final ItemGateway itemGateway, final CategoryGateway categoryGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateItemOutput> execute(final UpdateItemCommand aCommand) {
        final var itemId = ItemId.from(aCommand.id());
        final var name = aCommand.name();
        final var description = aCommand.description();
        final var dailyValue = aCommand.dailyValue();
        final var maxDays = aCommand.maxDays();
        final var minDays = aCommand.minDays();
        final var categories = toCategoryId(aCommand.categories());

        final var item = this.itemGateway.findById(itemId).orElseThrow(notFound(itemId));

        final var notification = Notification.create();
        notification.append(validateCategories(categories));
        item.update(name, description, dailyValue, maxDays, minDays, categories);
        item.validate(notification);

        return notification.hasError() ? Left(notification) : update(item);
    }

    private Either<Notification, UpdateItemOutput> update(final Item item) {
        return Try(() -> this.itemGateway.update(item)).toEither().bimap(Notification::create, UpdateItemOutput::from);
    }

    private Supplier<NotFoundException> notFound(final ItemId id) {
        return () -> NotFoundException.with(Item.class, id);
    }

    private ValidationHandler validateCategories(final List<CategoryId> ids) {
        final var notification = Notification.create();

        if (ids == null || ids.isEmpty()) return notification;

        final var retrievedIds = categoryGateway.existsByIds(ids);
        if (ids.size() != retrievedIds.size()) {
            final var commandIds = new ArrayList<>(ids);
            commandIds.removeAll(retrievedIds);

            final var missingIdsMessage =
                    commandIds.stream().map(CategoryId::getValue).collect(Collectors.joining(", "));

            notification.append(new Error("some categories could not be found: %s".formatted(missingIdsMessage)));
        }
        return notification;
    }

    private List<CategoryId> toCategoryId(final List<String> categories) {
        return categories.stream().map(CategoryId::from).toList();
    }
}
