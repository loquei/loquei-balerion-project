package com.loquei.core.infrastructure.rent.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record UpdateRentalDateRequest(
        @JsonProperty("id") String id,
        @JsonProperty("item") String item,
        @JsonProperty("start_date") LocalDateTime startDate,
        @JsonProperty("end_date") LocalDateTime endDate) {}
