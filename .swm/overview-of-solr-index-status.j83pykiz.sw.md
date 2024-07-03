---
title: Overview of Solr Index Status
---
Solr Index Status in BroadleafCommerce-demo refers to the status of the Solr index, which is crucial for search functionality in the application. The `SolrIndexStatusService` interface is responsible for managing this status, with methods for setting, adding, and retrieving the index status. It interacts with one or more `SolrIndexStatusProvider` instances, which are responsible for persisting the index status. The `SolrIndexStatusServiceImpl` class is the implementation of `SolrIndexStatusService`, and it uses a list of `SolrIndexStatusProvider` instances to read and update the index status. The `setIndexStatus` method is used to add an `IndexStatusInfo` entry into the status providers, while the `getIndexStatus` method returns a populated `IndexStatusInfo` instance from the provider(s).

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusService.java" line="29">

---

# SolrIndexStatusService Interface

This is the `SolrIndexStatusService` interface. It declares methods for managing the Solr index status. The `setIndexStatus` method is used to add an `IndexStatusInfo` entry into the status providers. The `getIndexStatus` method returns a populated `IndexStatusInfo` instance from the provider(s). The `addIndexStatus` method adds a new `IndexStatusInfo` given the eventId and the create date. The `addIndexErrorStatus` method adds an error into the index status.

```java
public interface SolrIndexStatusService {

    /**
     * Adds an IndexStatusInfo entry into the status providers
     * @param status
     */
    void setIndexStatus(IndexStatusInfo status);

    /**
     * Adds a new IndexStatusInfo given the eventId and the create date
     * @param status
     */
    void addIndexStatus(Long eventId, Date eventCreatedDate);

    /**
     * Returns a populated IndexStatusInfo instance from the provider(s)
     * @return the index status information 
     */
    IndexStatusInfo getIndexStatus();

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusServiceImpl.java" line="34">

---

# SolrIndexStatusServiceImpl Class

This is the `SolrIndexStatusServiceImpl` class, which implements the `SolrIndexStatusService` interface. It uses a list of `SolrIndexStatusProvider` instances to read and write the index status. The `setIndexStatus` method clears any existing error status and updates the index status. The `getIndexStatus` method reads the index status from all providers and returns it. The `addIndexStatus` method creates a new `IndexStatusInfo` instance and sets it as the current index status. The `addIndexErrorStatus` method handles index errors by adding a new error entry or updating an existing one.

```java
@Service("blSolrIndexStatusService")
public class SolrIndexStatusServiceImpl implements SolrIndexStatusService {

    @Resource(name="blSolrIndexStatusProviders")
    protected List<SolrIndexStatusProvider> providers;
    
    @Value("${solr.index.status.error.retry.count:3}")
    protected Integer solrIndexStatusErrorRetryCount;

    @Override
    public synchronized void setIndexStatus(IndexStatusInfo status) {
        clearErrorStatus(status);
        updateIndexStatus(status);
    }

    @Override
    public void addIndexStatus(Long eventId, Date eventCreatedDate) {
        IndexStatusInfo statusInfo = getSeedStatusInstance();
        statusInfo.setLastIndexDate(eventCreatedDate);
        statusInfo.getAdditionalInfo().put(String.format("SystemEventId%s", eventId), String.valueOf(eventId));
        setIndexStatus(statusInfo);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusProvider.java" line="25">

---

# SolrIndexStatusProvider Interface

This is the `SolrIndexStatusProvider` interface. It declares methods for reading and writing the index status to some persistent store. The `handleUpdateIndexStatus` method is used to handle the update of the index status. The `readIndexStatus` method is used to read the index status.

```java
public interface SolrIndexStatusProvider {

    void handleUpdateIndexStatus(IndexStatusInfo status);

    IndexStatusInfo readIndexStatus(IndexStatusInfo status);

}
```

---

</SwmSnippet>

# Solr Index Status Functions

This section will cover the main functions of Solr Index Status in BroadleafCommerce-demo.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusServiceImpl.java" line="44">

---

## setIndexStatus

The `setIndexStatus` function is used to update the index status. It first clears any existing error status and then updates the index status.

```java
    public synchronized void setIndexStatus(IndexStatusInfo status) {
        clearErrorStatus(status);
        updateIndexStatus(status);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusServiceImpl.java" line="57">

---

## getIndexStatus

The `getIndexStatus` function retrieves the current index status. It creates a new instance of the status and then reads the status from the provider.

```java
    @Override
    public synchronized IndexStatusInfo getIndexStatus() {
        IndexStatusInfo status = getSeedStatusInstance();
        for (SolrIndexStatusProvider provider : providers) {
            provider.readIndexStatus(status);
        }
        return status;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusServiceImpl.java" line="49">

---

## addIndexStatus

The `addIndexStatus` function adds a new index status entry. It creates a new status instance, sets the last index date, and adds additional information before setting the index status.

```java
    @Override
    public void addIndexStatus(Long eventId, Date eventCreatedDate) {
        IndexStatusInfo statusInfo = getSeedStatusInstance();
        statusInfo.setLastIndexDate(eventCreatedDate);
        statusInfo.getAdditionalInfo().put(String.format("SystemEventId%s", eventId), String.valueOf(eventId));
        setIndexStatus(statusInfo);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexStatusServiceImpl.java" line="74">

---

## addIndexErrorStatus

The `addIndexErrorStatus` function handles index errors. It retrieves the current index status and checks for errors. If an error exists, it increments the retry count or moves the event to the dead events list if the retry count is exceeded.

```java
    public synchronized void addIndexErrorStatus(Long eventId, Integer eventRetryCount, Date eventCreatedDate) {
        IndexStatusInfo status = getIndexStatus();
        if (status != null) {
            Integer retryCount = status.getIndexErrors().get(eventId);
            if (retryCount != null) {
                Integer allowedRetryAttempts = eventRetryCount != null && eventRetryCount > 0 ? eventRetryCount : solrIndexStatusErrorRetryCount;
                if (retryCount >= allowedRetryAttempts) {
                    status.getDeadIndexEvents().put(eventId, new Date());
                    status.setLastIndexDate(eventCreatedDate);
                    status.getAdditionalInfo().put(String.format("SystemEventId%s", eventId), String.valueOf(eventId));
                    status.getIndexErrors().remove(eventId);
                } else {
                    status.getIndexErrors().put(eventId, retryCount + 1);
                }
            } else {
                status.setLastIndexDate(eventCreatedDate);
                status.getIndexErrors().put(eventId, 0);//start with a retry count of 0
            }
        }
        updateIndexStatus(status);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
