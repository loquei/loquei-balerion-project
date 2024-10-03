package com.loquei.core.infrastructure.item.image.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateImageResponse(
        @JsonProperty("id") String id
) {
}
