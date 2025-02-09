package com.github.felipemantoan.user_api.application.usecase;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.github.felipemantoan.user_api.application.port.in.CreateUserPort;
import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CreateUserUseCase implements CreateUserPort {
    
    @Autowired private UserServiceDatabasePort service;

    @Autowired private Validator validator;

    @Override
    public User execute(User user) {

        Set<ConstraintViolation<User>> errors = validator.validate(user);

        if (errors.isEmpty()) {
            return service.save(user);
        }

        throw new UserConstraintValidationException(errors);
    }
}
