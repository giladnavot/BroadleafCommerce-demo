---
title: Basic Concepts of Search
---
Search in BroadleafCommerce-demo refers to the functionality that allows users to find specific products within the e-commerce platform. It is implemented using classes like SearchQuery, SearchResult, and SearchService. The SearchQuery class is used to define the search query string. The SearchResult class is a container that holds the result of a ProductSearch or a SkuSearch. The SearchService interface defines the methods for performing searches for search results in a given category or across all categories for a given query, taking into consideration the SearchCriteria. The SearchCriteria class is a container that holds additional criteria to consider when performing searches for Products.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/SearchService.java" line="34">

---

# SearchService Interface

The SearchService interface defines the contract for the search functionality. It declares methods for performing searches based on various criteria, such as category and query string. The results of the search are returned as SearchResult objects.

```java
public interface SearchService {

    /**
     * Performs a search for search results in the given category, taking into consideration the SearchCriteria
     * 
     * This method will return search results that are in any sub-level of a given category. For example, if you had a 
     * "Routers" category and a "Enterprise Routers" sub-category, asking for search results in "Routers", would return
     * search results that are in the "Enterprise Routers" category. 
     * 
     * @see #findExplicitSearchResultsByCategory(Category, SearchCriteria)
     *
     * @param category
     * @param searchCriteria
     * @return the result of the search
     * @throws ServiceException
     * @deprecated use #findSearchResults(SearchCriteria)
     */
    @Deprecated
    public SearchResult findSearchResultsByCategory(Category category, SearchCriteria searchCriteria)
            throws ServiceException;
    
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchQuery.java" line="20">

---

# SearchQuery Class

The SearchQuery class encapsulates the query string used in a search. It provides getter and setter methods for the query string.

```java
public class SearchQuery {

    private String queryString;
    
    public SearchQuery() { }
    public SearchQuery(String queryString) {
        setQueryString(queryString);
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchResult.java" line="30">

---

# SearchResult Class

The SearchResult class is a container that holds the result of a product search. It includes a list of products, a list of facets, and pagination information such as total results, page, and page size.

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

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="123">

---

# Using SearchService

The SearchService is used in the BroadleafSearchController to perform a search based on the given SearchCriteria. The result of the search is a SearchResult object.

```java
                SearchResult result = getSearchService().findSearchResults(searchCriteria);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
