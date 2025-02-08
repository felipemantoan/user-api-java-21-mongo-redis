package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.request.UpdateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.PageableUserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserHttpMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    User map(CreateUserRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    User map(UpdateUserRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    UserResponseDTO map(User user);

    @Named("mapListUsersToUserResponseDTO")
    List<UserResponseDTO> map(List<User> users);

    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "last", target = "last")
    @Mapping(source = "first", target = "first")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "numberOfElements", target = "numberOfElements")
    @Mapping(source = "empty", target = "empty")
    @Mapping(source = "content", target = "content", qualifiedByName = "mapListUsersToUserResponseDTO")
    PageableUserResponseDTO map(Page<User> page);
}
