---
title: Understanding Pricing in BroadleafCommerce-demo
---
Pricing in BroadleafCommerce-demo refers to the functionality that calculates the cost of an order. It is primarily handled by the `PricingService` interface, which defines the `executePricing` method. This method takes an `Order` object as input and throws a `PricingException` if there's an issue during pricing calculation.

The `PricingService` is implemented by `PricingServiceImpl` class. The `executePricing` method in this class uses a workflow to calculate the price of an order. If there's an issue during this process, it throws a `PricingException`.

The `PricingService` is used in various parts of the codebase, such as `OrderServiceImpl` and `PricingServiceActivity`, to calculate the price of an order. It is also used in the `AutoBundleActivity` class, which automatically bundles items in the cart for pricing.

The `PricingException` class is an exception that is thrown when there's an issue during pricing calculation. It is used throughout the codebase wherever pricing calculation is performed.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/PricingService.java" line="23">

---

# PricingService

The PricingService interface defines the executePricing method which takes an Order as input and throws a PricingException. This method is used to execute pricing on an order.

```java
public interface PricingService {

    public Order executePricing(Order order) throws PricingException;

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/FulfillmentPricingService.java" line="30">

---

# FulfillmentPricingService

The FulfillmentPricingService interface provides methods to calculate costs for fulfillment groups and estimate costs for various fulfillment options. It is used to determine the cost for the FulfillmentGroup during the Pricing workflow.

```java
/**
 * This service can be used in a couple of different ways. First, this is used in the pricing workflow and specifically
 * {@link FulfillmentGroupMerchandiseTotalActivity} to calculate costs for {@link FulfillmentGroup}s in an {@link Order}. This can
 * also be injected in a controller to provide estimations for various {@link FulfillmentOption}s to display to the user
 * before an option is actually selected.
 * 
 * @author Phillip Verheyden
 */
public interface FulfillmentPricingService {

    /**
     * Called during the Pricing workflow to determine the cost for the {@link FulfillmentGroup}. This will loop through
     * {@link #getProcessors()} and call {@link FulfillmentPricingProvider#calculateCostForFulfillmentGroup(FulfillmentGroup)}
     * on the first processor that returns true from {@link FulfillmentPricingProvider#canCalculateCostForFulfillmentGroup(FulfillmentGroup)}
     * 
     * @param fulfillmentGroup
     * @return the updated </b>fulfillmentGroup</b> with its shippingPrice set
     * @throws FulfillmentPriceException if <b>fulfillmentGroup</b> does not have a FulfillmentOption associated to it or
     * if there was no processor found to calculate costs for <b>fulfillmentGroup</b>
     * @see {@link FulfillmentPricingProvider}
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/tax/provider/TaxProvider.java" line="30">

---

# TaxProvider

The TaxProvider interface provides methods for tax calculations. It is used to calculate tax for an order, commit tax for an order, and cancel tax.

```java
public interface TaxProvider extends ModuleProvider {

    /**
     * Calculates taxes on an entire order. Returns the order with taxes included.
     * @param order
     * @param config
     * @return
     */
    public Order calculateTaxForOrder(Order order, ModuleConfiguration config) throws TaxException;

    /**
     * This method provides the implementation an opportunity to finalize taxes on the order. This is 
     * often required when tax sub systems require tax documents to be created on checkout. This method 
     * will typically be called by the checkout workflow, rather than by the pricing workflow. Some implementations 
     * may wish to do nothing in this method, except perhaps recalculate taxes.
     * 
     * @param order
     * @param config
     * @return
     * @throws TaxException
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/AutoBundleActivity.java" line="48">

---

# AutoBundleActivity

The AutoBundleActivity class is a part of the pricing workflow that handles automatic bundling of items in the cart. It replaces individual items with their corresponding ProductBundle if the ProductBundle is set to 'automatically' bundle.

```java
/**
 * This pricing workflow step will automatically bundle items in the cart.
 *
 * For example, if a ProductBundle exists of two items and the user has
 * one of the items in their cart.   If they then add the second item,
 * this activity will replace the two items with the ProductBundle.
 *
 * This only occurs if the ProductBundle is set to "automatically" bundle.
 *
 */
public class AutoBundleActivity extends BaseActivity<ProcessContext<Order>> {
    @Resource(name="blCatalogService")
    protected CatalogService catalogService;

    @Resource(name="blOrderService")
    protected OrderService orderService;

    @Resource(name="blOrderItemDao")
    protected OrderItemDao orderItemDao;

    @Resource(name="blFulfillmentGroupItemDao")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
