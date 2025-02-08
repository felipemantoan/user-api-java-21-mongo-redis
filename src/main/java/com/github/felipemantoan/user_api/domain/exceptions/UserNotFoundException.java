package com.github.felipemantoan.user_api.domain.exceptions;

import com.github.felipemantoan.user_api.domain.entities.User;

public class UserNotFoundException extends RuntimeException {   
    
    public UserNotFoundException(String id) {
        super(String.format("User id: %s not found.", id));
    }

    public UserNotFoundException(User user) {
        this(user.getId());
    }
}
