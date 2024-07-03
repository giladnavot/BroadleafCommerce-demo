---
title: Basic Concepts of Order Item
---
An Order Item in Broadleaf Commerce represents a specific item within an order. It contains information about the item such as its unique identifier, the order it belongs to, its retail price, sale price, quantity, and other related details. It also includes methods for setting and getting these properties. Order Items can be associated with a specific category and can have child Order Items, forming a hierarchical structure. They can also be associated with offers, adjustments, and personal messages. The Order Item interface provides methods for managing these associations and for operations like price calculation and cloning.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="32">

---

# OrderItem Interface

This is the OrderItem interface. It declares a variety of methods for getting and setting properties of an order item, such as its ID, associated order, retail price, sale price, quantity, and more. It also includes methods for managing related entities like the item's category and adjustments.

```java
public interface OrderItem extends Serializable, Cloneable, MultiTenantCloneable<OrderItem> {

    /**
     * The unique identifier of this OrderItem
     * @return
     */
    Long getId();

    /**
     * Sets the unique id of the OrderItem.   Typically left null for new items and Broadleaf will
     * set using the next sequence number.
     * @param id
     */
    void setId(Long id);

    /**
     * Reference back to the containing order.
     * @return
     */
    Order getOrder();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="523">

---

# Usage of OrderItem

Here is an example of how the OrderItem interface is used in the OrderServiceImpl class. The findLastMatchingItem method retrieves the most recently added OrderItem that matches a given SKU and product ID from an order. It uses the getOrderItems method of the Order interface to get a list of OrderItems, and then iterates through this list to find the matching item.

```java
    @Override
    public OrderItem findLastMatchingItem(Order order, Long skuId, Long productId) {
        if (order.getOrderItems() != null) {
            for (int i=(order.getOrderItems().size()-1); i >= 0; i--) {
                OrderItem currentItem = (order.getOrderItems().get(i));
                if (currentItem instanceof DiscreteOrderItem) {
                    DiscreteOrderItem discreteItem = (DiscreteOrderItem) currentItem;
                    if (skuId != null) {
                        if (discreteItem.getSku() != null && skuId.equals(discreteItem.getSku().getId())) {
                            return discreteItem;
                        }
                    } else if (productId != null && discreteItem.getProduct() != null && productId.equals(discreteItem.getProduct().getId())) {
                        return discreteItem;
                    }

                } else if (currentItem instanceof BundleOrderItem) {
                    BundleOrderItem bundleItem = (BundleOrderItem) currentItem;
                    if (skuId != null) {
                        if (bundleItem.getSku() != null && skuId.equals(bundleItem.getSku().getId())) {
                            return bundleItem;
                        }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderItemServiceImpl.java" line="86">

---

# Manipulating OrderItem Properties

In the OrderItemServiceImpl class, the saveOrderItem method is used to save changes to an OrderItem. It calls the saveOrderItem method of the OrderItemDao, passing the OrderItem to be saved. This is an example of how the properties of an OrderItem, once manipulated, can be persisted.

```java
    @Override
    public OrderItem readOrderItemById(final Long orderItemId) {
        return orderItemDao.readOrderItemById(orderItemId);
    }

    @Override
    public OrderItem saveOrderItem(final OrderItem orderItem) {
        return orderItemDao.saveOrderItem(orderItem);
    }

    @Override
```

---

</SwmSnippet>

# Order Item Functions

The OrderItem interface provides a set of methods that allow manipulation and retrieval of order item data.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="38">

---

## Order Item Identifier

The `getId` and `setId` methods are used to get and set the unique identifier of the OrderItem respectively. This ID is typically generated and managed by the underlying persistence mechanism.

```java
    Long getId();

    /**
     * Sets the unique id of the OrderItem.   Typically left null for new items and Broadleaf will
     * set using the next sequence number.
     * @param id
     */
    void setId(Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="51">

---

## Associated Order

The `getOrder` and `setOrder` methods are used to get and set the order associated with the OrderItem. This establishes a link between an order item and the order it belongs to.

```java
    Order getOrder();

    /**
     * Sets the order for this orderItem.
     * @param order
     */
    void setOrder(Order order);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="67">

---

## Retail Price

The `getRetailPrice` and `setRetailPrice` methods are used to get and set the retail price of the OrderItem. The retail price is the original price of the item before any discounts or adjustments are applied.

```java
    Money getRetailPrice();

    /**
     * Calling this method will manually set the retailPrice.   To avoid the pricing engine
     * resetting this price, you should also make a call to 
     * {@link #setRetailPriceOverride(true)}
     * 
     * Consider also calling {@link #setDiscountingAllowed(boolean)} with a value of false to restrict
     * discounts after manually setting the retail price.    
     * 
     * @param retailPrice
     */
    void setRetailPrice(Money retailPrice);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="104">

---

## Sale Price

The `getSalePrice` and `setSalePrice` methods are used to get and set the sale price of the OrderItem. The sale price is the discounted price of the item, which may be lower than the retail price.

```java
    Money getSalePrice();

    /**
     * Calling this method will manually set the salePrice.    It will also make a call to 
     * {@link #setSalePriceSetManually(true)}
     * 
     *  To avoid the pricing engine resetting this price, you should also make a call to 
     *  {@link #setSalePriceOverride(true)}
     *      
     * Typically for {@link DiscreteOrderItem}s, the prices will be set with a call to {@link #updateSaleAndRetailPrices()}
     * which will use the Broadleaf dynamic pricing engine or the values directly tied to the SKU.
     * 
     * @param salePrice
     */
    void setSalePrice(Money salePrice);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="190">

---

## Quantity

The `getQuantity` and `setQuantity` methods are used to get and set the quantity of the OrderItem. This represents the number of units of the item in the order.

```java
    int getQuantity();

    /**
     * Sets the quantity of this item
     */
    void setQuantity(int quantity);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="206">

---

## Order Item Price Details

The `getOrderItemPriceDetails` and `setOrderItemPriceDetails` methods are used to get and set the price details of the OrderItem. These details include the breakdown of the item's price into its constituent parts, such as base price, discounts, and taxes.

```java
    List<OrderItemPriceDetail> getOrderItemPriceDetails();

    /**
     * Returns the list of orderItem price details.
     * @see {@link #getOrderItemPriceDetails()}
     * @param orderItemPriceDetails
     */
    void setOrderItemPriceDetails(List<OrderItemPriceDetail> orderItemPriceDetails);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
