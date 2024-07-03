---
title: Understanding Solr Indexing
---
Solr Indexing in BroadleafCommerce-demo refers to the process of creating a Solr index based on catalog product data. This is facilitated by the `SolrIndexService` interface, which exposes several methods for index creation. The indexing process involves executing a full rebuild of the Solr index, which includes pre-build, build, and post-build stages. The `SolrIndexOperation` interface defines the lifecycle of an indexing operation, with each method in the interface being executed in order during different phases of the indexing process.

The `SolrIndexCachedOperation` class provides a single cache while exposing a block of code for execution. This is used to boost performance while executing multiple calls to the `buildIncrementalIndex` method of the `SolrIndexService`. The cache operation is executed within a `performCachedOperation` method, which sets the cache, executes the cache operation, and then clears the cache.

The `SolrIndexService` also provides a method `getReindexOperation` which creates the `SolrIndexOperation` for rebuilding the current index. This is the primary index operation used to rebuild the index.

The `SolrIndexService` interface is implemented by the `SolrIndexServiceImpl` class, which is annotated with `@Service("blSolrIndexService")`. This class uses the `SolrIndexDao` for interacting with the Solr index.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexOperation.java" line="30">

---

# SolrIndexOperation Interface

The SolrIndexOperation interface defines the lifecycle of an indexing operation. It includes methods for obtaining a lock, determining the Solr client and collection for indexing, counting and reading indexables, and building the index page. These methods are executed in order during different phases of the SolrIndexService's executeSolrIndexOperation method.

```java
/**
 *  Defines the lifecylce of an indexing operation used in {@link SolrIndexService}. Each of the methods in this interface
 *  are executed in order during different phases of {@link SolrIndexService#executeSolrIndexOperation(SolrIndexOperation)}.
 *
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface SolrIndexOperation {

    /**
     * Grab some sort of lock so that nothing else can index items at the same time
     */
    public boolean obtainLock();
    
    /**
     * Which {@link SolrClient} the index should be built on
     */
    public SolrClient getSolrServerForIndexing();

    /**
     * Which collection the index should be built on
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexService.java" line="32">

---

# SolrIndexService Interface

The SolrIndexService interface provides methods for creating a Solr index based on catalog product data. It includes methods for rebuilding the index, executing a SolrIndexOperation, and building an incremental index. The buildIndex method is where all the SolrIndexOperations need to be created, executed, and the documents built and added to the Solr index.

```java
/**
 * Service exposing several methods for creating a Solr index based on catalog product data.
 *
 * @see org.broadleafcommerce.core.search.service.solr.index.SolrIndexCachedOperation
 * @author Andre Azzolini (apazzolini)
 * @author Jeff Fischer
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface SolrIndexService {

    /**
     * <p>
     * Executes a full rebuild of the Solr index. This will rebuild the index on a separate core/collection and then swap
     * out the active core/collection with the new version of the index (essentially replacing all documents that are
     * currently in the index).
     * 
     * <p>
     * The order of methods that are apart of rebuilding the entire index:
     * <ol>
     *  <li><pre>{@link #preBuildIndex()}</pre></li>
     *  <li><pre>{@link #buildIndex()}</pre></li>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexCachedOperation.java" line="29">

---

# SolrIndexCachedOperation Class

The SolrIndexCachedOperation class provides a single cache while exposing a block of code for execution to the SolrIndexService's performCachedOperation method. This serves to boost performance while executing multiple calls to the SolrIndexService's buildIncrementalIndex method.

```java
/**
 * Provides a single cache while exposing a block of code for execution to
 * {@link org.broadleafcommerce.core.search.service.solr.index.SolrIndexService#performCachedOperation(org.broadleafcommerce.core.search.service.solr.SolrIndexCachedOperation.CacheOperation)}.
 * This serves to boost performance while executing multiple calls to {@link org.broadleafcommerce.core.search.service.solr.index.SolrIndexService#buildIncrementalIndex(int, int, boolean)}.
 *
 * @see org.broadleafcommerce.core.search.service.solr.index.SolrIndexService
 * @author Jeff Fischer
 */
public class SolrIndexCachedOperation {

    public static final Long DEFAULT_CATALOG_CACHE_KEY = 0l;
    
    private static final ThreadLocal<Map<Long, CatalogStructure>> CACHE = new ThreadLocal<Map<Long, CatalogStructure>>();

    /**
     * Retrieve the cache bound to the current thread.
     *
     * @return The cache for the current thread, or null if not set
     */
    public static CatalogStructure getCache() {
        BroadleafRequestContext ctx = BroadleafRequestContext.getBroadleafRequestContext();
```

---

</SwmSnippet>

# Solr Indexing Functionality

This section provides an overview of the Solr indexing functionality in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexOperation.java" line="30">

---

## SolrIndexOperation Interface

The SolrIndexOperation interface defines the lifecycle of an indexing operation. It includes methods for obtaining a lock, specifying the Solr server and collection for indexing, counting and reading indexable items, and performing setup and cleanup tasks before and after counting and reading indexables.

```java
/**
 *  Defines the lifecylce of an indexing operation used in {@link SolrIndexService}. Each of the methods in this interface
 *  are executed in order during different phases of {@link SolrIndexService#executeSolrIndexOperation(SolrIndexOperation)}.
 *
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface SolrIndexOperation {

    /**
     * Grab some sort of lock so that nothing else can index items at the same time
     */
    public boolean obtainLock();
    
    /**
     * Which {@link SolrClient} the index should be built on
     */
    public SolrClient getSolrServerForIndexing();

    /**
     * Which collection the index should be built on
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexService.java" line="32">

---

## SolrIndexService Interface

The SolrIndexService interface provides methods for managing the Solr index. It includes methods for rebuilding the index, building the index incrementally, optimizing the index, and performing a cached operation. It also provides methods for obtaining the SolrIndexOperation for rebuilding the index and determining whether to use the legacy indexer.

```java
/**
 * Service exposing several methods for creating a Solr index based on catalog product data.
 *
 * @see org.broadleafcommerce.core.search.service.solr.index.SolrIndexCachedOperation
 * @author Andre Azzolini (apazzolini)
 * @author Jeff Fischer
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface SolrIndexService {

    /**
     * <p>
     * Executes a full rebuild of the Solr index. This will rebuild the index on a separate core/collection and then swap
     * out the active core/collection with the new version of the index (essentially replacing all documents that are
     * currently in the index).
     * 
     * <p>
     * The order of methods that are apart of rebuilding the entire index:
     * <ol>
     *  <li><pre>{@link #preBuildIndex()}</pre></li>
     *  <li><pre>{@link #buildIndex()}</pre></li>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
