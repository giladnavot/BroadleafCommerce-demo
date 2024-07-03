---
title: Understanding Structured Content
---
Structured Content in Broadleaf Commerce is a flexible way to manage and display content. It is a generic content item with a set of predefined fields, defined by its associated StructuredContentType. This allows for the creation of different types of content items, such as ads, banners, or any other custom content type that can be managed via the Broadleaf Commerce admin. The content can be targeted to different users based on rules, such as a user's location or other criteria. The StructuredContentService provides services to manage these content items, including finding, saving, and deleting content items.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="109">

---

## StructuredContentType

The StructuredContentType represents the type of the StructuredContent. It is associated with a StructuredContent instance using the `getStructuredContentType` and `setStructuredContentType` methods.

```java
    @Nonnull
    public StructuredContentType getStructuredContentType();

    /**
     * Sets the {@link StructuredContentType} associated with this content item.
     *
     */
    public void setStructuredContentType(@Nonnull StructuredContentType structuredContentType);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/dao/StructuredContentDao.java" line="76">

---

## StructuredContentDao

The StructuredContentDao class provides methods to save a StructuredContentType (`saveStructuredContentType`), retrieve all StructuredContentTypes (`retrieveAllStructuredContentTypes`), and delete a StructuredContent (`delete`).

```java
    /**
     * Saves the given <b>type</b> and returns the merged instance
     */
    public StructuredContentType saveStructuredContentType(StructuredContentType type);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="109">

---

## StructuredContentService

The StructuredContentService class provides a method to save a StructuredContentType (`saveStructuredContentType`).

```java
    /**
     * Saves the given <b>type</b> and returns the merged instance
     */
    StructuredContentType saveStructuredContentType(StructuredContentType type);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentFieldXref.java" line="29">

---

## StructuredContentFieldXref

The StructuredContentFieldXref class provides a method to get a StructuredContent (`getStructuredContent`).

```java
    public void setStructuredContent(StructuredContent sc);

    public StructuredContent getStructuredContent();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
