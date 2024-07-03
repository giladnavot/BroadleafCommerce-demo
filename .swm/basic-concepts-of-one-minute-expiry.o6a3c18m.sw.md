---
title: Basic Concepts of One Minute Expiry
---
One Minute Expiry refers to a policy in the Broadleaf Commerce framework that sets the expiration time for a cached value to one minute. This policy is implemented in the `OneMinuteExpiryPolicy` class, which extends the `DefaultExpiryPolicy` class. The expiration time is set in the constructor of `OneMinuteExpiryPolicy` by passing `60` (representing 60 seconds) to the superclass constructor. This policy can be overridden on a per entry basis when using a `TimedValueHolder` as the cached value.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OneMinuteExpiryPolicy.java" line="40">

---

# OneMinuteExpiryPolicy Class

Here is the `OneMinuteExpiryPolicy` class. It extends `DefaultExpiryPolicy` and sets the expiry time to 60 seconds in its constructor.

```java
public final class OneMinuteExpiryPolicy extends DefaultExpiryPolicy {

    public OneMinuteExpiryPolicy() {
        super(60);
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OneMinuteExpiryPolicy.java" line="28">

---

# Using OneMinuteExpiryPolicy in EhCache XML

This is an example of how to use the `OneMinuteExpiryPolicy` in the EhCache XML. You can specify the `OneMinuteExpiryPolicy` as the expiry policy for a cache by using its fully qualified class name.

```java
 * <cache alias="myCache">
 *        <expiry>
 *            <class>org.broadleafcommerce.common.extensibility.cache.ehcache.OneMinuteExpiryPolicy</class>
 *        </expiry>
 *        <heap>5000</heap>
 * </cache>
 * }
```

---

</SwmSnippet>

# OneMinuteExpiryPolicy Functionality

The OneMinuteExpiryPolicy class

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OneMinuteExpiryPolicy.java" line="22">

---

## OneMinuteExpiryPolicy Class

The `OneMinuteExpiryPolicy` class is a convenience class that provides a 1-minute expiry policy. It can be used to override the expiry policy on a per entry basis when using a `TimedValueHolder` as the cached value. This class can also be used in the EhCache XML to specify an expiry policy. The class extends the `DefaultExpiryPolicy` class and sets the expiry time to 60 seconds (1 minute).

```java
/**
 * Convenience class providing a 1 minute expiry policy, along with the ability to override it on a per entry basis when 
 * using a {@link TimedValueHolder} as the cached value. This can also be used in the EhCache XML to specify an expiry policy:
 * 
 * <pre>
 * {@code
 * <cache alias="myCache">
 *        <expiry>
 *            <class>org.broadleafcommerce.common.extensibility.cache.ehcache.OneMinuteExpiryPolicy</class>
 *        </expiry>
 *        <heap>5000</heap>
 * </cache>
 * }
 * </pre>
 * 
 * @author Kelly Tisdell
 *
 */
public final class OneMinuteExpiryPolicy extends DefaultExpiryPolicy {

    public OneMinuteExpiryPolicy() {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
