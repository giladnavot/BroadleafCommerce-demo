---
title: Understanding Search Indexing in BroadleafCommerce-demo
---
Search Indexing in BroadleafCommerce-demo refers to the process of organizing data to optimize search performance. It is implemented using the SolrIndexOperation interface, which defines the lifecycle of an indexing operation. This interface includes methods for obtaining a lock, determining the SolrClient and collection for indexing, counting indexable items, and building the index page. The IndexStatusInfo interface provides information about the current status of the Solr instance's index, including the last index date and any errors. The process of indexing involves reading data, building an index, and then writing this index to a Solr server.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexOperation.java" line="30">

---

# SolrIndexOperation Interface

The `SolrIndexOperation` interface defines the lifecycle of an indexing operation. It includes methods for obtaining a lock, determining the Solr client and collection for indexing, counting and reading indexables, and building the index page.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexService.java" line="71">

---

# SolrIndexService Class

The `SolrIndexService` class uses the `SolrIndexOperation` interface to execute indexing operations. It creates a `SolrIndexOperation` for rebuilding the current index and executes it.

```java
     * <p>
     * Handles all the document building for the current index rebuild. This is where all of the SolrIndexOperation's need to be
     * created, executed and the documents built and added to the Solr index
     * 
     * <p>
     * This is the method that should be overridden to specify which operations should be run to build the correct index.
     *
     * @throws IOException
     * @throws ServiceException
     * @see {@link #rebuildIndex()}
     * @see {@link #preBuildIndex()}
     */
    public void buildIndex() throws IOException, ServiceException;

    /**
     * Executed after we do any indexing when rebuilding the current index. Usually this handles optimizing the index and swapping the cores.
     *
     * @throws IOException
     * @throws ServiceException
     */
    public void postBuildIndex() throws IOException, ServiceException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexServiceImpl.java" line="213">

---

# SolrIndexServiceImpl Class

The `SolrIndexServiceImpl` class provides the implementation for the `SolrIndexService`. It defines the `getReindexOperation` method to create a new `SolrIndexOperation` and the `executeSolrIndexOperation` method to execute the indexing operation.

```java
    @Override
    public SolrIndexOperation getReindexOperation() {
        return new GlobalSolrFullReIndexOperation(this, solrConfiguration, shs, errorOnConcurrentReIndex) {

            @Override
            public List<? extends Indexable> readIndexables(int pageSize, Long lastId) {
                return readAllActiveIndexables(pageSize, lastId);
            }

            @Override
            public Long countIndexables() {
                return countIndexableItems();
            }

            @Override
            public void buildPage(List<? extends Indexable> indexables) throws ServiceException {
                buildIncrementalIndex(getSolrCollectionForIndexing(), indexables, getSolrServerForIndexing());
            }
        };
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/IndexStatusInfo.java" line="23">

---

# IndexStatusInfo Interface

The `IndexStatusInfo` interface provides information about the current status of the Solr index. It includes methods for getting the last index date, additional info about the index, index errors, and dead index events.

```java
/**
 * General information about the current status of a (embedded) Solr instance's index
 *
 * @author Jeff Fischer
 */
public interface IndexStatusInfo {

    /**
     * The most recent index date
     *
     * @return
     */
    Date getLastIndexDate();

    void setLastIndexDate(Date lastIndexDate);

    /**
     * Arbitrary information about the index.
     *
     * @return
     */
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
