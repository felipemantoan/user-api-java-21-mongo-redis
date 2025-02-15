package com.github.felipemantoan.user_api.domain.exception;

public class UserNotFoundException extends RuntimeException {   
    
    public UserNotFoundException(String id) {
        super(String.format("User id: %s not found.", id));
    }
}
