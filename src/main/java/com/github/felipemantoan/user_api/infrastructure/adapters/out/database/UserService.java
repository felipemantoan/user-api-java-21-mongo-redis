package com.github.felipemantoan.user_api.infrastructure.adapters.out.database;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.domain.exceptions.UserNotFound;
import com.github.felipemantoan.user_api.domain.repositories.UserRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {

    @Autowired private UserRepository repository;

    @Autowired
    private Validator validator;

    public User create(User user) {
        log.info("Creates a new user {}", user);
        return save(user);
    }

    public User update(String userId, String name, String email, String phoneNumber) throws UserNotFound {
        
        User user = getOne(userId);

        if (user.getEmail() != email) {
            user.setEmail(email);
        }

        if (user.getName() != name) {
            user.setName(name);
        }

        if (user.getPhoneNumber() != phoneNumber) {
            user.setPhoneNumber(phoneNumber);
        }

        return save(user);
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

    public boolean uniqueEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User save(User user) {

        Set<ConstraintViolation<User>> errors = validator.validate(user);

        if (errors.isEmpty()) {

            log.info("UserService#save: Saving user id: {}", user.getId());
            return repository.save(user);
        }

        throw new RuntimeException(errors.iterator().next().getMessage());
    }
}
