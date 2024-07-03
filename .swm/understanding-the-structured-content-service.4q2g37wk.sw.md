---
title: Understanding the Structured Content Service
---
The StructuredContentService is an interface that provides services to manage `StructuredContent` items. It offers methods to find, save, and manage structured content and structured content types. It also provides methods to convert `StructuredContent` into `StructuredContentDTO` and build a list of `StructuredContentDTO` from a list of `StructuredContent`. The service is used in various parts of the application, including the CMS admin and web processor.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="38">

---

# StructuredContentService Interface

This is the StructuredContentService interface. It declares methods for managing StructuredContent items, such as findStructuredContentById, saveStructuredContentType, and buildStructuredContentDTO. These methods are used throughout the codebase to perform operations on StructuredContent items.

```java
public interface StructuredContentService {


    /**
     * Returns the StructuredContent item associated with the passed in id.
     *
     * @param contentId - The id of the content item.
     * @return The associated structured content item.
     */
    StructuredContent findStructuredContentById(Long contentId);


    /**
     * Returns the <code>StructuredContentType</code> associated with the passed in id.
     *
     * @param id - The id of the content type.
     * @return The associated <code>StructuredContentType</code>.
     */
    StructuredContentType findStructuredContentTypeById(Long id);


```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessor.java" line="103">

---

# Usage of StructuredContentService

Here is an example of how the StructuredContentService is used. It is injected into the ContentProcessor class using the @Resource annotation, and can then be used to call its methods.

```java
    @Resource(name = "blStructuredContentService")
    protected StructuredContentService structuredContentService;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="109">

---

# Saving StructuredContentType

The saveStructuredContentType method is used to save a StructuredContentType item and return the merged instance. This is an example of how the StructuredContentService can be used to manipulate StructuredContent items.

```java
    /**
     * Saves the given <b>type</b> and returns the merged instance
     */
    StructuredContentType saveStructuredContentType(StructuredContentType type);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="59">

---

# Finding StructuredContentType by Name

The findStructuredContentTypeByName method is used to retrieve a StructuredContentType associated with a given name. This is another example of how the StructuredContentService can be used to retrieve specific StructuredContent items.

```java
    /**
     * Returns the <code>StructuredContentType</code> associated with the passed in
     * String value.
     *
     * @param name - The name of the content type.
     * @return The associated <code>StructuredContentType</code>.
     */
    StructuredContentType findStructuredContentTypeByName(String name);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="194">

---

# Building StructuredContentDTO

The buildStructuredContentDTO method is used to convert a StructuredContent item into a StructuredContentDTO. If the item contains fields with broadleaf cms urls, the urls are converted to utilize the domain. This is an example of how the StructuredContentService can be used to transform StructuredContent items.

```java
    /**
     * Converts a StructuredContent into a StructuredContentDTO.   If the item contains fields with
     * broadleaf cms urls, the urls are converted to utilize the domain.
     * 
     * The StructuredContentDTO is built via the {@link EntityConfiguration}. To override the actual type that is returned,
     * include an override in an applicationContext like any other entity override.
     * 
     * @param sc
     * @param secure
     * @return
     */
    StructuredContentDTO buildStructuredContentDTO(StructuredContent sc, boolean secure);
```

---

</SwmSnippet>

# Structured Content Service Functions

The StructuredContentService interface provides a set of methods for managing StructuredContent items.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="47">

---

## findStructuredContentById

The `findStructuredContentById` function retrieves a StructuredContent item by its ID.

```java
    StructuredContent findStructuredContentById(Long contentId);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="56">

---

## findStructuredContentTypeById

The `findStructuredContentTypeById` function retrieves a StructuredContentType by its ID.

```java
    StructuredContentType findStructuredContentTypeById(Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="66">

---

## findStructuredContentTypeByName

The `findStructuredContentTypeByName` function retrieves a StructuredContentType by its name.

```java
    StructuredContentType findStructuredContentTypeByName(String name);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="72">

---

## retrieveAllStructuredContentTypes

The `retrieveAllStructuredContentTypes` function retrieves all StructuredContentType items.

```java
    List<StructuredContentType> retrieveAllStructuredContentTypes();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="94">

---

## findContentItems

The `findContentItems` function retrieves content items that match the passed in criteria.

```java
    List<StructuredContent> findContentItems(Criteria criteria);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="100">

---

## findAllContentItems

The `findAllContentItems` function retrieves all content items regardless of the Sandbox they are a member of.

```java
    List<StructuredContent> findAllContentItems();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="107">

---

## countContentItems

The `countContentItems` function counts the number of items in a sandbox that match the passed in Criteria.

```java
    Long countContentItems(Criteria c);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="112">

---

## saveStructuredContentType

The `saveStructuredContentType` function saves a given StructuredContentType and returns the merged instance.

```java
    StructuredContentType saveStructuredContentType(StructuredContentType type);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="137">

---

## lookupStructuredContentItemsByType

The `lookupStructuredContentItemsByType` function retrieves active content items that match the passed in type.

```java
    List<StructuredContentDTO> lookupStructuredContentItemsByType(StructuredContentType contentType, Locale locale, Integer count, Map<String,Object> ruleDTOs, boolean secure);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="159">

---

## lookupStructuredContentItemsByName

The `lookupStructuredContentItemsByName` function retrieves active content items that match the passed in name.

```java
    List<StructuredContentDTO> lookupStructuredContentItemsByName(String contentName, Locale locale, Integer count, Map<String,Object> ruleDTOs, boolean secure);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
