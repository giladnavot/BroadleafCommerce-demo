---
title: Understanding Search Intercept
---
SearchIntercept is a deprecated interface in the Broadleaf Commerce framework. It was used to intercept search terms and potentially redirect the user to a different page. The interface defines methods to get and set the search term (`getTerm` and `setTerm`) and to get and set the redirect URL (`getRedirect` and `setRedirect`). However, its functionality has been replaced by the `SearchRedirect` interface.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchIntercept.java" line="26">

---

# SearchIntercept Interface

This is the SearchIntercept interface. It declares four methods: `getTerm`, `setTerm`, `getRedirect`, and `setRedirect`. These methods were used to handle the search term and the potential redirect URL.

```java
public interface SearchIntercept {

    public abstract String getTerm();

    public abstract void setTerm(String term);

    public abstract String getRedirect();

    public abstract void setRedirect(String redirect);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/dao/SearchInterceptDaoImpl.java" line="41">

---

# Usage of SearchIntercept

Here is an example of how SearchIntercept was used. The `findInterceptByTerm` method in the `SearchInterceptDaoImpl` class used the search term to find a corresponding SearchIntercept object. The `findAllIntercepts` method returned all SearchIntercept objects.

```java
    public SearchIntercept findInterceptByTerm(String term) {
        Query query = em.createNamedQuery("BC_READ_SEARCH_INTERCEPT_BY_TERM");
        query.setParameter("searchTerm", term);
        SearchIntercept result;
        try {
            result = (SearchIntercept) query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchIntercept> findAllIntercepts() {
        Query query = em.createNamedQuery("BC_READ_ALL_SEARCH_INTERCEPTS");
```

---

</SwmSnippet>

# SearchIntercept Interface Functions

The SearchIntercept interface provides four main methods: getTerm, setTerm, getRedirect, and setRedirect.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchIntercept.java" line="28">

---

## getTerm and setTerm

The `getTerm` and `setTerm` methods are used to get and set the search term respectively. The search term is a string that represents the keyword or phrase that the user is searching for.

```java
    public abstract String getTerm();

    public abstract void setTerm(String term);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchIntercept.java" line="32">

---

## getRedirect and setRedirect

The `getRedirect` and `setRedirect` methods are used to get and set the redirect URL respectively. The redirect URL is a string that represents the URL to which the user should be redirected when they search for the specified term.

```java
    public abstract String getRedirect();

    public abstract void setRedirect(String redirect);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
