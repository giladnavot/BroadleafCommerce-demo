---
title: Exploring Search Facet
---
A SearchFacet in BroadleafCommerce-demo refers to a specific facet that can be used to guide faceted searching on a results page. It is an interface that provides methods to get and set various properties such as the internal id, field type, field, facet field type, name, label, and whether it should be displayed on search result pages. It also provides methods to handle search facet ranges and required facets. This allows for more refined and specific searches, enhancing the user experience.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchFacet.java" line="25">

---

# SearchFacet Interface

This is the SearchFacet interface. It provides methods to get and set various properties of a search facet. These properties include the internal id, field type, field, facet field type, name, label, and others. The interface also extends Serializable and MultiTenantCloneable, indicating that the objects of classes implementing this interface can be serialized and cloned.

```java
/**
 * A SearchFacet is an object that represents a particular facet that can be used to guide faceted 
 * searching on a results page.
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface SearchFacet extends Serializable, MultiTenantCloneable<SearchFacet> {

    /**
     * Returns the internal id
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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrSearchServiceImpl.java" line="449">

---

# Usage of SearchFacet

Here is an example of how SearchFacet is used in the SolrSearchServiceImpl class. The getSearchFacets and getCategoryFacets methods use the SearchFacet interface to retrieve a list of search facets. The buildSearchFacetDTOs method uses the SearchFacet interface to build a list of SearchFacetDTO objects.

```java
    public List<SearchFacetDTO> getSearchFacets() {
        List<SearchFacet> searchFacets = new ArrayList<>();
        ExtensionResultStatusType status = extensionManager.getProxy().getSearchFacets(searchFacets);

        if (Objects.equals(ExtensionResultStatusType.NOT_HANDLED, status)) {
            return buildSearchFacetDTOs(searchFacetDao.readAllSearchFacets(FieldEntity.PRODUCT));
        }

        return buildSearchFacetDTOs(searchFacets);
    }

    @Override
    public List<SearchFacetDTO> getSearchFacets(Category category) {
        List<SearchFacetDTO> searchFacetDTOs = new ArrayList<>();

        if (category != null) {
            searchFacetDTOs.addAll(getCategoryFacets(category));
        }

        // if we aren't searching in a category, or globalFacetsForCategorySearch is true, include the global search facets
        if (globalFacetsForCategorySearch || category == null) {
```

---

</SwmSnippet>

# SearchFacet Interface

The SearchFacet interface provides methods to manage the properties of a search facet.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/SearchFacet.java" line="31">

---

## SearchFacet Interface

The SearchFacet interface provides methods to get and set various properties of a search facet. These properties include the internal id, field type, field, facet field type, name, label, whether to show on search, display priority on search result pages, whether multiple values can be selected for this facet, whether this facet uses facet ranges, the applicable ranges for this search facet, the list of facets this facet depends on, and whether all dependent facets must be active or only one is necessary.

```java
public interface SearchFacet extends Serializable, MultiTenantCloneable<SearchFacet> {

    /**
     * Returns the internal id
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
     * The main relationship to the rest of the search index entities
     * @see {@link #getField()}
     * @see {@link #getFacetFieldType()}
     */
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
