package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserNotFoundResponseDTO(
    @JsonProperty("status") Integer status,
    @JsonProperty("details") String details,
    @JsonProperty("timestamp") LocalDateTime timestamp
) {
    
}
