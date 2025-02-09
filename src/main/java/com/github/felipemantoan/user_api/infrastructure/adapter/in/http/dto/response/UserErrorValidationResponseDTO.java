package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserErrorValidationResponseDTO(
    @Schema(name = "status", examples = {"400"})
    @JsonProperty("status")
    Integer status,
    @Schema(name = "details", examples = {"BAD_REQUEST"})
    @JsonProperty("details")
    String details,
    @Schema(name = "errors")
    @JsonProperty("errors")
    List<ErrorValidationResponseDTO> errors,
    @Schema(name = "timestamp")
    @JsonProperty("timestamp")
    LocalDateTime timestamp
) {
    
}
