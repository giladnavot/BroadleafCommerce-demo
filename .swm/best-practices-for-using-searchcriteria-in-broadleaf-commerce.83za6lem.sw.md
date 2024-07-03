---
title: Best Practices for Using SearchCriteria in Broadleaf Commerce
---
This document will cover the best practices around using SearchCriteria in Broadleaf Commerce, which includes:

1. Understanding the role of SearchCriteria
2. How to use SearchCriteria in Broadleaf Commerce
3. Best practices when using SearchCriteria.

# Understanding the role of SearchCriteria

SearchCriteria in Broadleaf Commerce is a key component in the search functionality of the framework. It is used to encapsulate the criteria for a search, such as the search terms, filters, and sorting options. This allows for a flexible and powerful search functionality that can be easily customized to meet specific requirements.

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="26">

---

# How to use SearchCriteria in Broadleaf Commerce

Here, SearchCriteria is imported and used in the BroadleafSearchController. It is used to capture the search terms and filters from the user's request and pass them to the search service.

```java
import org.broadleafcommerce.core.search.domain.SearchCriteria;
import org.broadleafcommerce.core.search.domain.SearchResult;
import org.broadleafcommerce.core.search.redirect.domain.SearchRedirect;
import org.broadleafcommerce.core.search.redirect.service.SearchRedirectService;
import org.broadleafcommerce.core.search.service.SearchService;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/SearchService.java" line="22">

---

In the SearchService, SearchCriteria is used to perform the actual search against the product catalog. The results are then encapsulated in a SearchResult object.

```java
import org.broadleafcommerce.core.search.domain.SearchCriteria;
import org.broadleafcommerce.core.search.domain.SearchFacetDTO;
import org.broadleafcommerce.core.search.domain.SearchResult;
```

---

</SwmSnippet>

# Best practices when using SearchCriteria

When using SearchCriteria, it's important to ensure that the criteria accurately capture the user's intent. This includes properly handling any filters and sorting options. Additionally, it's recommended to validate the SearchCriteria to prevent any potential issues such as SQL injection attacks.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
