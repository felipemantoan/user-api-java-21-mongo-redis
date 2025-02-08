package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.mapper;

import org.mapstruct.Mapper;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.UpdateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserHttpMapper {

    User map(CreateUserRequestDTO dto);

    User map(UpdateUserRequestDTO dto);

    UserResponseDTO map(User user);

    // Page<UserResponseDTO> map(Page<User> page);
}
