---
title: Introduction to Search Redirection
---
Search Redirection in BroadleafCommerce-demo refers to the functionality that allows the system to redirect a user based on their search term. This is achieved through the `SearchRedirect` interface, which contains methods for setting and retrieving the search term, the URL to redirect to, and the active status of the redirect. The `SearchRedirectService` interface provides a method `findSearchRedirectBySearchTerm` that checks if there is a matching `SearchRedirect` for a given URL. The `SearchRedirectDao` interface provides a method with the same name that retrieves the `SearchRedirect` from the database. The `SearchRedirectServiceImpl` and `SearchRedirectDaoImpl` classes are the implementations of these interfaces.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirect.java" line="23">

---

## SearchRedirect Object

The `SearchRedirect` interface defines the structure of a search redirect object. It includes methods to get and set the search term (`getSearchTerm`, `setSearchTerm`), the URL to redirect to (`getUrl`, `setUrl`), and whether the redirect is active (`isActive`).

```java
public interface SearchRedirect extends Serializable {

    public Long getId();

    public void setId(Long id);

    public String getSearchTerm();

    public void setSearchTerm(String searchTerm);

    public String getUrl();

    public void setUrl(String url);

    public Integer getSearchPriority() ;
    
    public void setSearchPriority(Integer searchPriority);

    public Date getActiveStartDate() ;

    public void setActiveStartDate(Date activeStartDate);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectService.java" line="26">

---

## SearchRedirectService

The `SearchRedirectService` interface provides a method `findSearchRedirectBySearchTerm` that takes a search term as input and returns a `SearchRedirect` object if a matching one is found.

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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="105">

---

## Using SearchRedirectService

Here, `SearchRedirectService` is used in `BroadleafSearchController` to find a `SearchRedirect` for a given search term. If a matching `SearchRedirect` is found, the user is redirected to the URL specified in the `SearchRedirect` object.

```java
            // from the POST method, we can actually process the results
            SearchRedirect handler = searchRedirectService.findSearchRedirectBySearchTerm(query);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
