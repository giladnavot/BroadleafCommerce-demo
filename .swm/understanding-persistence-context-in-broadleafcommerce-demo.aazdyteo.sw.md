---
title: Understanding Persistence Context in BroadleafCommerce-demo
---
Persistence Context in BroadleafCommerce-demo refers to the lifecycle and management of persistence objects, which are instances of a class that can be stored in a database. The PersistenceManagerContext class is a key part of this, providing a context for managing persistence objects within a single thread of execution. It maintains a stack of PersistenceManager instances, allowing for nested persistence contexts. The PersistenceManagerContext is thread-local, meaning it's specific to the thread where the object is created. This allows each thread to have its own isolated context, preventing interference between threads.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="24">

---

# PersistenceManagerContext Class

The `PersistenceManagerContext` class is the main class for managing the Persistence Context. It provides methods to add, retrieve, and remove the Persistence Context. It also manages a stack of `PersistenceManager` instances, providing methods to add and retrieve `PersistenceManager` instances.

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

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerFactory.java" line="60">

---

# Using PersistenceManagerContext

`PersistenceManagerContext` is used in the `PersistenceManagerFactory` class. The `getPersistenceManager` method retrieves the current `PersistenceManager` from the `PersistenceManagerContext`. The `startPersistenceManager` method creates a new `PersistenceManagerContext` if one does not already exist, and adds it to the `PersistenceManagerContext`.

```java
    /**
     * This method should only be used within the context of a thread with an established {@link PersistenceManagerContext}
     *  and the operation to be performed is on an entity that is managed by the {@link EntityManager} identified
     *  by {@link #startPersistenceManager(TargetModeType)}.
     *
     * See {@link PersistenceThreadManager#operation(TargetModeType, Persistable)} and {@link #startPersistenceManager(TargetModeType)}
     *  for an example of how the context is established.
     */
    public static PersistenceManager getPersistenceManager() {
        if (PersistenceManagerContext.getPersistenceManagerContext() != null) {
            return PersistenceManagerContext.getPersistenceManagerContext().getPersistenceManager();
        }
        throw new IllegalStateException("PersistenceManagerContext is not set on ThreadLocal. If you want to use the " +
                "non-cached version, try getPersistenceManager(Class, TargetModeType)");
    }

    public static PersistenceManager getPersistenceManager(String className) {
        return getPersistenceManager(className, TargetModeType.SANDBOX);
    }

    public static PersistenceManager getPersistenceManager(String className, TargetModeType targetModeType) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="39">

---

# Clearing the Persistence Context

The `clear` method is used to remove the current `PersistenceManagerContext` from the thread local storage. This is typically done at the end of a transaction or request.

```java
    private static void clear() {
        BROADLEAF_PERSISTENCE_MANAGER_CONTEXT.remove();
    }
```

---

</SwmSnippet>

# Persistence Context Functions

This section will cover the key functions related to the Persistence Context in the BroadleafCommerce-demo application.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/module/BasicPersistenceModule.java" line="141">

---

## BasicPersistenceModule

The BasicPersistenceModule class is a core part of the Persistence Context. It is responsible for managing the persistence of basic entities in the application. The class is annotated with @Component and @Scope('prototype'), indicating that a new instance is created for each request. It implements the PersistenceModule and ApplicationContextAware interfaces, allowing it to interact with the Spring application context. The class contains several important methods, including init(), which initializes the module, and setApplicationContext(), which sets the application context.

```java
@Primary
@Component("blBasicPersistenceModule")
@Scope("prototype")
public class BasicPersistenceModule implements PersistenceModule, RecordHelper, ApplicationContextAware {

    private static final Log LOG = LogFactory.getLog(BasicPersistenceModule.class);

    public static final String MAIN_ENTITY_NAME_PROPERTY = "MAIN_ENTITY_NAME";
    public static final String ALTERNATE_ID_PROPERTY = "ALTERNATE_ID";

    protected ApplicationContext applicationContext;
    protected PersistenceManager persistenceManager;

    @Resource(name = "blEntityValidatorService")
    protected EntityValidatorService entityValidatorService;

    @Resource(name = "blPersistenceProviders")
    protected List<FieldPersistenceProvider> fieldPersistenceProviders = new ArrayList<FieldPersistenceProvider>();

    @Resource(name = "blPopulateValueRequestValidators")
    protected List<PopulateValueRequestValidator> populateValidators;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerContext.java" line="27">

---

## PersistenceManagerContext

The PersistenceManagerContext class is another key part of the Persistence Context. It maintains a stack of PersistenceManager instances, allowing for nested transactions. The class provides methods to add and remove PersistenceManager instances from the stack, as well as to retrieve the current PersistenceManager. The getPersistenceManagerContext() method retrieves the current PersistenceManagerContext, while the addPersistenceManagerContext() method adds a new PersistenceManagerContext.

```java
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

    public void addPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager.add(persistenceManager);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
