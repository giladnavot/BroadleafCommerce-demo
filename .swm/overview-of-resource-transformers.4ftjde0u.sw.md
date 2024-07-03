---
title: Overview of Resource Transformers
---
Resource Transformers in BroadleafCommerce-demo are components that perform transformations on web resources. They are used to modify the content of resources before they are sent to the client. For example, the `BroadleafCachingResourceTransformer` class is a resource transformer that caches resources to improve performance. It checks if caching is enabled and if so, it uses the `transform` method of the superclass `CachingResourceTransformer` to transform the resource. If caching is not enabled, it simply passes the resource through the transformer chain without caching it.

Another example of a resource transformer is the `MinifyResourceTransformer` class. This transformer minifies resources, which can help to reduce the size of the resources and improve load times. It uses the `ResourceMinificationService` to perform the minification. The `transform` method of this class first transforms the resource using the transformer chain, and then minifies the transformed resource.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/transformer/BroadleafCachingResourceTransformer.java" line="48">

---

# BroadleafCachingResourceTransformer

The `BroadleafCachingResourceTransformer` class is a Resource Transformer that provides caching functionality. It extends `CachingResourceTransformer` from Spring and implements the `Ordered` interface to specify the order in which transformers are applied. The `transform` method checks if caching is enabled and either performs the transformation and caches the result or simply delegates to the next transformer in the chain.

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

The `MinifyResourceTransformer` class is another Resource Transformer that provides minification functionality. It implements the `ResourceTransformer` and `Ordered` interfaces. The `transform` method first delegates to the next transformer in the chain, then minifies the result using the `ResourceMinificationService`.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
