---
title: Examining the Role of IndexField in Interfacing with Solr
---
This document will cover the role of the `IndexField` interface in the BroadleafCommerce-demo repository, specifically in its interaction with Solr. We'll cover:

1. What is `IndexField` and its methods
2. How `IndexField` is used in the codebase
3. The interaction of `IndexField` with Solr

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="25">

---

# What is IndexField

`IndexField` is an interface that represents a field stored in the search index. It has methods to get and set the id, determine if the field is searchable, get and set the field, and get and set the searchable field types.

```java
/**
 * Represents a field that gets stored in the search index
 * 
 * @author Chad Harchar (charchar)
 */
public interface IndexField extends Serializable, MultiTenantCloneable<IndexField>  {

    /**
     * Gets the id for this search field
     *
     * @return
     */
    public Long getId();

    /**
     * Sets the id for this search field
     *
     * @param id
     */
    public void setId(Long id);
    
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceExtensionHandler.java" line="48">

---

# Usage of IndexField in the codebase

`IndexField` is used as a parameter in the `buildPrefixListForIndexField` method of the `SolrSearchServiceExtensionHandler` interface. This method returns a prefix if required for the passed in searchable field.

```java
    ExtensionResultStatusType buildPrefixListForIndexField(IndexField field, FieldType fieldType,
            List<String> prefixList);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceExtensionHandler.java" line="112">

---

# Interaction of IndexField with Solr

`IndexField` is used in the `getQueryField` method of the `SolrSearchServiceExtensionHandler` interface. This method finds and adds the query fields for the given search field and searchable field type.

```java
    ExtensionResultStatusType getQueryField(SolrQuery query, SearchCriteria searchCriteria, IndexFieldType indexFieldType, ExtensionResultHolder<List<String>> queryFieldsResult);

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
