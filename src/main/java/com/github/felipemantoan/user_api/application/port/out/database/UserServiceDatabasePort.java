package com.github.felipemantoan.user_api.application.port.out.database;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.felipemantoan.user_api.domain.entity.User;

public interface UserServiceDatabasePort {

    public Page<User> getAll(Pageable pageable);

    public Optional<User> getOne(String userId);

    public void delete(String id);

    public User save(User user);
}
