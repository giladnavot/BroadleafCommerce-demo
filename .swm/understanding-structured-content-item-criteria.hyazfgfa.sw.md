---
title: Understanding Structured Content Item Criteria
---
Structured Content Item Criteria in Broadleaf Commerce refers to the rules that determine the display of structured content items. These rules are used for targeting specific structured content items to users based on certain conditions. For instance, a structured content item could be set up to only show to users who have a particular product in their cart. The StructuredContentItemCriteria interface provides methods to get and set the parent structured content item, and to clone the item criteria. This functionality is crucial in the content management system when an item is edited.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="36">

---

# StructuredContentItemCriteria Interface

This is the StructuredContentItemCriteria interface. It extends QuantityBasedRule and MultiTenantCloneable. It provides methods to get and set the parent StructuredContent item, and to clone the entity.

```java
public interface StructuredContentItemCriteria extends QuantityBasedRule,MultiTenantCloneable<StructuredContentItemCriteria> {

    /**
     * Returns the parent <code>StructuredContent</code> item to which this
     * field belongs.
     *
     * @return
     */
    @Nonnull
    public StructuredContent getStructuredContent();

    /**
     * Sets the parent <code>StructuredContent</code> item.
     * @param structuredContent
     */
    public void setStructuredContent(@Nonnull StructuredContent structuredContent);

    /**
     * Builds a copy of this item.   Used by the content management system when an
     * item is edited.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="44">

---

# getStructuredContent Method

The getStructuredContent method is used to return the parent StructuredContent item to which this field belongs.

```java
    @Nonnull
    public StructuredContent getStructuredContent();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="51">

---

# setStructuredContent Method

The setStructuredContent method is used to set the parent StructuredContent item.

```java
    public void setStructuredContent(@Nonnull StructuredContent structuredContent);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="59">

---

# cloneEntity Method

The cloneEntity method is used to build a copy of this item. It is used by the content management system when an item is edited.

```java
    @Nonnull
    public StructuredContentItemCriteria cloneEntity();
```

---

</SwmSnippet>

# Functions of Structured Content Item Criteria

The StructuredContentItemCriteria interface has three main functions.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="44">

---

## getStructuredContent

The `getStructuredContent` function returns the parent StructuredContent item to which this field belongs.

```java
    @Nonnull
    public StructuredContent getStructuredContent();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="51">

---

## setStructuredContent

The `setStructuredContent` function sets the parent StructuredContent item.

```java
    public void setStructuredContent(@Nonnull StructuredContent structuredContent);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentItemCriteria.java" line="59">

---

## cloneEntity

The `cloneEntity` function builds a copy of this item. It is used by the content management system when an item is edited.

```java
    @Nonnull
    public StructuredContentItemCriteria cloneEntity();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
