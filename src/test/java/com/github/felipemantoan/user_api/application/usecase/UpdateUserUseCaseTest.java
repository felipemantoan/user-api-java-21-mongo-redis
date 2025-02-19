package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {
    
    @Mock
    private UserServiceDatabasePort port;

    @Mock
    private Validator validator;

    @InjectMocks
    private UpdateUserUseCase useCase;

    @Test
    public void testExecuteSuccess() {

        String userId = ObjectId.get().toString();

        User user = User.builder()
            .id(userId)
            .name("lucifer")
            .cpf("76343098852")
            .phoneNumber("1988887777")
            .email("email@email.com")
            .createdAt(LocalDateTime.of(2024, 12, 31, 23, 59))
            .updatedAt(LocalDateTime.of(2025, 02, 02, 23, 59))
        .build();

        when(validator.validate(any(User.class))).thenReturn(Set.of());
        when(port.getOne(anyString())).thenReturn(Optional.of(user));
        when(port.save(any(User.class))).thenReturn(user);

        User updatedUser = useCase.execute(userId, "Felipe", "felipe@email.com", "1977778888");

        Assertions.assertEquals("Felipe", updatedUser.getName());
        Assertions.assertEquals("felipe@email.com", updatedUser.getEmail());
        Assertions.assertEquals("1977778888", updatedUser.getPhoneNumber());

        verify(port).save(any(User.class));
        verify(port).getOne(anyString());
        verify(validator).validate(any(User.class));
    }

    @Test
    public void testExecuteUserNotFoundException() {

        when(port.getOne(anyString())).thenReturn(Optional.empty());

        String userId = ObjectId.get().toString();

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            useCase.execute(userId, "Felipe", "felipe@email.com", "1977778888");
        });

        verify(port).getOne(anyString());
    }

    @Test
    public void testExecuteUserConstraintValidationException() {

        String userId = ObjectId.get().toString();
    
        User user = User.builder()
            .id(userId)
            .name("lucifer")
            .cpf("76343098852")
            .phoneNumber("1988887777")
            .email("email@email.com")
            .createdAt(LocalDateTime.of(2024, 12, 31, 23, 59))
            .updatedAt(LocalDateTime.of(2025, 02, 02, 23, 59))
        .build();

        ConstraintViolation<User> mockConstraintViolation = mock(ConstraintViolation.class);
        Set<ConstraintViolation<User>> errors = new HashSet<ConstraintViolation<User>>();
        errors.add(mockConstraintViolation);

        when(validator.validate(any(User.class))).thenReturn(errors);
        when(port.getOne(anyString())).thenReturn(Optional.of(user));

        Assertions.assertThrows(UserConstraintValidationException.class, () -> {
            useCase.execute(userId, "lucifer", "email@email.com", "1988887777");
        });

        verify(port, times(0)).save(any(User.class));
        verify(port).getOne(anyString());
        verify(validator).validate(any(User.class));
    }

}
