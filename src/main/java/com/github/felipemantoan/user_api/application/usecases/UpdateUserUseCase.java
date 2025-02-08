package com.github.felipemantoan.user_api.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.UserService;

@Component
public class UpdateUserUseCase {
    
    @Autowired private UserService service;

    public User execute(String userId, String name, String email, String phoneNumber){
        return service.update(userId, name, email, phoneNumber);
    }
}
