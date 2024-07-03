---
title: Understanding Persistence Response
---
PersistenceResponse is a class in the Broadleaf Commerce framework that encapsulates the result of a persistence operation. It contains an instance of DynamicResultSet, which holds the result set of a fetch operation, and an instance of Entity, which represents the persisted entity. It also contains a map for additional data that can be used to store any extra information needed. The PersistenceResponse class provides methods to set and get these properties.

The PersistenceResponse class also provides builder-style methods (withEntity, withDynamicResultSet, withAdditionalData) for setting its properties and returning the PersistenceResponse instance itself. This allows for chaining of method calls to set multiple properties in a single statement.

PersistenceResponse is used extensively across the Broadleaf Commerce framework, particularly in persistence-related operations such as inspect, fetch, add, update, and remove operations. These operations are defined in the PersistenceManager interface and implemented in the PersistenceManagerImpl class.

The PersistenceResponse class also contains a nested static class called AdditionalData, which defines constants for keys that can be used in the additional data map. For example, the CLONEID constant is used as a key to store the clone ID of an entity in the additional data map.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="29">

---

# PersistenceResponse Class

This is the definition of the PersistenceResponse class. It contains fields for the dynamic result set, the entity, and the additional data. It also contains getter and setter methods for these fields, as well as methods to chain these setters for more fluent usage.

```java
public class PersistenceResponse {

    protected DynamicResultSet dynamicResultSet;
    protected Entity entity;
    protected Map<String, Object> additionalData = new HashMap<String, Object>();

    public PersistenceResponse withDynamicResultSet(DynamicResultSet dynamicResultSet) {
        setDynamicResultSet(dynamicResultSet);
        return this;
    }

    public PersistenceResponse withEntity(Entity entity) {
        setEntity(entity);
        return this;
    }

    public PersistenceResponse withAdditionalData(Map<String, Object> additionalData) {
        setAdditionalData(additionalData);
        return this;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceManagerImpl.java" line="233">

---

# Usage of PersistenceResponse

Here is an example of how PersistenceResponse is used in the PersistenceManagerImpl class. A new PersistenceResponse object is created and its setter methods are used to set the dynamic result set. The PersistenceResponse object is then returned from the method.

```java
    @Override
    public PersistenceResponse inspect(PersistencePackage persistencePackage) throws ServiceException, ClassNotFoundException {
        for (PersistenceManagerEventHandler handler : persistenceManagerEventHandlers) {
            PersistenceManagerEventHandlerResponse response = handler.preInspect(this, persistencePackage);
            if (PersistenceManagerEventHandlerResponse.PersistenceManagerEventHandlerResponseStatus.HANDLED_BREAK==response.getStatus()) {
                break;
            }
        }
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
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="74">

---

# AdditionalData Class

The AdditionalData class is a static inner class of PersistenceResponse. It contains constants that define keys for the additional data map in the PersistenceResponse object.

```java
    public static class AdditionalData {
        public static final String CLONEID = "cloneId";
    }
```

---

</SwmSnippet>

# PersistenceResponse Functions

This section covers the main functions of the PersistenceResponse class.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="35">

---

## withDynamicResultSet

The `withDynamicResultSet` function is a setter method that sets the DynamicResultSet of the PersistenceResponse and returns the PersistenceResponse itself. This allows for method chaining.

```java
    public PersistenceResponse withDynamicResultSet(DynamicResultSet dynamicResultSet) {
        setDynamicResultSet(dynamicResultSet);
        return this;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="40">

---

## withEntity

The `withEntity` function is a setter method that sets the Entity of the PersistenceResponse and returns the PersistenceResponse itself. This allows for method chaining.

```java
    public PersistenceResponse withEntity(Entity entity) {
        setEntity(entity);
        return this;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="45">

---

## withAdditionalData

The `withAdditionalData` function is a setter method that sets the additional data of the PersistenceResponse and returns the PersistenceResponse itself. This allows for method chaining.

```java
    public PersistenceResponse withAdditionalData(Map<String, Object> additionalData) {
        setAdditionalData(additionalData);
        return this;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="58">

---

## getDynamicResultSet

The `getDynamicResultSet` function is a getter method that returns the DynamicResultSet of the PersistenceResponse.

```java
    public DynamicResultSet getDynamicResultSet() {
        return dynamicResultSet;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="66">

---

## getEntity

The `getEntity` function is a getter method that returns the Entity of the PersistenceResponse.

```java
    public Entity getEntity() {
        return entity;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="50">

---

## getAdditionalData

The `getAdditionalData` function is a getter method that returns the additional data of the PersistenceResponse.

```java
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="62">

---

## setDynamicResultSet

The `setDynamicResultSet` function is a setter method that sets the DynamicResultSet of the PersistenceResponse.

```java
    public void setDynamicResultSet(DynamicResultSet dynamicResultSet) {
        this.dynamicResultSet = dynamicResultSet;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="70">

---

## setEntity

The `setEntity` function is a setter method that sets the Entity of the PersistenceResponse.

```java
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/PersistenceResponse.java" line="54">

---

## setAdditionalData

The `setAdditionalData` function is a setter method that sets the additional data of the PersistenceResponse.

```java
    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
