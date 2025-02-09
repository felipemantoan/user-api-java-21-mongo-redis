package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.application.port.in.GetUserByIdPort;
import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.domain.exception.UserNotFoundException;

@Component
public class GetUserByIdUseCase implements GetUserByIdPort {

    @Autowired private UserServiceDatabasePort port;

    public User execute(String userId) {
        return port.getOne(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
