---
title: Getting Started with Resource Resolvers
---
Resource Resolvers in BroadleafCommerce-demo are components that help in resolving resources required by the application. They are part of the web resource handling system and are used to serve previously bundled files or to resolve resources based on certain conditions. For instance, the `BundleResourceResolver` serves previously bundled files, while the `BroadleafVersionResourceResolver` resolves resources based on their versioning status. These resolvers extend the `AbstractResourceResolver` and implement the `Ordered` interface, which allows them to be prioritized during resource resolution.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BundleResourceResolver.java" line="43">

---

# BundleResourceResolver

The BundleResourceResolver is used to serve previously bundled files. It works with the ResourceBundlingService to create and read bundle files. The 'resolveResourceInternal' method checks if the requested path is a bundle file and if so, it tries to resolve the bundle resource. If the bundle resource does not exist, it attempts to rebuild it.

```java
@Component("blBundleResourceResolver")
public class BundleResourceResolver extends AbstractResourceResolver implements Ordered {

    protected static final Log LOG = LogFactory.getLog(BundleResourceResolver.class);

    private int order = BroadleafResourceResolverOrder.BLC_BUNDLE_RESOURCE_RESOLVER;

    @javax.annotation.Resource(name = "blResourceBundlingService")
    protected ResourceBundlingService bundlingService;

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {

        if (requestPath != null) {
            if (isBundleFile(requestPath)) {
                Resource bundle = bundlingService.resolveBundleResource(requestPath);

                logTraceInformation(bundle);
                if (bundle != null) {
                    if (!bundle.exists()) {
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafVersionResourceResolver.java" line="49">

---

# BroadleafVersionResourceResolver

The BroadleafVersionResourceResolver is used for resource versioning. It checks if resource versioning is enabled and if the requested path is not a registered bundle file. If these conditions are met, it calls the parent class's 'resolveResourceInternal' method to resolve the resource.

```java
@Component("blVersionResourceResolver")
public class BroadleafVersionResourceResolver extends VersionResourceResolver implements Ordered {

    protected static final Log LOG = LogFactory.getLog(BroadleafVersionResourceResolver.class);

    private int order = BroadleafResourceResolverOrder.BLC_VERSION_RESOURCE_RESOLVER;

    @Value("${resource.versioning.enabled:true}")
    protected boolean resourceVersioningEnabled;

    @javax.annotation.Resource(name = "blResourceBundlingService")
    protected ResourceBundlingService bundlingService;

    @javax.annotation.Resource(name = "blVersionResourceResolverStrategyMap")
    protected Map<String, VersionStrategy> versionStrategyMap;

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
        if (resourceVersioningEnabled && !bundlingService.checkForRegisteredBundleFile(requestPath)) {
            return super.resolveResourceInternal(request, requestPath, locations, chain);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafCachingResourceResolver.java" line="53">

---

# BroadleafCachingResourceResolver

The BroadleafCachingResourceResolver is used for caching resources. It checks if resource caching is enabled. If so, it tries to get the resource from the cache. If the resource is not in the cache, it resolves the resource and puts it in the cache.

```java
@Component("blCacheResourceResolver")
public class BroadleafCachingResourceResolver extends AbstractResourceResolver implements Ordered {

    public static final String RESOLVED_RESOURCE_CACHE_KEY_PREFIX = "resolvedResource:";
    public static final String RESOLVED_URL_PATH_CACHE_KEY_PREFIX = "resolvedUrlPath:";
    public static final String RESOLVED_RESOURCE_CACHE_KEY_PREFIX_NULL = "resolvedResourceNull:";
    public static final String RESOLVED_URL_PATH_CACHE_KEY_PREFIX_NULL = "resolvedUrlPathNull:";
    private static final Object NULL_REFERENCE = new Object();
    private int order = BroadleafResourceResolverOrder.BLC_CACHE_RESOURCE_RESOLVER;

    private final Cache cache;

    @javax.annotation.Resource(name = "blSpringCacheManager")
    private CacheManager cacheManager;

    @javax.annotation.Resource(name = "blBroadleafContextUtil")
    protected BroadleafContextUtil blcContextUtil;
    
    private static final String DEFAULT_CACHE_NAME = "blResourceCacheElements";

    @Value("${resource.caching.enabled:true}")
```

---

</SwmSnippet>

This section will cover the main functions of the resource resolvers in the BroadleafCommerce-demo repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLCJSResourceResolver.java" line="72">

---

## resolveResourceInternal

The `resolveResourceInternal` function in `BLCJSResourceResolver` is used to resolve a resource based on the request path. It checks if the request path contains 'BLC', and if so, it modifies the request path and tries to resolve the resource. If the resource is not found, it tries to resolve the resource again with the modified request path. If an exception occurs during the resource conversion, it logs the error.

```java
    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath, List<? extends Resource> locations,
            ResourceResolverChain chain) {
        return chain.resolveUrlPath(resourceUrlPath, locations);
    }
    
    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
        if (requestPath != null && requestPath.contains("BLC")) {
            Matcher matcher = pattern.matcher(requestPath);
            if (matcher.find()) {
                requestPath = matcher.group(1) + "BLC.js";
                Resource resource = chain.resolveResource(request, "BLC.js", locations);
                if (resource == null) {
                    requestPath = matcher.group(1) + "BLC.js";
                    resource = chain.resolveResource(request, requestPath, locations);
                }

                try {
                    resource = convertResource(resource, requestPath);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLCJSResourceResolver.java" line="72">

---

## resolveUrlPathInternal

The `resolveUrlPathInternal` function in `BLCJSResourceResolver` is used to resolve the URL path of a resource. It simply delegates the resolution to the next resolver in the chain.

```java
    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath, List<? extends Resource> locations,
            ResourceResolverChain chain) {
        return chain.resolveUrlPath(resourceUrlPath, locations);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
