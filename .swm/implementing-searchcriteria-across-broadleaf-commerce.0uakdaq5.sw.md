---
title: Implementing SearchCriteria Across Broadleaf Commerce
---
This document will cover the implementation of SearchCriteria across the BroadleafCommerce-demo application. We'll cover:

1. The definition of SearchCriteria
2. How SearchCriteria is used in the codebase
3. The data flow of SearchCriteria
4. Examples and use cases of SearchCriteria.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="29">

---

# Definition of SearchCriteria

SearchCriteria is a class that holds additional criteria to consider when performing searches for Products. It contains several static strings representing different search parameters, and a number of instance variables including page, pageSize, startIndex, sortQuery, requestHandler, filterCriteria, filterQueries, category, query, and searchExplicitCategory.

```java
/**
 * Container that holds additional criteria to consider when performing searches for Products
 * 
 * @author Andre Azzolini (apazzolini)
 */
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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/DatabaseSearchServiceImpl.java" line="74">

---

# Usage of SearchCriteria in the codebase

SearchCriteria is used in the DatabaseSearchServiceImpl class. It is passed as a parameter to various methods that perform searches for Products.

```java
    @Override
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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/SearchService.java" line="51">

---

SearchCriteria is also used in the SearchService interface. It is passed as a parameter to the findSearchResultsByCategory method.

```java
    @Deprecated
    public SearchResult findSearchResultsByCategory(Category category, SearchCriteria searchCriteria)
            throws ServiceException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/service/SearchFacetDTOServiceImpl.java" line="54">

---

# Data flow of SearchCriteria

The buildSearchCriteria method in the SearchFacetDTOServiceImpl class constructs a SearchCriteria object based on the parameters of an HTTP request.

```java
    @Override
    public SearchCriteria buildSearchCriteria(HttpServletRequest request) {
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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafCategoryController.java" line="122">

---

The constructed SearchCriteria object is then used in the findSearchResults method of the SearchService.

```java
            SearchCriteria searchCriteria = facetService.buildSearchCriteria(request);
            SearchResult result = getSearchService().findSearchResults(searchCriteria);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="130">

---

# Examples and use cases of SearchCriteria

In the SolrSearchServiceImpl class, SearchCriteria is used to set search parameters such as the category and query for a search.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
