package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserNotFoundResponseDTO(
    @Schema(name = "status", examples = {"404"})    
    @JsonProperty("status") Integer status,
    @Schema(name = "details", examples = {"NOT_FOUND"})    
    @JsonProperty("details") String details,
    @Schema(name = "timestamp")    
    @JsonProperty("timestamp") LocalDateTime timestamp
) {
    
}
