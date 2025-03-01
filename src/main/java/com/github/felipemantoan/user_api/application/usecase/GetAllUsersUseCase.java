package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.application.port.in.GetAllUsersPort;
import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;
import com.github.felipemantoan.user_api.domain.entity.User;

@Component
public class GetAllUsersUseCase implements GetAllUsersPort {
    
    @Autowired private UserServiceDatabasePort port;

    @Override
    public Page<User> execute(Pageable pageable) {
        return port.getAll(pageable);
    }

}
