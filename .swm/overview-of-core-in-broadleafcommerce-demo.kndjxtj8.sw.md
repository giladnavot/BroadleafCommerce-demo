---
title: Overview of Core in BroadleafCommerce-demo
---
In the BroadleafCommerce-demo repository, 'Core' refers to the central part of the application where the main business logic resides. It contains key functionalities such as search services, configuration settings, and data models. For instance, the <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="52:4:4" line-data="public class SolrConfiguration implements InitializingBean {">`SolrConfiguration`</SwmToken> class in the 'core' directory is responsible for holding the Solr server configurations.&nbsp;

Another important component in the 'core' is the <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrHelperService.java" pos="48:4:4" line-data="public interface SolrHelperService {">`SolrHelperService`</SwmToken> interface which provides methods for managing Solr cores and converting index fields. The 'core' is organized into different modules like 'broadleaf-framework', 'broadleaf-profile', 'broadleaf-framework-web', and 'broadleaf-profile-web', each serving a specific purpose in the application.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" line="43">

---

## <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="52:4:4" line-data="public class SolrConfiguration implements InitializingBean {">`SolrConfiguration`</SwmToken> Class

The <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="52:4:4" line-data="public class SolrConfiguration implements InitializingBean {">`SolrConfiguration`</SwmToken> class is a part of the 'Core'. It statically holds the Solr server and is initialized in <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="48:15:15" line-data=" * This is initialized in {@link SolrSearchServiceImpl} and used in {@link SolrIndexServiceImpl}">`SolrSearchServiceImpl`</SwmToken> and used in <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="48:28:28" line-data=" * This is initialized in {@link SolrSearchServiceImpl} and used in {@link SolrIndexServiceImpl}">`SolrIndexServiceImpl`</SwmToken>. It contains various configurations related to Solr such as primary name, reindex name, namespace, Solr client servers, Solr cloud configurations, and Solr home path.

```java
/**
 * <p>
 * Provides a class that will statically hold the Solr server.
 * 
 * <p>
 * This is initialized in {@link SolrSearchServiceImpl} and used in {@link SolrIndexServiceImpl}
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class SolrConfiguration implements InitializingBean {
    private static final Log LOG = LogFactory.getLog(SolrConfiguration.class);

    protected String primaryName = null;
    protected String reindexName = null;

    // this is a field to differentiate between collections of items and it must be non-blank
    protected String namespace = "d";

    protected SolrClient adminServer = null;
    protected SolrClient primaryServer = null;
    protected SolrClient reindexServer = null;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" line="236">

---

## <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="252:5:5" line-data="    public SolrClient getAdminServer() {">`getAdminServer`</SwmToken> Method

The <SwmToken path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" pos="252:5:5" line-data="    public SolrClient getAdminServer() {">`getAdminServer`</SwmToken> method is a part of the 'Core'. It returns the admin server if configured, otherwise, it returns the primary server. This method is used to connect to Solr and is used for swapping cores in newer versions of Solr.

```java
    /**
     * The adminServer is just a reference to a SolrClient component for connecting to Solr.  In newer
     * versions of Solr, 4.4 and beyond, auto discovery of cores is  
     * provided.  When using a stand-alone server or server cluster, 
     * the admin server, for swapping cores, is a different URL. For example, 
     * one needs to use http://solrserver:8983/solr, which formerly acted as the admin server 
     * AND as the primary core.  As of Solr 4.4, one needs to specify the cores separately: 
     * http://solrserver:8983/solr/primary and http://solrserver:8983/solr/reindex, 
     * and use http://solrserver:8983/solr for swapping cores.
     * 
     * By default, this method attempts to return an admin server if configured. Otherwise, 
     * it returns the primary server if the admin server is null, which is backwards compatible 
     * with the way that BLC worked prior to this change.
     * 
     * @return
     */
    public SolrClient getAdminServer() {
        if (adminServer != null) {
            return adminServer;
        }
        //If the admin server hasn't been set, return the primary server.
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
