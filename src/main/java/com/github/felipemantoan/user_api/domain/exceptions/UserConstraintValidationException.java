package com.github.felipemantoan.user_api.domain.exceptions;

import java.util.Set;

import com.github.felipemantoan.user_api.domain.entities.User;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

public class UserConstraintValidationException extends RuntimeException {
    
    @Getter
    final private Set<ConstraintViolation<User>> errors;

    public UserConstraintValidationException(Set<ConstraintViolation<User>> errors) {
        this.errors = errors;
    }

}
