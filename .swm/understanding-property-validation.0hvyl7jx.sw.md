---
title: Understanding Property Validation
---
Property Validation in BroadleafCommerce-demo refers to the process of checking the data of an entity's properties against certain conditions or rules. This is done to ensure the integrity and correctness of the data before it is used or stored. The validation process is implemented through various classes and interfaces, such as `PropertyValidator`, `GlobalPropertyValidator`, `PropertyValidationResult`, and `GlobalValidationResult`.

`PropertyValidator` is an interface that defines the `validate` method. This method takes several parameters including the entity to be validated, the instance of the entity, metadata of the entity's fields, validation configuration, metadata of the property to be validated, the property's name, and its value. The method returns a `PropertyValidationResult` object which indicates whether the validation passed or failed.

`GlobalPropertyValidator` is similar to `PropertyValidator` but it does not use any `ValidationConfiguration` from an `AdminPresentation` annotation. These global validators will execute on every field of every entity that is being validated.

`PropertyValidationResult` is a class that extends `GlobalValidationResult`. It represents the result of a property validation. It contains a boolean value indicating whether the validation passed or failed, and an error message if the validation failed.

`GlobalValidationResult` is a class that represents a validation result. It contains a boolean value indicating whether the validation passed or failed, and a list of error messages if the validation failed.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/GlobalValidationResult.java" line="26">

---

# GlobalValidationResult Class

The `GlobalValidationResult` class is a Data Transfer Object (DTO) that represents the result of a validation. It contains a boolean indicating whether the validation passed or not, and a list of error messages. It provides methods to set the validation result, add error messages, and check if the validation passed or not.

```java
/**
 * DTO representing a boolean whether or not it passed validation and String error message. An error message is not required
 * if the result is not an error.
 * 
 * This is most suitable for global errors like those from {@link RowLevelSecurityService}
 * 
 * @author Phillip Verheyden (phillipuniverse)
 * @see {@link RowLevelSecurityService}
 * @see {@link PropertyValidationResult}
 */
public class GlobalValidationResult {
    
    protected boolean valid;
    protected List<String> errorMessages = new ArrayList<>();
    
    public GlobalValidationResult(boolean valid, String errorMessage) {
        setValid(valid);
        addErrorMessage(errorMessage);
    }
    
    public GlobalValidationResult(boolean valid) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/PropertyValidator.java" line="30">

---

# PropertyValidator Interface

The `PropertyValidator` interface defines the `validate` method that all property validators must implement. This method takes in several parameters including the entity to validate, the instance of the entity, the field metadata, the validation configuration, the property metadata, the property name, and the value to validate. It returns a `PropertyValidationResult` object.

```java
/**
 * <p>Interface for performing validation on a property. If you are attempting to write a validator based on the
 * @ValidationConfiguration component, (which is the normal use case) consider subclassing
 * {@link ValidationConfigurationBasedPropertyValidator} and overriding
 * {@link ValidationConfigurationBasedPropertyValidator#validateInternal(Entity, Serializable, Map, Map, BasicFieldMetadata, String, String)}
 * as it provides a slightly more convenient step for getting the error message from the given configuration.</p>
 * 
 * <p>If instead you need to validate based on something else (like the field type, for instance) then you should instead
 * implement this interface directly so that you can provide your own error message.</p>
 * 
 * <p>Property validators are designed to be executed after an entity has been fully populated. If instead you would like
 * to validate {@link PopulationRequests} (which will be invoked immediately prior to populating a particular field on an
 * entity) then instead look at {@link PopulateValueRequestValidator}.</p>
 * 
 * @author Phillip Verheyden
 * @see {@link ValidationConfigurationBasedPropertyValidator}
 * @see {@link EntityValidatorService}
 * @see {@link GlobalPropertyValidator}
 */
public interface PropertyValidator {

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/PropertyValidationResult.java" line="21">

---

# PropertyValidationResult Class

The `PropertyValidationResult` class extends `GlobalValidationResult` and represents the result of a property validation. It does not add any additional functionality or data to `GlobalValidationResult`, but its existence allows for more specific typing and handling of validation results.

```java
/**
 * Empty DTO for now that just denotes that this validation error is from a property
 *
 * @author Phillip Verheyden (phillipuniverse)
 * @see {@link PropertyValidator}
 */
public class PropertyValidationResult extends GlobalValidationResult {

    public PropertyValidationResult(boolean valid, String errorMessage) {
        super(valid, errorMessage);
    }
    
    public PropertyValidationResult(boolean valid) {
        super(valid);
    }
    
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/ValidationConfigurationBasedPropertyValidator.java" line="30">

---

# ValidationConfigurationBasedPropertyValidator Class

The `ValidationConfigurationBasedPropertyValidator` class is an abstract class that provides a default implementation of the `validate` method from the `PropertyValidator` interface. This default implementation uses the validation configuration map to pre-populate the `PropertyValidationResult` based on the error message from the configuration. Subclasses of `ValidationConfigurationBasedPropertyValidator` can override the `validateInternal` method to provide their own validation logic.

```java
/**
 * Provides a default validate method that uses the validation configuration map to pull out the error key and pre-populate
 * the {@link PropertyValidationResult} based on {@link ConfigurationItem#ERROR_MESSAGE}.
 * 
 * This class should be used as your base if you are writing a validator based on a {@link ValidationConfiguration}
 *
 * @author Phillip Verheyden (phillipuniverse)
 */
public abstract class ValidationConfigurationBasedPropertyValidator implements PropertyValidator {

    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration,
            BasicFieldMetadata propertyMetadata,
            String propertyName,
            String value) {
        return new PropertyValidationResult(validateInternal(entity,
                instance,
                entityFieldMetadata,
                validationConfiguration,
                propertyMetadata,
```

---

</SwmSnippet>

# Property Validation Functions

This section will cover the main functions related to property validation in the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/ValidationConfigurationBasedPropertyValidator.java" line="30">

---

## ValidationConfigurationBasedPropertyValidator

The `ValidationConfigurationBasedPropertyValidator` class is an abstract class that provides a default validate method. This method uses the validation configuration map to pull out the error key and pre-populate the `PropertyValidationResult` based on `ConfigurationItem#ERROR_MESSAGE`. This class should be used as a base if you are writing a validator based on a `ValidationConfiguration`.

```java
/**
 * Provides a default validate method that uses the validation configuration map to pull out the error key and pre-populate
 * the {@link PropertyValidationResult} based on {@link ConfigurationItem#ERROR_MESSAGE}.
 * 
 * This class should be used as your base if you are writing a validator based on a {@link ValidationConfiguration}
 *
 * @author Phillip Verheyden (phillipuniverse)
 */
public abstract class ValidationConfigurationBasedPropertyValidator implements PropertyValidator {

    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration,
            BasicFieldMetadata propertyMetadata,
            String propertyName,
            String value) {
        return new PropertyValidationResult(validateInternal(entity,
                instance,
                entityFieldMetadata,
                validationConfiguration,
                propertyMetadata,
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/RequiredPropertyValidator.java" line="38">

---

## RequiredPropertyValidator

The `RequiredPropertyValidator` class implements the `GlobalPropertyValidator` interface. It ensures that every property that is required has a non-empty value being set. If a required property is empty, it returns a `PropertyValidationResult` with `valid` set to false and an error message.

```java
public class RequiredPropertyValidator implements GlobalPropertyValidator {

    public static String ERROR_MESSAGE = "requiredValidationFailure";
    
    @Override
    public PropertyValidationResult validate(Entity entity,
                            Serializable instance,
                            Map<String, FieldMetadata> entityFieldMetadata,
                            BasicFieldMetadata propertyMetadata,
                            String propertyName,
                            String value) {
        boolean required = BooleanUtils.isTrue(propertyMetadata.getRequired());
        if (propertyMetadata.getRequiredOverride() != null) {
            required = propertyMetadata.getRequiredOverride();
        }
        boolean valid = !(required && StringUtils.isEmpty(value));
        return new PropertyValidationResult(valid, ERROR_MESSAGE);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/validation/PropertyValidationResult.java" line="27">

---

## PropertyValidationResult

The `PropertyValidationResult` class extends `GlobalValidationResult`. It represents the result of a property validation. It contains a boolean indicating whether the validation passed or not and an error message in case of validation failure.

```java
public class PropertyValidationResult extends GlobalValidationResult {

    public PropertyValidationResult(boolean valid, String errorMessage) {
        super(valid, errorMessage);
    }
    
    public PropertyValidationResult(boolean valid) {
        super(valid);
    }
    
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
