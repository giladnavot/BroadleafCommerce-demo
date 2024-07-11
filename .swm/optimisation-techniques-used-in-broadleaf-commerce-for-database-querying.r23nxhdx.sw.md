---
title: Optimisation techniques used in Broadleaf Commerce for database querying
---
This document will cover the optimization techniques used for database querying in the BroadleafCommerce-demo repository, with a focus on caching. We'll cover:

1. The use of the SparselyPopulatedQueryExtensionHandler interface
2. The role of the refineParameterRetrieve method
3. The use of the OverridePreCacheInitializer interface
4. The role of the initializeOverride method
5. The use of the BroadleafCachingResourceResolver class
6. The role of the ModuleConfigurationDao interface
7. The use of the SystemPropertyImpl class
8. The role of the StreamingTransactionCapable interface.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/SparselyPopulatedQueryExtensionHandler.java" line="29">

---

# SparselyPopulatedQueryExtensionHandler Interface

The SparselyPopulatedQueryExtensionHandler interface is used to contribute to a query, primarily for handling querying for sparsely populated caches in multitenant scenarios. It helps maintain a single cache of common data, with sparse caches for site-specific overrides, optimizing memory usage.

```java
/**
 * Extension handler (generally for DAO usage) that allows contribution to a query (presumably from another module). The primary
 * use case is to handle querying for sparsely populated caches in multitenant scenarios. Take for example, a price list scenario
 * where there may be thousands of standard sites all inheriting from a template catalog containing some basic pricelist information.
 * Rather than having a cache of these same basic price lists for every site, it is advantageous to have a single cache of the common
 * pricelists. Then, if one or more standard sites override one of these pricelists, then a sparse cache is maintained per site
 * with that site's overrides only.
 * </p>
 * This functionality is achieved by running several versions of a query based on the desired
 * {@link org.broadleafcommerce.common.extension.ResultType}. Requesting results for a ResultType of STANDARD drives results filtered
 * specifically to a standard site, which is multitenant module concept. A ResultType of TEMPLATE drives results specifically
 * for template site catalog or profile (also a multitenant concept). In the absence of the multitenant module, ExtensionManager
 * instance of this type should have no effect.
 *
 * @see org.broadleafcommerce.common.extension.ResultType
 * @author Jeff Fischer
 */
public interface SparselyPopulatedQueryExtensionHandler extends ExtensionHandler {

    /**
     * Add additional restrictions to the fetch query
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/SparselyPopulatedQueryExtensionHandler.java" line="61">

---

# refineParameterRetrieve Method

The refineParameterRetrieve method is used to add additional restrictions to the fetch query. It uses parameters, rather than embedding values directly in the query, which is more efficient from a Hibernate statement cache and database prepared statement cache perspective.

```java
    /**
     * Add additional restrictions to the fetch query. Uses parameters, rather than embedding values directly in the query. This is more
     * efficient from a Hibernate statement cache and database prepared statement cache perspective. Use in conjunction with
     * {@link #refineQuery(Class, ResultType, TypedQuery)} to pass the actual parameter values before retrieving the query
     * results.
     *
     * @param type the class type for the query
     * @param resultType pass a ResultType of IGNORE to explicitly ignore refineRetrieve, even if the multitenant module is loaded
     * @param builder
     * @param criteria
     * @param root
     * @param restrictions any additional JPA criteria restrictions should be added here
     * @return the status of the extension operation
     */
    ExtensionResultStatusType refineParameterRetrieve(Class<?> type, ResultType resultType, CriteriaBuilder builder, CriteriaQuery criteria, Root root, List<Predicate> restrictions);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/cache/OverridePreCacheInitializer.java" line="22">

---

# OverridePreCacheInitializer Interface

The OverridePreCacheInitializer interface is used to perform cache item initialization for a specific entity type. This helps in optimizing the cache usage.

```java
/**
 * Performs cache item initialization for a specific entity type.
 *
 * @author Jeff Fischer
 */
public interface OverridePreCacheInitializer {

    /**
     * Whether or not this initializer is qualified to work on the given entity type
     *
     * @param type
     * @return
     */
    boolean isOverrideQualified(Class<?> type);

    /**
     * Perform any initialization tasks (e.g. exercising a lazy collection) and returns
     * a StandardCacheItem instance.
     *
     * @param entity
     * @return
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/cache/OverridePreCacheInitializer.java" line="37">

---

# initializeOverride Method

The initializeOverride method is used to perform any initialization tasks and returns a StandardCacheItem instance. This helps in preparing the cache item for use.

```java
    /**
     * Perform any initialization tasks (e.g. exercising a lazy collection) and returns
     * a StandardCacheItem instance.
     *
     * @param entity
     * @return
     */
    StandardCacheItem initializeOverride(Object entity);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafCachingResourceResolver.java" line="56">

---

# BroadleafCachingResourceResolver Class

The BroadleafCachingResourceResolver class uses caching to optimize resource resolution. It uses a cache key prefix to identify cached resources.

```java
    public static final String RESOLVED_RESOURCE_CACHE_KEY_PREFIX = "resolvedResource:";
    public static final String RESOLVED_URL_PATH_CACHE_KEY_PREFIX = "resolvedUrlPath:";
    public static final String RESOLVED_RESOURCE_CACHE_KEY_PREFIX_NULL = "resolvedResourceNull:";
    public static final String RESOLVED_URL_PATH_CACHE_KEY_PREFIX_NULL = "resolvedUrlPathNull:";
    private static final Object NULL_REFERENCE = new Object();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/config/dao/ModuleConfigurationDao.java" line="25">

---

# ModuleConfigurationDao Interface

The ModuleConfigurationDao interface provides methods to read, save, and delete module configurations. It also provides a method to set the number of milliseconds that the current date/time will be cached for queries before refreshing, which aids in query caching.

```java
public interface ModuleConfigurationDao {

    public ModuleConfiguration readById(Long id);

    public ModuleConfiguration save(ModuleConfiguration config);

    public void delete(ModuleConfiguration config);

    public List<ModuleConfiguration> readAllByType(ModuleConfigurationType type);

    public List<ModuleConfiguration> readActiveByType(ModuleConfigurationType type);

    public List<ModuleConfiguration> readByType(Class<? extends ModuleConfiguration> type);

    /**
     * Returns the number of milliseconds that the current date/time will be cached for queries before refreshing.
     * This aids in query caching, otherwise every query that utilized current date would be different and caching
     * would be ineffective.
     *
     * @return the milliseconds to cache the current date/time
     */
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/config/domain/SystemPropertyImpl.java" line="53">

---

# SystemPropertyImpl Class

The SystemPropertyImpl class allows the storage and retrieval of System Properties in the database. It uses caching to optimize the retrieval of these properties.

```java
@Entity
@Table(name="BLC_SYSTEM_PROPERTY", indexes = { 
        @Index(name = "IDX_BLSYPR_PROPERTY_NAME", columnList = "PROPERTY_NAME")
    })
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "blSystemProperties")
@DirectCopyTransform({
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.MULTITENANT_SITE),
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.SANDBOX)
})
public class SystemPropertyImpl implements SystemProperty, AdminMainEntity, SystemPropertyAdminPresentation {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SystemPropertyId")
    @GenericGenerator(
        name="SystemPropertyId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="SystemPropertyImpl"),
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/util/StreamingTransactionCapable.java" line="22">

---

# StreamingTransactionCapable Interface

The StreamingTransactionCapable interface is used for running transactional operations. It provides methods to run a streaming operation inside a transaction and an operation inside of a single transaction. This helps in optimizing transaction times and conserving heap.

```java
/**
 * Utility used for running transactional operations. Streaming operations are interesting because you may want to iterate
 * over a large set of data and perform some work in a transaction. In order to limit transaction times and conserve heap,
 * iterating over the large set should be done in chunks and the level 1 cache should be clear between each chunk. This
 * utility abstracts the creation of chunks and small transactions and level 1 cache clearing so that the caller only need
 * worry about coding the work to be done.
 *
 * @author Jeff Fischer
 */
public interface StreamingTransactionCapable {

    /**
     * The result set size per page of data when streaming. See {@link #runStreamingTransactionalOperation(StreamCapableTransactionalOperation, Class)}.
     * @return
     */
    int getPageSize();

    void setPageSize(int pageSize);

    int getRetryMax();

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
