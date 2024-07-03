---
title: Understanding Dynamic Entity Retriever
---
The DynamicEntityRetriever is an interface in the Broadleaf Commerce framework that provides methods for fetching dynamic entities. These entities are a part of the Broadleaf's flexible and extensible data model. The interface defines methods such as `fetchDynamicEntity` and `fetchEntityBasedOnId` which are used to retrieve dynamic entities based on certain parameters. It is implemented in classes like `StructuredContentTypeCustomPersistenceHandler` and `PageTemplateCustomPersistenceHandler`, indicating its use in handling custom persistence of structured content types and page templates.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/admin/server/handler/StructuredContentTypeCustomPersistenceHandler.java" line="70">

---

## Implementing DynamicEntityRetriever

Here, the `StructuredContentTypeCustomPersistenceHandler` class implements the `DynamicEntityRetriever` interface. This means it provides concrete implementations for the methods declared in the interface.

```java
@Component("blStructuredContentTypeCustomPersistenceHandler")
public class StructuredContentTypeCustomPersistenceHandler extends CustomPersistenceHandlerAdapter implements DynamicEntityRetriever {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/DynamicEntityRetriever.java" line="30">

---

## Using DynamicEntityRetriever Methods

The `DynamicEntityRetriever` interface declares three methods: `fetchDynamicEntity`, `fetchEntityBasedOnId`, and `getFieldContainerClassName`. These methods are used to fetch dynamic entities and their related information.

```java
    Entity fetchDynamicEntity(Serializable root, List<String> dirtyFields, boolean includeId) throws Exception;

    Entity fetchEntityBasedOnId(String themeConfigurationId, List<String> dirtyFields) throws Exception;

    String getFieldContainerClassName();
```

---

</SwmSnippet>

# Dynamic Entity Retriever Functions

This section will explain the main functions of the Dynamic Entity Retriever.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/DynamicEntityRetriever.java" line="30">

---

## fetchDynamicEntity

The `fetchDynamicEntity` function retrieves a dynamic entity based on the root, dirtyFields, and includeId parameters. It throws an exception if the entity cannot be fetched.

```java
    Entity fetchDynamicEntity(Serializable root, List<String> dirtyFields, boolean includeId) throws Exception;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/DynamicEntityRetriever.java" line="32">

---

## fetchEntityBasedOnId

The `fetchEntityBasedOnId` function retrieves an entity based on its ID and dirtyFields. It throws an exception if the entity cannot be fetched.

```java
    Entity fetchEntityBasedOnId(String themeConfigurationId, List<String> dirtyFields) throws Exception;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/handler/DynamicEntityRetriever.java" line="34">

---

## getFieldContainerClassName

The `getFieldContainerClassName` function returns the class name of the field container.

```java
    String getFieldContainerClassName();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
