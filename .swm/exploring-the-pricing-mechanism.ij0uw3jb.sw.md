---
title: Exploring the Pricing Mechanism
---
Pricing in the BroadleafCommerce-demo refers to the functionality of calculating the cost of items in an order. This is primarily handled by the `PricingService` interface located in the `org.broadleafcommerce.core.pricing.service` package.

The `PricingService` interface defines a single method, `executePricing(Order order)`, which takes an `Order` object as input and returns an `Order` object. This method is responsible for calculating the prices of items in the order.

The `PricingServiceImpl` class implements the `PricingService` interface. It uses a `Processor` object named `pricingWorkflow` to perform the pricing activities on the order.

The `PricingService` is used in various parts of the application, such as the `OrderServiceImpl` and `PricingServiceActivity` classes, to calculate the prices of items in an order.

The `AutoBundleActivity` class in the `org.broadleafcommerce.core.pricing.service.workflow` package is an example of a pricing workflow activity. It automatically bundles items in the cart if a `ProductBundle` exists for those items and the bundle is set to automatically bundle.

The `removeAutomaticBundles` method in the `AutoBundleActivity` class is an example of a method that modifies the pricing of an order. It removes all automatic bundles from the order and replaces them with `DiscreteOrderItems`.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/PricingService.java" line="23">

---

# PricingService Interface

The `PricingService` interface defines the `executePricing(Order order)` method, which is responsible for calculating the prices of items in an order.

```java
public interface PricingService {

    public Order executePricing(Order order) throws PricingException;

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/PricingServiceImpl.java" line="30">

---

# PricingServiceImpl Class

The `PricingServiceImpl` class implements the `PricingService` interface. It uses a `Processor` object named `pricingWorkflow` to perform the pricing activities on the order.

```java
@Service("blPricingService")
public class PricingServiceImpl implements PricingService {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/AutoBundleActivity.java" line="48">

---

# AutoBundleActivity Class

The `AutoBundleActivity` class is an example of a pricing workflow activity. It automatically bundles items in the cart if a `ProductBundle` exists for those items and the bundle is set to automatically bundle.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/AutoBundleActivity.java" line="116">

---

# removeAutomaticBundles Method

The `removeAutomaticBundles` method in the `AutoBundleActivity` class is an example of a method that modifies the pricing of an order. It removes all automatic bundles from the order and replaces them with `DiscreteOrderItems`.

```java
    private Order removeAutomaticBundles(Order order) throws PricingException {
        List<BundleOrderItem> bundlesToRemove = new ArrayList<BundleOrderItem>();

        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem instanceof BundleOrderItem) {
                BundleOrderItem bundleOrderItem = (BundleOrderItem) orderItem;
                if (bundleOrderItem.getProductBundle() != null && bundleOrderItem.getProductBundle().getAutoBundle()) {
                    bundlesToRemove.add(bundleOrderItem);
                }
            }
        }

        for (BundleOrderItem bundleOrderItem : bundlesToRemove) {
            try {
                order = orderService.removeItem(order.getId(), bundleOrderItem.getId(), false);
            } catch (RemoveFromCartException e) {
                throw new PricingException("Could not remove item", e);
            }
        }

        return order;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
