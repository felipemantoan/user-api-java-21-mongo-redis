package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("cpf") String cpf,
    @JsonProperty("email") String email,
    @JsonProperty("phone_number") String phoneNumber,
    @JsonProperty("created_at") LocalDateTime createdAt,
    @JsonProperty("updated_at") LocalDateTime updatedAt
) {
}
