---
title: Guidelines for Creating New Exception Classes in Broadleaf Commerce
---
This document will cover the process of creating a new exception class in the BroadleafCommerce-demo framework. It will explain the following:

1. The structure of an exception class in the framework.
2. How and when to use the exception class.
3. The functionality and purpose of the exception class.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/AdminEntityService.java" line="52">

---

# Structure of an Exception Class

In the BroadleafCommerce-demo framework, exception classes are often used in service interfaces. For example, in the `AdminEntityService` interface, the `ServiceException` is thrown in various methods. This indicates that when implementing these methods, developers should handle or throw the `ServiceException`.

```java
    public PersistenceResponse getClassMetadata(PersistencePackageRequest request)
            throws ServiceException;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/DynamicEntityService.java" line="63">

---

# Using the Exception Class

The `AdminEntityService` is used in the `DynamicEntityService` class. Here, the `ServiceException` is thrown, indicating that the methods in `AdminEntityService` can throw a `ServiceException`.

```java
     * @throws ServiceException
     * @see {@link AdminEntityService#add(org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest)}
     */
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/util/GenericOperationUtil.java" line="39">

---

# Functionality and Purpose of the Exception Class

The `GenericOperationUtil` class provides a method `executeRetryableOperation` that executes a given operation and retries if exceptions occur. This method throws a generic `Exception`, which can be a custom exception class in the framework. This shows that exception classes in the framework are used to handle errors and exceptional cases in the application logic.

```java
    public static <R> R executeRetryableOperation(final GenericOperation<R> operation) throws Exception {
        return executeRetryableOperation(operation, 5, 100L, true, null);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
