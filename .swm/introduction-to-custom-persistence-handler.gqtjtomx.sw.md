---
title: Introduction to Custom Persistence Handler
---
The Custom Persistence Handler in Broadleaf Commerce is an interface that provides a hook to override the normal persistence behavior of the admin application. This is particularly useful when an alternate pathway for working with persisted data is desirable. For instance, if you want to work directly with a service API, rather than go through the standard admin persistence pipeline. In such a case, you can use Spring to inject an instance of your service into your custom persistence handler and utilize that service to work with your entity. The implementation is responsible for converting domain object into the return type required by the admin.

The CustomPersistenceHandler interface defines several methods that determine whether the handler can handle various operations such as inspect, fetch, add, remove, and update. These methods return a Boolean value indicating whether the handler can perform the operation.

The CustomPersistenceHandlerAdapter is a convenience class for those CustomPersistenceHandler implementations that do not wish to implement all the methods of the interface. It provides default implementations of the methods, which can be overridden as needed.

The CustomPersistenceHandlerFilter interface provides a method to determine whether a particular handler should be used. The DefaultCustomPersistenceHandlerFilter class is a default implementation of this interface.

There are also specific implementations of CustomPersistenceHandler for handling specific entities, such as SystemPropertyCustomPersistenceHandler and TranslationCustomPersistenceHandler. These classes provide custom persistence behavior for SystemProperty and Translation entities respectively.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandler.java" line="43">

---

# CustomPersistenceHandler Interface

This is the CustomPersistenceHandler interface. It defines methods for handling inspect, fetch, add, remove, and update operations. Each method returns a Boolean indicating whether the handler can handle the corresponding operation.

```java
public interface CustomPersistenceHandler extends Ordered {

    public static final int DEFAULT_ORDER = Integer.MAX_VALUE;

    /**
     * Is this persistence handler capable of dealing with an inspect request from the admin
     *
     * @param persistencePackage details about the inspect operation
     * @return whether or not this handler supports inspects
     */
    public Boolean canHandleInspect(PersistencePackage persistencePackage);

    /**
     * Is this persistence handler capable of dealing with an fetch request from the admin
     *
     * @param persistencePackage details about the fetch operation
     * @return whether or not this handler supports fetches
     */
    public Boolean canHandleFetch(PersistencePackage persistencePackage);

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="241">

---

# CustomPersistenceHandler Usage

This is an example of how CustomPersistenceHandler is used. The PersistenceManagerImpl class checks if there is a custom handler registered for each operation and uses it if the handler can handle the operation.

```java
        // check to see if there is a custom handler registered
        for (CustomPersistenceHandler handler : getCustomPersistenceHandlers()) {
            if (handler.canHandleInspect(persistencePackage)) {
                if (!handler.willHandleSecurity(persistencePackage)) {
                    adminRemoteSecurityService.securityCheck(persistencePackage, EntityOperationType.INSPECT);
                }
                DynamicResultSet results = handler.inspect(persistencePackage, dynamicEntityDao, this);
                return executePostInspectHandlers(persistencePackage, new PersistenceResponse().withDynamicResultSet
                        (results));
            }
        }

        adminRemoteSecurityService.securityCheck(persistencePackage, EntityOperationType.INSPECT);
        Class<?>[] entities = getPolymorphicEntities(persistencePackage.getCeilingEntityFullyQualifiedClassname());
        Map<MergedPropertyType, Map<String, FieldMetadata>> allMergedProperties = new HashMap<>();
        for (PersistenceModule module : modules) {
            module.updateMergedProperties(persistencePackage, allMergedProperties);
        }
        ClassMetadata classMetadata = buildClassMetadata(entities, persistencePackage, allMergedProperties);
        DynamicResultSet results = new DynamicResultSet(classMetadata);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="47">

---

# CustomPersistenceHandlerAdapter Class

The CustomPersistenceHandlerAdapter is a convenience class for implementations of CustomPersistenceHandler that do not wish to implement all the methods of the interface. It provides default implementations that throw a ServiceException.

```java
public class CustomPersistenceHandlerAdapter implements CustomPersistenceHandler {

    @Override
    public Boolean canHandleInspect(PersistencePackage persistencePackage) {
        return false;
    }

    @Override
    public Boolean canHandleFetch(PersistencePackage persistencePackage) {
        return false;
    }

    @Override
    public Boolean canHandleAdd(PersistencePackage persistencePackage) {
        return false;
    }

    @Override
    public Boolean canHandleRemove(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

# Custom Persistence Handler Functions

The Custom Persistence Handlers in BroadleafCommerce-demo provide a number of key functions that allow for custom handling of data persistence. These functions include inspecting, fetching, adding, removing, and updating data.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="49">

---

## canHandleInspect

The `canHandleInspect` function is used to determine if the handler can deal with an inspect request from the admin. It returns a boolean value.

```java
    @Override
    public Boolean canHandleInspect(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="54">

---

## canHandleFetch

The `canHandleFetch` function is used to determine if the handler can deal with a fetch request from the admin. It returns a boolean value.

```java
    @Override
    public Boolean canHandleFetch(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="59">

---

## canHandleAdd

The `canHandleAdd` function is used to determine if the handler can deal with an add request from the admin. It returns a boolean value.

```java
    @Override
    public Boolean canHandleAdd(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="64">

---

## canHandleRemove

The `canHandleRemove` function is used to determine if the handler can deal with a remove request from the admin. It returns a boolean value.

```java
    @Override
    public Boolean canHandleRemove(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="69">

---

## canHandleUpdate

The `canHandleUpdate` function is used to determine if the handler can deal with an update request from the admin. It returns a boolean value.

```java
    @Override
    public Boolean canHandleUpdate(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
