---
title: Basic Concepts of SearchConfig
---
SearchConfig is an interface in the Broadleaf Commerce framework. It is located in the package org.broadleafcommerce.core.search.domain. Although the interface itself is empty, it is referenced in the Field and FieldImpl classes. In these classes, it is used to get and set search configurations, although the default Field implementation does not support search configurations.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchConfig.java" line="25">

---

# SearchConfig Interface

This is the SearchConfig interface. It is currently empty, indicating that it can be implemented with any necessary methods for configuring search operations.

```java
public interface SearchConfig {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/Field.java" line="107">

---

# Usage in Field Interface

Here, the SearchConfig interface is used as a part of the Field interface. The getSearchConfigs and setSearchConfigs methods are defined to handle a list of SearchConfig objects.

```java
    @Deprecated
    public List<SearchConfig> getSearchConfigs();
    
    /**
     * Sets the searchConfigs. 
     * @param searchConfigs
     * @deprecated
     */
    @Deprecated
    public void setSearchConfigs(List<SearchConfig> searchConfigs);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/FieldImpl.java" line="198">

---

# Usage in FieldImpl Class

In the FieldImpl class, which implements the Field interface, the getSearchConfigs and setSearchConfigs methods throw an UnsupportedOperationException. This indicates that the default implementation does not support search configs.

```java
    @Override
    public List<SearchConfig> getSearchConfigs() {
        throw new UnsupportedOperationException("The default Field implementation does not support search configs");
    }

    @Deprecated
    @Override
    public void setSearchConfigs(List<SearchConfig> searchConfigs) {
        throw new UnsupportedOperationException("The default Field implementation does not support search configs");
```

---

</SwmSnippet>

# SearchConfig Interface and Its Usage

The SearchConfig Interface

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchConfig.java" line="25">

---

## SearchConfig Interface

The SearchConfig interface is defined here. However, it does not contain any methods or fields.

```java
public interface SearchConfig {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/Field.java" line="107">

---

## Usage in Field Class

The SearchConfig interface is used in the Field class. It is used in the getSearchConfigs and setSearchConfigs methods, which are deprecated.

```java
    @Deprecated
    public List<SearchConfig> getSearchConfigs();
    
    /**
     * Sets the searchConfigs. 
     * @param searchConfigs
     * @deprecated
     */
    @Deprecated
    public void setSearchConfigs(List<SearchConfig> searchConfigs);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/FieldImpl.java" line="198">

---

## Usage in FieldImpl Class

The SearchConfig interface is also used in the FieldImpl class. However, the getSearchConfigs and setSearchConfigs methods throw an UnsupportedOperationException, indicating that the default Field implementation does not support search configs.

```java
    @Override
    public List<SearchConfig> getSearchConfigs() {
        throw new UnsupportedOperationException("The default Field implementation does not support search configs");
    }

    @Deprecated
    @Override
    public void setSearchConfigs(List<SearchConfig> searchConfigs) {
        throw new UnsupportedOperationException("The default Field implementation does not support search configs");
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
