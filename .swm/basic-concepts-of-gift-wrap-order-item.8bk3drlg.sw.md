---
title: Basic Concepts of Gift Wrap Order Item
---
The Gift Wrap Order Item is a specific type of order item in the Broadleaf Commerce framework. It extends the DiscreteOrderItem interface, which means it represents a single unit within an order. The Gift Wrap Order Item is used when a customer chooses to have one or more items in their order gift wrapped. It contains a list of the items to be wrapped, which can be accessed and modified using the getWrappedItems and setWrappedItems methods respectively.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/GiftWrapOrderItem.java" line="22">

---

# GiftWrapOrderItem Interface

The GiftWrapOrderItem interface extends the DiscreteOrderItem interface and provides two methods: `getWrappedItems()` and `setWrappedItems(List<OrderItem> wrappedItems)`. These methods are used to get and set the items that need to be gift wrapped in an order.

```java
public interface GiftWrapOrderItem extends DiscreteOrderItem {

    public List<OrderItem> getWrappedItems();

    public void setWrappedItems(List<OrderItem> wrappedItems);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyMergeCartServiceImpl.java" line="178">

---

# Usage of GiftWrapOrderItem

Here, the GiftWrapOrderItem is used to check if an order item is a gift wrap order item and to get the items to be wrapped. This is done in the process of merging carts.

```java
            for (OrderItem orderItem : customerCart.getOrderItems()) {
                if (orderItem instanceof GiftWrapOrderItem) {
                    for (OrderItem wrappedItem : ((GiftWrapOrderItem) orderItem).getWrappedItems()) {
                        if (itemsToRemove.contains(wrappedItem)) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="644">

---

In this example, a gift wrap item is added to an order. The `createGiftWrapOrderItem` method is used to create a new GiftWrapOrderItem, which is then saved to the order.

```java
    public OrderItem addGiftWrapItemToOrder(Order order, GiftWrapOrderItemRequest itemRequest, boolean priceOrder) throws PricingException {
        GiftWrapOrderItem item = orderItemService.createGiftWrapOrderItem(itemRequest);
        item.setOrder(order);
        item = (GiftWrapOrderItem) orderItemService.saveOrderItem(item);
```

---

</SwmSnippet>

# GiftWrapOrderItem Functions

The GiftWrapOrderItem interface provides two main functions.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/GiftWrapOrderItem.java" line="24">

---

## getWrappedItems

The `getWrappedItems` function is used to retrieve a list of OrderItems that are wrapped by the GiftWrapOrderItem. This function is used in various parts of the codebase to manipulate and access the items wrapped by the gift wrap item.

```java
    public List<OrderItem> getWrappedItems();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/GiftWrapOrderItem.java" line="26">

---

## setWrappedItems

The `setWrappedItems` function is used to set the list of OrderItems that are wrapped by the GiftWrapOrderItem. This function is essential for creating and updating the gift wrap item with the items it wraps.

```java
    public void setWrappedItems(List<OrderItem> wrappedItems);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
