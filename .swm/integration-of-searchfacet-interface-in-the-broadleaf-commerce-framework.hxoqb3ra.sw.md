---
title: Integration of SearchFacet Interface in the Broadleaf Commerce Framework
---
This document will cover the integration of the SearchFacet interface into the Broadleaf Commerce framework. We'll cover:

1. The role of the SearchFacet interface
2. How the SearchFacet interface is used in the codebase
3. The data flow involving the SearchFacet interface
4. Examples of the SearchFacet interface usage.

# The role of the SearchFacet interface

The SearchFacet interface represents a particular facet that can be used to guide faceted searching on a results page. It is a key component in the search functionality of the Broadleaf Commerce framework, enabling the categorization and filtering of search results.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchFacet.java" line="25">

---

# How the SearchFacet interface is used in the codebase

The SearchFacet interface is defined here. It includes methods for getting and setting the internal id, field type, name, label, and other properties of a search facet. It also includes methods for working with search facet ranges and required facets.

```java
/**
 * A SearchFacet is an object that represents a particular facet that can be used to guide faceted 
 * searching on a results page.
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface SearchFacet extends Serializable, MultiTenantCloneable<SearchFacet> {

    /**
     * Returns the internal id
     * 
     * @return the internal id
     */
    public Long getId();

    /**
     * Sets the internal id
     * 
     * @param id
     */
    public void setId(Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="605">

---

# Data flow involving the SearchFacet interface

The `buildSearchFacetDTOs` method in the `SolrSearchServiceImpl` class uses the SearchFacet interface. It calls the `buildSearchFacetDTOs` method of the `SearchFacetDTOService` to create a list of SearchFacetDTO objects from a list of SearchFacet objects.

```java
    /**
     * Create the wrapper DTO around the SearchFacet
     * 
     * @param searchFacets
     * @return the wrapper DTO
     */
    protected List<SearchFacetDTO> buildSearchFacetDTOs(List<SearchFacet> searchFacets) {
        return shs.buildSearchFacetDTOs(searchFacets);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafCategoryController.java" line="75">

---

# Examples of the SearchFacet interface usage

The `SearchFacetDTOService` is used in the `BroadleafCategoryController` class. This service, which uses the SearchFacet interface, is injected as a resource.

```java
    @Resource(name = "blSearchFacetDTOService")
    protected SearchFacetDTOService facetService;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
