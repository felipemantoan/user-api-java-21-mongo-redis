package com.github.felipemantoan.user_api.adapter.in.http.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.felipemantoan.user_api.application.port.in.CreateUserPort;
import com.github.felipemantoan.user_api.application.port.in.DeleteUserPort;
import com.github.felipemantoan.user_api.application.port.in.GetAllUsersPort;
import com.github.felipemantoan.user_api.application.port.in.GetUserByIdPort;
import com.github.felipemantoan.user_api.application.port.in.UpdateUserPort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.controller.UsersController;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.mapper.UserHttpMapper;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {
    
    @InjectMocks private UsersController controller;

    @Mock
    private CreateUserPort createUserPort;
    
    @Mock
    private GetAllUsersPort getAllUsersPort;
    
    @Mock
    private GetUserByIdPort getUserByIdPort;
    
    @Mock
    private DeleteUserPort deleteUserPort;
    
    @Mock
    private UpdateUserPort updateUserPort;

    @Mock
    private UserHttpMapper userHttpMapper;
 
    @Test
    public void testCreate() {
        
        User user = User.builder().build();

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO(
            "dino", 
            "12345678909", 
            "email@email.com", 
            "12345098760"
        );
   
        UserResponseDTO responseDTO = new UserResponseDTO(
            ObjectId.get().toString(),
            "dino", 
            "12345678909", 
            "email@email.com", 
            "12345098760", 
            LocalDateTime.now(), 
            LocalDateTime.now()
        );
        
        when(userHttpMapper.map(any(CreateUserRequestDTO.class))).thenReturn(user);
        when(createUserPort.execute(any(User.class))).thenReturn(user);
        when(userHttpMapper.map(user)).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> response = controller.create(createUserRequestDTO);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(responseDTO, response.getBody());

        verify(userHttpMapper).map(any(CreateUserRequestDTO.class));
        verify(createUserPort).execute(any(User.class));
        verify(userHttpMapper).map(any(User.class));
    }

    @Test
    public void testDelete() {
        ResponseEntity<Void> reponse = controller.delete("xpto");
        verify(deleteUserPort).execute(anyString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, reponse.getStatusCode());
    }
    
}
