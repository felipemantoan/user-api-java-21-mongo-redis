package com.github.felipemantoan.user_api.infrastructure.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ExtendWith(MockitoExtension.class)
public class LocalValidationConfigTest {

    @Test
    public void testLocalValidationGetInstance() {
        LocalValidationConfig config = new LocalValidationConfig();
        Assertions.assertInstanceOf(LocalValidatorFactoryBean.class, config.validator());
    }

}
