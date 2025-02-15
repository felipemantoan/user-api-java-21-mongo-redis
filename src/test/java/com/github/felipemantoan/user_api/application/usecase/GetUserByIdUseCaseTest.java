package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {
    
    @Mock
    private UserServiceDatabasePort port;

    @InjectMocks
    private GetUserByIdUseCase useCase;    

    @Test
    public void testExecuteSuccess() {
        when(port.getOne(anyString())).thenReturn(Optional.of(User.builder().build()));
        useCase.execute("wzz");
        verify(port).getOne(anyString());
    }

    @Test
    public void testExecuteError() {
        when(port.getOne(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            useCase.execute("wzz");
        });

        verify(port).getOne(anyString());
    }
}
