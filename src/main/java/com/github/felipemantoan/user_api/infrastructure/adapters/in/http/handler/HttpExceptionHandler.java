package com.github.felipemantoan.user_api.infrastructure.adapters.in.http.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.felipemantoan.user_api.domain.exceptions.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exceptions.UserNotFoundException;

import jakarta.validation.Path;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
 
    }

    @ExceptionHandler(UserConstraintValidationException.class)
    ResponseEntity<?> handleUserConstraintValidationException(UserConstraintValidationException e) {

        List<Map<String, String>> errors = new ArrayList<>();

        e.getErrors().forEach(error -> {

            Path property = error.getPropertyPath();
            String parameter = error.getInvalidValue().toString();

            Map<String, String> details = Map.of(
                "message", error.getMessage(),
                "field", property.toString(),
                "value", parameter.toString()
            );
            errors.add(details);
        });


        return ResponseEntity.badRequest().body(errors);
    }
}
