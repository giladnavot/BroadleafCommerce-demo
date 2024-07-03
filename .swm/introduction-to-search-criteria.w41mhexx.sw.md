---
title: Introduction to Search Criteria
---
SearchCriteria is a class that serves as a container for additional criteria to consider when performing searches for Products. It holds various parameters such as page size, page number, sort order, query string, and request handler. It also contains information about the category that the user searched on and the query that the user actually typed into the search box. This class is used in various parts of the codebase to facilitate product searches, making it a crucial part of the product search functionality in Broadleaf Commerce.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchCriteria.java" line="34">

---

# SearchCriteria Class

This is the definition of the SearchCriteria class. It contains fields for various search parameters and getter and setter methods for these fields.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="130">

---

# Usage in SolrSearchServiceImpl

Here, the SearchCriteria object is used in the findSearchResultsByCategory and findSearchResultsByQuery methods. The search parameters are set on the SearchCriteria object and then passed to the findSearchResults method.

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

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/service/SearchFacetDTOServiceImpl.java" line="54">

---

# Usage in SearchFacetDTOServiceImpl

In this class, a SearchCriteria object is built from a HttpServletRequest. The various search parameters are extracted from the request and set on the SearchCriteria object.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
