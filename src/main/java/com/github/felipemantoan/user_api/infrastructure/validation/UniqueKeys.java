package com.github.felipemantoan.user_api.infrastructure.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueKeysValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueKeys {

    String mongoId() default "_id";

    String id() default "id";

    @AliasFor("keys")
    String[] value() default {};

    @AliasFor("value")
    String[] keys() default {};

    String message() default "Already exists an {document} with {key}.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
