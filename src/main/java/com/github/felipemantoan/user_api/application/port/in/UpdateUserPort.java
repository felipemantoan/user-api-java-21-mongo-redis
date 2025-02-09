package com.github.felipemantoan.user_api.application.port.in;

import com.github.felipemantoan.user_api.domain.entity.User;

public interface UpdateUserPort {
    
    public User execute(String userId, String name, String email, String phoneNumber);

}
