---
title: Understanding Adorned Target Auto Populate Extension
---
The AdornedTargetAutoPopulateExtension in BroadleafCommerce-demo refers to a mechanism that automatically sets the values for one or more adorned target collection managed fields. This can allow, through code, partial or total completion of the form in the second tab on an adorned target add interaction. It provides a hook for validation during persistence and the implementation is responsible for determining suitability based on the property information passed in.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/extension/AdornedTargetAutoPopulateExtensionManager.java" line="28">

---

## AdornedTargetAutoPopulateExtensionManager

The AdornedTargetAutoPopulateExtensionManager is a manager for the AdornedTargetAutoPopulateExtensionHandler. It extends the ExtensionManager class, which is a part of Broadleaf's extension mechanism. The manager is annotated with @Component, which means it is a Spring Bean and can be autowired into other classes.

```java
@Component("blAdornedTargetAutoPopulateExtensionManager")
public class AdornedTargetAutoPopulateExtensionManager extends ExtensionManager<AdornedTargetAutoPopulateExtensionHandler> {

    public AdornedTargetAutoPopulateExtensionManager() {
        super(AdornedTargetAutoPopulateExtensionHandler.class);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/extension/AdornedTargetAutoPopulateExtensionHandler.java" line="35">

---

## AdornedTargetAutoPopulateExtensionHandler

The AdornedTargetAutoPopulateExtensionHandler interface defines two methods: autoSetAdornedTargetManagedFields and validateSubmittedAdornedTargetManagedFields. The autoSetAdornedTargetManagedFields method provides a hook for automatically setting the values for one or more adorned target collection managed fields. The validateSubmittedAdornedTargetManagedFields method provides a hook for validation during persistence.

```java
public interface AdornedTargetAutoPopulateExtensionHandler extends ExtensionHandler {

    /**
     * Provides a hook for automatically setting the values for one or more adorned target collection managed fields. This can
     * allow, through code, partial or total completion of the form in the second tab on an adorned target add interaction.
     * Note, a special key/value pair can be included put into the managedField map to cause the second tab to be skipped
     * and the adorned target item add form to auto submit after completing the first tab.
     *
     * @param md the metadata describing the adorned target collection field
     * @param mainClassName the class name of the entity that contains this adorned target field
     * @param id the id of the containing entity
     * @param collectionField the name of the adorned target field
     * @param collectionItemId the id of the adorned target collection member
     * @param managedFields the map containing the adorned target field values that should be auto populated
     * @return the final status of the operation
     */
    public ExtensionResultStatusType autoSetAdornedTargetManagedFields(FieldMetadata md, String mainClassName,
                                   String id, String collectionField, String collectionItemId, Map<String, Object> managedFields);

    /**
     * Provide validation during persistence. The implementation is responsible for determining suitability based
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/web/controller/entity/AdminBasicEntityController.java" line="1081">

---

## Using AdornedTargetAutoPopulateExtension

The AdornedTargetAutoPopulateExtension is used in the AdminBasicEntityController class. The autoSetAdornedTargetManagedFields method of the AdornedTargetAutoPopulateExtensionManager is called when adding a new item to an adorned target collection. This allows for automatic population of certain fields in the form.

```java
        if (md instanceof AdornedTargetCollectionMetadata) {
            adornedTargetAutoPopulateExtensionManager.getProxy().autoSetAdornedTargetManagedFields(md, mainClassName, id,
                    collectionField,
```

---

</SwmSnippet>

# AdornedTargetAutoPopulateExtension Functions

This section discusses the main functions of the AdornedTargetAutoPopulateExtension.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/extension/AdornedTargetAutoPopulateExtensionHandler.java" line="37">

---

## autoSetAdornedTargetManagedFields

The `autoSetAdornedTargetManagedFields` function is a hook for automatically setting the values for one or more adorned target collection managed fields. This can allow, through code, partial or total completion of the form in the second tab on an adorned target add interaction. It is used in the `AdminBasicEntityController` class within the `addCollectionItem` and `showViewUpdateCollection` methods.

```java
    /**
     * Provides a hook for automatically setting the values for one or more adorned target collection managed fields. This can
     * allow, through code, partial or total completion of the form in the second tab on an adorned target add interaction.
     * Note, a special key/value pair can be included put into the managedField map to cause the second tab to be skipped
     * and the adorned target item add form to auto submit after completing the first tab.
     *
     * @param md the metadata describing the adorned target collection field
     * @param mainClassName the class name of the entity that contains this adorned target field
     * @param id the id of the containing entity
     * @param collectionField the name of the adorned target field
     * @param collectionItemId the id of the adorned target collection member
     * @param managedFields the map containing the adorned target field values that should be auto populated
     * @return the final status of the operation
     */
    public ExtensionResultStatusType autoSetAdornedTargetManagedFields(FieldMetadata md, String mainClassName,
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/extension/AdornedTargetAutoPopulateExtensionHandler.java" line="54">

---

## validateSubmittedAdornedTargetManagedFields

The `validateSubmittedAdornedTargetManagedFields` function provides validation during persistence. The implementation is responsible for determining suitability based on the property information passed in. It is used in the `AdornedTargetMaintainedFieldPropertyValidator` class within the `validate` method.

```java
    /**
     * Provide validation during persistence. The implementation is responsible for determining suitability based
     * on the property information passed in.
     *
     * @param entity all the values passed from the client for persistence
     * @param instance the entity instance that is being populated
     * @param entityFieldMetadata the {@link FieldMetadata} for all the fields
     * @param propertyMetadata the {@link FieldMetadata} for the property
     * @param propertyName the name of the field
     * @param value the value being assigned to the property
     * @param validationResult whether or not the property passes validation
     * @return the final status of the operation
     */
    public ExtensionResultStatusType validateSubmittedAdornedTargetManagedFields(Entity entity, Serializable instance,
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
