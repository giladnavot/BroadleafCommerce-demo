---
title: Overview of Search Redirect Service
---
The Search Redirect Service in BroadleafCommerce-demo is a component that handles the redirection of search queries. It is implemented as an interface `SearchRedirectService` and its implementation `SearchRedirectServiceImpl`. The service provides a method `findSearchRedirectBySearchTerm(String uri)`, which checks if there is a matching `SearchRedirect` for the passed in URL. If a match is found, it returns the `SearchRedirect` object, otherwise it returns null. This service is crucial for managing search operations in the e-commerce platform, ensuring that search queries are properly redirected to their corresponding destinations.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectService.java" line="26">

---

# SearchRedirectService Interface

This is the `SearchRedirectService` interface. It declares the `findSearchRedirectBySearchTerm` method, which takes a URL as a parameter and returns a `SearchRedirect` object if a match is found.

```java
public interface SearchRedirectService {

    /**
     * Checks the passed in URL to determine if there is a matching SearchRedirect.
     * Returns null if no handler was found.
     * 
     * @param uri
     * @return
     */
    public SearchRedirect findSearchRedirectBySearchTerm(String uri);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectServiceImpl.java" line="29">

---

# SearchRedirectServiceImpl Class

This is the `SearchRedirectServiceImpl` class, which implements the `SearchRedirectService` interface. It uses the `SearchRedirectDao` to perform the actual search for a matching redirect.

```java
@Service("blSearchRedirectService")
public class SearchRedirectServiceImpl implements SearchRedirectService {

  
    @Resource(name = "blSearchRedirectDao")
    protected SearchRedirectDao SearchRedirectDao;


    /**
     * Checks the passed in URL to determine if there is a matching
     * SearchRedirect. Returns null if no handler was found.
     * 
     * @param uri
     * @return
     */
    @Override
    public SearchRedirect findSearchRedirectBySearchTerm(String uri) {

        SearchRedirect SearchRedirect = SearchRedirectDao
                .findSearchRedirectBySearchTerm(uri);
        if (SearchRedirect != null) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectServiceImpl.java" line="44">

---

# findSearchRedirectBySearchTerm Method

This is the `findSearchRedirectBySearchTerm` method implementation. It uses the `SearchRedirectDao` to find a `SearchRedirect` that matches the given URL. If a match is found, it returns the `SearchRedirect`; otherwise, it returns null.

```java
    @Override
    public SearchRedirect findSearchRedirectBySearchTerm(String uri) {

        SearchRedirect SearchRedirect = SearchRedirectDao
                .findSearchRedirectBySearchTerm(uri);
        if (SearchRedirect != null) {
            return SearchRedirect;
        } else {
            return null;
        }

    }
```

---

</SwmSnippet>

# Search Redirect Service Functions

The Search Redirect Service is a key component in the search functionality of BroadleafCommerce-demo. It primarily consists of the function `findSearchRedirectBySearchTerm`.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectServiceImpl.java" line="44">

---

## findSearchRedirectBySearchTerm

The `findSearchRedirectBySearchTerm` function is the main function of the Search Redirect Service. It checks the passed in URL to determine if there is a matching SearchRedirect. If a matching SearchRedirect is found, it returns the SearchRedirect. If no matching SearchRedirect is found, it returns null.

```java
    @Override
    public SearchRedirect findSearchRedirectBySearchTerm(String uri) {

        SearchRedirect SearchRedirect = SearchRedirectDao
                .findSearchRedirectBySearchTerm(uri);
        if (SearchRedirect != null) {
            return SearchRedirect;
        } else {
            return null;
        }

    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
