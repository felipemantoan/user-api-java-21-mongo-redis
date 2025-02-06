package com.github.felipemantoan.user_api.infrastructure.validation;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableFindOperation.ExecutableFind;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class UniqueKeysValidator implements ConstraintValidator<UniqueKeys, Object> {

    @Autowired
    private MongoTemplate template;

    private String documentId;

    private String mongoId;

    private Set<String> keys;

    final private String CONSTRAINT_MESSAGE = "Already exists collection %s with %s.";

    public void initialize(UniqueKeys constraintAnnotation) {
        documentId = constraintAnnotation.id();
        mongoId = constraintAnnotation.mongoId();
        keys = Set.of(constraintAnnotation.keys());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final Class<?> classType = value.getClass();
        final String collectionName = template.getCollectionName(classType);   
        final Map<String, String> properties = mapPropertiesValues(value);

        boolean hasKeys = hasKeys(collectionName, classType, properties);

        if (hasKeys) {
            String message = String.format(CONSTRAINT_MESSAGE, collectionName, Arrays.toString(keys.toArray()));
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

            return false;
        }

        return true;
    }

    private Map<String, String> mapPropertiesValues(Object object) {
        Map<String, String> fieldsValues = new HashMap<String, String>();

        for (String fieldName : keys) {
            String value = extractValue(object, fieldName);
            
            if (StringUtils.hasText(value)) {
                fieldsValues.put(fieldName, value);
            }
        }

        String idValue = extractValue(object, documentId);

        if (StringUtils.hasText(idValue)) {
            fieldsValues.put(mongoId, idValue);
        }

        return fieldsValues;
    }

    private String extractValue(Object object, String fieldName) {
        try {
            return BeanUtils.getSimpleProperty(object, fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.debug("UniqueKeysValidator#extractValue: Cannot access property {} {}.", object.getClass(), fieldName);
        }

        return "";
    }

    private boolean hasKeys(String collectionName, Class<?> classType, Map<String, String> properties) {
        
        ExecutableFind<?> executableFind = template.query(classType);
        Criteria orCriteria = new Criteria();
        orCriteria.orOperator(createCriteria(properties));

        Query query = new Query(orCriteria);
        boolean exists = executableFind.matching(query).exists();

        log.debug("UniqueKeysValidator#hasKeys: {}", query);
        log.debug("UniqueKeysValidator#hasKeys: Document collection {} unique exists {}", collectionName, exists);
        
        return exists;
    }

    private List<Criteria> createCriteria(Map<String, String> properties) {
        List<Criteria> criteriaList = new ArrayList<Criteria>();

        for (String key : keys) {

            Criteria criteria = where(key).is(properties.get(key));
            
            if (properties.containsKey(mongoId)) {
                criteria.and(mongoId).ne(properties.get(mongoId));   
            }
         
            criteriaList.add(criteria);
        }

        return criteriaList;
    }
    
}
