---
title: Getting Started with Structured Content Data Access
---
Structured Content Data Access in Broadleaf Commerce refers to the operations performed on the structured content data in the system. This includes querying, updating, adding, and deleting structured content items. The `StructuredContentDao` interface in the Broadleaf Commerce CMS module is responsible for these operations. It provides methods to find structured content by ID, find structured content types by ID or name, retrieve all structured content types, find all content items, add or update content items, and delete content items. The `StructuredContentDao` interface is implemented in `StructuredContentDaoImpl` class.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="30">

---

# StructuredContentDao Interface

The `StructuredContentDao` interface provides methods for querying and updating structured content items. It includes methods for finding content by ID or type, retrieving all content types, adding or updating content items, and deleting content items.

```java
public interface StructuredContentDao {

    /**
     * Returns the <code>StructuredContent</code> item that matches
     * the passed in Id.
     * @param contentId
     * @return the found item or null if it does not exist
     */
    public StructuredContent findStructuredContentById(Long contentId);

    /**
     * Returns the <code>StructuredContentType</code> that matches
     * the passed in contentTypeId.
     * @param contentTypeId
     * @return the found item or null if it does not exist
     */
    public StructuredContentType findStructuredContentTypeById(Long contentTypeId);

    /**
     * Returns the list of all <code>StructuredContentType</code>s.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceImpl.java" line="82">

---

# Using StructuredContentDao

The `StructuredContentDao` is used in the `StructuredContentServiceImpl` class. It is injected as a resource and used to perform operations on structured content data.

```java
    protected StructuredContentDao structuredContentDao;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDaoImpl.java" line="48">

---

# Implementing StructuredContentDao

The `StructuredContentDaoImpl` class is an implementation of the `StructuredContentDao` interface. It is annotated with `@Repository`, indicating that it's a Spring Data Repository.

```java
public class StructuredContentDaoImpl implements StructuredContentDao {
```

---

</SwmSnippet>

# Structured Content Data Access Functions

This section provides an overview of the main functions of Structured Content Data Access.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="33">

---

## findStructuredContentById

The `findStructuredContentById` function is used to find a StructuredContent item by its ID. It returns the StructuredContent item that matches the passed in ID or null if it does not exist.

```java
     * Returns the <code>StructuredContent</code> item that matches
     * the passed in Id.
     * @param contentId
     * @return the found item or null if it does not exist
     */
    public StructuredContent findStructuredContentById(Long contentId);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="41">

---

## findStructuredContentTypeById

The `findStructuredContentTypeById` function is used to find a StructuredContentType by its ID. It returns the StructuredContentType that matches the passed in ID or null if it does not exist.

```java
     * Returns the <code>StructuredContentType</code> that matches
     * the passed in contentTypeId.
     * @param contentTypeId
     * @return the found item or null if it does not exist
     */
    public StructuredContentType findStructuredContentTypeById(Long contentTypeId);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="62">

---

## addOrUpdateContentItem

The `addOrUpdateContentItem` function is used to persist the changes or save a new content item. It returns the newly saved or persisted item.

```java
     * Persists the changes or saves a new content item.
     *
     * @param content
     * @return the newly saved or persisted item
     */
    public StructuredContent addOrUpdateContentItem(StructuredContent content);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="70">

---

## delete

The `delete` function is used to remove the passed in item from the underlying storage.

```java
     * Removes the passed in item from the underlying storage.
     *
     * @param content
     */
    public void delete(StructuredContent content);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
