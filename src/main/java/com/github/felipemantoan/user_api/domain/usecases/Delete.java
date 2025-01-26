package com.github.felipemantoan.user_api.domain.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories.UserRepository;

@Component
public class Delete {
    
    @Autowired private UserRepository repository;

    public void execute(UUID userId) {
        repository.findById(userId);
    }

}
