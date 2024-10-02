package com.loquei.core.infrastructure.rent.presenter;

import com.loquei.core.application.rent.retrieve.get.GetRentOutput;
import com.loquei.core.application.rent.retrieve.list.ListRentOutput;
import com.loquei.core.infrastructure.rent.persistence.RentListResponse;
import com.loquei.core.infrastructure.rent.persistence.RentResponse;

public interface RentApiPresenter {

    static RentListResponse present(final ListRentOutput output) {
        return new RentListResponse(
                output.id(),
                output.lessorId(),
                output.lesseeId(),
                output.itemId(),
                output.startDate(),
                output.endDate(),
                output.totalValue(),
                output.status()
        );
    }

    static RentResponse present(final GetRentOutput output) {
        return new RentResponse(
                output.id(),
                output.lessorId(),
                output.lesseeId(),
                output.itemId(),
                output.startDate(),
                output.endDate(),
                output.totalValue(),
                output.status(),
                output.cancellationReason(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
