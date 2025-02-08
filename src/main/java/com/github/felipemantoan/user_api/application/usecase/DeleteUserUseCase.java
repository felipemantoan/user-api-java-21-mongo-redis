package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.infrastructure.adapter.out.database.UserService;

@Component
public class DeleteUserUseCase {
    
    @Autowired private UserService service;

    public void execute(String userId) {
        service.delete(userId);
    }

}
