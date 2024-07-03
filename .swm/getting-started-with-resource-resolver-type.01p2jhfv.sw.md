---
title: Getting Started with Resource Resolver Type
---
Resource Resolver Type in BroadleafCommerce-demo refers to the different types of resource resolvers used in the application. These resolvers are used to handle and manage resources within the application. They include BundleResourceResolver, BroadleafVersionResourceResolver, BLCJSResourceResolver, and others. Each resolver type has a specific role in managing a certain type of resource. For instance, BundleResourceResolver is used to serve previously bundled files, while BroadleafVersionResourceResolver handles resource versioning.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BundleResourceResolver.java" line="43">

---

# BundleResourceResolver

The BundleResourceResolver class is a type of Resource Resolver that is used to serve previously bundled files. It works with the ResourceBundlingService to create and read bundle files. The order property determines the order in which this resolver is applied.

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

The BroadleafVersionResourceResolver class is another type of Resource Resolver that handles versioned resources. It checks if resource versioning is enabled and if the requested resource is not a registered bundle file, it uses the parent class's resolveResourceInternal method. The order property determines the order in which this resolver is applied.

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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLCJSResourceResolver.java" line="56">

---

# BLCJSResourceResolver

The BLCJSResourceResolver class is a type of Resource Resolver that handles JavaScript resources. It modifies the requested resource path if it contains 'BLC' and uses the chain's resolveResource method to get the resource. The order property determines the order in which this resolver is applied.

```java
@Component("blBLCJSResolver")
public class BLCJSResourceResolver extends AbstractResourceResolver implements Ordered {

    protected static final Log LOG = LogFactory.getLog(BLCJSResourceResolver.class);

    private static final String BLC_JS_NAME = "BLC.js";
    protected static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @javax.annotation.Resource(name = "blBaseUrlResolver")
    BaseUrlResolver urlResolver;

    private int order = BroadleafResourceResolverOrder.BLC_JS_RESOURCE_RESOLVER;

    protected static final Pattern pattern = Pattern.compile("(\\S*)BLC((\\S{0})|([-]{1,2}[0-9]+)|([-]{1,2}[0-9]+(-[0-9]+)+)).js");


    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath, List<? extends Resource> locations,
            ResourceResolverChain chain) {
        return chain.resolveUrlPath(resourceUrlPath, locations);
    }
```

---

</SwmSnippet>

# Resource Resolver Type Functions

Let's delve into the functions of Resource Resolver Type.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafVersionResourceResolver.java" line="65">

---

## resolveResourceInternal Function

The `resolveResourceInternal` function is a key method in the Resource Resolver. It is responsible for resolving the requested resource. If resource versioning is enabled and the requested path is not a registered bundle file, it calls the parent class's `resolveResourceInternal` method. Otherwise, it calls the `resolveResource` method of the chain.

```java
    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
        if (resourceVersioningEnabled && !bundlingService.checkForRegisteredBundleFile(requestPath)) {
            return super.resolveResourceInternal(request, requestPath, locations, chain);
        } else {
            return chain.resolveResource(request, requestPath, locations);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafVersionResourceResolver.java" line="75">

---

## resolveUrlPathInternal Function

The `resolveUrlPathInternal` function is used to resolve the URL path of the resource. Similar to `resolveResourceInternal`, it checks if resource versioning is enabled and if the resource URL path is not a registered bundle file. If both conditions are met, it calls the parent class's `resolveUrlPathInternal` method. Otherwise, it calls the `resolveUrlPath` method of the chain.

```java
    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
        if (resourceVersioningEnabled && !bundlingService.checkForRegisteredBundleFile(resourceUrlPath)) {
            String result = super.resolveUrlPathInternal(resourceUrlPath, locations, chain);

            // Spring's default version handler will return null if it doesn't have a strategy
            // for that resource - that seems incorrect.   Overriding here.
            if (result == null) {
                return chain.resolveUrlPath(resourceUrlPath, locations);
            } else {
                return result;
            }
        } else {
            return chain.resolveUrlPath(resourceUrlPath, locations);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafVersionResourceResolver.java" line="93">

---

## getOrder Function

The `getOrder` function is used to get the order of the Resource Resolver. This is used to determine the order in which the resolvers are applied.

```java
    @Override
    public int getOrder() {
        return order;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
