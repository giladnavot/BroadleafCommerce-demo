---
title: Understanding Cache Manager
---
The Cache Manager in BroadleafCommerce-demo refers to a component that manages the caching operations. It is implemented in the `NoOpCacheManager` class, which is a part of the `org.broadleafcommerce.common.extensibility.cache.ehcache` package. This class implements the `CacheManager` interface from the `javax.cache` package. The Cache Manager is responsible for creating, retrieving, and managing cache instances. It provides methods to get and create cache instances, get cache names, and manage cache statistics. The `NoOpCacheManager` class in this repository is a no-operation (NoOp) implementation of the Cache Manager, meaning it doesn't perform any actual caching operations.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/NoOpCacheManager.java" line="30">

---

# NoOpCacheManager Class

This is the `NoOpCacheManager` class which implements the `CacheManager` interface. It provides no-operation implementations of the methods defined in the `CacheManager` interface.

```java
public class NoOpCacheManager implements CacheManager {

    private NoOpCache noOpCache = new NoOpCache(this);

    @Override
    public CachingProvider getCachingProvider() {
        return null;
    }

    @Override
    public URI getURI() {
        return URI.create("noop:cachemanager");
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Properties getProperties() {
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/jcache/MergeJCacheManagerFactoryBean.java" line="79">

---

# Usage of NoOpCacheManager

Here, an instance of `NoOpCacheManager` is created and assigned to `this.cacheManager` when the `disableCache` flag is true. This effectively disables caching in the application.

```java
        if(disableCache){
            this.cacheManager = new NoOpCacheManager();
            return;
```

---

</SwmSnippet>

# Cache Manager Functions

The Cache Manager in BroadleafCommerce-demo has several key functions. Some of them are getCache, createCache, getCacheNames, and destroyCache. We will dive a little into each of these functions.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/NoOpCacheManager.java" line="59">

---

## getCache

The `getCache` function is used to retrieve a cache. It returns an instance of NoOpCache.

```java
    @Override
    public <K, V> Cache<K, V> getCache(String s, Class<K> aClass, Class<V> aClass1) {
        return noOpCache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) {
        return noOpCache;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/NoOpCacheManager.java" line="54">

---

## createCache

The `createCache` function is used to create a cache. It also returns an instance of NoOpCache.

```java
    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String s, C c) throws IllegalArgumentException {
        return noOpCache;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/NoOpCacheManager.java" line="69">

---

## getCacheNames

The `getCacheNames` function is used to retrieve the names of all available caches. In this implementation, it returns an empty list.

```java
    @Override
    public Iterable<String> getCacheNames() {
        return Collections.EMPTY_LIST;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/NoOpCacheManager.java" line="74">

---

## destroyCache

The `destroyCache` function is used to destroy a cache. In this implementation, it does nothing.

```java
    @Override
    public void destroyCache(String s) {

    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
