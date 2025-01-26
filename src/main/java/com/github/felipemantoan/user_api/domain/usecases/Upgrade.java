package com.github.felipemantoan.user_api.domain.usecases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories.UserRepository;

@Component
public class Upgrade {
    
    @Autowired private UserRepository repository;

    public Optional<User> execute(UUID userId, String name, String email, String phoneNumber) {
        return repository.findById(userId);
    }

}
