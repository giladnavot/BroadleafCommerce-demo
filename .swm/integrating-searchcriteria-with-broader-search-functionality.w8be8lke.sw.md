---
title: Integrating SearchCriteria With Broader Search Functionality
---
This document will cover the integration of SearchCriteria with the broader search functionality of Broadleaf Commerce. We'll cover:

1. The role of SearchCriteria in the search functionality
2. How SearchCriteria interacts with other components
3. The use of SearchCriteria in different classes

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="26">

---

# Role of SearchCriteria

`SearchCriteria` is imported in `BroadleafSearchController`, indicating its role in handling search requests.

```java
import org.broadleafcommerce.core.search.domain.SearchCriteria;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafSearchController.java" line="30">

---

# Interaction with other components

`SearchCriteria` interacts with `SearchService` and `SearchFacetDTOService` to provide search functionality.

```java
import org.broadleafcommerce.core.search.service.SearchService;
import org.broadleafcommerce.core.web.service.SearchFacetDTOService;
import org.broadleafcommerce.core.web.util.ProcessorUtils;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/SearchService.java" line="22">

---

# Use of SearchCriteria in different classes

`SearchCriteria` is used in `SearchService` to define the criteria for search operations.

```java
import org.broadleafcommerce.core.search.domain.SearchCriteria;
import org.broadleafcommerce.core.search.domain.SearchFacetDTO;
import org.broadleafcommerce.core.search.domain.SearchResult;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/controller/catalog/BroadleafCategoryController.java" line="31">

---

`SearchCriteria` is also used in `BroadleafCategoryController` to handle category-based search operations.

```java
import org.broadleafcommerce.core.search.domain.SearchCriteria;
import org.broadleafcommerce.core.search.domain.SearchResult;
import org.broadleafcommerce.core.search.service.SearchService;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
