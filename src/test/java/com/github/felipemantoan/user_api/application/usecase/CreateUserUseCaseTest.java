package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;

import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {
    
    @Mock
    private Validator validator;
    
    @Mock
    private UserServiceDatabasePort port;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Test
    public void testExecuteSuccess() {
        User user = User.builder().build();

        when(port.save(any(User.class))).thenReturn(user);

        User createdUser = useCase.execute(user);
    }

}
