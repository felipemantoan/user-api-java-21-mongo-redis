package com.github.felipemantoan.user_api.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.felipemantoan.user_api.application.port.out.database.UserServiceDatabasePort;

@ExtendWith(MockitoExtension.class)
public class GetAllUsersUseCaseTest {
    
    @Mock
    private UserServiceDatabasePort port;

    @InjectMocks
    private GetAllUsersUseCase useCase;

    @Test
    public void testExecuteSuccess() {
        when(port.getAll(any(Pageable.class))).thenReturn(Page.empty());
        useCase.execute(Pageable.unpaged());
        verify(port).getAll(any(Pageable.class));
    }

}
