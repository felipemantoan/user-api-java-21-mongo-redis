package com.github.felipemantoan.user_api.infrastructure.validation;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UniqueKeysValidatorTest {

    @Mock
    private UniqueKeys uniqueKeys;

    @InjectMocks
    private UniqueKeysValidator validator;

    @BeforeEach
    public void beforeTest() {
        when(uniqueKeys.id()).thenReturn("id");
        when(uniqueKeys.mongoId()).thenReturn("_id");

    }

    @Test
    public void testUniqueKeysValidator() {

    }
}
