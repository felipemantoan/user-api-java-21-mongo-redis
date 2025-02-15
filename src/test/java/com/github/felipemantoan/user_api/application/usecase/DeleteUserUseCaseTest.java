package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {
    
    @Mock
    private UserServiceDatabasePort port;

    @InjectMocks
    private DeleteUserUseCase useCase;

    @Test
    public void testExecuteSuccess() {
        useCase.execute("xpto");
        verify(port).delete(anyString());
    }

}
