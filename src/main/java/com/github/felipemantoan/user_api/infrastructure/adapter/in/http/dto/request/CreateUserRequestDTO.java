package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateUserRequestDTO(
    @JsonProperty("name")
    @Schema(name = "name", examples = {"Ronaldo Nazario"}, required = true)
    String name,
    @JsonProperty("cpf")
    @Schema(name = "cpf", examples = {"52414147890"}, required = true)
    String cpf,
    @JsonProperty("email")
    @Schema(name = "email", examples = {"email@email.com"}, required = true)
    String email,
    @Schema(name = "phone_number", examples = {"19999998888"}, required = true)
    @JsonProperty("phone_number")
    String phoneNumber
) {
}
