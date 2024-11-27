package com.loquei.core.domain.rent;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentGateway {

    Rent rent(Rent rent);

    Rent update(Rent rent);

    List<Rent> findAll();

    Optional<Rent> findById(RentId rentId);

    Pagination<Rent> findAllByUserId(UserId userId);

    boolean isItemAvailableForRent(ItemId itemId, LocalDateTime startDate, LocalDateTime endDate);

    List<Rent> findConflictingPendingRentals(UserId userId, ItemId itemId, LocalDateTime startDate, LocalDateTime endDate);

}
