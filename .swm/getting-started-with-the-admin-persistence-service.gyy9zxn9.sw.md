---
title: Getting Started with the Admin Persistence Service
---
The Admin Persistence Service in BroadleafCommerce-demo refers to a set of classes and interfaces that manage the persistence layer of the admin module. It is primarily responsible for handling CRUD operations for admin entities. The service is organized in the package `org.broadleafcommerce.openadmin.server.service.persistence`.

The `PersistenceManager` interface and its implementation `PersistenceManagerImpl` are central to this service. They provide methods for fetching, adding, updating, and deleting entities. They also handle exceptions that may occur during these operations.

The `PersistenceModule` interface and its implementations like `BasicPersistenceModule` provide more specific persistence operations. They are used by the `PersistenceManager` to perform its tasks.

The `Persistable` interface is used for objects that can be persisted. It has a single method `execute` that throws a generic exception.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManager.java" line="18">

---

# PersistenceManager Interface

The `PersistenceManager` interface is part of the Admin Persistence Service. It defines the methods that the service provides for interacting with the data.

```java
package org.broadleafcommerce.openadmin.server.service.persistence;

import org.broadleafcommerce.common.exception.ServiceException;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="18">

---

# PersistenceManager Implementation

The `PersistenceManagerImpl` class is the implementation of the `PersistenceManager` interface. It contains the actual logic for the CRUD operations.

```java
package org.broadleafcommerce.openadmin.server.service.persistence;

import org.apache.commons.collections.CollectionUtils;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/Persistable.java" line="18">

---

# Persistable Interface

The `Persistable` interface is used to mark classes that can be persisted by the Admin Persistence Service. It contains a single method `execute` that is used to persist the data.

```java
package org.broadleafcommerce.openadmin.server.service.persistence;

/**
 * @author Jeff Fischer
 */
public interface Persistable<T, G extends Throwable> {

    public T execute() throws G;

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
