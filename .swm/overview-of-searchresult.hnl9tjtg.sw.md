---
title: Overview of SearchResult
---
SearchResult is a class that acts as a container for the results of a ProductSearch or a SkuSearch. It holds a list of products and facets, along with information about the total number of results, the current page, and the size of the page. It also contains a QueryResponse object, which represents the response from a Solr query. The SearchResult class provides methods to get and set these properties, as well as to calculate the start and end result indices and the total number of pages.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="30">

---

# SearchResult Class Structure

The 'SearchResult' class contains fields for the list of products found (`products`), the facets of the search (`facets`), and pagination details (`totalResults`, `page`, `pageSize`). It also includes a `queryResponse` field for the raw response from the search query.

```java
public class SearchResult {
    
    protected List<Product> products;
    protected List<SearchFacetDTO> facets;
    
    protected Integer totalResults;
    protected Integer page;
    protected Integer pageSize;

    protected QueryResponse queryResponse;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<SearchFacetDTO> getFacets() {
        return facets;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="130">

---

# Using SearchResult in Search Services

The 'SearchResult' class is used in various search services. For example, in the 'SolrSearchServiceImpl' class, 'SearchResult' is used to hold the results of explicit search operations by category.

```java
    @Override
    public SearchResult findExplicitSearchResultsByCategory(Category category, SearchCriteria searchCriteria) throws ServiceException {
        searchCriteria.setSearchExplicitCategory(true);
        searchCriteria.setCategory(category);
        return findSearchResults(searchCriteria);
    }

    @Override
    @Deprecated
    public SearchResult findSearchResultsByCategory(Category category, SearchCriteria searchCriteria) throws ServiceException {
        searchCriteria.setCategory(category);
        return findSearchResults(searchCriteria);
    }

    @Override
    @Deprecated
    public SearchResult findSearchResultsByQuery(String query, SearchCriteria searchCriteria) throws ServiceException {
        searchCriteria.setQuery(query);
        return findSearchResults(searchCriteria);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="123">

---

# Accessing Search Results

The 'SearchResult' class's getter methods are used to access the search results. For example, in the 'BroadleafSearchController' class, the 'findSearchResults' method of the search service returns a 'SearchResult' object, and its results are accessed using the 'getProducts' method.

```java
                SearchResult result = getSearchService().findSearchResults(searchCriteria);
```

---

</SwmSnippet>

# SearchResult Class Functions

The SearchResult class is a container that holds the result of a ProductSearch or a SkuSearch. It has several methods that allow to get and set the properties of a search result.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="41">

---

## getProducts and setProducts

The `getProducts` method is used to retrieve the list of products from a search result. The `setProducts` method is used to set the list of products in a search result.

```java
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="49">

---

## getFacets and setFacets

The `getFacets` method is used to retrieve the list of facets from a search result. The `setFacets` method is used to set the list of facets in a search result.

```java
    public List<SearchFacetDTO> getFacets() {
        return facets;
    }

    public void setFacets(List<SearchFacetDTO> facets) {
        this.facets = facets;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="57">

---

## getTotalResults and setTotalResults

The `getTotalResults` method is used to retrieve the total number of results from a search. The `setTotalResults` method is used to set the total number of results in a search.

```java
    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="65">

---

## getPage and setPage

The `getPage` method is used to retrieve the current page of the search results. The `setPage` method is used to set the current page of the search results.

```java
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="73">

---

## getPageSize and setPageSize

The `getPageSize` method is used to retrieve the size of the page in the search results. The `setPageSize` method is used to set the size of the page in the search results.

```java
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="81">

---

## getStartResult, getEndResult, and getTotalPages

The `getStartResult` method is used to calculate the start result of the search based on the page and page size. The `getEndResult` method is used to calculate the end result of the search based on the page, page size, and total results. The `getTotalPages` method is used to calculate the total number of pages in the search results.

```java
    public Integer getStartResult() {
        return (products == null || products.size() == 0) ? 0 : ((page - 1) * pageSize) + 1;
    }
    
    public Integer getEndResult() {
        return Math.min(page * pageSize, totalResults);
    }
    
    public Integer getTotalPages() {
        return (products == null || products.size() == 0) ? 1 : (int) Math.ceil(totalResults * 1.0 / pageSize);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="93">

---

## getQueryResponse and setQueryResponse

The `getQueryResponse` method is used to retrieve the query response from a search. The `setQueryResponse` method is used to set the query response in a search.

```java
    public QueryResponse getQueryResponse() {
        return queryResponse;
    }

    public void setQueryResponse(QueryResponse queryResponse) {
        this.queryResponse = queryResponse;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
