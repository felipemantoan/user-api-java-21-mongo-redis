package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageableUserResponseDTO(
    @Schema(name = "_content")
    @JsonProperty("_content")
    List<UserResponseDTO> content,
    @Schema(name = "total_pages", example = "1")
    @JsonProperty("total_pages")
    int totalPages,
    @Schema(name = "total_elements", example = "20")
    @JsonProperty("total_elements")
    int totalElements,
    @Schema(name = "last", example = "false")
    @JsonProperty("last")
    boolean last,
    @Schema(name = "first", example = "true")
    @JsonProperty("first")
    boolean first,
    @Schema(name = "size", example = "100")
    @JsonProperty("size")
    int size,
    @Schema(name = "number", example = "2")
    @JsonProperty("number")
    int number,
    @Schema(name = "number_elements", example = "100")
    @JsonProperty("number_elements")
    int numberOfElements,
    @Schema(name = "empty", example = "false")
    @JsonProperty("empty")
    boolean empty
) {
}