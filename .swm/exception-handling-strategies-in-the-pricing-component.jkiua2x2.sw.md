---
title: Exception Handling Strategies in the Pricing Component
---
This document will cover the exception handling strategies within the pricing component of the BroadleafCommerce-demo repository. We'll cover:

1. The role of the SkuPricingConsiderationContext class
2. The use of DynamicSkuPricingService
3. The implementation of PricingService

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/dynamic/SkuPricingConsiderationContext.java" line="34">

---

# SkuPricingConsiderationContext

The SkuPricingConsiderationContext class is a central part of the pricing component's exception handling strategy. It provides methods to start and end pricing considerations, check if pricing consideration is active, and check if dynamic pricing is available. It also provides access to the pricing service and pricing consideration context.

```java
/**
 * Convenient place to store the pricing considerations context and the pricing service on thread local. This class is
 * usually filled out by a DynamicSkuPricingFilter. The default implementation of this is DefaultDynamicSkuPricingFilter.
 * 
 * @author jfischer
 * @see {@link SkuImpl#getRetailPrice}
 * @see {@link SkuImpl#getSalePrice}
 */
public class SkuPricingConsiderationContext {

    protected static final ConcurrentHashMap<String, Field> FIELD_CACHE = new ConcurrentHashMap<>();
    private static final ThreadLocal<SkuPricingConsiderationContext> skuPricingConsiderationContext = ThreadLocalManager.createThreadLocal(SkuPricingConsiderationContext.class);

    public static HashMap getSkuPricingConsiderationContext() {
        return SkuPricingConsiderationContext.skuPricingConsiderationContext.get().considerations;
    }
    
    public static void setSkuPricingConsiderationContext(HashMap skuPricingConsiderations) {
        SkuPricingConsiderationContext.skuPricingConsiderationContext.get().considerations = skuPricingConsiderations;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/dynamic/DynamicSkuPricingService.java" line="29">

---

# DynamicSkuPricingService

The DynamicSkuPricingService interface defines methods for calculating dynamic pricing for a Sku. It is used in conjunction with SkuPricingConsiderationContext to handle pricing exceptions.

```java
/**
 * <p>Interface for calculating dynamic pricing for a {@link Sku}. This should be hooked up via a custom subclass of 
 * {@link org.broadleafcommerce.core.web.catalog.DefaultDynamicSkuPricingFilter} where an implementation of this class
 * should be injected and returned in the getPricing() method.</p>
 * 
 * <p>Rather than implementing this interface directly, consider subclassing the {@link DefaultDynamicSkuPricingServiceImpl}
 * and providing overrides to methods there.</p>
 * 
 * @author jfischer
 * @see {@link DefaultDynamicSkuPricingServiceImpl}
 * @see {@link org.broadleafcommerce.core.web.catalog.DefaultDynamicSkuPricingFilter}
 * @see {@link SkuPricingConsiderationContext}
 */
public interface DynamicSkuPricingService {

    /**
     * While this method should return a {@link DynamicSkuPrices} (and not just null) the members of the result can all
     * be null; they do not have to be set
     * 
     * @param skuWrapper
     * @param skuPricingConsiderations
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/PricingService.java" line="23">

---

# PricingService

The PricingService interface provides a method to execute pricing on an order, which can throw a PricingException. This is another key part of the exception handling strategy in the pricing component.

```java
public interface PricingService {

    public Order executePricing(Order order) throws PricingException;

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
