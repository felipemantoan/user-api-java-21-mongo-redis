package com.github.felipemantoan.user_api.infrastructure.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import io.swagger.v3.oas.models.OpenAPI;

@ExtendWith(MockitoExtension.class)
public class OpenApiConfigurationTest {
    
    @Test
    public void testOpenApiConfigurationGetInstance() {
        OpenApiConfiguration config = new OpenApiConfiguration();
        Assertions.assertInstanceOf(OpenAPI.class, config.customOpenAPI());
    }

}
