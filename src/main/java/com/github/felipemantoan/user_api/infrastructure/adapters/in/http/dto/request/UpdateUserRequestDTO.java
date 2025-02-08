package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateUserRequestDTO(
    @JsonProperty("name")
    @Schema(name = "name", example = "Ronaldo Nazario", required = true)
    String name,
    @JsonProperty("email")
    @Schema(name = "email", example = "email@email.com", required = true)
    String email,
    @JsonProperty("phone_number")
    @Schema(name = "phone_number", example = "19999998888", required = true)
    String phoneNumber
) { 
}
