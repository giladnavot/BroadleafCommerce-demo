---
title: Exploring Search Facet
---
Search Facet in BroadleafCommerce-demo refers to a feature that allows users to refine search results by applying filters. It is a key component of the search functionality in the e-commerce framework. The SearchFacet interface and its implementations like CategorySearchFacet, CategoryExcludedSearchFacet, and SearchFacetRange provide the necessary methods to get and set search facets. These facets can be associated with categories, have a sequence for ordering, and can be excluded from search. They can also have a range, allowing for more specific filtering of search results.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchFacetRange.java" line="80">

---

## Using Search Facet in Code

The `getSearchFacet` method is used to get the associated SearchFacet to this range.

```java
    public SearchFacet getSearchFacet();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchFacetRange.java" line="87">

---

## Setting Search Facet in Code

The `setSearchFacet` method is used to set the associated SearchFacet.

```java
    public void setSearchFacet(SearchFacet searchFacet);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrHelperServiceImpl.java" line="651">

---

## Using Search Facet in Services

The `getFacet` method is used within `setFacetResults` method in `SolrHelperServiceImpl` to get the associated SearchFacet.

```java
                    resultDTO.setFacet(facetDTO.getFacet());
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
