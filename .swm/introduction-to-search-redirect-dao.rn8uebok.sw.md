---
title: Introduction to Search Redirect DAO
---
The Search Redirect DAO (Data Access Object) is a key component in the Broadleaf Commerce framework, specifically designed to handle search redirections. It provides an interface to retrieve search redirects based on search terms. The DAO is implemented by the `SearchRedirectDaoImpl` class, which uses the `EntityManager` to interact with the database. It also uses the `CriteriaBuilder` to construct the query for finding the search redirect by the search term. The DAO is designed to improve the efficiency of database operations related to search redirects by caching the current date/time for a specified duration, which is configurable. This helps in making the query caching more effective.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/dao/SearchRedirectDao.java" line="26">

---

# SearchRedirectDao Interface

This is the `SearchRedirectDao` interface. It declares the methods that are implemented in the `SearchRedirectDaoImpl` class. The `findSearchRedirectBySearchTerm` method is used to find a specific search redirect by a search term. The `getCurrentDateResolution` and `setCurrentDateResolution` methods are used to get and set the number of milliseconds that the current date/time will be cached for queries before refreshing.

```java
public interface SearchRedirectDao {


    public SearchRedirect findSearchRedirectBySearchTerm(String uri);

    /**
     * Returns the number of milliseconds that the current date/time will be cached for queries before refreshing.
     * This aids in query caching, otherwise every query that utilized current date would be different and caching
     * would be ineffective.
     *
     * @return the milliseconds to cache the current date/time
     */
    Long getCurrentDateResolution();

    /**
     * Sets the number of milliseconds that the current date/time will be cached for queries before refreshing.
     * This aids in query caching, otherwise every query that utilized current date would be different and caching
     * would be ineffective.
     *
     * @param currentDateResolution the milliseconds to cache the current date/time
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/dao/SearchRedirectDaoImpl.java" line="43">

---

# SearchRedirectDaoImpl Class

This is the `SearchRedirectDaoImpl` class. It implements the `SearchRedirectDao` interface and provides the functionality for managing search redirects. The `findSearchRedirectBySearchTerm` method is used to find a specific search redirect by a search term. It uses the `buildFindSearchRedirectBySearchTermCriteria` method to build the criteria for the search. The `getCurrentDateResolution` and `setCurrentDateResolution` methods are used to get and set the number of milliseconds that the current date/time will be cached for queries before refreshing.

```java
@Repository("blSearchRedirectDao")
public class SearchRedirectDaoImpl implements SearchRedirectDao {

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Value("${searchRedirect.is.null.activeStartDate.active:false}")
    protected boolean isNullActiveStartDateActive;

    @Value("${query.dateResolution.searchredirect:10000}")
    protected Long currentDateResolution;

    protected Date cachedDate = SystemTime.asDate();

    protected Date getCurrentDateAfterFactoringInDateResolution() {
        Date returnDate = SystemTime.getCurrentDateWithinTimeResolution(cachedDate, getCurrentDateResolution());
        if (returnDate != cachedDate) {
            if (SystemTime.shouldCacheDate()) {
                cachedDate = returnDate;
            }
        }
```

---

</SwmSnippet>

# SearchRedirectDao Functions

The SearchRedirectDao interface provides three main methods for interacting with SearchRedirect data.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/dao/SearchRedirectDaoImpl.java" line="67">

---

## findSearchRedirectBySearchTerm

The `findSearchRedirectBySearchTerm` method is used to find a SearchRedirect by its search term. It creates a query using the `buildFindSearchRedirectBySearchTermCriteria` method, sets the maximum results to 1, and sets the query to be cacheable. It then executes the query and returns the first result if any, or null if no results are found.

```java
    @Override
    public SearchRedirect findSearchRedirectBySearchTerm(String searchTerm) {
        Query query = em.createQuery(buildFindSearchRedirectBySearchTermCriteria(searchTerm));
        query.setMaxResults(1);
        query.setHint(QueryHints.HINT_CACHEABLE, true);

        List<SearchRedirect> results = query.getResultList();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/dao/SearchRedirectDaoImpl.java" line="107">

---

## getCurrentDateResolution

The `getCurrentDateResolution` method is used to get the current date resolution. This is the number of milliseconds that the current date/time will be cached for queries before refreshing. This aids in query caching, otherwise every query that utilized current date would be different and caching would be ineffective.

```java
    @Override
    public Long getCurrentDateResolution() {
        return currentDateResolution;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/dao/SearchRedirectDaoImpl.java" line="112">

---

## setCurrentDateResolution

The `setCurrentDateResolution` method is used to set the current date resolution. This is the number of milliseconds that the current date/time will be cached for queries before refreshing. This aids in query caching, otherwise every query that utilized current date would be different and caching would be ineffective.

```java
    @Override
    public void setCurrentDateResolution(Long currentDateResolution) {
        this.currentDateResolution = currentDateResolution;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
