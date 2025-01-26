package com.github.felipemantoan.user_api.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories.UserRepository;

@Component
public class Create {
    
    @Autowired private UserRepository repository;

    public void execute(User user) {
        repository.save(user);
    }

}
