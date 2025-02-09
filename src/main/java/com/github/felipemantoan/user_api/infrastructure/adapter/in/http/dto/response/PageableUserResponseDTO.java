package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageableUserResponseDTO(
    @Schema(name = "_content")
    @JsonProperty("_content")
    List<UserResponseDTO> content,
    @Schema(name = "total_pages", examples = {"1"})
    @JsonProperty("total_pages")
    int totalPages,
    @Schema(name = "total_elements", examples = {"20"})
    @JsonProperty("total_elements")
    int totalElements,
    @Schema(name = "last", examples = {"false"})
    @JsonProperty("last")
    boolean last,
    @Schema(name = "first", examples = {"true"})
    @JsonProperty("first")
    boolean first,
    @Schema(name = "size", examples = {"100"})
    @JsonProperty("size")
    int size,
    @Schema(name = "number", examples = {"2"})
    @JsonProperty("number")
    int number,
    @Schema(name = "number_elements", examples = {"100"})
    @JsonProperty("number_elements")
    int numberOfElements,
    @Schema(name = "empty", examples = {"false"})
    @JsonProperty("empty")
    boolean empty
) {
}