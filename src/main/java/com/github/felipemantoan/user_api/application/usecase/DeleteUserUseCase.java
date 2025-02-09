package com.github.felipemantoan.user_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.felipemantoan.user_api.application.port.in.DeleteUserPort;
import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;


@Component
public class DeleteUserUseCase implements DeleteUserPort {
    
    @Autowired private UserServiceDatabasePort port;

    @Override
    public void execute(String userId) {
        port.delete(userId);
    }

}
