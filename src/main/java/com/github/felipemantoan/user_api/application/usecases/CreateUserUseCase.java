package com.github.felipemantoan.user_api.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.UserService;

@Component
public class CreateUserUseCase {
    
    @Autowired private UserService service;

    public User execute(User user) {
        return service.create(user);
    }

}
