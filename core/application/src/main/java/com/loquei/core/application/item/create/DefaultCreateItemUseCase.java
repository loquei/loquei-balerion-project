package com.loquei.core.application.item.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.handler.Notification;
import com.loquei.core.domain.category.CategoryGateway;
import com.loquei.core.domain.category.CategoryId;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.user.UserId;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCreateItemUseCase extends CreateItemUseCase {

    private final ItemGateway itemGateway;
    private final CategoryGateway categoryGateway;

    public DefaultCreateItemUseCase(final ItemGateway itemGateway, final CategoryGateway categoryGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateItemOutput> execute(final CreateItemCommand aCommand) {
        final var name = aCommand.name();
        final var description = aCommand.description();
        final var dailyValue = aCommand.dailyValue();
        final var maxDays = aCommand.maxDays();
        final var minDays = aCommand.minDays();
        final var userId = UserId.from(aCommand.userId());
        final var categories = toCategoryId(aCommand.categories());

        final var notification = Notification.create();
        notification.append(validateCategories(categories));
        final var item = Item.newItem(name, description, dailyValue, maxDays, minDays, userId);
        item.validate(notification);
        item.addCategories(categories);

        return notification.hasError() ? Left(notification) : create(item);
    }

    private Either<Notification, CreateItemOutput> create(final Item item) {
        return Try(() -> this.itemGateway.create(item)).toEither().bimap(Notification::create, CreateItemOutput::from);
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
