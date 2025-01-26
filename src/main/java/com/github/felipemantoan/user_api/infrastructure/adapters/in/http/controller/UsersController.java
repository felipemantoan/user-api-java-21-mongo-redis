package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

import com.github.felipemantoan.user_api.application.usecases.CreateUserUseCase;
import com.github.felipemantoan.user_api.application.usecases.DeleteUserUseCase;
import com.github.felipemantoan.user_api.application.usecases.GetAllUsersUseCase;
import com.github.felipemantoan.user_api.application.usecases.GetUserById;
import com.github.felipemantoan.user_api.application.usecases.UpdateUserUseCase;
import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.UpdateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.UserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.mapper.UserHttpMapper;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/users")
@Log4j2
@Validated
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
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserRequestDTO dto) {
        User user = createUserUseCase.execute(userHttpMapper.map(dto));
        URI uri = URI.create("/users/" + user.id());
        return ResponseEntity.created(uri).body(userHttpMapper.map(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userHttpMapper.map(getUserById.execute(userId)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") String userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> put(@PathVariable("userId") String userId,
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO) throws Exception {
        return ResponseEntity.ok(userHttpMapper.map(updateUserUseCase.execute(userId, updateUserRequestDTO.name(),
                updateUserRequestDTO.name(), updateUserRequestDTO.phoneNumber())));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
            getAllUsersUseCase.execute(pageable).map(userHttpMapper::map)
        );
    }

}
