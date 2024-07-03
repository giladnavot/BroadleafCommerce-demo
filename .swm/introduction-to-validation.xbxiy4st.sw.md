---
title: Introduction to Validation
---
Validation in BroadleafCommerce-demo refers to the process of checking the data integrity and correctness before processing it. It is used to ensure that the data meets the required format and constraints. For instance, the SystemPropertyAttributeNameValidator class validates system property attribute names, ensuring they do not contain disallowed characters or reserved keywords. Similarly, the OfferQualifyingCriteriaValidator and OfferTargetItemCriteriaValidator classes validate offer criteria. These validations help maintain the consistency and reliability of the data in the application.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="39">

---

# SystemPropertyAttributeNameValidator

The `SystemPropertyAttributeNameValidator` class is a specific implementation of a validator. It validates that a SystemProperty's AttributeName field does not contain a reserved keyword surrounded by '.'. If the validation fails, it returns a `PropertyValidationResult` with an error message.

```java
/**
 * Validates that a SystemProperty's AttributeName field does not contain a reserved key word surrounded by ".".
 *  AttributeNames such as "should.not.fail" will be converted to "should__not__fail" by JSCompatibilityHelper.
 *  This will later lead to a Thymeleaf exception when it attempts to process #fields.hasErrors('fields[should__not__fail].value')
 *  in entityForm.html.
 * 
 * 
 * @author Chris Kittrell (ckittrell)
 */
@Component("blSystemPropertyAttributeNameValidator")
public class SystemPropertyAttributeNameValidator extends ValidationConfigurationBasedPropertyValidator {

    protected static final Log LOG = LogFactory.getLog(SystemPropertyAttributeNameValidator.class);

    private static final List<String> reservedKeywords = new ArrayList(Arrays.asList("not", "and", "or", "gt", "lt", "ge", "le", "eq", "ne"));
    private static final String RESERVED_WORD_ERROR_MESSAGE = "SystemPropertyImpl_name_reservedWordError";
    private static final String DISALLOWED_CHARACTERS_ERROR_MESSAGE = "SystemPropertyImpl_name_disallowedCharactersError";

    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration, BasicFieldMetadata propertyMetadata, String propertyName,
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="57">

---

# Validation Methods

The `validate` method is the main validation method. It checks if the attribute name contains white space or any characters other than letters, numbers, periods, and dashes. If it does, it calls the `createDisallowedCharactersValidationResult` method to create a validation result with an error message.

```java
    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration, BasicFieldMetadata propertyMetadata, String propertyName,
            String value) {
        String attributeName = entity.findProperty("name") == null ? null : entity.findProperty("name").getValue();

        if (attributeName != null) {
            if (containsWhiteSpace(attributeName) || !containsOnlyLettersNumbersPeriodsDashes(attributeName)) {
                return createDisallowedCharactersValidationResult();
            }

            Set<String> containedReservedKeywords = retrieveContainedReservedKeywords(attributeName);

            if (!containedReservedKeywords.isEmpty()) {
                return createContainsReservedKeywordsValidationResult(containedReservedKeywords);
            }
        }

        return new PropertyValidationResult(true);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="121">

---

# Error Message Retrieval

The `getDisallowedCharactersErrorMesssage` method retrieves the error message for disallowed characters. This message is used in the `createDisallowedCharactersValidationResult` method to create a validation result with an error message.

```java
    private String getDisallowedCharactersErrorMesssage() {
        return BLCMessageUtils.getMessage(DISALLOWED_CHARACTERS_ERROR_MESSAGE);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="86">

---

# Reserved Keywords Check

The `retrieveContainedReservedKeywords` method checks if the attribute name contains any reserved keywords. If it does, it returns a set of the contained reserved keywords.

```java
    private Set<String> retrieveContainedReservedKeywords(String attributeName) {
        Set<String> containedReservedKeywords = new LinkedHashSet<>();

        List<String> attributeNamePieces = new ArrayList<>(Arrays.asList(attributeName.split("\\.")));

        attributeNamePieces = removeFirstAndLastPieces(attributeNamePieces);

        for (String attributeNamePiece: attributeNamePieces) {
            if (reservedKeywords.contains(attributeNamePiece)) {
                containedReservedKeywords.add(attributeNamePiece);
            }
        }
        return containedReservedKeywords;
    }
```

---

</SwmSnippet>

# Validation Functions

Let's delve into the SystemPropertyAttributeNameValidator function.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="48">

---

## SystemPropertyAttributeNameValidator

The `SystemPropertyAttributeNameValidator` class extends `ValidationConfigurationBasedPropertyValidator` and is used to validate system property attribute names. It checks if the attribute name contains any reserved keywords or disallowed characters. If the attribute name fails the validation, it returns a `PropertyValidationResult` with an error message.

```java
@Component("blSystemPropertyAttributeNameValidator")
public class SystemPropertyAttributeNameValidator extends ValidationConfigurationBasedPropertyValidator {

    protected static final Log LOG = LogFactory.getLog(SystemPropertyAttributeNameValidator.class);

    private static final List<String> reservedKeywords = new ArrayList(Arrays.asList("not", "and", "or", "gt", "lt", "ge", "le", "eq", "ne"));
    private static final String RESERVED_WORD_ERROR_MESSAGE = "SystemPropertyImpl_name_reservedWordError";
    private static final String DISALLOWED_CHARACTERS_ERROR_MESSAGE = "SystemPropertyImpl_name_disallowedCharactersError";

    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration, BasicFieldMetadata propertyMetadata, String propertyName,
            String value) {
        String attributeName = entity.findProperty("name") == null ? null : entity.findProperty("name").getValue();

        if (attributeName != null) {
            if (containsWhiteSpace(attributeName) || !containsOnlyLettersNumbersPeriodsDashes(attributeName)) {
                return createDisallowedCharactersValidationResult();
            }

            Set<String> containedReservedKeywords = retrieveContainedReservedKeywords(attributeName);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="57">

---

### validate Method

The `validate` method is the main method in this class. It takes in several parameters including the entity, instance, entityFieldMetadata, validationConfiguration, propertyMetadata, propertyName, and value. It retrieves the attribute name from the entity and checks if it contains any whitespace or disallowed characters. If it does, it returns a validation result with an error message. It also checks if the attribute name contains any reserved keywords. If it does, it returns a validation result with an error message.

```java
    @Override
    public PropertyValidationResult validate(Entity entity, Serializable instance, Map<String, FieldMetadata> entityFieldMetadata,
            Map<String, String> validationConfiguration, BasicFieldMetadata propertyMetadata, String propertyName,
            String value) {
        String attributeName = entity.findProperty("name") == null ? null : entity.findProperty("name").getValue();

        if (attributeName != null) {
            if (containsWhiteSpace(attributeName) || !containsOnlyLettersNumbersPeriodsDashes(attributeName)) {
                return createDisallowedCharactersValidationResult();
            }

            Set<String> containedReservedKeywords = retrieveContainedReservedKeywords(attributeName);

            if (!containedReservedKeywords.isEmpty()) {
                return createContainsReservedKeywordsValidationResult(containedReservedKeywords);
            }
        }

        return new PropertyValidationResult(true);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/persistence/validation/SystemPropertyAttributeNameValidator.java" line="78">

---

### Helper Methods

There are several helper methods in this class such as `containsWhiteSpace`, `containsOnlyLettersNumbersPeriodsDashes`, `retrieveContainedReservedKeywords`, and `removeFirstAndLastPieces`. These methods are used within the `validate` method to perform specific validation checks.

```java
    private boolean containsWhiteSpace(String attributeName) {
        return Pattern.compile("\\s").matcher(attributeName).find();
    }

    private boolean containsOnlyLettersNumbersPeriodsDashes(String attributeName) {
        return attributeName.replaceAll("\\.", "").replaceAll("-", "").matches("([a-zA-Z0-9])\\w+");
    }

    private Set<String> retrieveContainedReservedKeywords(String attributeName) {
        Set<String> containedReservedKeywords = new LinkedHashSet<>();

        List<String> attributeNamePieces = new ArrayList<>(Arrays.asList(attributeName.split("\\.")));

        attributeNamePieces = removeFirstAndLastPieces(attributeNamePieces);

        for (String attributeNamePiece: attributeNamePieces) {
            if (reservedKeywords.contains(attributeNamePiece)) {
                containedReservedKeywords.add(attributeNamePiece);
            }
        }
        return containedReservedKeywords;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
