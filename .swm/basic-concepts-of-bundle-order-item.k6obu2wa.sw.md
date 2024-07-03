---
title: Basic Concepts of Bundle Order Item
---
A Bundle Order Item in Broadleaf Commerce represents a collection of discrete order items that are purchased together as a single product. This is typically used for product bundles, where multiple products are sold together as a package. The Bundle Order Item interface provides methods to get and set the discrete order items, the associated product bundle, and pricing information. It also provides methods to check if the bundle has adjusted items and whether the items should be summed. Note that the Bundle Order Item is deprecated and the usage of Discrete Order Items in the Product Type Module's Product Add-Ons is recommended instead.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItem.java" line="31">

---

# BundleOrderItem Interface

This is the BundleOrderItem interface. It extends OrderItem, OrderItemContainer, and SkuAccessor. It provides methods to manipulate and retrieve information about the bundle.

```java
public interface BundleOrderItem extends OrderItem, OrderItemContainer, SkuAccessor {

    List<DiscreteOrderItem> getDiscreteOrderItems();

    void setDiscreteOrderItems(List<DiscreteOrderItem> discreteOrderItems);

    Money getTaxablePrice();
    
    public List<BundleOrderItemFeePrice> getBundleOrderItemFeePrices();

    public void setBundleOrderItemFeePrices(List<BundleOrderItemFeePrice> bundleOrderItemFeePrices);

    public boolean hasAdjustedItems();

    public Money getBaseRetailPrice();

    public void setBaseRetailPrice(Money baseRetailPrice);

    public Money getBaseSalePrice();

    public void setBaseSalePrice(Money baseSalePrice);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderItemServiceImpl.java" line="346">

---

# Using BundleOrderItem

Here is an example of how to use the BundleOrderItem. In this case, a new BundleOrderItem is being created using the `createBundleOrderItem` method of the `OrderItemService`.

```java
    public BundleOrderItem createBundleOrderItem(final BundleOrderItemRequest itemRequest) {
        final BundleOrderItem item = (BundleOrderItem) orderItemDao.create(OrderItemType.BUNDLE);
        item.setQuantity(itemRequest.getQuantity());
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CheckAddAvailabilityActivity.java" line="91">

---

# BundleOrderItem in Order Processing

In the order processing workflow, the BundleOrderItem is used to check the availability of the SKU associated with the bundle.

```java
                skuFromOrder = ((DiscreteOrderItem) orderItem).getSku();
            } else if (orderItem instanceof BundleOrderItem) {
                skuFromOrder = ((BundleOrderItem) orderItem).getSku();
            }
```

---

</SwmSnippet>

# BundleOrderItem Interface

The BundleOrderItem interface provides several methods to manage and manipulate bundle order items.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItem.java" line="31">

---

## BundleOrderItem Interface

The BundleOrderItem interface provides methods to manage bundle order items. These methods allow you to get and set discrete order items, get the taxable price, get and set bundle order item fee prices, check if the item has adjustments, get and set base retail and sale prices, get and set the SKU, get and set the product bundle, get the product, and check if items should be summed.

```java
public interface BundleOrderItem extends OrderItem, OrderItemContainer, SkuAccessor {

    List<DiscreteOrderItem> getDiscreteOrderItems();

    void setDiscreteOrderItems(List<DiscreteOrderItem> discreteOrderItems);

    Money getTaxablePrice();
    
    public List<BundleOrderItemFeePrice> getBundleOrderItemFeePrices();

    public void setBundleOrderItemFeePrices(List<BundleOrderItemFeePrice> bundleOrderItemFeePrices);

    public boolean hasAdjustedItems();

    public Money getBaseRetailPrice();

    public void setBaseRetailPrice(Money baseRetailPrice);

    public Money getBaseSalePrice();

    public void setBaseSalePrice(Money baseSalePrice);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
