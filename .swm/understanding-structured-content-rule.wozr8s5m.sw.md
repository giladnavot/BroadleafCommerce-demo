---
title: Understanding Structured Content Rule
---
The StructuredContentRule in BroadleafCommerce-demo refers to a rule used to determine if a StructuredContent item should be displayed. It is represented as a valid MVEL string. The Content Management System by default is able to process rules based on the current customer, product, time, or request. It also includes methods to get and set the primary key, and to clone the rule, which is used by the content management system when an item is edited.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="26">

---

# StructuredContentRule Interface

This is the StructuredContentRule interface. It extends SimpleRule and MultiTenantCloneable. It provides methods to get and set the primary key, and to clone the rule.

```java
/**
 * Implementations hold the values for a rule used to determine if a <code>StructuredContent</code>
 * item should be displayed.
 * <br>
 * The rule is represented as a valid MVEL string.    The Content Management System by default
 * is able to process rules based on the current customer, product,
 * {@link org.broadleafcommerce.common.TimeDTO time}, or {@link org.broadleafcommerce.common.RequestDTO request}
 *
 * @see org.broadleafcommerce.cms.web.structure.DisplayContentTag
 * @see org.broadleafcommerce.cms.structure.service.StructuredContentServiceImpl#evaluateAndPriortizeContent(java.util.List, int, java.util.Map)
 * @author jfischer
 * @author bpolster
 *
 */
public interface StructuredContentRule extends SimpleRule,MultiTenantCloneable<StructuredContentRule> {

    /**
     * Gets the primary key.
     *
     * @return the primary key
     */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="47">

---

# getId Method

The getId method is used to get the primary key of the StructuredContentRule.

```java
    @Nullable
    public Long getId();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="55">

---

# setId Method

The setId method is used to set the primary key of the StructuredContentRule.

```java
    public void setId(@Nullable Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="63">

---

# cloneEntity Method

The cloneEntity method is used to create a copy of the StructuredContentRule. This is useful when an item is edited in the content management system.

```java
    @Nonnull
    public StructuredContentRule cloneEntity();
```

---

</SwmSnippet>

# StructuredContentRule Functions

The StructuredContentRule interface

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="47">

---

## getId Function

The `getId` function is used to get the primary key of the StructuredContentRule.

```java
    @Nullable
    public Long getId();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="55">

---

## setId Function

The `setId` function is used to set the primary key of the StructuredContentRule.

```java
    public void setId(@Nullable Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentRule.java" line="63">

---

## cloneEntity Function

The `cloneEntity` function is used to create a copy of the StructuredContentRule. This is particularly useful when an item is edited in the content management system.

```java
    @Nonnull
    public StructuredContentRule cloneEntity();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
