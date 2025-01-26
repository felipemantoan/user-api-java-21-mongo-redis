package com.github.felipemantoan.user_api.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories.UserRepository;

@Component
public class FindAll {
    
    @Autowired private UserRepository repository;

    public Page<User> execute(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
