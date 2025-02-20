package com.github.felipemantoan.user_api.infrastructure.validation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UniqueKeysValidatorTest {

    @Mock
    private UniqueKeys annotation;

    @InjectMocks
    private UniqueKeysValidator validator;

    @BeforeEach
    public void beforeTest() {

        String[] keys = {"email", "username"};

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(keys);
    }

    @Test
    public void testUniqueKeysValidatorInitialize() {
        validator.initialize(annotation);
        verify(annotation).id();
        verify(annotation).mongoId();
        verify(annotation).keys();
    }
}
