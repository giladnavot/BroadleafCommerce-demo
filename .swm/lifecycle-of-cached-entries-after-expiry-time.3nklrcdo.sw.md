---
title: Lifecycle of Cached Entries After Expiry Time
---
This document will cover the handling of cached entries after their expiry time in the BroadleafCommerce-demo repository. We'll cover:

1. The different expiry policies
2. The role of the `TimedValueHolder` class
3. The `shouldCacheDate` method and its usage
4. The `BroadleafProcessURLFilter` class and its role in cache expiration.

# Expiry Policies

The BroadleafCommerce-demo repository implements different expiry policies for cached entries. These policies are defined in classes like `OneMinuteExpiryPolicy`, `TenMinuteExpiryPolicy`, `ThirtyMinuteExpiryPolicy`, `OneHourExpiryPolicy`, `TwelveHourExpiryPolicy`, and `TwentyFourHourExpiryPolicy`. Each of these classes represents a different duration after which the cached entries expire.

# TimedValueHolder Class

The `TimedValueHolder` class is used in the expiry policy classes. This class likely holds the cached value along with its timestamp, allowing the system to determine when the value should expire.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/time/SystemTime.java" line="105">

---

# shouldCacheDate Method

The `shouldCacheDate` method checks if the current time source is a `FixedTimeSource`. If it is, the method returns false, indicating that the time is being overridden and the date should not be cached. This method is used in various places in the codebase to decide whether to cache a date or not.

```java
    /**
     * Returns false if the current time source is a {@link FixedTimeSource} indicating that the 
     * time is being overridden.   For example to preview items in a later time.
     * 
     * @return
     */
    public static boolean shouldCacheDate() {
        if (SystemTime.getTimeSource() instanceof FixedTimeSource) {
            return false;
        } else {
            return true;
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/BroadleafProcessURLFilter.java" line="64">

---

# BroadleafProcessURLFilter Class

The `BroadleafProcessURLFilter` class sets up the CMS system by setting the current sandbox, locale, time of day, and language code used by content items. It also creates an internal cache to quickly determine if the request should be processed by an instance of `URLProcessor` or be passed to the next filter in the filter chain. The cache settings, including expiration seconds, can be configured via Spring at startup.

```java
/**
 * @deprecated In favor of org.broadleafcommerce.common.web.BroadleafRequestFilter.
 * formally component name "blProcessURLFilter"
 *
 * This filter sets up the CMS system by setting the current sandbox, locale, time of day, and languageCode
 * that used by content items.
 * <p/>
 * After setting up content variables, it checks to see if a request can be processed by an instance of
 * URLProcessor and if so, delegates the request to that processor.
 *
 * This filter creates an internal cache to quickly determine if the request should be processed
 * by an instance of URLProcessor or be passed to the next filter in the filter chain.    The
 * cache settings (including expiration seconds, maximum elements, and concurrency) can be
 * configured via Spring at startup.   See {@code com.google.common.cache.CacheBuilder} for more information
 * on these parameters.
 *
 * @author bpolster
 */
@Deprecated
public class BroadleafProcessURLFilter extends OncePerRequestFilter {
    private final Log LOG = LogFactory.getLog(BroadleafProcessURLFilter.class);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
