---
title: Overview of Resource Transformer Type
---
Resource Transformer Type in BroadleafCommerce-demo refers to the type of transformation applied to a web resource. It's an integral part of the resource serving process, which can involve operations like caching and minification. For instance, the `BroadleafCachingResourceTransformer` class implements a caching mechanism for resources, while the `MinifyResourceTransformer` class provides a minification process for certain resource types. These transformers are organized in a specific order defined by the `BroadleafResourceTransformerOrder` class, which determines the sequence of transformations applied to a resource.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="48">

---

# BroadleafCachingResourceTransformer

The `BroadleafCachingResourceTransformer` class is a resource transformer that caches web resources. It extends `CachingResourceTransformer` and implements `Ordered`. The `transform` method checks if caching is enabled and either returns the cached resource or proceeds with the transformation chain. The `getOrder` method returns the order of this transformer.

```java
@Component("blCachingResourceTransformer")
public class BroadleafCachingResourceTransformer extends CachingResourceTransformer implements Ordered {

    protected static final Log LOG = LogFactory.getLog(BroadleafCachingResourceTransformer.class);
    private int order = BroadleafResourceTransformerOrder.BLC_CACHE_RESOURCE_TRANSFORMER;
    
    @javax.annotation.Resource(name = "blSpringCacheManager")
    private CacheManager cacheManager;
    
    private static final String DEFAULT_CACHE_NAME = "blResourceTransformerCacheElements";

    @Value("${resource.transformer.caching.enabled:true}")
    protected boolean resourceTransformerCachingEnabled;

    @Autowired
    public BroadleafCachingResourceTransformer(@Qualifier("blSpringCacheManager") CacheManager cacheManager) {
        super(cacheManager, DEFAULT_CACHE_NAME);
    }

    // Allows for an implementor to override the default cache settings.
    public BroadleafCachingResourceTransformer(Cache cache) {
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/MinifyResourceTransformer.java" line="42">

---

# MinifyResourceTransformer

The `MinifyResourceTransformer` class is a resource transformer that minifies web resources. It implements `ResourceTransformer` and `Ordered`. The `transform` method transforms the resource and then minifies it using the `minifyService`. The `getOrder` method returns the order of this transformer.

```java
@Component("blMinifyResourceTransformer")
public class MinifyResourceTransformer implements ResourceTransformer, Ordered {

    @javax.annotation.Resource(name = "blResourceMinificationService")
    protected ResourceMinificationService minifyService;

    private int order = BroadleafResourceTransformerOrder.BLC_MINIFY_RESOURCE_TRANSFORMER;

    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain)
            throws IOException {

        Resource transformed = transformerChain.transform(request, resource);

        return minifyService.minify(transformed);
    }

    @Override
    public int getOrder() {
        return order;
    }
```

---

</SwmSnippet>

# Resource Transformer Functions

This section will cover the main functions of the Resource Transformer Type in the BroadleafCommerce-demo repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="72">

---

## Transform Function

The `transform` function is a key method in both BroadleafCachingResourceTransformer and MinifyResourceTransformer classes. It takes a request, a resource, and a transformer chain as parameters. In BroadleafCachingResourceTransformer, it checks if resource transformer caching is enabled. If it is, it calls the parent class's transform method; otherwise, it calls the transform method on the transformer chain. In MinifyResourceTransformer, it first transforms the resource using the transformer chain, then minifies the transformed resource using the minifyService.

```java
    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain)
            throws IOException {
        if (resourceTransformerCachingEnabled) {
            return super.transform(request, resource, transformerChain);
        } else {
            return transformerChain.transform(request, resource);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="87">

---

## SetOrder Function

The `setOrder` function is used to set the order of the resource transformer. This is important as it determines the order in which the transformers are applied to a resource.

```java
    public void setOrder(int order) {
        this.order = order;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="82">

---

## GetOrder Function

The `getOrder` function is used to retrieve the order of the resource transformer. This can be useful when you need to know the order in which the transformers are applied.

```java
    @Override
    public int getOrder() {
        return order;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
