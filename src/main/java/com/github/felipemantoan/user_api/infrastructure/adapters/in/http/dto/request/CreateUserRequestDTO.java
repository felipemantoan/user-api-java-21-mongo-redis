package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateUserRequestDTO(
    @JsonProperty("name") String name,
    @JsonProperty("cpf") String cpf,
    @JsonProperty("email") String email,
    @JsonProperty("phone_number") String phoneNumber
) {
}
