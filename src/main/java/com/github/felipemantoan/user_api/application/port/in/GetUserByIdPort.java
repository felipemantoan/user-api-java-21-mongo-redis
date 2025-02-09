package com.github.felipemantoan.user_api.application.port.in;

import com.github.felipemantoan.user_api.domain.entity.User;

public interface GetUserByIdPort {

    public User execute(String userId);
}
