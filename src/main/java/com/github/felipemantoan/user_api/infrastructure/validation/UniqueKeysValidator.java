package com.github.felipemantoan.user_api.infrastructure.validation;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableFindOperation.ExecutableFind;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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

    public void initialize(UniqueKeys constraintAnnotation) {
        documentId = constraintAnnotation.id();
        mongoId = constraintAnnotation.mongoId();
        keys = Set.of(constraintAnnotation.keys());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {        
        Map<String, String> properties = mapValues(value);
        return !executeQuery(value.getClass(), properties);
    }

    private Map<String, String> mapValues(Object object) {
        Map<String, String> fieldsValues = new HashMap<String, String>();

        for (String fieldName : keys) {
            String value = extractValue(object, fieldName);
            
            if (!Strings.isBlank(value)) {
                fieldsValues.put(fieldName, value);
            }
        }

        String idValue = extractValue(object, documentId);

        if (!Strings.isBlank(idValue)) {
            fieldsValues.put(mongoId, idValue);
        }

        return fieldsValues;
    }

    private String extractValue(Object object, String fieldName) {
        try {
            return BeanUtils.getSimpleProperty(object, fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.info("Can't not access {}", fieldName);
        }

        return null;
    }

    private boolean executeQuery(Class<?> classType,  Map<String, String> properties) {

        String collectionName = template.getCollectionName(classType);
        ExecutableFind<?> executableFind = template.query(classType);
        Criteria orCriteria = new Criteria();
        orCriteria.orOperator(createCriteria(properties));

        Query query = new Query(orCriteria);
        boolean exists = executableFind.matching(query).exists();

        log.info("UniqueKeysValidator#executeQuery: {}", query);
        log.info("UniqueKeysValidator#executeQuery: Document collection {} unique exists {}", collectionName, exists);
        return exists;
    }

    private List<Criteria> createCriteria(Map<String, String> properties) {
        List<Criteria> criteriaList = new ArrayList<Criteria>();

        for (String key : keys) {
            if (properties.containsKey(mongoId)) {
                Criteria fieldWithOtherId = where(mongoId)
                    .ne(properties.get(mongoId))
                    .and(key)
                    .is(properties.get(key));
                
                criteriaList.add(fieldWithOtherId);
            }
            else {
                criteriaList.add(where(key).is(properties.get(key)));
            }
        }

        return criteriaList;
    }
    
}
