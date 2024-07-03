---
title: Exploring Rule Field Persistence Provider
---
The Rule Field Persistence Provider in BroadleafCommerce-demo is a component that handles the persistence of rule fields in the admin interface. It is specifically designed to handle fields of type RULE_WITH_QUANTITY, RULE_SIMPLE, and RULE_SIMPLE_TIME. This provider is responsible for populating and extracting values for these rule fields during persistence operations. It also handles the filtering of properties during an add or update operation in the admin interface.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="76">

---

# RuleFieldPersistenceProvider Class

This is the RuleFieldPersistenceProvider class. It extends the FieldPersistenceProviderAdapter and is annotated as a Spring component. It contains methods to handle persistence, extraction, and filtering of properties. It also has resources for rule building, sandboxing, rule field extraction, and extension management.

```java
@Component("blRuleFieldPersistenceProvider")
@Scope("prototype")
public class RuleFieldPersistenceProvider extends FieldPersistenceProviderAdapter {

    protected boolean canHandlePersistence(PopulateValueRequest populateValueRequest, Serializable instance) {
        return populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_WITH_QUANTITY ||
                populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE ||
                populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE_TIME;
    }

    protected boolean canHandleExtraction(ExtractValueRequest extractValueRequest, Property property) {
        return extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_WITH_QUANTITY ||
                extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE ||
                extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE_TIME;
    }

    @Resource(name = "blRuleBuilderFieldServiceFactory")
    protected RuleBuilderFieldServiceFactory ruleBuilderFieldServiceFactory;

    @Resource(name = "blSandBoxHelper")
    protected SandBoxHelper sandBoxHelper;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="769">

---

# getOrder Method

The getOrder method is used to get the order of the RuleFieldPersistenceProvider. It returns the RULE constant from the FieldPersistenceProvider class.

```java
    public int getOrder() {
        return FieldPersistenceProvider.RULE;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="107">

---

# populateValue Method

The populateValue method is used to set the property value on the target object. It checks if the persistence request can be handled, sets non-displayable values, and populates the value based on the field type. If an exception occurs, it throws a PersistenceException.

```java
    @Override
    public MetadataProviderResponse populateValue(PopulateValueRequest populateValueRequest, Serializable instance) throws PersistenceException {
        if (!canHandlePersistence(populateValueRequest, instance)) {
            return MetadataProviderResponse.NOT_HANDLED;
        }
        boolean dirty = false;
        try {
            setNonDisplayableValues(populateValueRequest);
            switch (populateValueRequest.getMetadata().getFieldType()) {
                case RULE_WITH_QUANTITY:{
                    dirty = populateQuantityRule(populateValueRequest, instance);
                    break;
                }
                case RULE_SIMPLE:{
                    dirty = populateSimpleRule(populateValueRequest, instance);
                    break;
                }
                case RULE_SIMPLE_TIME:{
                    dirty = populateSimpleRule(populateValueRequest, instance);
                    break;
                }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="154">

---

# filterProperties Method

The filterProperties method is used to filter the list of properties posted by the admin during an add or update. It converts and filters out rule Json fields, and adjusts the properties of the entity.

```java
    @Override
    public MetadataProviderResponse filterProperties(AddFilterPropertiesRequest addFilterPropertiesRequest, Map<String, FieldMetadata> properties) {
        //This may contain rule Json fields - convert and filter out
        List<Property> propertyList = new ArrayList<Property>();
        propertyList.addAll(Arrays.asList(addFilterPropertiesRequest.getEntity().getProperties()));
        Iterator<Property> itr = propertyList.iterator();
        List<Property> additionalProperties = new ArrayList<Property>();
        while(itr.hasNext()) {
            Property prop = itr.next();
            if (prop.getName().endsWith("Json")) {
                for (Map.Entry<String, FieldMetadata> entry : properties.entrySet()) {
                    String propName = prop.getName().substring(0, prop.getName().length()-4);
                    if (propName.equals(entry.getKey())) {
                        BasicFieldMetadata originalFM = (BasicFieldMetadata) entry.getValue();
                        if (originalFM.getFieldType() == SupportedFieldType.RULE_SIMPLE ||
                                originalFM.getFieldType() == SupportedFieldType.RULE_SIMPLE_TIME ||
                                originalFM.getFieldType() == SupportedFieldType.RULE_WITH_QUANTITY) {
                            Property originalProp = addFilterPropertiesRequest.getEntity().findProperty(entry.getKey());
                            if (originalProp == null) {
                                originalProp = new Property();
                                originalProp.setName(entry.getKey());
```

---

</SwmSnippet>

# Rule Field Persistence Provider Functions

This section provides an overview of the key functions in the Rule Field Persistence Provider.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="107">

---

## populateValue

The `populateValue` function is responsible for setting the property value on the target object. It translates the requested value from the request and sets it on the instance parameter. This function is used during admin create and update events.

```java
    @Override
    public MetadataProviderResponse populateValue(PopulateValueRequest populateValueRequest, Serializable instance) throws PersistenceException {
        if (!canHandlePersistence(populateValueRequest, instance)) {
            return MetadataProviderResponse.NOT_HANDLED;
        }
        boolean dirty = false;
        try {
            setNonDisplayableValues(populateValueRequest);
            switch (populateValueRequest.getMetadata().getFieldType()) {
                case RULE_WITH_QUANTITY:{
                    dirty = populateQuantityRule(populateValueRequest, instance);
                    break;
                }
                case RULE_SIMPLE:{
                    dirty = populateSimpleRule(populateValueRequest, instance);
                    break;
                }
                case RULE_SIMPLE_TIME:{
                    dirty = populateSimpleRule(populateValueRequest, instance);
                    break;
                }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="80">

---

## canHandlePersistence

The `canHandlePersistence` function checks if the provider can handle the persistence of the given value request and instance. It returns true if the field type is either RULE_WITH_QUANTITY, RULE_SIMPLE, or RULE_SIMPLE_TIME.

```java
    protected boolean canHandlePersistence(PopulateValueRequest populateValueRequest, Serializable instance) {
        return populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_WITH_QUANTITY ||
                populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE ||
                populateValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE_TIME;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="86">

---

## canHandleExtraction

The `canHandleExtraction` function checks if the provider can handle the extraction of the given value request and property. It returns true if the field type is either RULE_WITH_QUANTITY, RULE_SIMPLE, or RULE_SIMPLE_TIME.

```java
    protected boolean canHandleExtraction(ExtractValueRequest extractValueRequest, Property property) {
        return extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_WITH_QUANTITY ||
                extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE ||
                extractValueRequest.getMetadata().getFieldType() == SupportedFieldType.RULE_SIMPLE_TIME;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="351">

---

## getRuleId

The `getRuleId` function retrieves the identifier of the rule from the Hibernate entity. It is used to get the unique identifier of a rule for persistence purposes.

```java
    protected Long getRuleId(SimpleRule rule, EntityManager em) {
        if (!em.contains(rule)) {
            rule = em.merge(rule);
        }

        Long id = (Long) em.unwrap(Session.class).getIdentifier(rule);
        id = transformId(id, rule);
        return id;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="361">

---

## getContainedRuleId

The `getContainedRuleId` function retrieves the identifier of the contained rule if applicable. It is used to get the unique identifier of a rule that is contained within another rule for persistence purposes.

```java
    protected Long getContainedRuleId(SimpleRule simpleRule, EntityManager em) {
        Long containedId = null;

        Object containedRule = findContainedRuleIfApplicable(simpleRule);
        if (containedRule != null) {
            if (!em.contains(containedRule)) {
                containedRule = em.merge(containedRule);
            }

            containedId = (Long) em.unwrap(Session.class).getIdentifier(containedRule);
            containedId = transformId(containedId, containedRule);

        }

        return containedId;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/RuleFieldPersistenceProvider.java" line="446">

---

## isEmbeddable

The `isEmbeddable` function checks if the class of the instance is embeddable. If it is, we don't want to check if the entity manager contains the instance since embeddables are not entities.

```java
    /**
     * This method is responsible for determining whether the class is embeddable. If it is we don't want to check if
     * the entity manager contains the instance since embeddables are not entities.
     *
     * @param clazz the parent class of the populate value request
     * @return whether the class is embeddable
     */
    protected boolean isEmbeddable(Class<?> clazz) {
        return AnnotationUtils.findAnnotation(clazz, Embeddable.class) != null;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
