package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
    @Schema(name = "id", examples = {"6796a1203634e10462542bb5"})
    @JsonProperty("id")
    String id,
    @Schema(name = "name", examples = {"Vanderlei Luxemburgo"})
    @JsonProperty("name")
    String name,
    @Schema(name = "cpf", examples = {"52414147890"})
    @JsonProperty("cpf")
    String cpf,
    @Schema(name = "email", examples = {"email@email.com"})
    @JsonProperty("email")
    String email,
    @Schema(name = "phone_number", examples = {"19888887777"})
    @JsonProperty("phone_number")
    String phoneNumber,
    @Schema(name = "created_at", examples = {"2025-02-08T16:16:52.183Z"})
    @JsonProperty("created_at")
    LocalDateTime createdAt,
    @Schema(name = "updated_at", examples = {"2025-02-08T16:16:52.183Z"})
    @JsonProperty("updated_at")
    LocalDateTime updatedAt
) {
}
