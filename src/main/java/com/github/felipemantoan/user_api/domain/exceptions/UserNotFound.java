package com.github.felipemantoan.user_api.domain.exceptions;

public class UserNotFound extends RuntimeException {   
    
    public UserNotFound(String id) {
        super(String.format("User id: %s not found.", id));
    }
}
