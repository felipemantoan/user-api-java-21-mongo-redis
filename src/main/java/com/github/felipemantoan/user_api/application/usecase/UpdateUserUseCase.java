package com.github.felipemantoan.user_api.application.usecase;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.application.port.in.UpdateUserPort;
import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class UpdateUserUseCase implements UpdateUserPort {
    
    @Autowired private UserServiceDatabasePort port;

    @Autowired private Validator validator;

    @Override
    public User execute(String userId, String name, String email, String phoneNumber) {
        User user = port.getOne(userId).orElseThrow(() -> new UserNotFoundException(userId));
        
        if (user.getName() != name) {
            user.setName(name);
        }

        if (user.getEmail() != email) {
            user.setEmail(email);
        }

        if (user.getPhoneNumber() != phoneNumber) {
            user.setPhoneNumber(phoneNumber);
        }

        Set<ConstraintViolation<User>> errors = validator.validate(user);

        if (errors.isEmpty()) {
            return port.save(user);
        }

        throw new UserConstraintValidationException(errors);
    }
}
