package com.github.felipemantoan.user_api.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.felipemantoan.user_api.domain.entity.User;

public interface GetAllUsersPort {
    
    public Page<User> execute(Pageable pageable);    
}
