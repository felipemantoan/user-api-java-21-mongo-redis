package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;

import jakarta.validation.ConstraintViolation;
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
        when(validator.validate(any(User.class))).thenReturn(Set.of());

        useCase.execute(user);

        verify(validator).validate(any(User.class));
        verify(port).save(any(User.class));
    }

    @Test
    public void testExecuteError() {
        User user = User.builder().build();
        ConstraintViolation mockConstraintViolation = mock(ConstraintViolation.class);
        
        when(validator.validate(any(User.class))).thenReturn(Set.of(mockConstraintViolation));

        Assertions.assertThrows(UserConstraintValidationException.class, () -> {
            useCase.execute(user);
        });

        verify(validator).validate(any(User.class));
        verify(port, times(0)).save(any(User.class));
    }

}
