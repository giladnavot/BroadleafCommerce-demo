---
title: Exploring Structured Content
---
Structured Content in BroadleafCommerce-demo refers to a generic content item with a set of predefined fields. The fields associated with an instance of StructuredContent are defined by its associated StructuredContentType. StructuredContent items are typically maintained via the Broadleaf Commerce admin and are often used to display targeted ads. The content can be customized based on StructuredContentMatchRules and priority settings, allowing different content to be shown to different users.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="30">

---

# StructuredContent Interface

This is the StructuredContent interface. It defines the methods that a StructuredContent implementation should have. These include getters and setters for the id, name, locale, and StructuredContentType, among others.

```java
/**
 * StructuredContent implementations provide a representation of a generic content
 * item with a set of predefined fields.    The fields associated with an instance
 * of StructuredContent are defined by its associated {@link StructuredContentType}.
 * <br>
 * StructuredContent items are typically maintained via the Broadleaf Commerce admin.
 * <br>
 * Display structured content items is typically done using the
 * {@link org.broadleafcommerce.cms.web.structure.DisplayContentTag} taglib.
 * <br>
 * An typical usage for <code>StructuredContent</code> is to display targeted ads.
 * Consider a <code>StructuredContentType</code> of "ad" with fields "ad-image" and
 * "target-url".    This "ad" might show on a websites home page.  By adding
 * <code>StructuredContentMatchRules</code> and setting the <code>priority</code>,
 * different ads could be shown to different users.
 *
 * It would not be typical in a Broadleaf implementation to extend this interface
 * or to use any implementation other than {@link StructuredContentImpl}.
 *
 * @see {@link StructuredContentType}
 * @see {@link StructuredContentImpl}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentImpl.java" line="139">

---

# StructuredContent Implementation

This is the StructuredContentImpl class, which is the default implementation of the StructuredContent interface in BroadleafCommerce-demo. It implements the methods defined in the StructuredContent interface.

```java
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")
public class StructuredContentImpl implements StructuredContent, AdminMainEntity, ProfileEntity {

    private static final long serialVersionUID = 1L;
    
    public static final String SC_DONT_DUPLICATE_SC_TYPE_HINT = "dont-duplicate-sc-type";

    @Id
    @GeneratedValue(generator = "StructuredContentId")
    @GenericGenerator(
        name="StructuredContentId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="StructuredContentImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.structure.domain.StructuredContentImpl")
        }
    )
    @Column(name = "SC_ID")
    protected Long id;

    @AdminPresentation(friendlyName = "StructuredContentImpl_Content_Name", order = 1,
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceImpl.java" line="110">

---

# Using StructuredContent

This is an example of how StructuredContent is used in the codebase. The StructuredContentServiceImpl class provides services to manage StructuredContent items. It uses the StructuredContentDao to fetch StructuredContent items from the database.

```java
    @Override
    public StructuredContent findStructuredContentById(Long contentId) {
        return structuredContentDao.findStructuredContentById(contentId);
    }

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
```

---

</SwmSnippet>

# StructuredContent Interface Functions

The StructuredContent interface provides a set of methods for managing structured content items.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="63">

---

## getId and setId

The `getId` and `setId` methods are used to get and set the primary key of the structured content item, respectively.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="80">

---

## getContentName and setContentName

The `getContentName` and `setContentName` methods are used to get and set the name of the structured content item, respectively.

```java
    @Nonnull
    public String getContentName();

    /**
     * Sets the name.
     * @param contentName
     */
    public void setContentName(@Nonnull String contentName);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="94">

---

## getLocale and setLocale

The `getLocale` and `setLocale` methods are used to get and set the locale associated with the structured content item, respectively.

```java
    @Nonnull
    public Locale getLocale();


    /**
     * Sets the locale associated with this content item.
     * @param locale
     */
    public void setLocale(@Nonnull Locale locale);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="109">

---

## getStructuredContentType and setStructuredContentType

The `getStructuredContentType` and `setStructuredContentType` methods are used to get and set the StructuredContentType associated with the structured content item, respectively.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="122">

---

## getStructuredContentFields and setStructuredContentFields

The `getStructuredContentFields` and `setStructuredContentFields` methods are used to get and set the structured content fields for this item, respectively. These methods are deprecated and it's recommended to use `getStructuredContentFieldXrefs` and `setStructuredContentFieldXrefs` instead.

```java
    @Nullable
    @Deprecated
    public Map<String, StructuredContentField> getStructuredContentFields();

    /**
     * @deprecated - Use {@link #setStructuredContentFieldXrefs(Map)}
     *
     * @param structuredContentFields
     */
    @Deprecated
    public void setStructuredContentFields(@Nullable Map<String, StructuredContentField> structuredContentFields);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="163">

---

## getOfflineFlag and setOfflineFlag

The `getOfflineFlag` and `setOfflineFlag` methods are used to get and set the offline flag of the structured content item, respectively. If the offline flag is true, the item should no longer appear on the site.

```java
    @Nullable
    public Boolean getOfflineFlag();

    /**
     * Sets the offline flag.
     *
     * @param offlineFlag
     */
    public void setOfflineFlag(@Nullable Boolean offlineFlag);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="179">

---

## getPriority and setPriority

The `getPriority` and `setPriority` methods are used to get and set the display priority of the structured content item, respectively. Items with a lower priority should be displayed before items with a higher priority.

```java
    @Nullable
    public Integer getPriority();

    /**
     * Sets the display priority of this item.   Lower priorities should be displayed first.
     *
     * @param priority
     */
    public void setPriority(@Nullable Integer priority);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
