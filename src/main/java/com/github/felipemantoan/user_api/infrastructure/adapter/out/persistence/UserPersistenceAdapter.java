package com.github.felipemantoan.user_api.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserPersistenceAdapter implements UserServiceDatabasePort {
    
    @Autowired private UserMongoAdapter adapter;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return adapter.findAll(pageable);
    }

    @Cacheable(value = "USERS")
    @Override
    public Optional<User> getOne(String userId) {
        log.info("UserServiceDatabasePort#getOne: Search user by id {}", userId);
        return adapter.findById(userId);
    }

    @CacheEvict(value = "USERS")
    @Override
    public void delete(String id) {
        log.info("UserServiceDatabasePort#save: Disabled user by id {}", id);
        adapter.deleteById(id);
    }

    @CachePut(value = "USERS", key = "#user.id")
    @Override
    public User save(User user) {
        log.info("UserServiceDatabasePort#save: Saved user {}", user);
        return adapter.save(user);
    }
}
