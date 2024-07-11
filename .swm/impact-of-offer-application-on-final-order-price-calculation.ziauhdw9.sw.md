---
title: Impact of Offer Application on Final Order Price Calculation
---
This document will cover the process of calculating the final order price in the BroadleafCommerce-demo repository. The topics covered include:

1. The role of the Order and OrderItem classes in the calculation.
2. The use of the PromotableOrder interface in the calculation.
3. The impact of the FulfillmentOption and TaxService on the final price.
4. The involvement of the PricingWorkflow in the calculation process.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="45">

---

# Order and OrderItem Classes

The Order class defines an order in Broadleaf. It includes several price-related methods that are useful when displaying totals on the cart. These methods include getSubTotal(), getOrderAdjustmentsValue(), getTotalTax(), and getTotal(). The OrderItem class is associated with the Order class and represents an item in the order.

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
public interface Order extends Serializable, MultiTenantCloneable<Order> {

    Long getId();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/discount/domain/PromotableOrder.java" line="28">

---

# PromotableOrder Interface

The PromotableOrder interface is used in the pricing workflow to calculate the prices of all the OrderItems as well as any taxes, fees, shipping, and adjustments for all three. It includes methods like setOrderSubTotalToPriceWithoutAdjustments(), setOrderSubTotalToPriceWithAdjustments(), and calculateSubtotalWithAdjustments().

```java
public interface PromotableOrder extends Serializable {
    
    /**
     * Sets the order subTotal to the sum of item total prices without
     * adjustments.     
     */
    void setOrderSubTotalToPriceWithoutAdjustments();

    /**
     * Sets the order subTotal to the sum of item total prices without
     * adjustments.     
     */
    void setOrderSubTotalToPriceWithAdjustments();

    /**
     * Returns all OrderItems for the order wrapped with PromotableOrderItem
     * @return
     */
    List<PromotableOrderItem> getAllOrderItems();

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentOption.java" line="27">

---

# FulfillmentOption and TaxService

The FulfillmentOption class is used to hold information about a particular type of Fulfillment implementation. It is associated with the calculation of shipping costs. The TaxService class is used to calculate taxes for the order, which is included in the final price.

```java
/**
 * A FulfillmentOption is used to hold information about a particular type of Fulfillment implementation.
 * Third-party fulfillment implementations should extend this to provide their own configuration options
 * particular to that implementation. For instance, a UPS shipping calculator might want an admin user to be
 * able to specify which type of UPS shipping this FulfillmentOption represents.
 * <br />
 * <br />
 * This entity will be presented to the user to allow them to specify which shipping they want. A possible
 * scenario is that say a site can ship with both UPS and Fedex. They will import both the Fedex and UPS
 * third-party modules, each of which will have a unique definition of FulfillmentOption (for instance,
 * FedexFulfillmentOption and UPSFulfillmentOption). Let's say the site can do 2-day shipping with UPS,
 * and next-day shipping with Fedex. What they would do in the admin is create an instance of FedexFulfillmentOption
 * entity and give it the name "Overnight" (along with any needed Fedex configuration properties), then create an instance of
 * UPSFulfillmentOption and give it the name "2 Day". When the user goes to check out, they will then see a list
 * with "Overnight" and "2 day" in it. A FulfillmentPricingProvider can then be used to estimate the fulfillment cost
 * (and calculate the fulfillment cost) for that particular option.
 * <br />
 * <br />
 * FulfillmentOptions are also inherently related to FulfillmentProcessors, in that specific types of FulfillmentOption
 * implementations should also have a FulfillmentPricingProvider that can handle operations (estimation and calculation) for
 * pricing a FulfillmentGroup. Typical third-party implementations of this paradigm would have a 1 FulfillmentOption
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/FulfillmentGroupPricingActivity.java" line="33">

---

# PricingWorkflow

The PricingWorkflow includes activities like FulfillmentGroupPricingActivity, which is used to compute all of the fulfillment costs for all of the FulfillmentGroups on an Order and updates Order with the total price of all of the FulfillmentGroups.

```java
/**
 * Called during the pricing workflow to compute all of the fulfillment costs
 * for all of the FulfillmentGroups on an Order and updates Order with the
 * total price of all of the FufillmentGroups
 * 
 * @author Phillip Verheyden
 * @see {@link FulfillmentGroup}, {@link Order}
 */
@Component("blFulfillmentGroupPricingActivity")
public class FulfillmentGroupPricingActivity extends BaseActivity<ProcessContext<Order>> {

    public static final int ORDER = 5000;
    
    @Resource(name = "blFulfillmentPricingService")
    private FulfillmentPricingService fulfillmentPricingService;

    public FulfillmentGroupPricingActivity() {
        setOrder(ORDER);
    }
    
    public void setFulfillmentPricingService(FulfillmentPricingService fulfillmentPricingService) {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
