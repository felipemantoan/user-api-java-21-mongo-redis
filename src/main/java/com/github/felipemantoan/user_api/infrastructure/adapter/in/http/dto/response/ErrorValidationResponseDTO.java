package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorValidationResponseDTO(
    @Schema(name = "message", examples = {"must be a well-formed email address"})
    @JsonProperty("message")
    String message,
    @Schema(name = "field", examples = {"email"})
    @JsonProperty("field")
    String field,
    @Schema(name = "value", examples = {"email#email.com"})
    @JsonProperty("value")
    String value
) {
}
