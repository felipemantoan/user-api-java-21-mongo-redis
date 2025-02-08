package com.github.felipemantoan.user_api.domain.exception;

import com.github.felipemantoan.user_api.domain.entity.User;

public class UserNotFoundException extends RuntimeException {   
    
    public UserNotFoundException(String id) {
        super(String.format("User id: %s not found.", id));
    }

    public UserNotFoundException(User user) {
        this(user.getId());
    }
}
