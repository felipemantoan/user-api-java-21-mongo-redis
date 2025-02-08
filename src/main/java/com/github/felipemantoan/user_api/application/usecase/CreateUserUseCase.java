package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.out.database.UserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CreateUserUseCase {
    
    @Autowired private UserService service;

    public User execute(User user) {
        return service.create(user);
    }

}
