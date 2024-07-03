---
title: 'Resource Resolver Strategy '
---
This document will cover the Resource Resolver Strategy in the BroadleafCommerce-demo project. We'll cover:

1. The purpose of the Resource Resolver Strategy
2. How it is implemented in the codebase
3. Key classes and methods involved in the Resource Resolver Strategy.

# Purpose of the Resource Resolver Strategy

The Resource Resolver Strategy in the BroadleafCommerce-demo project is a mechanism that helps in resolving resources required by the application. It is used to find and serve resources like images, CSS, JavaScript files, etc. The strategy is implemented using various classes and interfaces, each serving a specific purpose in the resource resolution process.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafVersionResourceResolver.java" line="16">

---

# Implementation of the Resource Resolver Strategy

The `BroadleafVersionResourceResolver` class is a key part of the Resource Resolver Strategy. It is responsible for resolving resources based on their versions. The `resourceVersioningEnabled` field determines whether resource versioning is enabled or not.

```java
 * #L%
 */
package org.broadleafcommerce.common.web.resource.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.resource.service.ResourceBundlingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/BroadleafDefaultResourceResolverChain.java" line="32">

---

The `BroadleafDefaultResourceResolverChain` class is another crucial part of the Resource Resolver Strategy. It maintains a chain of `ResourceResolver` instances and provides methods to resolve resources and URL paths. The `getNext()` method is used to get the next `ResourceResolver` in the chain.

```java
/**
 * This is a straight copy of Spring's DefaultResourceResolverChain  (as of 4.1.6).
 * 
 * This had to be copied as Spring set the class scope as  "package" scope thus not 
 * allowing it to be used or extended.
 *  
 * @author bpolster
 *
 */
public class BroadleafDefaultResourceResolverChain implements ResourceResolverChain {

    private final List<ResourceResolver> resolvers = new ArrayList<ResourceResolver>();

    protected static final Log LOG = LogFactory.getLog(BroadleafDefaultResourceResolverChain.class);

    private int index = -1;

    public BroadleafDefaultResourceResolverChain(List<? extends ResourceResolver> resolvers) {
        if (resolvers != null) {
            this.resolvers.addAll(resolvers);
        }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLCJSResourceResolver.java" line="59">

---

The `BLCJSResourceResolver` class is specifically used for resolving JavaScript resources. It uses a pattern to match the resource name with `BLC.js`.

```java
    protected static final Log LOG = LogFactory.getLog(BLCJSResourceResolver.class);

    private static final String BLC_JS_NAME = "BLC.js";
    protected static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @javax.annotation.Resource(name = "blBaseUrlResolver")
    BaseUrlResolver urlResolver;

    private int order = BroadleafResourceResolverOrder.BLC_JS_RESOURCE_RESOLVER;

    protected static final Pattern pattern = Pattern.compile("(\\S*)BLC((\\S{0})|([-]{1,2}[0-9]+)|([-]{1,2}[0-9]+(-[0-9]+)+)).js");
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BroadleafCachingResourceResolver.java" line="56">

---

The `BroadleafCachingResourceResolver` class is used for caching resources. It uses a cache key prefix to store and retrieve resources from the cache.

```java
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
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
