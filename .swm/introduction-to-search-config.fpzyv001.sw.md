---
title: Introduction to Search Config
---
SearchConfig is an interface in the Broadleaf Commerce framework. It is part of the core search domain, which is responsible for handling search-related functionalities in the e-commerce platform. The SearchConfig interface is used in the Field class, which represents a field in the search domain. However, the default Field implementation does not support search configs, as indicated by the UnsupportedOperationException thrown in the getSearchConfigs and setSearchConfigs methods in the FieldImpl class.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchConfig.java" line="25">

---

# SearchConfig Interface

This is the SearchConfig interface. It is an empty interface, which means it does not define any methods. It serves as a marker interface to represent search configurations in the BroadleafCommerce framework.

```java
public interface SearchConfig {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/Field.java" line="107">

---

# Usage of SearchConfig in Field

Here, the SearchConfig interface is used in the Field class. The `getSearchConfigs` method returns a list of SearchConfig objects, and the `setSearchConfigs` method accepts a list of SearchConfig objects. This shows how SearchConfig can be used to manage multiple search configurations.

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

# Usage of SearchConfig in FieldImpl

In the FieldImpl class, the `getSearchConfigs` and `setSearchConfigs` methods throw an UnsupportedOperationException. This indicates that the default Field implementation does not support search configurations, and a custom implementation of Field that supports SearchConfig is required.

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

The SearchConfig interface is a part of the Broadleaf Commerce Framework. It is used in the Field and FieldImpl classes.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchConfig.java" line="25">

---

## SearchConfig Interface

The `SearchConfig` interface is defined here. However, it does not contain any methods or fields.

```java
public interface SearchConfig {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/Field.java" line="107">

---

## Usage in Field Class

The `SearchConfig` interface is used in the `Field` class. The `getSearchConfigs` method returns a list of `SearchConfig` objects, and the `setSearchConfigs` method accepts a list of `SearchConfig` objects.

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

The `SearchConfig` interface is also used in the `FieldImpl` class. However, the `getSearchConfigs` and `setSearchConfigs` methods throw an `UnsupportedOperationException`, indicating that the default `Field` implementation does not support search configs.

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
