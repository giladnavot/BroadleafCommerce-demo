---
title: Understanding Ehcache in BroadleafCommerce-demo
---
Ehcache is a widely used, open-source Java distributed cache for general-purpose caching, Java EE and light-weight containers. It features memory and disk stores, replicate by copy and invalidate, listeners, a gzip caching servlet filter and much more. Ehcache is available under an Apache open-source license and is actively developed, maintained and supported.

In BroadleafCommerce-demo, Ehcache is used as a caching solution to improve the performance of the application by reducing the load on the database and network. It is used to cache frequently accessed data that does not change often, such as product details, categories, and static content.

The `org.broadleafcommerce.common.extensibility.cache.ehcache` package contains classes and interfaces related to the Ehcache implementation in the application. This includes utility classes for working with the cache, configuration builders for setting up the cache, and custom expiry policies for controlling how long data stays in the cache.

The `javax.cache.Cache` and `javax.cache.CacheManager` interfaces from the JCache API are used to interact with the cache. The `Cache` interface represents a cache, a map-like data structure that allows for the temporary storage of key-value pairs, and the `CacheManager` interface manages the lifecycle of caches.

The `ConditionalOnEhCache` annotation is used to conditionally enable or disable certain configurations or beans based on whether Ehcache is available and enabled in the application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="26">

---

# CacheManager and Cache

Here, the CacheManager and Cache classes from javax.cache are imported. CacheManager is a factory class for creating Cache instances. Cache is a map-like data structure for temporarily storing copies of data.

```java
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheConfigurationBuilder.java" line="21">

---

# Ehcache Configuration

Here, the CacheConfiguration class from org.ehcache.config is imported. This class is used to configure the settings for an Ehcache instance.

```java
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="37">

---

# DefaultEhCacheUtil Class

The DefaultEhCacheUtil class extends DefaultJCacheUtil and provides methods for creating and retrieving Cache instances. The `createCache` method is used to create a new Cache instance with the specified name, time-to-live (TTL) value, and maximum number of elements in memory.

```java
@Component("blJCacheUtil")
@ConditionalOnEhCache
public class DefaultEhCacheUtil extends DefaultJCacheUtil {
    
    public DefaultEhCacheUtil(String uri) {
        super(uri);
    }
    
    @Autowired
    public DefaultEhCacheUtil(CacheManager cacheManager) {
        super(cacheManager);
    }
    
    public DefaultEhCacheUtil(URI uri) {
        super(uri);
    }

    @Override
    public <K,V> Cache<K,V> getCache(String cacheName) {
        return this.getCacheManager().getCache(cacheName);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
