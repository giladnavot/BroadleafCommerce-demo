---
title: Implications of Changing the Expiry Time on Application Behavior
---
This document will cover the implications of changing the expiry time in the BroadleafCommerce-demo application. We'll cover:

1. What is expiry time and how it's used in the application
2. The impact of changing the expiry time on the application's behavior
3. Examples of different expiry policies in the application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultExpiryPolicy.java" line="23">

---

# What is expiry time and how it's used in the application

Expiry time is a concept used in caching mechanisms. It determines how long a cached item should be kept before it's considered stale and needs to be refreshed. In the BroadleafCommerce-demo application, expiry time is used in the EhCache caching mechanism, which is a widely used open-source Java-based cache.

```java
import java.time.Duration;
import java.util.function.Supplier;
```

---

</SwmSnippet>

# The impact of changing the expiry time on the application's behavior

Changing the expiry time can have significant implications on the application's behavior. A shorter expiry time means the cache will be refreshed more frequently, which can lead to increased load on the server and slower response times. On the other hand, a longer expiry time means the cache will be refreshed less frequently, which can lead to faster response times but the risk of serving stale data.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OneHourExpiryPolicy.java" line="40">

---

# Examples of different expiry policies in the application

Here is an example of an expiry policy in the application. The `OneHourExpiryPolicy` class extends the `DefaultExpiryPolicy` and sets the expiry time to 60 minutes.

```java
public final class OneHourExpiryPolicy extends DefaultExpiryPolicy {

    public OneHourExpiryPolicy() {
        super(60 * 60);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/TenMinuteExpiryPolicy.java" line="40">

---

Here is another example of an expiry policy. The `TenMinuteExpiryPolicy` class sets the expiry time to 10 minutes.

```java
public final class TenMinuteExpiryPolicy extends DefaultExpiryPolicy {

    public TenMinuteExpiryPolicy() {
        super(60 * 10);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
