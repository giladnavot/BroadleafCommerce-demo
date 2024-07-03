---
title: Overview of Search Criteria
---
Search Criteria in BroadleafCommerce-demo refers to a set of parameters that are used to filter and sort the product search results. It is encapsulated in the `SearchCriteria` class, which contains fields such as `page`, `pageSize`, `sortQuery`, `filterCriteria`, `filterQueries`, `category`, and `query`. These fields represent the page number, the number of results per page, the sorting query, the filtering criteria, the filter queries, the category of the product, and the search query respectively. The `SearchCriteria` class also provides getter and setter methods for these fields. This class is used across the codebase to perform product searches based on user input.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="34">

---

# SearchCriteria Class

This is the SearchCriteria class. It contains various fields representing search parameters such as page number, page size, sort query, filter criteria, etc. It also provides getter and setter methods for these fields.

```java
public class SearchCriteria {
    
    public static String PAGE_SIZE_STRING = "pageSize";
    public static String PAGE_NUMBER = "page";
    public static String SORT_STRING = "sort";
    public static String QUERY_STRING = "q";
    public static String REQUEST_HANDLER = "qt";
    
    protected Integer page = 1;
    protected Integer pageSize;
    protected Integer startIndex;
    protected String sortQuery;
    protected String requestHandler;
    protected Map<String, String[]> filterCriteria = new HashMap<>();
    protected Collection<String> filterQueries = new ArrayList<>();
    /**
     * The category that the user searched on
     */
    protected Category category;

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/DatabaseSearchServiceImpl.java" line="75">

---

# Usage of SearchCriteria

Here, the SearchCriteria object is passed as a parameter to the findExplicitSearchResultsByCategory method. The method uses the search parameters encapsulated in the SearchCriteria object to perform the search operation.

```java
    public SearchResult findExplicitSearchResultsByCategory(Category category, SearchCriteria searchCriteria) throws ServiceException {
        throw new UnsupportedOperationException("See findProductsByCategory or use the SolrSearchService implementation");
    }
    
    @Override
    public SearchResult findSearchResultsByCategoryAndQuery(Category category, String query, SearchCriteria searchCriteria) throws ServiceException {
        throw new UnsupportedOperationException("This operation is only supported by the SolrSearchService by default");
    }
    
    @Override
    public SearchResult findSearchResultsByCategory(Category category, SearchCriteria searchCriteria) {
        SearchResult result = new SearchResult();
        setQualifiedKeys(searchCriteria);
        List<Product> products = catalogService.findFilteredActiveProductsByCategory(category, searchCriteria);
        List<SearchFacetDTO> facets = getCategoryFacets(category);
        setActiveFacets(facets, searchCriteria);
        result.setProducts(products);
        result.setFacets(facets);
        result.setTotalResults(products.size());
        result.setPage(1);
        result.setPageSize(products.size());
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/service/SearchFacetDTOServiceImpl.java" line="56">

---

# Setting Search Parameters

In this example, the buildSearchCriteria method creates an instance of SearchCriteria and sets various search parameters like query, request handler, etc. using the setter methods provided by the SearchCriteria class.

```java
        SearchCriteria searchCriteria = createSearchCriteria();
        searchCriteria.setPageSize(getDefaultPageSize());

        Map<String, String[]> facets = new HashMap<>();

        for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey();

            if (Objects.equals(key, SearchCriteria.SORT_STRING)) {
                searchCriteria.setSortQuery(StringUtils.join(entry.getValue(), ","));
            } else if (Objects.equals(key, SearchCriteria.PAGE_NUMBER)) {
                searchCriteria.setPage(Integer.parseInt(entry.getValue()[0]));
            } else if (Objects.equals(key, SearchCriteria.PAGE_SIZE_STRING)) {
                int requestedPageSize = Integer.parseInt(entry.getValue()[0]);
                int maxPageSize = getMaxPageSize();
                searchCriteria.setPageSize(Math.min(requestedPageSize, maxPageSize));
            } else if (Objects.equals(key, SearchCriteria.QUERY_STRING)) {
                String query = request.getParameter(SearchCriteria.QUERY_STRING);
                try {
                    if (StringUtils.isNotEmpty(query)) {
                        query = exploitProtectionService.cleanString(StringUtils.trim(query));
```

---

</SwmSnippet>

# SearchCriteria Functionality

The SearchCriteria class is a crucial part of the search functionality in the BroadleafCommerce-demo repository. It provides a way to specify the criteria for product searches, including page size, page number, sort order, query string, and request handler. It also allows for filtering based on specific criteria.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="65">

---

## getPage and setPage

The `getPage` method returns the current page number for the search results. The `setPage` method allows you to set the page number. This is useful for implementing pagination in search results.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="89">

---

## getSortQuery and setSortQuery

The `getSortQuery` method returns the current sort query for the search results. The `setSortQuery` method allows you to set the sort query. This is useful for sorting the search results based on different criteria.

```java
    public String getSortQuery() {
        return sortQuery;
    }
    
    public void setSortQuery(String sortQuery) {
        this.sortQuery = sortQuery;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="97">

---

## getFilterCriteria and setFilterCriteria

The `getFilterCriteria` method returns the current filter criteria for the search results. The `setFilterCriteria` method allows you to set the filter criteria. This is useful for filtering the search results based on different criteria.

```java
    public Map<String, String[]> getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(Map<String, String[]> filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="113">

---

## getQuery and setQuery

The `getQuery` method returns the current query string for the search. The `setQuery` method allows you to set the query string. This is useful for performing searches based on user input.

```java
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
