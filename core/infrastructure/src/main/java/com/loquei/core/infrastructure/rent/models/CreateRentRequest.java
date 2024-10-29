package com.loquei.core.infrastructure.rent.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateRentRequest(
        @JsonProperty("lessor") String lessor,
        @JsonProperty("lessee") String lessee,
        @JsonProperty("item") String item,
        @JsonProperty("start_date") LocalDateTime startDate,
        @JsonProperty("end_date") LocalDateTime endDate,
        @JsonProperty("total_value") BigDecimal totalValue) {}
