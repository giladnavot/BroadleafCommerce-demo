---
title: CustomPersistenceHandlerAdapter Class Overview
---
This document will cover the `CustomPersistenceHandlerAdapter` class. We'll cover:

1. What is `CustomPersistenceHandlerAdapter`.
2. The variables and functions defined in `CustomPersistenceHandlerAdapter`.
3. An example of how to use `CustomPersistenceHandlerAdapter`.

```mermaid
graph TD;
 CustomPersistenceHandler --> CustomPersistenceHandlerAdapter:::currentBaseStyle
CustomPersistenceHandlerAdapter --> TimeDTOCustomPersistenceHandler
TimeDTOCustomPersistenceHandler --> 1[...]
CustomPersistenceHandlerAdapter --> ClassCustomPersistenceHandlerAdapter
ClassCustomPersistenceHandlerAdapter --> 2[...]
CustomPersistenceHandlerAdapter --> ProductCustomPersistenceHandler
CustomPersistenceHandlerAdapter --> 3[...]

 classDef currentBaseStyle color:#000000,fill:#7CB9F4
```

# What is CustomPersistenceHandlerAdapter

`CustomPersistenceHandlerAdapter` is a convenience class for `CustomPersistenceHandler` implementations that do not wish to implement all the methods of the interface. It provides default implementations for all methods of the `CustomPersistenceHandler` interface, which throw a `ServiceException` indicating that the operation is not supported. This class is intended to be extended by other classes that need to implement some, but not all, of the `CustomPersistenceHandler` methods.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="50">

---

# Variables and functions

The function `canHandleInspect` is used to determine if this handler can handle inspect operations. By default, it returns false, indicating that it cannot handle inspect operations.

```java
    public Boolean canHandleInspect(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="54">

---

The function `canHandleFetch` is used to determine if this handler can handle fetch operations. By default, it returns false, indicating that it cannot handle fetch operations.

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

The function `canHandleAdd` is used to determine if this handler can handle add operations. By default, it returns false, indicating that it cannot handle add operations.

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

The function `canHandleRemove` is used to determine if this handler can handle remove operations. By default, it returns false, indicating that it cannot handle remove operations.

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

The function `canHandleUpdate` is used to determine if this handler can handle update operations. By default, it returns false, indicating that it cannot handle update operations.

```java
    @Override
    public Boolean canHandleUpdate(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="74">

---

The function `inspect` is used to perform an inspect operation. By default, it throws a `ServiceException` indicating that inspect operations are not supported.

```java
    @Override
    public DynamicResultSet inspect(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, InspectHelper helper) throws ServiceException {
        throw new ServiceException("Inspect not supported");
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="79">

---

The function `fetch` is used to perform a fetch operation. By default, it throws a `ServiceException` indicating that fetch operations are not supported.

```java
    @Override
    public DynamicResultSet fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        throw new ServiceException("Fetch not supported");
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="84">

---

The function `add` is used to perform an add operation. By default, it throws a `ServiceException` indicating that add operations are not supported.

```java
    @Override
    public Entity add(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        throw new ServiceException("Add not supported");
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="89">

---

The function `remove` is used to perform a remove operation. By default, it throws a `ServiceException` indicating that remove operations are not supported.

```java
    @Override
    public void remove(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
       throw new ServiceException("Remove not supported");
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="94">

---

The function `update` is used to perform an update operation. By default, it throws a `ServiceException` indicating that update operations are not supported.

```java
    @Override
    public Entity update(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        throw new ServiceException("Update not supported");
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="99">

---

The function `willHandleSecurity` is used to determine if this handler will handle security. By default, it returns false, indicating that it will not handle security.

```java
    @Override
    public Boolean willHandleSecurity(PersistencePackage persistencePackage) {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/CustomPersistenceHandlerAdapter.java" line="104">

---

The function `getOrder` is used to get the order of this handler. By default, it returns `CustomPersistenceHandler.DEFAULT_ORDER`.

```java
    @Override
    public int getOrder() {
        return CustomPersistenceHandler.DEFAULT_ORDER;
    }
```

---

</SwmSnippet>

# Usage example

`CustomPersistenceHandlerAdapter` is intended to be extended by other classes that need to implement some, but not all, of the `CustomPersistenceHandler` methods. For example, the `SystemPropertyCustomPersistenceHandler` class extends `CustomPersistenceHandlerAdapter` and provides its own implementations for some of the methods.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="class"><sup>Powered by [Swimm](/)</sup></SwmMeta>
