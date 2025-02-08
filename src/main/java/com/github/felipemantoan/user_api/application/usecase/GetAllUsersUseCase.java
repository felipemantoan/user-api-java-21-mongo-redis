package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.out.database.UserService;

@Component
public class GetAllUsersUseCase {
    
    @Autowired private UserService service;

    public Page<User> execute(Pageable pageable) {
        return service.getAll(pageable);
    }

}
