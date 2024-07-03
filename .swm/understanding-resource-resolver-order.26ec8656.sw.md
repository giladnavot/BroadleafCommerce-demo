---
title: Understanding Resource Resolver Order
---
Resource Resolver Order in Broadleaf Commerce refers to the sequence in which resource resolvers are applied. Resource resolvers are components that help in locating and possibly transforming resources like CSS, JavaScript, and images. The BroadleafResourceResolverOrder class defines constants that represent the order of out-of-the-box Broadleaf resource resolvers. This order is used by the BroadleafResourceHttpRequestHandler, which sorts resolvers that implement the Ordered interface. The order of resolvers is important as it can affect how resources are processed and delivered.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceResolverOrder.java" line="34">

---

# BroadleafResourceResolverOrder Class

The `BroadleafResourceResolverOrder` class defines constants for the order values of out-of-the-box Broadleaf resource resolvers. These constants are used to set the order value of each resolver.

```java
public class BroadleafResourceResolverOrder {

    // Negative values should occur before any custom resolvers
    public static int THEME_FILE_URL_RESOLVER = -1000;
    public static int BLC_JS_PATH_RESOLVER = -2000;

    // Implementors typically want dynamic URL before the cache resolver (e.g. BLC_CACHE_RESOURCE_RESOLVER -1) 
    // and anything else after the version resolver (e.g. BLC_VERSION_RESOURCE_RESOLVER + 1)
    public static int BLC_CACHE_RESOURCE_RESOLVER = 1000;
    public static int BLC_VERSION_RESOURCE_RESOLVER = 2000;

    // Custom resolvers (various lookup and file modification scenarios)
    public static int BLC_BUNDLE_RESOURCE_RESOLVER = 10000;
    public static int BLC_JS_RESOURCE_RESOLVER = 11000;
    public static int BLC_SYSTEM_PROPERTY_RESOURCE_RESOLVER = 12000;
    public static int BLC_THEME_FILE_RESOLVER = 13000;

    // Path Resolvers should always be last   
    public static int BLC_PATH_RESOURCE_RESOLVER = Integer.MAX_VALUE;

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLCJSUrlPathResolver.java" line="59">

---

# Setting the Order Value

The order value of a resolver is set by assigning a value from `BroadleafResourceResolverOrder` to the `order` field of the resolver's class. For example, the `BLCJSUrlPathResolver` class sets its order value to `BroadleafResourceResolverOrder.BLC_JS_PATH_RESOLVER`.

```java
    private int order = BroadleafResourceResolverOrder.BLC_JS_PATH_RESOLVER;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceResolverOrder.java" line="36">

---

# Order of Execution

The order of execution of resolvers is determined by their order values. Resolvers with lower values are executed before those with higher values. For example, `THEME_FILE_URL_RESOLVER` with an order value of -1000 will be executed before `BLC_JS_PATH_RESOLVER` with an order value of -2000. `BLC_PATH_RESOURCE_RESOLVER` with an order value of `Integer.MAX_VALUE` is always executed last.

```java
    // Negative values should occur before any custom resolvers
    public static int THEME_FILE_URL_RESOLVER = -1000;
    public static int BLC_JS_PATH_RESOLVER = -2000;

    // Implementors typically want dynamic URL before the cache resolver (e.g. BLC_CACHE_RESOURCE_RESOLVER -1) 
    // and anything else after the version resolver (e.g. BLC_VERSION_RESOURCE_RESOLVER + 1)
    public static int BLC_CACHE_RESOURCE_RESOLVER = 1000;
    public static int BLC_VERSION_RESOURCE_RESOLVER = 2000;

    // Custom resolvers (various lookup and file modification scenarios)
    public static int BLC_BUNDLE_RESOURCE_RESOLVER = 10000;
    public static int BLC_JS_RESOURCE_RESOLVER = 11000;
    public static int BLC_SYSTEM_PROPERTY_RESOURCE_RESOLVER = 12000;
    public static int BLC_THEME_FILE_RESOLVER = 13000;

    // Path Resolvers should always be last   
    public static int BLC_PATH_RESOURCE_RESOLVER = Integer.MAX_VALUE;
```

---

</SwmSnippet>

# Resource Resolver and Transformer Order

Resource Resolver Order

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceResolverOrder.java" line="34">

---

## BroadleafResourceResolverOrder

The BroadleafResourceResolverOrder class defines the order of resource resolvers. It contains constants that represent the order of execution for various resource resolvers such as THEME_FILE_URL_RESOLVER, BLC_JS_PATH_RESOLVER, BLC_CACHE_RESOURCE_RESOLVER, BLC_VERSION_RESOURCE_RESOLVER, BLC_BUNDLE_RESOURCE_RESOLVER, BLC_JS_RESOURCE_RESOLVER, BLC_SYSTEM_PROPERTY_RESOURCE_RESOLVER, BLC_THEME_FILE_RESOLVER, and BLC_PATH_RESOURCE_RESOLVER.

```java
public class BroadleafResourceResolverOrder {

    // Negative values should occur before any custom resolvers
    public static int THEME_FILE_URL_RESOLVER = -1000;
    public static int BLC_JS_PATH_RESOLVER = -2000;

    // Implementors typically want dynamic URL before the cache resolver (e.g. BLC_CACHE_RESOURCE_RESOLVER -1) 
    // and anything else after the version resolver (e.g. BLC_VERSION_RESOURCE_RESOLVER + 1)
    public static int BLC_CACHE_RESOURCE_RESOLVER = 1000;
    public static int BLC_VERSION_RESOURCE_RESOLVER = 2000;

    // Custom resolvers (various lookup and file modification scenarios)
    public static int BLC_BUNDLE_RESOURCE_RESOLVER = 10000;
    public static int BLC_JS_RESOURCE_RESOLVER = 11000;
    public static int BLC_SYSTEM_PROPERTY_RESOURCE_RESOLVER = 12000;
    public static int BLC_THEME_FILE_RESOLVER = 13000;

    // Path Resolvers should always be last   
    public static int BLC_PATH_RESOURCE_RESOLVER = Integer.MAX_VALUE;

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceTransformerOrder.java" line="34">

---

## BroadleafResourceTransformerOrder

The BroadleafResourceTransformerOrder class defines the order of resource transformers. It contains constants that represent the order of execution for various resource transformers such as BLC_CACHE_RESOURCE_TRANSFORMER and BLC_MINIFY_RESOURCE_TRANSFORMER.

```java
public class BroadleafResourceTransformerOrder {

    public static int BLC_CACHE_RESOURCE_TRANSFORMER = 1000;
    public static int BLC_MINIFY_RESOURCE_TRANSFORMER = 10000;
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
