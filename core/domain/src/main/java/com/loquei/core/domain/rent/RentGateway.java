package com.loquei.core.domain.rent;

import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;
import com.loquei.core.domain.user.UserId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface RentGateway {

    Rent rent(Rent rent);

    Rent updateRentalStatus(RentStatus status);

    Rent updateRentalDate (final LocalDateTime startDate, final LocalDateTime endDate, final BigDecimal totalValue);

    Rent cancelRental(RentStatus status, String cancellationReason);

    Optional<Rent> findById(RentId rentId);

    Pagination<Rent> findAllByUserId(UserId userId, SearchQuery query);

}
