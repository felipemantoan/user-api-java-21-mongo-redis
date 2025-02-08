package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserErrorValidationResponseDTO(
    @JsonProperty("status") Integer status,
    @JsonProperty("details") String details,
    @JsonProperty("errors") List<ErrorValidationResponseDTO> errors,
    @JsonProperty("timestamp") LocalDateTime timestamp
) {
    
}
