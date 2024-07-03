---
title: Understanding Basic Persistence Module Extension
---
The Basic Persistence Module Extension in Broadleaf Commerce is a mechanism that allows for the extension of entity API calls without the need to subclass the entity. It is managed by the `BasicPersistenceModuleExtensionManager` class, which extends the `ExtensionManager` class. This manager is responsible for handling extensions related to the persistence module. The `BasicPersistenceModuleExtensionHandler` interface defines the methods that must be implemented by any extension of the basic persistence module. These methods include `rebalanceForAdd` and `rebalanceForUpdate`, which are used to maintain the balance of data when adding or updating entities. The `DefaultBasicPersistenceModuleExtensionHandler` class provides a default implementation of these methods.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/extension/BasicPersistenceModuleExtensionManager.java" line="28">

---

# BasicPersistenceModuleExtensionManager Class

The `BasicPersistenceModuleExtensionManager` class extends the `ExtensionManager` class and is responsible for managing the `BasicPersistenceModuleExtensionHandler` instances. It is annotated with `@Service` to indicate that it is a Spring-managed bean.

```java
@Service("blBasicPersistenceModuleExtensionManager")
public class BasicPersistenceModuleExtensionManager extends ExtensionManager<BasicPersistenceModuleExtensionHandler> {

    public BasicPersistenceModuleExtensionManager() {
        super(BasicPersistenceModuleExtensionHandler.class);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/extension/DefaultBasicPersistenceModuleExtensionHandler.java" line="53">

---

# DefaultBasicPersistenceModuleExtensionHandler Class

The `DefaultBasicPersistenceModuleExtensionHandler` class is the default implementation of the `BasicPersistenceModuleExtensionHandler` interface. It is also a Spring-managed bean and it has a reference to the `BasicPersistenceModuleExtensionManager` instance, which is injected by Spring at runtime.

```java
@Service("blDefaultBasicPersistenceModuleExtensionHandler")
public class DefaultBasicPersistenceModuleExtensionHandler extends AbstractBasicPersistenceModuleExtensionHandler {

    @Resource(name = "blBasicPersistenceModuleExtensionManager")
    protected BasicPersistenceModuleExtensionManager extensionManager;

    @PostConstruct
    public void init() {
        setPriority(BasicPersistenceModuleExtensionHandler.DEFAULT_PRIORITY);
        if (isEnabled()) {
            extensionManager.registerHandler(this);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/extension/DefaultBasicPersistenceModuleExtensionHandler.java" line="67">

---

# rebalanceForAdd Method

The `rebalanceForAdd` method is an example of a method that can be overridden in a `BasicPersistenceModuleExtensionHandler` implementation. This method is responsible for rebalancing the data before it is added to the database.

```java
    @Override
    public ExtensionResultStatusType rebalanceForAdd(BasicPersistenceModule basicPersistenceModule,
                                                     PersistencePackage persistencePackage, Serializable instance,
                                                     Map<String, FieldMetadata> mergedProperties,
                                                     ExtensionResultHolder<Serializable> resultHolder) {
        try {
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            ForeignKey foreignKey = (ForeignKey) persistencePerspective.getPersistencePerspectiveItems().get
                    (PersistencePerspectiveItemType.FOREIGNKEY);
            CriteriaTransferObject cto = new CriteriaTransferObject();
            FilterAndSortCriteria sortCriteria = cto.get(foreignKey.getSortField());
            sortCriteria.setSortAscending(foreignKey.getSortAscending());
            List<FilterMapping> filterMappings = basicPersistenceModule.getFilterMappings(persistencePerspective,
                    cto, persistencePackage.getCeilingEntityFullyQualifiedClassname(), mergedProperties);
            int totalRecords = basicPersistenceModule.getTotalRecords(persistencePackage
                    .getCeilingEntityFullyQualifiedClassname(), filterMappings);
            Class<?> type = basicPersistenceModule.getFieldManager().getField(instance.getClass(),
                    foreignKey.getSortField()).getType();
            boolean isBigDecimal = BigDecimal.class.isAssignableFrom(type);
            basicPersistenceModule.getFieldManager().setFieldValue(instance, foreignKey.getSortField(),
                    isBigDecimal ? new BigDecimal(totalRecords + 1) : Long.valueOf(totalRecords + 1));
```

---

</SwmSnippet>

# Basic Persistence Module Extension Functions

This section will cover the main functions of the Basic Persistence Module Extension: rebalanceForUpdate and rebalanceForAdd.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/extension/BasicPersistenceModuleExtensionHandler.java" line="38">

---

## Rebalance For Update

The `rebalanceForUpdate` function handles reorder change requests from the admin for sortable basic collections. It takes in the persistence module responsible for handling basic collection persistence operations, the data representing the change, the persisted entity, descriptive data about the entity structure, the primary key value for the persisted entity, and a container for any relevant operation results. It returns the status of execution for this handler, informing the manager on how to proceed.

```java
     * Handle reorder change requests from the admin for sortable basic collections
     *
     * @param basicPersistenceModule the persistence module responsible for handling basic collection persistence
     *                               operations
     * @param persistencePackage     the data representing the change
     * @param instance               the persisted entity
     * @param mergedProperties       descriptive data about the entity structure
     * @param primaryKey             the primary key value for the persisted entity
     * @param resultHolder           container for any relevant operation results
     * @return the status of execution for this handler - informs the manager on how to proceed
     */
    ExtensionResultStatusType rebalanceForUpdate(BasicPersistenceModule basicPersistenceModule,
                                                 PersistencePackage persistencePackage, Serializable instance,
                                                 Map<String, FieldMetadata> mergedProperties, Object primaryKey,
                                                 ExtensionResultHolder<Serializable> resultHolder);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/extension/BasicPersistenceModuleExtensionHandler.java" line="54">

---

## Rebalance For Add

The `rebalanceForAdd` function handles additions of new members to a basic collection when the items are sortable. Similar to `rebalanceForUpdate`, it takes in the persistence module, the data representing the change, the persisted entity, descriptive data about the entity structure, and a container for any relevant operation results. It also returns the status of execution for this handler.

```java
    /**
     * Handle additions of new members to a basic collection when the items are sortable
     *
     * @param basicPersistenceModule the persistence module responsible for handling basic collection persistence
     *                               operations
     * @param persistencePackage     the data representing the change
     * @param instance               the persisted entity
     * @param mergedProperties       descriptive data about the entity structure
     * @param resultHolder           container for any relevant operation results
     * @return the status of execution for this handler - informs the manager on how to proceed
     */
    ExtensionResultStatusType rebalanceForAdd(BasicPersistenceModule basicPersistenceModule,
                                              PersistencePackage persistencePackage, Serializable instance,
                                              Map<String, FieldMetadata> mergedProperties,
                                              ExtensionResultHolder<Serializable> resultHolder);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
