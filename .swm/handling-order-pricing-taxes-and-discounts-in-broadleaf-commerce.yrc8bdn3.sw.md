---
title: 'Handling Order Pricing, Taxes, and Discounts in Broadleaf Commerce'
---
This document will cover the process of how the BroadleafCommerce-demo system handles order pricing, taxes, and discounts. The topics covered include:

1. How order pricing is calculated
2. How taxes are applied to an order
3. How discounts are applied to an order

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="45">

---

# Order Pricing

The `Order` interface in Broadleaf defines an order and its key features. It includes methods for calculating the subtotal, total tax, and total of an order.

```java
/**
 * Defines an order in Broadleaf.    There are several key items to be aware of with the BLC Order.
 * 
 * 1.  Carts are also Orders that are in a Pending status
 * 
 * 2.  Wishlists (and similar) are "NamedOrders"
 * 
 * 3.  Orders have several price related methods that are useful when displaying totals on the cart.
 * 3a.    getSubTotal() :  The total of all order items and their adjustments exclusive of taxes
 * 3b.    getOrderAdjustmentsValue() :  The total of all order adjustments
 * 3c.    getTotalTax() :  The total taxes being charged for the order
 * 3d.    getTotal() : The order total (equivalent of getSubTotal() - getOrderAdjustmentsValue() + getTotalTax())
 * 
 * 4.  Order payments are represented with OrderPayment objects.
 * 
 * 5.  Order shipping (e.g. fulfillment) are represented with Fulfillment objects.
 */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="92">

---

The `getSubTotal` method returns the subtotal price for the order. The subtotal price is the price of all order items with item offers applied. It does not take into account the order promotions, shipping costs, or any taxes that apply to this order.

```java
    /**
     * Returns the subtotal price for the order.  The subtotal price is the price of all order items
     * with item offers applied.  The subtotal does not take into account the order promotions, shipping costs or any
     * taxes that apply to this order.
     *
     * @return the total item price with offers applied
     */
    Money getSubTotal();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="122">

---

The `getTotal` method returns the grand total of the order which includes all shipping costs and taxes, as well as any adjustments from promotions.

```java
    /**
     * The grand total of this {@link Order} which includes all shipping costs and taxes, as well as any adjustments from
     * promotions.
     * 
     * @return the grand total price of this {@link Order}
     */
    Money getTotal();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/tax/provider/TaxProvider.java" line="32">

---

# Taxes

The `TaxProvider` interface provides methods for calculating taxes on an entire order. The `calculateTaxForOrder` method calculates taxes on an entire order and returns the order with taxes included.

```java
    /**
     * Calculates taxes on an entire order. Returns the order with taxes included.
     * @param order
     * @param config
     * @return
     */
    public Order calculateTaxForOrder(Order order, ModuleConfiguration config) throws TaxException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="416">

---

# Discounts

The `getOrderAdjustmentsValue` method returns the discount value of all the applied order offers. The value returned from this method should be subtracted from the getSubTotal() to get the order price with all item and order offers applied.

```java
    /**
     * Returns the discount value of all the applied order offers.  The value returned from this
     * method should be subtracted from the getSubTotal() to get the order price with all item and
     * order offers applied.
     *
     * @return the discount value of all applied order offers.
     */
    Money getOrderAdjustmentsValue();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="204">

---

The `save` method in the `OrderService` interface saves the given order while optionally repricing the order (meaning, going through the pricing workflow) along with updating the prices of individual items in the order, as opposed to just pricing taxes/shipping/etc.

```java
    /**
     * Saves the given <b>order</b> while optionally repricing the order (meaning, going through the pricing workflow)
     * along with updating the prices of individual items in the order, as opposed to just pricing taxes/shipping/etc.
     * 
     * @param order
     * @param priceOrder
     * @param repriceItems whether or not to reprice the items inside of the order via {@link Order#updatePrices()}
     * @return the persisted Order, which will be a different instance than the Order passed in
     * @throws PricingException
     */
    public Order save(Order order, boolean priceOrder, boolean repriceItems) throws PricingException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="237">

---

The `addOfferCodes` method in the `OrderService` interface adds the given OfferCodes to the order. Optionally prices the order as well.

```java
    /**
     * Adds the given OfferCodes to the order. Optionally prices the order as well.
     * 
     * @param order
     * @param offerCodes
     * @param priceOrder
     * @return
     * @throws PricingException
     * @throws OfferMaxUseExceededException
     * @throws OfferException 
     */
    public Order addOfferCodes(Order order, List<OfferCode> offerCodes, boolean priceOrder) throws PricingException, OfferException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="261">

---

The `removeAllOfferCodes` method in the `OrderService` interface removes all offer codes for the given order. Optionally prices the order as well.

```java
    /**
     * Removes all offer codes for the given order. Optionally prices the order as well.
     * 
     * @param order
     * @param priceOrder
     * @return the modified Order
     * @throws PricingException
     */
    public Order removeAllOfferCodes(Order order, boolean priceOrder) throws PricingException;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
