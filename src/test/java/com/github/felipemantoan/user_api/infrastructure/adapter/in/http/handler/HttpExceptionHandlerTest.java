package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.handler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserErrorValidationResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserNotFoundResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.handler.HttpExceptionHandler;

import jakarta.validation.ConstraintViolation;

@ExtendWith(MockitoExtension.class)
public class HttpExceptionHandlerTest {
    
    @Test
    public void testHandleUserNotFoundException() {
        final UserNotFoundException userNotFoundException = new UserNotFoundException(ObjectId.get().toString());
        final HttpExceptionHandler handler = new HttpExceptionHandler();
        ResponseEntity<UserNotFoundResponseDTO> response = handler.handleUserNotFoundException(userNotFoundException);
        UserNotFoundResponseDTO body = response.getBody();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(userNotFoundException.getMessage(), body.details());
    }

    @Test
    public void testHandleUserConstraintValidationException() {
        ConstraintViolation<User> mockConstraintViolation = mock(ConstraintViolation.class);
        when(mockConstraintViolation.getMessage()).thenReturn("Error XPTO");
        when(mockConstraintViolation.getInvalidValue()).thenReturn(Integer.valueOf(42));
        when(mockConstraintViolation.getPropertyPath()).thenReturn(PathImpl.createPathFromString("path.xyz"));

        Set<ConstraintViolation<User>> errors = new HashSet<ConstraintViolation<User>>();
        errors.add(mockConstraintViolation);
        final UserConstraintValidationException exception = new UserConstraintValidationException(errors);
        final HttpExceptionHandler handler = new HttpExceptionHandler();
        ResponseEntity<UserErrorValidationResponseDTO> response = handler.handleUserConstraintValidationException(exception);

        verify(mockConstraintViolation, times(2)).getMessage();
        verify(mockConstraintViolation).getInvalidValue();
        verify(mockConstraintViolation).getPropertyPath();

        Assertions.assertEquals(1, response.getBody().errors().size());
    }

}
