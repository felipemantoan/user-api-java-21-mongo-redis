package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorValidationResponseDTO(
    @JsonProperty("message") String message,
    @JsonProperty("field") String field,
    @JsonProperty("value") String value
) {
}
