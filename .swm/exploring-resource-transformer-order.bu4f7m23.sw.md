---
title: Exploring Resource Transformer Order
---
The `BroadleafResourceTransformerOrder` class in the BroadleafCommerce-demo repository defines constants that represent the order of resource transformers in Broadleaf. These constants are used by the `BroadleafResourceHttpRequestHandler` class, which sorts resolvers that implement the `Ordered` interface during its `PostConstruct` method. The `BroadleafResourceTransformerOrder` class defines two constants: `BLC_CACHE_RESOURCE_TRANSFORMER` and `BLC_MINIFY_RESOURCE_TRANSFORMER`, which are used to specify the order of the cache and minify resource transformers, respectively.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceTransformerOrder.java" line="34">

---

# BroadleafResourceTransformerOrder Class

The `BroadleafResourceTransformerOrder` class defines two constants: `BLC_CACHE_RESOURCE_TRANSFORMER` and `BLC_MINIFY_RESOURCE_TRANSFORMER`. These constants represent the order of the corresponding Resource Transformers.

```java
public class BroadleafResourceTransformerOrder {

    public static int BLC_CACHE_RESOURCE_TRANSFORMER = 1000;
    public static int BLC_MINIFY_RESOURCE_TRANSFORMER = 10000;
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/MinifyResourceTransformer.java" line="48">

---

# Usage in MinifyResourceTransformer

The `MinifyResourceTransformer` uses the `BLC_MINIFY_RESOURCE_TRANSFORMER` constant from `BroadleafResourceTransformerOrder` to set its `order` property. This determines its position in the sequence of transformations.

```java
    private int order = BroadleafResourceTransformerOrder.BLC_MINIFY_RESOURCE_TRANSFORMER;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="52">

---

# Usage in BroadleafCachingResourceTransformer

Similarly, the `BroadleafCachingResourceTransformer` uses the `BLC_CACHE_RESOURCE_TRANSFORMER` constant from `BroadleafResourceTransformerOrder` to set its `order` property. This determines its position in the sequence of transformations.

```java
    private int order = BroadleafResourceTransformerOrder.BLC_CACHE_RESOURCE_TRANSFORMER;
```

---

</SwmSnippet>

# BroadleafResourceTransformerOrder Class

BroadleafResourceTransformerOrder Class

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafResourceTransformerOrder.java" line="34">

---

## BroadleafResourceTransformerOrder Class

The BroadleafResourceTransformerOrder class provides constants that represent the ordering of Broadleaf Resource Transformers. These constants are used by the BroadleafResourceHttpRequestHandler, which sorts resolvers that implement the Ordered interface in its PostConstruct method. The two constants defined in this class are BLC_CACHE_RESOURCE_TRANSFORMER and BLC_MINIFY_RESOURCE_TRANSFORMER, which are assigned the values 1000 and 10000 respectively.

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
