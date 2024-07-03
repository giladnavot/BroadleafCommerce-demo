---
title: Introduction to Admin Service Handler
---
The Admin Service Handler, specifically the CustomPersistenceHandler in the BroadleafCommerce-demo, provides a hook to override the normal persistence behavior of the admin application. This is particularly useful when an alternate pathway for working with persisted data is desirable. For instance, if you want to work directly with a service API, rather than go through the standard admin persistence pipeline. The implementation is responsible for converting domain objects into the return type required by the admin. Helper classes are passed in to assist with conversion operations.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandler.java" line="30">

---

# CustomPersistenceHandler Interface

This is the CustomPersistenceHandler interface. It defines the methods that a custom persistence handler must implement. Each method corresponds to a specific operation that can be performed on an entity.

```java
/**
 * Custom Persistence Handlers provide a hook to override the normal persistence
 * behavior of the admin application. This is useful when an alternate pathway
 * for working with persisted data is desirable. For example, if you want to
 * work directly with a service API, rather than go through the standard
 * admin persistence pipeline. In such a case, you can use Spring to inject
 * an instance of your service into your custom persistence handler and
 * utilize that service to work with your entity. The implementation is responsible
 * for converting domain object into the return type required by the admin. Helper
 * classes are passed in to assist with conversion operations.
 *
 * @author Jeff Fischer
 */
public interface CustomPersistenceHandler extends Ordered {

    public static final int DEFAULT_ORDER = Integer.MAX_VALUE;

    /**
     * Is this persistence handler capable of dealing with an inspect request from the admin
     *
     * @param persistencePackage details about the inspect operation
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="41">

---

# CustomPersistenceHandlerAdapter Class

This is the CustomPersistenceHandlerAdapter class. It is a convenience class for those CustomPersistenceHandler implementations that do not wish to implement all the methods of the interface. By default, it throws a ServiceException for all operations, indicating that they are not supported.

```java
/**
 * Convenience class for those {@link org.broadleafcommerce.openadmin.server.service.handler.CustomPersistenceHandler} implementations
 * that do not wish to implement all the methods of the interface.
 *
 * @author Jeff Fischer
 */
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
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/handler/ProductCustomPersistenceHandler.java" line="87">

---

# Example of CustomPersistenceHandler Implementation

This is an example of a CustomPersistenceHandler implementation. The ProductCustomPersistenceHandler class extends the CustomPersistenceHandlerAdapter and is used to handle custom persistence operations for the Product entity.

```java
@Component("blProductCustomPersistenceHandler")
public class ProductCustomPersistenceHandler extends CustomPersistenceHandlerAdapter {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
