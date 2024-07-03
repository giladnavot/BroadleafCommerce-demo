---
title: Database Schema for Site Interactions
---
This document will cover the structure of the underlying database schema for `Site` interactions in the BroadleafCommerce-demo repository. We'll cover:

1. How the `Site` data is represented in the database
2. How the `Site` data is accessed and manipulated in the codebase
3. How the `Site` data is used in the application

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/domain/SiteImpl.java" line="55">

---

# `Site` Data Representation

The `SiteImpl` class is a JPA entity that represents the `Site` data in the database. It uses the `@Table` annotation to specify the table in the database where the `Site` data is stored.

```java
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/linkeddata/generator/HomepageLinkedDataGeneratorImpl.java" line="45">

---

# Accessing and Manipulating `Site` Data

The `HomepageLinkedDataGeneratorImpl` class is responsible for generating structured data for a homepage. It uses the `Site` data to generate this structured data. The `addOrganizationData` method, for example, creates a JSON object that represents the `Site` data.

```java
@Service(value = "blHomepageLinkedDataGenerator")
public class HomepageLinkedDataGeneratorImpl extends AbstractLinkedDataGenerator {

    @Override
    public boolean canHandle(final HttpServletRequest request) {
        return Objects.equals(request.getRequestURI(), "/");
    }

    @Override
    protected JSONArray getLinkedDataJsonInternal(final String url, final HttpServletRequest request,
                                                  final JSONArray schemaObjects) throws JSONException {
        schemaObjects.put(addWebSiteData(request));
        schemaObjects.put(addOrganizationData(request));

        extensionManager.getProxy().addHomepageData(request, schemaObjects);

        return schemaObjects;
    }

    /**
     * Generates an object representing the Schema.org organization
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/indexer/CatalogSolrIndexUpdateCommandHandlerImpl.java" line="631">

---

# Using `Site` Data in the Application

The `buildIncrementalIndex` method in the `CatalogSolrIndexUpdateCommandHandlerImpl` class uses the `Site` data to build an incremental index for the catalog. The `Site` data is used to determine the locales and fields for the index.

```java
    /**
     * Builds an incremental or batch portion of the index.  Activities include reading {@link Locale}s and {@link IndexField}s, building a page, 
     * adding the documents to the index, and incrementally committing.  It is not recommended that you override this method.  Rather, consider overriding 
     * one of the other methods as this is largely responsible for orchestrating and delegating.
     * @param productIds
     * @param products
     * @param holder
     * @param catalog
     * @param site
     * @throws Exception
     */
    protected void buildIncrementalIndex(
            final List<Long> productIds,
            final List<Product> products,
            final ReindexStateHolder holder, 
            final Catalog catalog, 
            final Site site) throws Exception {
        
        if (products != null && ! products.isEmpty()) {
            List<Locale> locales = getAllLocales();
            List<IndexField> fields = getIndexFields();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
