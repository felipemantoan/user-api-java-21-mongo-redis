package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.UpdateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.PageableUserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserResponseDTO;

@ExtendWith(MockitoExtension.class)
public class UserHttpMapperImplTest {
    
    private final UserHttpMapper mapper = new UserHttpMapperImpl();

    @Test
    public void testMapUserToUserResponseDTO() {
        
        User user = User.builder()
            .id("wzz")
            .name("Luis")
            .cpf("12345678901")
            .email("email@email.com")
            .phoneNumber("191234567890")
            .deleted(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
        .build();
        
        UserResponseDTO response = mapper.map(user);

        Assertions.assertEquals(user.getId(), response.id());
        Assertions.assertEquals(user.getName(), response.name());
        Assertions.assertEquals(user.getCpf(), response.cpf());
        Assertions.assertEquals(user.getEmail(), response.email());
        Assertions.assertEquals(user.getPhoneNumber(), response.phoneNumber());
        Assertions.assertEquals(user.getCreatedAt(), response.createdAt());
        Assertions.assertEquals(user.getUpdatedAt(), response.updatedAt());
    }   

    @Test
    public void testMapCreateUserRequestDTOToUser() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
            "Luis",
            "11223344556",
            "email@email.com",
            "11999994444"
        );

        User user = mapper.map(dto);

        Assertions.assertEquals(dto.name(), user.getName());
        Assertions.assertEquals(dto.cpf(), user.getCpf());
        Assertions.assertEquals(dto.email(), user.getEmail());
        Assertions.assertEquals(dto.phoneNumber(), user.getPhoneNumber());
    }

    @Test
    public void testMapUpdateUserRequestDTOToUser() {
        UpdateUserRequestDTO dto = new UpdateUserRequestDTO(
            "Luis",
            "email@email.com",
            "11999994444"
        );

        User user = mapper.map(dto);

        Assertions.assertEquals(dto.name(), user.getName());
        Assertions.assertEquals(dto.email(), user.getEmail());
        Assertions.assertEquals(dto.phoneNumber(), user.getPhoneNumber());
    }

    @Test
    public void mapPageToPageableUserResponseDTO() {
        
        User user = User.builder()
            .id("wzz")
            .name("Luis")
            .cpf("12345678901")
            .email("email@email.com")
            .phoneNumber("191234567890")
            .deleted(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
        .build();

        Page<User> page = new PageImpl<User>(List.of(user));

        PageableUserResponseDTO pageableUserResponseDTO = mapper.map(page);
        List<UserResponseDTO> content = pageableUserResponseDTO.content();
        UserResponseDTO item = content.getFirst();

        Assertions.assertEquals(1, content.size());
        Assertions.assertEquals(user.getId(), item.id());
        Assertions.assertEquals(user.getName(), item.name());
        Assertions.assertEquals(user.getCpf(), item.cpf());
        Assertions.assertEquals(user.getEmail(), item.email());
        Assertions.assertEquals(user.getPhoneNumber(), item.phoneNumber());
        Assertions.assertEquals(user.getCreatedAt(), item.createdAt());
        Assertions.assertEquals(user.getUpdatedAt(), item.updatedAt());
    }
}
