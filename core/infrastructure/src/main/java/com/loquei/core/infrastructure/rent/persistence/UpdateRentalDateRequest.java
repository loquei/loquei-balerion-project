package com.loquei.core.infrastructure.rent.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.RentId;

import java.time.LocalDateTime;

public record UpdateRentalDateRequest(
        @JsonProperty("id") RentId rentId,
        @JsonProperty("item") ItemId itemId,
        @JsonProperty("startDate") LocalDateTime startDate,
        @JsonProperty("endDate") LocalDateTime endDate
) {
}
