package com.github.felipemantoan.user_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class LocalValidationConfig {

    @Bean
	public LocalValidatorFactoryBean validator() {
		log.info("LocalValidationConfig#validator: Loadded {}", LocalValidatorFactoryBean.class);
        return new LocalValidatorFactoryBean();
	}
}
