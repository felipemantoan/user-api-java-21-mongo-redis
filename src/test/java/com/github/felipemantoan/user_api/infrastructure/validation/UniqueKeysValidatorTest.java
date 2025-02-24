package com.github.felipemantoan.user_api.infrastructure.validation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ExecutableFindOperation.ExecutableFind;
import org.springframework.data.mongodb.core.ExecutableFindOperation.TerminatingFind;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.assertions.Assertions;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import lombok.Data;

@ExtendWith(MockitoExtension.class)
public class UniqueKeysValidatorTest {

    @Mock
    private UniqueKeys annotation;

    @Mock
    private MongoTemplate template;

    @InjectMocks
    private UniqueKeysValidator validator;

    @UniqueKeys(keys = {"email", "username"})
    @Data
    public class InnerUniqueKeys {
        
        private String id;
        
        private String email;
        
        private String username;        
    }

    @Test
    public void testUniqueKeysValidatorInitialize() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username"});
        
        validator.initialize(annotation);

        verify(annotation).id();
        verify(annotation).mongoId();
        verify(annotation).keys();
    }

    @Test
    public void testUniqueKeysValidatorIsValidTrue() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username"});
        
        validator.initialize(annotation);

        InnerUniqueKeys innerClass = new InnerUniqueKeys();

        innerClass.setId(ObjectId.get().toString());
        innerClass.setEmail("email@email.com");
        innerClass.setUsername("ghost-user");

        when(template.getCollectionName(InnerUniqueKeys.class)).thenReturn("stub");

        TerminatingFind terminatingFind = mock(TerminatingFind.class);
        when(terminatingFind.exists()).thenReturn(Boolean.FALSE);
        
        ExecutableFind executableFind = mock(ExecutableFind.class);
        when(executableFind.matching(any(Query.class))).thenReturn(terminatingFind);
        
        when(template.query(InnerUniqueKeys.class)).thenReturn(executableFind);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        Assertions.assertTrue(validator.isValid(innerClass, context));

        verify(context, times(0)).disableDefaultConstraintViolation();
    }

    @Test
    public void testUniqueKeysValidatorIsValidFalse() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username"});
        
        validator.initialize(annotation);

        InnerUniqueKeys innerClass = new InnerUniqueKeys();

        innerClass.setId(ObjectId.get().toString());
        innerClass.setEmail("email@email.com");
        innerClass.setUsername("ghost-user");

        when(template.getCollectionName(InnerUniqueKeys.class)).thenReturn("stub");

        TerminatingFind terminatingFind = mock(TerminatingFind.class);
        when(terminatingFind.exists()).thenReturn(Boolean.TRUE);
        
        ExecutableFind executableFind = mock(ExecutableFind.class);
        when(executableFind.matching(any(Query.class))).thenReturn(terminatingFind);
        
        when(template.query(InnerUniqueKeys.class)).thenReturn(executableFind);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        NodeBuilderCustomizableContext node = mock(NodeBuilderCustomizableContext.class);
        when(node.addConstraintViolation()).thenReturn(context);
        
        ConstraintViolationBuilder builder = mock(ConstraintViolationBuilder.class);
        when(builder.addPropertyNode(anyString())).thenReturn(node);
        
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        
        Assertions.assertFalse(validator.isValid(innerClass, context));

        verify(context, times(1)).disableDefaultConstraintViolation();
    }

    /**
     * Este cenário deixa claro que existe um bug.
     * Pensando que um index de banco de dados não pode ser nulo ou vazio deveria dar erro ;(
     */
    @Test
    public void testUniqueKeysValidatorIsValidWithoutEmail() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username"});
        
        validator.initialize(annotation);

        InnerUniqueKeys innerClass = new InnerUniqueKeys();

        innerClass.setId(ObjectId.get().toString());
        innerClass.setUsername("ghost-user");

        when(template.getCollectionName(InnerUniqueKeys.class)).thenReturn("stub");

        TerminatingFind terminatingFind = mock(TerminatingFind.class);
        when(terminatingFind.exists()).thenReturn(Boolean.FALSE);
        
        ExecutableFind executableFind = mock(ExecutableFind.class);
        when(executableFind.matching(any(Query.class))).thenReturn(terminatingFind);
        
        when(template.query(InnerUniqueKeys.class)).thenReturn(executableFind);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        Assertions.assertTrue(validator.isValid(innerClass, context));

        verify(context, times(0)).disableDefaultConstraintViolation();
    }

    @Test
    public void testUniqueKeysValidatorIsValidWithoutId() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username"});
        
        validator.initialize(annotation);

        InnerUniqueKeys innerClass = new InnerUniqueKeys();

        innerClass.setEmail("email@email.com");
        innerClass.setUsername("ghost-user");

        when(template.getCollectionName(InnerUniqueKeys.class)).thenReturn("stub");

        TerminatingFind terminatingFind = mock(TerminatingFind.class);
        when(terminatingFind.exists()).thenReturn(Boolean.FALSE);
        
        ExecutableFind executableFind = mock(ExecutableFind.class);
        when(executableFind.matching(any(Query.class))).thenReturn(terminatingFind);
        
        when(template.query(InnerUniqueKeys.class)).thenReturn(executableFind);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        Assertions.assertTrue(validator.isValid(innerClass, context));

        verify(context, times(0)).disableDefaultConstraintViolation();
    }


    @Test
    public void testUniqueKeysValidatorIsValidFieldInvalid() {

        when(annotation.id()).thenReturn("id");
        when(annotation.mongoId()).thenReturn("_id");
        when(annotation.keys()).thenReturn(new String[]{"email", "username", "password"});
        
        validator.initialize(annotation);

        InnerUniqueKeys innerClass = new InnerUniqueKeys();

        innerClass.setId(ObjectId.get().toString());
        innerClass.setEmail("email@email.com");
        innerClass.setUsername("ghost-user");

        when(template.getCollectionName(InnerUniqueKeys.class)).thenReturn("stub");

        TerminatingFind terminatingFind = mock(TerminatingFind.class);
        when(terminatingFind.exists()).thenReturn(Boolean.FALSE);
        
        ExecutableFind executableFind = mock(ExecutableFind.class);
        when(executableFind.matching(any(Query.class))).thenReturn(terminatingFind);
        
        when(template.query(InnerUniqueKeys.class)).thenReturn(executableFind);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        Assertions.assertTrue(validator.isValid(innerClass, context));

        verify(context, times(0)).disableDefaultConstraintViolation();
    }

}
