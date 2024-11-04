package com.loquei.core.infrastructure.item.presenter;

import com.loquei.core.application.item.retrieve.by.category.ItemListByCategoryOutput;
import com.loquei.core.application.item.retrieve.get.ItemOutput;
import com.loquei.core.application.item.retrieve.list.ItemListOutput;
import com.loquei.core.infrastructure.item.models.ItemListResponse;
import com.loquei.core.infrastructure.item.models.ItemResponse;

public interface ItemApiPresenter {
    static ItemListResponse present(final ItemListOutput output) {
        return new ItemListResponse(
                output.id(),
                output.name(),
                output.description(),
                output.dailyValue(),
                output.maxDays(),
                output.minDays(),
                output.userId(),
                output.updatedAt());
    }

    static ItemListResponse present(final ItemListByCategoryOutput output) {
        return new ItemListResponse(
                output.id(),
                output.name(),
                output.description(),
                output.dailyValue(),
                output.maxDays(),
                output.minDays(),
                output.userId(),
                output.updatedAt());
    }

    static ItemResponse present(final ItemOutput output) {
        return new ItemResponse(
                output.id(),
                output.name(),
                output.description(),
                output.dailyValue(),
                output.maxDays(),
                output.minDays(),
                output.userId(),
                output.createdAt(),
                output.updatedAt());
    }
}
