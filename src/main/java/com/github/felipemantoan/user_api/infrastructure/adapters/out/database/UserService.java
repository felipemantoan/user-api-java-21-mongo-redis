package com.github.felipemantoan.user_api.infrastructure.adapters.out.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.domain.exceptions.UserNotFound;
import com.github.felipemantoan.user_api.domain.repositories.UserRepository;

@Service
public class UserService {

    @Autowired private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public User update(String userId, String name, String email, String phoneNumber) throws UserNotFound {
        
        User user = getOne(userId);
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        return repository.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return repository.findAllNoDeleted(pageable);
    }

    public User getOne(String userId) throws UserNotFound {
        return repository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }

    public void delete(String id) {
        repository.disable(id);
    }
}
