---
title: Overview of EhCache Util
---
EhCache Util in BroadleafCommerce-demo refers to a utility class, specifically `DefaultEhCacheUtil`, that provides an encapsulated way to create caches programmatically from an EhCache `CacheManager`. This is because the standard APIs do not provide enough control. The `DefaultEhCacheUtil` class extends `DefaultJCacheUtil` and is annotated with `@Component("blJCacheUtil")` and `@ConditionalOnEhCache`, indicating that it is a Spring component and its instantiation is conditional on EhCache being available. It provides methods to get a cache by its name and to create a cache with specific parameters such as time-to-live seconds and maximum elements in memory.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="39">

---

# DefaultEhCacheUtil Class

This is the DefaultEhCacheUtil class. It extends DefaultJCacheUtil and provides methods to create and manage caches. It is annotated with @Component and @ConditionalOnEhCache, which means it is a Spring component and it is only active when EhCache is available.

```java
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

    @Override
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="54">

---

# getCache Method

The getCache method is used to get a cache by its name. It calls the getCache method of the CacheManager.

```java
    @Override
    public <K,V> Cache<K,V> getCache(String cacheName) {
        return this.getCacheManager().getCache(cacheName);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="59">

---

# createCache Method

The createCache method is used to create a cache with specific parameters. It builds a configuration with the provided parameters, creates a cache with the configuration, enables management and statistics for the cache, and then returns the cache.

```java
    @Override
    public synchronized Cache<Object, Object> createCache(String cacheName, int ttlSeconds, int maxElementsInMemory) {
        return this.createCache(cacheName, ttlSeconds, maxElementsInMemory, Object.class, Object.class);
    }

    @Override
    public synchronized <K, V> Cache<K, V> createCache(String cacheName, int ttlSeconds, int maxElementsInMemory, Class<K> key, Class<V> value) {
        Configuration<K, V> configuration = builder.buildConfiguration(ttlSeconds, maxElementsInMemory, key, value);
        Cache<K, V> cache = getCacheManager().createCache(cacheName, configuration);
        enableManagement(cache);
        enableStatistics(cache);
        return cache;
    }
```

---

</SwmSnippet>

# EhCache Util Functions

This section will cover the main functions of the EhCache Util class.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="54">

---

## getCache Function

The `getCache` function is used to retrieve a cache by its name. It calls the `getCacheManager` method to get the CacheManager and then retrieves the cache using the provided cache name.

```java
    @Override
    public <K,V> Cache<K,V> getCache(String cacheName) {
        return this.getCacheManager().getCache(cacheName);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUtil.java" line="59">

---

## createCache Function

The `createCache` function is used to create a new cache with the provided name, time-to-live (TTL) in seconds, and maximum elements in memory. It builds a configuration using these parameters and the provided key and value classes, then creates the cache using the CacheManager. It also enables management and statistics for the cache.

```java
    @Override
    public synchronized Cache<Object, Object> createCache(String cacheName, int ttlSeconds, int maxElementsInMemory) {
        return this.createCache(cacheName, ttlSeconds, maxElementsInMemory, Object.class, Object.class);
    }

    @Override
    public synchronized <K, V> Cache<K, V> createCache(String cacheName, int ttlSeconds, int maxElementsInMemory, Class<K> key, Class<V> value) {
        Configuration<K, V> configuration = builder.buildConfiguration(ttlSeconds, maxElementsInMemory, key, value);
        Cache<K, V> cache = getCacheManager().createCache(cacheName, configuration);
        enableManagement(cache);
        enableStatistics(cache);
        return cache;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
