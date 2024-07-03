---
title: Basic Concepts of Twelve Hour Expiry
---
Twelve Hour Expiry refers to a policy in the BroadleafCommerce-demo repository that sets a time limit for cached data. This policy is implemented in the `TwelveHourExpiryPolicy` class, which extends the `DefaultExpiryPolicy` class. The expiry time is set to 12 hours, which is specified in seconds (60 \* 60 \* 12) in the constructor of the `TwelveHourExpiryPolicy` class. This policy can be overridden on a per entry basis when using a `TimedValueHolder` as the cached value. It can also be specified in the EhCache XML configuration.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/TwelveHourExpiryPolicy.java" line="22">

---

## TwelveHourExpiryPolicy Class

This is the TwelveHourExpiryPolicy class. It extends the DefaultExpiryPolicy class and sets the expiry time to 12 hours (60 \* 60 \* 12 seconds). This class can be used to set a default expiry time for cached values.

```java
/**
 * Convenience class providing a 12 hour expiry policy, along with the ability to override it on a per entry basis when 
 * using a {@link TimedValueHolder} as the cached value. This can also be used in the EhCache XML to specify an expiry policy:
 * 
 * <pre>
 * {@code
 * <cache alias="myCache">
 *        <expiry>
 *            <class>org.broadleafcommerce.common.extensibility.cache.ehcache.TwelveHourExpiryPolicy</class>
 *        </expiry>
 *        <heap>5000</heap>
 * </cache>
 * }
 * </pre>
 * 
 * @author Kelly Tisdell
 *
 */
public final class TwelveHourExpiryPolicy extends DefaultExpiryPolicy {

    public TwelveHourExpiryPolicy() {
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/TwelveHourExpiryPolicy.java" line="28">

---

## Using TwelveHourExpiryPolicy in EhCache XML

This is an example of how to use the TwelveHourExpiryPolicy in the EhCache XML to specify an expiry policy for a particular cache. The 'class' element within the 'expiry' element is set to the TwelveHourExpiryPolicy class, indicating that this expiry policy should be used for the cache.

```java
 * <cache alias="myCache">
 *        <expiry>
 *            <class>org.broadleafcommerce.common.extensibility.cache.ehcache.TwelveHourExpiryPolicy</class>
 *        </expiry>
 *        <heap>5000</heap>
 * </cache>
```

---

</SwmSnippet>

# TwelveHourExpiryPolicy Function

This section discusses the TwelveHourExpiryPolicy class and its function.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/TwelveHourExpiryPolicy.java" line="40">

---

## TwelveHourExpiryPolicy

The `TwelveHourExpiryPolicy` class extends the `DefaultExpiryPolicy`. It sets a default expiry time of 12 hours for cached entries. This is done in the constructor of the class, where the super constructor of `DefaultExpiryPolicy` is called with the argument `60 * 60 * 12`, which represents 12 hours in seconds.

```java
public final class TwelveHourExpiryPolicy extends DefaultExpiryPolicy {

    public TwelveHourExpiryPolicy() {
        super(60 * 60 * 12);
    }
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
