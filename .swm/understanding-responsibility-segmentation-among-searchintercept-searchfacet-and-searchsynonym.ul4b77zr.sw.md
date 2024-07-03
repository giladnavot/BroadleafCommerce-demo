---
title: >-
  Understanding Responsibility Segmentation Among SearchIntercept, SearchFacet,
  and SearchSynonym
---
This document will cover the roles and responsibilities of the `SearchIntercept`, `SearchFacet`, and `SearchSynonym` interfaces in the BroadleafCommerce-demo repository. We'll cover:

1. The purpose and usage of `SearchIntercept`
2. The purpose and usage of `SearchFacet`
3. The purpose and usage of `SearchSynonym`

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchIntercept.java" line="23">

---

# SearchIntercept

`SearchIntercept` is an interface that provides methods to get and set search terms and redirects. It's deprecated and its functionality has been replaced by `SearchRedirect`.

```java
 * @deprecated Replaced in functionality by {@link SearchRedirect}
 */
@Deprecated
public interface SearchIntercept {

    public abstract String getTerm();

    public abstract void setTerm(String term);

    public abstract String getRedirect();

    public abstract void setRedirect(String redirect);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/CategorySearchFacet.java" line="26">

---

# SearchFacet

`SearchFacet` is represented by the `CategorySearchFacet` interface. It provides methods to get and set the internal id, associated category, associated search facet, and the priority of the search facet in relation to other search facets in the category.

```java
/**
 * @author Andre Azzolini (apazzolini)
 */
public interface CategorySearchFacet extends Serializable, MultiTenantCloneable<CategorySearchFacet> {

    /**
     * Gets the internal id
     * 
     * @return the internal id
     */
    public Long getId();

    /** 
     * Sets the internal id
     * 
     * @param id
     */
    public void setId(Long id);

    /**
     * Gets the associated category
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchSynonym.java" line="20">

---

# SearchSynonym

`SearchSynonym` is an interface that provides methods to get and set search terms and their synonyms. It is used to manage synonyms for search terms in the search functionality of the application.

```java
public interface SearchSynonym {
    public String getTerm();
    public void setTerm(String term);
    public String[] getSynonyms();
    public void setSynonyms(String[] synonyms);
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
