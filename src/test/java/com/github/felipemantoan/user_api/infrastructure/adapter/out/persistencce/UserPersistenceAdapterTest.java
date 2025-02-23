package com.github.felipemantoan.user_api.infrastructure.adapter.out.persistencce;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.felipemantoan.user_api.domain.entity.User;
import com.github.felipemantoan.user_api.infrastructure.adapter.out.persistence.UserMongoAdapter;
import com.github.felipemantoan.user_api.infrastructure.adapter.out.persistence.UserPersistenceAdapter;

@ExtendWith(MockitoExtension.class)
public class UserPersistenceAdapterTest {
    
    @Mock
    private UserMongoAdapter userMongoAdapter;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @Test
    public void testSaveUser() {
        when(userMongoAdapter.save(any(User.class))).then(called -> called.getArguments()[0]);
        User user = User.builder()
            .name("Felipe")
            .email("email@email.com")
            .phoneNumber("1140028922")
            .cpf("12345678909")
            .build();
        userPersistenceAdapter.save(user);
        verify(userMongoAdapter).save(any(User.class));
    }

    @Test
    public void testGetOne() {
        final String userId = "67a9156c122b71468d7783f0";
        User user = User.builder().id(userId).build();
        when(userMongoAdapter.findById(userId)).thenReturn(Optional.of(user));
        userPersistenceAdapter.getOne(userId);
        verify(userMongoAdapter).findById(userId);
    }

    @Test
    public void testDelete() {
        final String userId = "67a9156c122b71468d7783f0";
        userPersistenceAdapter.delete(userId);
        verify(userMongoAdapter).deleteById(userId);
    }

    @Test
    public void testGetAll() {
        when(userMongoAdapter.findAll(any(Pageable.class)))
            .thenReturn(Page.empty());
        userPersistenceAdapter.getAll(Pageable.unpaged());
        verify(userMongoAdapter).findAll(any(Pageable.class));
    }
}
