package com.github.felipemantoan.user_api.infrastructure.adapter.in.http.handler;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserNotFoundResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.handler.HttpExceptionHandler;

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

}
