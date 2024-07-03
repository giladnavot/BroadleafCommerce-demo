---
title: Understanding the Persistence Manager
---
The Persistence Manager in BroadleafCommerce-demo is a crucial component that handles the persistence layer of the application. It is responsible for managing the lifecycle of entities and their interactions with the database. The Persistence Manager provides methods for CRUD operations, fetching and inspecting entities, and managing the dynamic entity DAO. It also offers support for handling polymorphic entities and custom persistence handlers. The Persistence Manager is used throughout the codebase, for example, in the `PersistenceManagerFactory`, `PersistenceManagerEventHandler`, and `PersistenceManagerContext` classes.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManager.java" line="35">

---

# PersistenceManager Interface

The PersistenceManager interface defines the contract for the Persistence Manager. It declares methods for various operations such as fetching, adding, updating, and removing entities. It also provides methods to get and set the DynamicEntityDao, which is used to perform database operations.

```java
public interface PersistenceManager {

    Class<?>[] getAllPolymorphicEntitiesFromCeiling(Class<?> ceilingClass);

    Class<?>[] getPolymorphicEntities(String ceilingEntityFullyQualifiedClassname) throws ClassNotFoundException;

    Map<String, FieldMetadata> getSimpleMergedProperties(String entityName, PersistencePerspective persistencePerspective) throws ClassNotFoundException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;

    ClassMetadata buildClassMetadata(Class<?>[] entities, PersistencePackage persistencePackage, Map<MergedPropertyType, Map<String, FieldMetadata>> mergedProperties) throws IllegalArgumentException;

    PersistenceResponse inspect(PersistencePackage persistencePackage) throws ServiceException, ClassNotFoundException;

    PersistenceResponse fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto) throws ServiceException;

    PersistenceResponse add(PersistencePackage persistencePackage) throws ServiceException;

    PersistenceResponse update(PersistencePackage persistencePackage) throws ServiceException;

    PersistenceResponse remove(PersistencePackage persistencePackage) throws ServiceException;

    void configureDynamicEntityDao(Class entityClass, TargetModeType targetMode);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="89">

---

# PersistenceManager Implementation

PersistenceManagerImpl is the implementation of the PersistenceManager interface. It provides the actual implementation of the methods declared in the PersistenceManager interface.

```java
@Scope("prototype")
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/DynamicEntityRemoteService.java" line="124">

---

# Using PersistenceManager

Here is an example of how the PersistenceManager is used. The `inspect` method of the PersistenceManager is called to inspect a PersistencePackage.

```java
                try {
                    PersistenceManager persistenceManager = PersistenceManagerFactory.getPersistenceManager();
                    return persistenceManager.inspect(persistencePackage);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="24">

---

# PersistenceManagerContext

The PersistenceManagerContext is used to manage the context of the PersistenceManager. It provides methods to add and remove PersistenceManager instances, and to get the current PersistenceManager. It uses a ThreadLocal variable to store the context, ensuring that each thread has its own isolated context.

```java
/**
 * @author Jeff Fischer
 */
public class PersistenceManagerContext {

    private static final ThreadLocal<PersistenceManagerContext> BROADLEAF_PERSISTENCE_MANAGER_CONTEXT = ThreadLocalManager.createThreadLocal(PersistenceManagerContext.class, false);

    public static PersistenceManagerContext getPersistenceManagerContext() {
        return BROADLEAF_PERSISTENCE_MANAGER_CONTEXT.get();
    }

    public static void addPersistenceManagerContext(PersistenceManagerContext persistenceManagerContext) {
        BROADLEAF_PERSISTENCE_MANAGER_CONTEXT.set(persistenceManagerContext);
    }

    private static void clear() {
        BROADLEAF_PERSISTENCE_MANAGER_CONTEXT.remove();
    }

    private final Stack<PersistenceManager> persistenceManager = new Stack<PersistenceManager>();

```

---

</SwmSnippet>

# Key Functions of Persistence Manager

Let's delve into the key functions of the Persistence Manager.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="124">

---

## postConstruct

The `postConstruct` function is invoked after the PersistenceManagerImpl bean is fully initialized. This function sets the PersistenceManager for each module and sorts the persistenceManagerEventHandlers based on their order. It also calls the `honorExplicitPersistenceHandlerSorting` function.

```java
    @PostConstruct
    public void postConstruct() {
        for (PersistenceModule module : modules) {
            module.setPersistenceManager(this);
        }
        Collections.sort(persistenceManagerEventHandlers, new Comparator<PersistenceManagerEventHandler>() {
            @Override
            public int compare(PersistenceManagerEventHandler o1, PersistenceManagerEventHandler o2) {
                return Integer.valueOf(o1.getOrder()).compareTo(Integer.valueOf(o2.getOrder()));
            }
        });
        honorExplicitPersistenceHandlerSorting();

    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="49">

---

## getPersistenceManager

The `getPersistenceManager` function retrieves the current PersistenceManager from the context. If the persistenceManager stack is not empty, it returns the PersistenceManager at the top of the stack.

```java
    public PersistenceManager getPersistenceManager() {
        return !persistenceManager.empty()?persistenceManager.peek():null;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="45">

---

## addPersistenceManager

The `addPersistenceManager` function adds a PersistenceManager to the persistenceManager stack in the context.

```java
    public void addPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager.add(persistenceManager);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="53">

---

## remove

The `remove` function removes the top PersistenceManager from the persistenceManager stack in the context. If the stack becomes empty after the removal, it calls the `clear` function to remove the PersistenceManagerContext from the ThreadLocal.

```java
    public void remove() {
        if (!persistenceManager.empty()) {
            persistenceManager.pop();
        }
        if (persistenceManager.empty()) {
            PersistenceManagerContext.clear();
        }
    }
```

---

</SwmSnippet>

# Persistence Manager Operations

Persistence Manager Operations

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="304">

---

## Fetch Operation

The `fetch` method is used to retrieve data from the database. It takes a PersistencePackage and a CriteriaTransferObject as parameters, and returns a PersistenceResponse. The PersistencePackage contains information about the entity to be fetched, while the CriteriaTransferObject contains the criteria for the fetch operation.

```java
    public PersistenceResponse fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto) throws ServiceException {
        for (PersistenceManagerEventHandler handler : persistenceManagerEventHandlers) {
            PersistenceManagerEventHandlerResponse response = handler.preFetch(this, persistencePackage, cto);
            if (PersistenceManagerEventHandlerResponse.PersistenceManagerEventHandlerResponseStatus.HANDLED_BREAK==response.getStatus()) {
                break;
            }
        }
        //check to see if there is a custom handler registered
        for (CustomPersistenceHandler handler : getCustomPersistenceHandlers()) {
            if (handler.canHandleFetch(persistencePackage)) {
                if (!handler.willHandleSecurity(persistencePackage)) {
                    adminRemoteSecurityService.securityCheck(persistencePackage, EntityOperationType.FETCH);
                }
                DynamicResultSet results = handler.fetch(persistencePackage, cto, dynamicEntityDao, (RecordHelper) getCompatibleModule(OperationType.BASIC));
                return executePostFetchHandlers(persistencePackage, cto, new PersistenceResponse().withDynamicResultSet(results));
            }
        }
        adminRemoteSecurityService.securityCheck(persistencePackage, EntityOperationType.FETCH);
        PersistenceModule myModule = getCompatibleModule(persistencePackage.getPersistencePerspective().getOperationTypes().getFetchType());

        try {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="476">

---

## Add Operation

The `add` method is used to add a new entity to the database. It takes a PersistencePackage as a parameter and returns a PersistenceResponse. The PersistencePackage contains information about the entity to be added.

```java
    public PersistenceResponse add(PersistencePackage persistencePackage) throws ServiceException {
        for (PersistenceManagerEventHandler handler : persistenceManagerEventHandlers) {
            PersistenceManagerEventHandlerResponse response = handler.preAdd(this, persistencePackage);
            if (PersistenceManagerEventHandlerResponse.PersistenceManagerEventHandlerResponseStatus.HANDLED_BREAK==response.getStatus()) {
                break;
            }
        }
        //check to see if there is a custom handler registered
        //execute the root PersistencePackage
        Entity response;
        try {
            checkRoot: {
                //if there is a validation exception in the root check, let it bubble, as we need a valid, persisted
                //entity to execute the subPackage code later
                for (CustomPersistenceHandler handler : getCustomPersistenceHandlers()) {
                    if (handler.canHandleAdd(persistencePackage)) {
                        if (!handler.willHandleSecurity(persistencePackage)) {
                            adminRemoteSecurityService.securityCheck(persistencePackage, EntityOperationType.ADD);
                        }
                        response = handler.add(persistencePackage, dynamicEntityDao, (RecordHelper) getCompatibleModule(OperationType.BASIC));
                        break checkRoot;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
