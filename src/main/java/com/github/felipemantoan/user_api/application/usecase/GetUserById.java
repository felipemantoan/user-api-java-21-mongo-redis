package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.out.database.UserService;

@Component
public class GetUserById {

    @Autowired private UserService service;

    public User execute(String userId) {
        return service.getOne(userId);
    }
}
