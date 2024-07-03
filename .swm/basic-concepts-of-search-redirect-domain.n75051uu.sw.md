---
title: Basic Concepts of Search Redirect Domain
---
Search Redirect Domain in BroadleafCommerce-demo refers to a functionality that allows the application to redirect search queries to specific URLs. This is implemented through the `SearchRedirect` interface and its implementation `SearchRedirectImpl`. The `SearchRedirect` interface defines methods for getting and setting properties such as the search term, URL, search priority, and active start and end dates. The `SearchRedirectImpl` class implements these methods and provides additional functionality.

The `SearchRedirect` is used in various parts of the application. For instance, the `SearchRedirectService` uses it to find a matching redirect for a given search term. The `SearchRedirectDao` uses it to interact with the database and retrieve `SearchRedirect` instances based on search terms. It's also used in the `BroadleafSearchController` to find a matching redirect for a given search query.

The `SearchRedirect` is also referenced in the application's SQL scripts to define permissions for creating, updating, deleting, and reading search redirects. This shows that the `SearchRedirect` is a crucial part of the application's security and access control mechanisms.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirect.java" line="23">

---

# SearchRedirect Interface

The `SearchRedirect` interface defines the methods that a search redirect object should implement. These include getters and setters for the ID, search term, URL, search priority, active start and end dates, and a method to check if the redirect is active.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirectImpl.java" line="62">

---

# SearchRedirect Implementation

The `SearchRedirectImpl` class is the implementation of the `SearchRedirect` interface. It includes the implementation of the methods defined in the interface and additional fields and methods for logging and serialization.

```java
public class SearchRedirectImpl implements SearchRedirect, java.io.Serializable, AdminMainEntity, SearchRedirectAdminPresentation {
    
    private static final long serialVersionUID = 1L;
    
    @Transient
    private static final Log LOG = LogFactory.getLog(SearchRedirectImpl.class);
    
    @Id
    @GeneratedValue(generator = "SearchRedirectID")
    @GenericGenerator(
        name="SearchRedirectID",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="SearchRedirectImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.core.search.redirect.domain.SearchRedirectImpl")
        }
    )
    @Column(name = "SEARCH_REDIRECT_ID")
    @AdminPresentation(visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long id;
    
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/service/SearchRedirectServiceImpl.java" line="44">

---

# Using SearchRedirect in Services

The `SearchRedirectService` uses the `SearchRedirect` object to find a matching search term and return the corresponding redirect. If a matching search term is found, the user is redirected to the specified URL.

```java
    @Override
    public SearchRedirect findSearchRedirectBySearchTerm(String uri) {

        SearchRedirect SearchRedirect = SearchRedirectDao
                .findSearchRedirectBySearchTerm(uri);
        if (SearchRedirect != null) {
            return SearchRedirect;
```

---

</SwmSnippet>

# Search Redirect Domain Functions

Let's delve into the main functions of the Search Redirect Domain.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirectImpl.java" line="149">

---

## getSearchTerm

The `getSearchTerm` function is used to retrieve the search term that the user has entered. This search term is then used to determine the appropriate redirect URL.

```java
    @Override
    public String getSearchTerm() {
        return searchTerm;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirectImpl.java" line="159">

---

## getUrl

The `getUrl` function is used to retrieve the URL to which the user should be redirected. This URL is determined based on the search term.

```java
    @Override
    public String getUrl() {
        return url;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/redirect/domain/SearchRedirectImpl.java" line="179">

---

## isActive

The `isActive` function checks if the redirect is currently active. This is determined based on the active start and end dates of the redirect. If the current date is within this range, the redirect is considered active.

```java
    @Override
    public boolean isActive() {
        Long date = SystemTime.asMillis(true);
        boolean isNullActiveStartDateActive = BLCSystemProperty.resolveBooleanSystemProperty("searchRedirect.is.null.activeStartDate.active");

        boolean isActive;
        if (isNullActiveStartDateActive) {
            isActive = (getActiveStartDate() == null || getActiveStartDate().getTime() <= date) && (getActiveEndDate() == null || getActiveEndDate().getTime() > date);
        } else {
            isActive = (getActiveStartDate() != null && getActiveStartDate().getTime() <= date) && (getActiveEndDate() == null || getActiveEndDate().getTime() > date);
        }

        if (LOG.isDebugEnabled() && !isActive) {
            LOG.debug("product, " + id + ", inactive due to date");
        }
        return isActive;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
