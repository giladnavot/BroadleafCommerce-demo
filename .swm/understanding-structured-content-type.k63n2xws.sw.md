---
title: Understanding Structured Content Type
---
A Structured Content Type in Broadleaf Commerce refers to a specific area where content should be targeted. It is typically used for placement, but can also be used to describe the fields. For instance, a content type of 'message' might be used to store messages that can be retrieved by name. A valid content name could be 'homepage banner' or 'cart rhs ad'. The custom fields associated with a Structured Content Type are defined by the `StructuredContentFieldTemplate` associated with it.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="26">

---

# StructuredContentType Interface

This is the StructuredContentType interface. It defines the methods that are available for a Structured Content Type. These methods include getting and setting the ID, name, description, and the associated template of the content type.

```java
/**
 * A content type corresponds to an area where content should be targeted.   For example,
 * a valid content name would be "homepage banner" or "cart rhs ad".
 * <br>
 * While typically used for placement, a content type can also be used just to describe
 * the fields.    For example, a content type of message might be used to store messages
 * that can be retrieved by name.
 * <br>
 * The custom fields associated by with a <code>StructuredContentType</code>
 * <br>
 *
 * @author bpolster.
 */
public interface StructuredContentType extends Serializable,MultiTenantCloneable<StructuredContentType> {

    /**
     * Gets the primary key.
     *
     * @return the primary key
     */
    @Nullable
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceImpl.java" line="115">

---

# Usage of StructuredContentType

Here is an example of how StructuredContentType is used in the StructuredContentServiceImpl. The service provides methods to find, retrieve, and save Structured Content Types.

```java
    @Override
    public StructuredContentType findStructuredContentTypeById(Long id) {
        return structuredContentDao.findStructuredContentTypeById(id);
    }

    @Override
    public StructuredContentType findStructuredContentTypeByName(String name) {
        return structuredContentDao.findStructuredContentTypeByName(name);
    }

    @Override
    public List<StructuredContentType> retrieveAllStructuredContentTypes() {
        return structuredContentDao.retrieveAllStructuredContentTypes();
    }

    @Override
    public List<StructuredContent> findContentItems(Criteria c) {
        return c.list();
    }

    @Override
```

---

</SwmSnippet>

# StructuredContentType Interface Functions

The StructuredContentType interface provides several key methods for managing content types in the CMS.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="46">

---

## getId and setId

The `getId` and `setId` methods are used to get and set the primary key of the content type, respectively.

```java
    @Nullable
    public Long getId();


    /**
     * Sets the primary key.
     *
     * @param id the new primary key
     */
    public void setId(@Nullable Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="62">

---

## getName and setName

The `getName` and `setName` methods are used to get and set the name of the content type, respectively.

```java
    @Nonnull
    String getName();

    /**
     * Sets the name.
     */
    void setName(@Nonnull String name);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="74">

---

## getDescription and setDescription

The `getDescription` and `setDescription` methods are used to get and set the description of the content type, respectively.

```java
    @Nullable
    String getDescription();

    /**
     * Sets the description.
     */
    void setDescription(@Nullable String description);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="86">

---

## getStructuredContentFieldTemplate and setStructuredContentFieldTemplate

The `getStructuredContentFieldTemplate` and `setStructuredContentFieldTemplate` methods are used to get and set the template associated with the content type, respectively.

```java
    @Nonnull
    StructuredContentFieldTemplate getStructuredContentFieldTemplate();

    /**
     * Sets the template associated with this content type.
     * @param scft
     */
    void setStructuredContentFieldTemplate(@Nonnull StructuredContentFieldTemplate scft);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
