package com.github.felipemantoan.user_api.adapter.in.http.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.controller.UsersController;
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
    public void testDelete() {
        ResponseEntity<Void> reponse = controller.delete("xpto");
        verify(deleteUserPort).execute(anyString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, reponse.getStatusCode());
    }
    
}
