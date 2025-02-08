package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorValidationResponseDTO(
    @Schema(name = "message", example = "must be a well-formed email address")
    @JsonProperty("message")
    String message,
    @Schema(name = "field", example = "email")
    @JsonProperty("field")
    String field,
    @Schema(name = "value", example = "email#email.com")
    @JsonProperty("value")
    String value
) {
}
