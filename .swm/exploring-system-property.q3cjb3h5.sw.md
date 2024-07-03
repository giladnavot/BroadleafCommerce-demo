---
title: Exploring System Property
---
System Property in BroadleafCommerce-demo refers to a specific type of entity used to manage configurable properties within the system. It is used to store and retrieve various configuration values that can be used across the application. The SystemProperty entity is manipulated through the `SystemPropertyCustomPersistenceHandler` class, which provides methods for adding and updating system properties. The `validateTypeAndValueCombo` method is used to ensure that the value of a system property is valid for its specified type.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/SystemPropertyCustomPersistenceHandler.java" line="65">

---

# Using System Property

In this `update` method, an instance of `SystemProperty` is retrieved and updated with values from the form. The updated instance is then merged back into the database. This is an example of how System Property is used to manage system configurations.

```java
    @Override
    public Entity update(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) 
            throws ServiceException {
        Entity entity = persistencePackage.getEntity();
        try {
            // Get an instance of SystemProperty with the updated values from the form
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            Map<String, FieldMetadata> adminProperties = helper.getSimpleMergedProperties(SystemProperty.class.getName(), persistencePerspective);
            Object primaryKey = helper.getPrimaryKey(entity, adminProperties);
            SystemProperty adminInstance = (SystemProperty) dynamicEntityDao.retrieve(Class.forName(entity.getType()[0]), primaryKey);
            adminInstance = (SystemProperty) helper.createPopulatedInstance(adminInstance, entity, adminProperties, false);

            // Verify that the value entered matches up with the type of this property
            Entity errorEntity = validateTypeAndValueCombo(adminInstance);
            if (errorEntity != null) {
                entity.setPropertyValidationErrors(errorEntity.getPropertyValidationErrors());
                return entity;
            }

            adminInstance = (SystemProperty) dynamicEntityDao.merge(adminInstance);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/TranslationCustomPersistenceHandler.java" line="77">

---

# Retrieving System Property

In the `add` method, an instance of `SystemProperty` is created and populated with values from the form. This instance is then used to check for duplicates before being merged into the database. This is an example of how System Property is used to ensure data integrity.

```java
    @Override
    public Entity add(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        Entity entity = persistencePackage.getEntity();
        try {
            // Get an instance of SystemProperty with the updated values from the form
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            Translation adminInstance = (Translation) Class.forName(entity.getType()[0]).newInstance();
            Map<String, FieldMetadata> adminProperties = helper.getSimpleMergedProperties(Translation.class.getName(), persistencePerspective);
            adminInstance = (Translation) helper.createPopulatedInstance(adminInstance, entity, adminProperties, false);

            // We only want to check for duplicates during a save
            if (!sandBoxHelper.isReplayOperation()) {
                Translation res = translationService.getTranslation(adminInstance.getEntityType(), adminInstance.getEntityId(), adminInstance.getFieldName(), adminInstance.getLocaleCode());
                if (res != null) {
                    Entity errorEntity = new Entity();
                    errorEntity.setType(new String[] { res.getClass().getName() });
                    errorEntity.addValidationError("localeCode", "translation.record.exists.for.locale");
                    return errorEntity;
                }
            }
            persistencePackage.setRequestingEntityName(adminInstance.getEntityType().getFriendlyType() + "|" + adminInstance.getFieldName() + "|" + adminInstance.getLocaleCode());
```

---

</SwmSnippet>

# System Property Functions

This section will cover the main functions of the System Property: update, add, and validateTypeAndValueCombo.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/SystemPropertyCustomPersistenceHandler.java" line="65">

---

## Update Function

The `update` function is used to update a system property. It retrieves the system property instance with the updated values from the form, validates the value entered against the type of the property, and merges the updated instance. If there's an error during the process, it throws a ServiceException.

```java
    @Override
    public Entity update(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) 
            throws ServiceException {
        Entity entity = persistencePackage.getEntity();
        try {
            // Get an instance of SystemProperty with the updated values from the form
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            Map<String, FieldMetadata> adminProperties = helper.getSimpleMergedProperties(SystemProperty.class.getName(), persistencePerspective);
            Object primaryKey = helper.getPrimaryKey(entity, adminProperties);
            SystemProperty adminInstance = (SystemProperty) dynamicEntityDao.retrieve(Class.forName(entity.getType()[0]), primaryKey);
            adminInstance = (SystemProperty) helper.createPopulatedInstance(adminInstance, entity, adminProperties, false);

            // Verify that the value entered matches up with the type of this property
            Entity errorEntity = validateTypeAndValueCombo(adminInstance);
            if (errorEntity != null) {
                entity.setPropertyValidationErrors(errorEntity.getPropertyValidationErrors());
                return entity;
            }

            adminInstance = (SystemProperty) dynamicEntityDao.merge(adminInstance);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/TranslationCustomPersistenceHandler.java" line="77">

---

## Add Function

The `add` function is used to add a new system property. It creates a new instance of the system property with the values from the form, checks for duplicates, and merges the new instance. If there's an error during the process, it throws a ServiceException.

```java
    @Override
    public Entity add(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        Entity entity = persistencePackage.getEntity();
        try {
            // Get an instance of SystemProperty with the updated values from the form
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            Translation adminInstance = (Translation) Class.forName(entity.getType()[0]).newInstance();
            Map<String, FieldMetadata> adminProperties = helper.getSimpleMergedProperties(Translation.class.getName(), persistencePerspective);
            adminInstance = (Translation) helper.createPopulatedInstance(adminInstance, entity, adminProperties, false);

            // We only want to check for duplicates during a save
            if (!sandBoxHelper.isReplayOperation()) {
                Translation res = translationService.getTranslation(adminInstance.getEntityType(), adminInstance.getEntityId(), adminInstance.getFieldName(), adminInstance.getLocaleCode());
                if (res != null) {
                    Entity errorEntity = new Entity();
                    errorEntity.setType(new String[] { res.getClass().getName() });
                    errorEntity.addValidationError("localeCode", "translation.record.exists.for.locale");
                    return errorEntity;
                }
            }
            persistencePackage.setRequestingEntityName(adminInstance.getEntityType().getFriendlyType() + "|" + adminInstance.getFieldName() + "|" + adminInstance.getLocaleCode());
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/SystemPropertyCustomPersistenceHandler.java" line="119">

---

## ValidateTypeAndValueCombo Function

The `validateTypeAndValueCombo` function is used to validate the value of a system property against its type. It checks if the given value is valid for the specified type and returns an error entity if the value is not valid.

```java
    protected Entity validateTypeAndValueCombo(SystemProperty prop) {
        if (!spService.isValueValidForType(prop.getValue(), prop.getPropertyType())) {
            Entity errorEntity = new Entity();
            errorEntity.addValidationError("value", "valueIllegalForPropertyType");
            return errorEntity;
        }

        return null;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
