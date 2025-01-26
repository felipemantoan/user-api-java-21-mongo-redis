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

    public Optional<User> update(String userId, String name, String email, String phoneNumber) throws Exception {
        
        Optional<User> optional = repository.findById(userId);
        
        if (optional.isPresent()) {
            User user = optional.get();
            User updated = new User(user.id(), name, user.cpf(), email, phoneNumber, user.createdAt(), null, null, null);
            repository.save(updated);
            return Optional.of(updated);
        }
        
        return Optional.empty();
    }

    public Page<User> getAll(Pageable pageable) {
        return repository.findAllNoDeleted(pageable);
    }

    public Optional<User> getOne(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.disable(id);
    }
}
