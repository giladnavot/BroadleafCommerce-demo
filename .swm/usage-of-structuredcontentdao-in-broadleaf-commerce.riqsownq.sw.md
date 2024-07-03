---
title: Usage of StructuredContentDao in Broadleaf Commerce
---
This document will cover the following aspects of `StructuredContentDao`:

1. What is `StructuredContentDao`?
2. The key methods provided by `StructuredContentDao`.
3. How `StructuredContentDao` is used in the application.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="26">

---

# What is StructuredContentDao

`StructuredContentDao` is an interface responsible for querying and updating `StructuredContent` items. `StructuredContent` represents structured data that can be managed in the CMS.

```java
/**
 * Responsible for querying and updating {@link StructuredContent} items
 * @author bpolster
 */
public interface StructuredContentDao {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="38">

---

# Key methods of StructuredContentDao

`StructuredContentDao` provides several methods for managing `StructuredContent` and `StructuredContentType`. These include methods for finding content by ID or name, retrieving all content types, adding or updating content items, deleting content items, and more.

```java
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
     * @return the list of found items
     */
    public List<StructuredContentType> retrieveAllStructuredContentTypes();
    
    /**
     * Finds all content regardless of the {@link Sandbox} they are a member of
     * @return the list of {@link StructuredContent}, an empty list of none are found
     */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceImpl.java" line="82">

---

# Usage of StructuredContentDao in the application

`StructuredContentDao` is used in `StructuredContentServiceImpl` for managing structured content in the application.

```java
    protected StructuredContentDao structuredContentDao;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDaoImpl.java" line="47">

---

`StructuredContentDaoImpl` is the implementation of `StructuredContentDao` interface. It provides the actual logic for the methods declared in `StructuredContentDao`.

```java
@Repository("blStructuredContentDao")
public class StructuredContentDaoImpl implements StructuredContentDao {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
