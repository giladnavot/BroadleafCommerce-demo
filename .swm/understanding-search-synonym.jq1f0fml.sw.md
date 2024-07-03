---
title: Understanding Search Synonym
---
Search Synonym is an interface in the Broadleaf Commerce framework that is used to manage synonyms for search terms. It provides methods to get and set the term, and to get and set an array of synonyms for the term. This allows the search functionality to return results that not only match the exact search term, but also its synonyms, enhancing the user's search experience.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchSynonym.java" line="20">

---

# SearchSynonym Interface

This is the SearchSynonym interface. It declares the methods that need to be implemented to manage synonyms for search terms.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/dao/SearchSynonymDao.java" line="24">

---

# SearchSynonymDao Interface

This is the SearchSynonymDao interface. It declares the methods that need to be implemented to interact with the data source for the SearchSynonym objects.

```java
public interface SearchSynonymDao {
    public List<SearchSynonym> getAllSynonyms();
    public void createSynonym(SearchSynonym synonym);
    public void updateSynonym(SearchSynonym synonym);
    public void deleteSynonym(SearchSynonym synonym);
}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/dao/SearchSynonymDaoImpl.java" line="35">

---

# SearchSynonymDaoImpl Class

This is the SearchSynonymDaoImpl class. It implements the SearchSynonymDao interface and defines how to interact with the data source for the SearchSynonym objects.

```java
    @SuppressWarnings("unchecked")
    public List<SearchSynonym> getAllSynonyms() {
        Query query = em.createNamedQuery("BC_READ_SEARCH_SYNONYMS");
        List<SearchSynonym> result;
        try {
            result = (List<SearchSynonym>) query.getResultList();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    public void createSynonym(SearchSynonym synonym) {
        em.persist(synonym);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchSynonymImpl.java" line="36">

---

# SearchSynonymImpl Class

This is the SearchSynonymImpl class. It implements the SearchSynonym interface and defines how to manage synonyms for search terms.

```java
public class SearchSynonymImpl implements SearchSynonym {
```

---

</SwmSnippet>

# Search Synonym Functions

The SearchSynonym interface provides methods to manage search synonyms.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchSynonym.java" line="21">

---

## getTerm and setTerm

The `getTerm` and `setTerm` methods are used to get and set the search term respectively. The term is the word for which synonyms are defined.

```java
    public String getTerm();
    public void setTerm(String term);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchSynonym.java" line="23">

---

## getSynonyms and setSynonyms

The `getSynonyms` and `setSynonyms` methods are used to get and set the synonyms for the search term respectively. The synonyms are the words that are considered equivalent to the term for search purposes.

```java
    public String[] getSynonyms();
    public void setSynonyms(String[] synonyms);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
