---
title: Understanding Field Persistence Provider
---
The Field Persistence Provider in Broadleaf Commerce is an interface that handles persistence-related events for fields in the admin section of the application. It is capable of handling special translations or transformations required to get from the string representation in the admin back to the field on a Hibernate entity and vice versa. This includes creating, updating, and fetching events. It also provides the ability to add search mappings and filter properties. The Field Persistence Provider is part of the Basic Persistence Module, and it is typically extended by other classes to handle specific types of fields, such as media fields, rule fields, and money fields.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/FieldPersistenceProvider.java" line="34">

---

# FieldPersistenceProvider Interface

This is the FieldPersistenceProvider interface. It defines the methods that a Field Persistence Provider must implement. The methods include populateValue for setting the property value on the target object, extractValue for retrieving the property value, addSearchMapping for adding filter mappings, and filterProperties for filtering the list of properties posted by the admin during an add or update. It also includes the alwaysRun method to determine if the provider should always run, and the canHandlePopulateNull method to determine if the provider should handle populating null values.

```java
/**
 * Classes implementing this interface are capable of handling persistence related events for fields whose values
 * are being requested or set for the admin. This includes any special translations or transformations required to get
 * from the string representation in the admin back to the field on a Hibernate entity - and the reverse. Providers are
 * typically added in response to new admin presentation annotation support that requires special persistence behavior.
 * Note, {@link FieldPersistenceProvider} instances are part of {@link org.broadleafcommerce.openadmin.server.service.persistence.module.BasicPersistenceModule},
 * and therefore relate to variations on persistence of basic fields. Implementers should generally
 * extend {@link FieldPersistenceProviderAdapter}.
 *
 * @see org.broadleafcommerce.openadmin.server.service.persistence.module.PersistenceModule
 * @author Jeff Fischer
 */
public interface FieldPersistenceProvider extends Ordered {

    //standard ordering constants for BLC providers
    public static final int BASIC = Integer.MAX_VALUE;
    /**
     * The {@link MediaFieldPersistenceProvider} MUST come prior to the normal Map field provider since they can both
     * respond to the same type of map fields. However, the Media fields are a special case since it needs to parse out the
     * Media DTO
     */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/AbstractFieldPersistenceProvider.java" line="36">

---

# AbstractFieldPersistenceProvider Class

This is the AbstractFieldPersistenceProvider class. It is an abstract class that implements the FieldPersistenceProvider interface. It provides default implementations for some of the methods in the interface. This class can be extended by other classes to inherit these default implementations.

```java
public abstract class AbstractFieldPersistenceProvider implements FieldPersistenceProvider {

    protected Class<?> getListFieldType(Serializable instance, FieldManager fieldManager, Property property, PersistenceManager persistenceManager) {
        Class<?> returnType = null;
        Field field = fieldManager.getField(instance.getClass(), property.getName());
        java.lang.reflect.Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];
            Class<?>[] entities = persistenceManager.getDynamicEntityDao().getAllPolymorphicEntitiesFromCeiling(clazz);
            if (!ArrayUtils.isEmpty(entities)) {
                returnType = entities[entities.length-1];
            }
        }
        return returnType;
    }

    protected Class<?> getMapFieldType(Serializable instance, FieldManager fieldManager, Property property, PersistenceManager persistenceManager) {
        Class<?> returnType = null;
        Field field = fieldManager.getField(instance.getClass(), property.getName().substring(0, property.getName().indexOf(FieldManager.MAPFIELDSEPARATOR)));
        java.lang.reflect.Type type = field.getGenericType();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/FieldPersistenceProviderAdapter.java" line="43">

---

# FieldPersistenceProviderAdapter Class

This is the FieldPersistenceProviderAdapter class. It extends the AbstractFieldPersistenceProvider class and provides default implementations for all the methods in the FieldPersistenceProvider interface. This class can be extended by other classes to inherit these default implementations and override them as needed.

```java
public class FieldPersistenceProviderAdapter extends AbstractFieldPersistenceProvider {

    @Override
    public MetadataProviderResponse addSearchMapping(AddSearchMappingRequest addSearchMappingRequest, List<FilterMapping> filterMappings) {
        return MetadataProviderResponse.NOT_HANDLED;
    }

    @Override
    public MetadataProviderResponse populateValue(PopulateValueRequest populateValueRequest, Serializable instance) {
        return MetadataProviderResponse.NOT_HANDLED;
    }

    @Override
    public MetadataProviderResponse extractValue(ExtractValueRequest extractValueRequest, Property property) {
        return MetadataProviderResponse.NOT_HANDLED;
    }

    @Override
    public MetadataProviderResponse filterProperties(AddFilterPropertiesRequest addFilterPropertiesRequest, Map<String, FieldMetadata> properties) {
        return MetadataProviderResponse.NOT_HANDLED;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/BasicPersistenceModule.java" line="157">

---

# Use of FieldPersistenceProvider in BasicPersistenceModule

This is an example of how the FieldPersistenceProvider is used in the BasicPersistenceModule. The fieldPersistenceProviders list is populated with instances of FieldPersistenceProvider. The providers are sorted based on their order, and then used to handle the persistence of fields.

```java
    @Resource(name = "blPersistenceProviders")
    protected List<FieldPersistenceProvider> fieldPersistenceProviders = new ArrayList<FieldPersistenceProvider>();

    @Resource(name = "blPopulateValueRequestValidators")
    protected List<PopulateValueRequestValidator> populateValidators;

    @Resource(name = "blDefaultFieldPersistenceProvider")
    protected FieldPersistenceProvider defaultFieldPersistenceProvider;

    @Resource(name = "blCriteriaTranslator")
    protected CriteriaTranslator criteriaTranslator;

    @Resource(name = "blRestrictionFactory")
    protected RestrictionFactory restrictionFactory;

    @Resource(name = "blBasicPersistenceModuleExtensionManager")
    protected BasicPersistenceModuleExtensionManager extensionManager;

    @Resource(name = "blFetchWrapper")
    protected FetchWrapper fetchWrapper;

```

---

</SwmSnippet>

# Field Persistence Provider Functions

In this section, we will delve into the key functions of the Field Persistence Provider, namely populateValue and extractValue.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/FieldPersistenceProvider.java" line="75">

---

## Populate Value

The `populateValue` function is used to set the property value on the target object. It translates the requested value from the request and sets it on the instance parameter. Essentially, it converts the string value submitted by the admin application into the format required to set on the target field of the instance, which should be a JPA managed entity. This function is used during admin create and update events.

```java
    MetadataProviderResponse populateValue(PopulateValueRequest populateValueRequest, Serializable instance);

    /**
     * Retrieve the property value from the requestedValue field from the request. Implementations should translate the requestedValue
     * and set on the property parameter. The requestedValue is the field value taken from the JPA managed entity instance.
     * You are taking this field value and converting it into a string representation appropriate for the <tt>property</tt>
     * instance parameter. Used during admin fetch events.
     *
     * @param extractValueRequest contains the requested value and support classes.
     * @param property the property for the admin that will contain the information harvested from the persistence value
     * @return whether or not the implementation handled the persistence request
     */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/provider/FieldPersistenceProvider.java" line="87">

---

## Extract Value

The `extractValue` function retrieves the property value from the requested value field from the request. It translates the requested value and sets it on the property parameter. The requested value is the field value taken from the JPA managed entity instance. This function converts this field value into a string representation appropriate for the property instance parameter. This function is used during admin fetch events.

```java
    MetadataProviderResponse extractValue(ExtractValueRequest extractValueRequest, Property property);

    /**
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
