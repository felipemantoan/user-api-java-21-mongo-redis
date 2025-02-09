package com.github.felipemantoan.user_api.infrastructure.adapter.out.database;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserConstraintValidationException;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;
import com.github.felipemantoan.user_api.domain.repository.UserMongoRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {

    @Autowired private UserMongoRepository repository;

    @Autowired
    private Validator validator;

    public User create(User user) {
        log.info("Creates a new user {}", user);
        return save(user);
    }

    public User update(String userId, String name, String email, String phoneNumber) throws UserNotFoundException {
        
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

    @Cacheable(value = "USERS")
    public User getOne(String userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @CacheEvict(value = "USERS")
    public void delete(String id) {
        repository.disable(id);
    }

    @CachePut(value = "USERS")
    public User save(User user) {

        Set<ConstraintViolation<User>> errors = validator.validate(user);

        if (errors.isEmpty()) {

            log.info("UserService#save: Saving user {}", user);
            return repository.save(user);
        }

        throw new UserConstraintValidationException(errors);
    }
}
