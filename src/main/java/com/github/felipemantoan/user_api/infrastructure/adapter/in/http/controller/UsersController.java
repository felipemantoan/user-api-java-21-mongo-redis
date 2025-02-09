package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.felipemantoan.user_api.application.usecase.CreateUserUseCase;
import com.github.felipemantoan.user_api.application.usecase.DeleteUserUseCase;
import com.github.felipemantoan.user_api.application.usecase.GetAllUsersUseCase;
import com.github.felipemantoan.user_api.application.usecase.GetUserById;
import com.github.felipemantoan.user_api.application.usecase.UpdateUserUseCase;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.UpdateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.PageableUserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserErrorValidationResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserNotFoundResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.mapper.UserHttpMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/users")
@Log4j2
@Validated
@Tag(name = "Users", description = "CRUD Operations of User")
public class UsersController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private GetAllUsersUseCase getAllUsersUseCase;

    @Autowired
    private GetUserById getUserById;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private UserHttpMapper userHttpMapper;

    @PostMapping
    @Operation(summary = "Creates a new user", description = "Returns a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "Bad Request - The user was incorrected filled", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserErrorValidationResponseDTO.class))
        }),
    })
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserRequestDTO userRequestDTO) {
        log.info("UsersController#create: Request {}", userRequestDTO);
        final User user = createUserUseCase.execute(userHttpMapper.map(userRequestDTO));
        final String uriString = String.format("/api/v1/users/%s", user.getId());
        final URI uri = URI.create(uriString);
        final UserResponseDTO userResponseDTO = userHttpMapper.map(user);
        log.info("UsersController#create: Response {}", userResponseDTO);
        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Retrieves an existing user by id", description = "Returns an user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserResponseDTO.class))
        }),
        @ApiResponse(responseCode = "404", description = "Not found - The user was not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserNotFoundResponseDTO.class))
        }),
    })
    public ResponseEntity<UserResponseDTO> getById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userHttpMapper.map(getUserById.execute(userId)));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Deletes an existing user by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Not found - The user was not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserNotFoundResponseDTO.class))
        }),
    })
    public ResponseEntity<Void> delete(@Valid @NotBlank @PathVariable("userId") String userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Updates an existing user by id", description = "Returns an updated user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "Bad Request - The user was incorrected filled", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserErrorValidationResponseDTO.class))
        }),
        @ApiResponse(responseCode = "404", description = "Not found - The user was not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = UserNotFoundResponseDTO.class))
        }),
    })
    public ResponseEntity<UserResponseDTO> put(@Valid @NotBlank @PathVariable("userId") String userId,
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return ResponseEntity.ok(userHttpMapper.map(updateUserUseCase.execute(userId, updateUserRequestDTO.name(),
                updateUserRequestDTO.email(), updateUserRequestDTO.phoneNumber())));
    }

    @GetMapping
    @Operation(summary = "Retrieves a list of users", description = "Returns a list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "object", oneOf = PageableUserResponseDTO.class))
        }),
    })
    public ResponseEntity<PageableUserResponseDTO> getAll(@PageableDefault(size = 100, sort = "created_at") Pageable pageable) {
        return ResponseEntity.ok(
            userHttpMapper.map(getAllUsersUseCase.execute(pageable))
        );
    }

}
