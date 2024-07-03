---
title: Exploring the Index Field
---
The IndexField in BroadleafCommerce-demo refers to a field that gets stored in the search index. It is an interface that extends Serializable and MultiTenantCloneable. It has methods to get and set the id of the search field, determine if the field is searchable, get and set the field for this search field, and get and set the searchable field types for this search field. It is used extensively across the codebase, particularly in search and indexing related functionalities.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="30">

---

# IndexField Interface

This is the IndexField interface. It provides several methods to interact with the search field. These methods include `getId()`, `setId(Long id)`, `getSearchable()`, `setSearchable(Boolean searchable)`, `getField()`, `setField(Field field)`, `getFieldTypes()`, and `setFieldTypes(List<IndexFieldType> fieldTypes)`. These methods are used to manage the data fields that are stored in the search index.

```java
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
    
    /**
     * Whether or not the user should see results for this field when typing in search terms in the omnibox, or if
     * this is just a field stored in the index (like margin or sorts)
     */
    public Boolean getSearchable();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceExtensionHandler.java" line="48">

---

# Usage of IndexField

Here, the `buildPrefixListForIndexField(IndexField field, FieldType fieldType, List<String> prefixList)` method is used. This method is part of the SolrSearchServiceExtensionHandler interface and it uses the IndexField to build a prefix list for the index field.

```java
    ExtensionResultStatusType buildPrefixListForIndexField(IndexField field, FieldType fieldType,
            List<String> prefixList);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="366">

---

# IndexField in SolrSearchService

In the SolrSearchServiceImpl class, the `getSearchableIndexFields()` method is used to retrieve a list of IndexFields. Then, for each IndexField, the `getQueryFields(SolrQuery query, final List<String> queryFields, IndexField indexField, SearchCriteria searchCriteria)` method is used to get the query fields for the given IndexField.

```java
        List<IndexField> fields = shs.getSearchableIndexFields();

        // we want to gather all the query fields into one list
        List<String> queryFields = new ArrayList<>();
        for (IndexField currentField : fields) {
            getQueryFields(query, queryFields, currentField, searchCriteria);
```

---

</SwmSnippet>

# IndexField Interface Functions

The IndexField interface provides several methods for manipulating and retrieving information about the index field.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="32">

---

## getId and setId

The `getId` and `setId` methods are used to get and set the ID of the search field respectively.

```java
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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="46">

---

## getSearchable and setSearchable

The `getSearchable` and `setSearchable` methods are used to determine whether the user should see results for this field when typing in search terms in the omnibox, or if this is just a field stored in the index.

```java
    /**
     * Whether or not the user should see results for this field when typing in search terms in the omnibox, or if
     * this is just a field stored in the index (like margin or sorts)
     */
    public Boolean getSearchable();

    public void setSearchable(Boolean searchable);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="54">

---

## getField and setField

The `getField` and `setField` methods are used to get and set the field for this search field respectively.

```java
    /**
     * Gets the field for this search field
     *
     * @return
     */
    public Field getField();

    /**
     * Sets the field for this search field
     *
     * @param field
     */
    public void setField(Field field);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="68">

---

## getFieldTypes and setFieldTypes

The `getFieldTypes` and `setFieldTypes` methods are used to get and set the searchable field types for this search field respectively.

```java
    /**
     * Gets the searchable field types for this search field
     *
     * @return
     */
    public List<IndexFieldType> getFieldTypes();

    /**
     * Sets the searchable field types for this search field
     *
     * @param fieldTypes
     */
    public void setFieldTypes(List<IndexFieldType> fieldTypes);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
