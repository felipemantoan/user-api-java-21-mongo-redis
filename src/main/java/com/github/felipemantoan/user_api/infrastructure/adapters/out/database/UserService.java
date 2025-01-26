package com.github.felipemantoan.user_api.infrastructure.adapters.out.database;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.domain.repositories.UserRepository;

@Service
public class UserService {

    @Autowired private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public Optional<User> update(UUID userId, String name, String email, String phoneNumber) {
        return repository.findById(userId);
    }

    public Page<User> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<User> getOne(UUID id) {
        return repository.findById(id);
    }

    public void delete(UUID id) {
        
    }
}
