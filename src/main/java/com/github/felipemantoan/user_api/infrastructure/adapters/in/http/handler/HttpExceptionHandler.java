package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.handler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.felipemantoan.user_api.domain.exceptions.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exceptions.UserNotFoundException;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.ErrorValidationResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.UserErrorValidationResponseDTO;
import com.github.felipemantoan.user_api.infrastructure.adapters.in.http.dto.response.UserNotFoundResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<UserNotFoundResponseDTO> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new UserNotFoundResponseDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserConstraintValidationException.class)
    ResponseEntity<UserErrorValidationResponseDTO> handleUserConstraintValidationException(UserConstraintValidationException e) {
        
        List<ErrorValidationResponseDTO> errors = new ArrayList<>();

        e.getErrors().forEach(error -> {
            String property = error.getPropertyPath().toString();
            String parameter = error.getInvalidValue().toString();
            
            errors.add(new ErrorValidationResponseDTO(
                error.getMessage(), 
                property, 
                parameter
            ));
        });

        UserErrorValidationResponseDTO response = new UserErrorValidationResponseDTO(
            HttpStatus.BAD_REQUEST.value(), 
            HttpStatus.BAD_REQUEST.name(), 
            errors, 
            LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(response);
    }
}
