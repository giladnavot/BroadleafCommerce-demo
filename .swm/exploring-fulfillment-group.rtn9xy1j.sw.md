---
title: Exploring Fulfillment Group
---
A Fulfillment Group in Broadleaf Commerce refers to the main entity used to hold fulfillment information about an Order. An Order can have multiple Fulfillment Groups, allowing for items to be shipped to multiple addresses and fulfilled in different ways, such as shipping some items overnight or delivering some with digital download. This means that a Fulfillment Group is unique based on an Address and Fulfillment Option combination. In simpler cases where Orders are being delivered to a single Address and in a single way, there will be only one Fulfillment Group for that Order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="50">

---

## Using FulfillmentGroup

The `getOrder` and `setOrder` methods are used to associate a FulfillmentGroup with an Order.

```java
    public Order getOrder();

    public void setOrder(Order order);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="79">

---

The `getFulfillmentGroupItems` and `addFulfillmentGroupItem` methods are used to manage the items within a FulfillmentGroup.

```java
    public List<FulfillmentGroupItem> getFulfillmentGroupItems();

    public void setFulfillmentGroupItems(List<FulfillmentGroupItem> fulfillmentGroupItems);

    public void addFulfillmentGroupItem(FulfillmentGroupItem fulfillmentGroupItem);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="58">

---

The `getFulfillmentOption` and `setFulfillmentOption` methods are used to manage the fulfillment option of a FulfillmentGroup.

```java
    public FulfillmentOption getFulfillmentOption();

    public void setFulfillmentOption(FulfillmentOption fulfillmentOption);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="132">

---

The `getFulfillmentPrice` method is used to get the total price to charge for the FulfillmentGroup, including any adjustments such as promotions.

```java
    public Money getFulfillmentPrice();
```

---

</SwmSnippet>

# FulfillmentGroup Interface

The FulfillmentGroup interface provides several methods that allow manipulation and retrieval of key information related to an Order's fulfillment. These include methods for handling the Order, FulfillmentOption, Address, FulfillmentGroupItems, and pricing details.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="44">

---

## FulfillmentGroup Interface

The FulfillmentGroup interface provides methods to handle various aspects of an Order's fulfillment. These include methods for setting and getting the ID of the FulfillmentGroup (`getId`, `setId`), handling the associated Order (`getOrder`, `setOrder`), managing the FulfillmentOption (`getFulfillmentOption`, `setFulfillmentOption`), managing the Address (`getAddress`, `setAddress`), handling the phone details (`getPhone`, `setPhone`), managing the FulfillmentGroupItems (`getFulfillmentGroupItems`, `setFulfillmentGroupItems`, `addFulfillmentGroupItem`), handling the pricing details (`getRetailFulfillmentPrice`, `setRetailFulfillmentPrice`, `getFulfillmentPrice`, `setFulfillmentPrice`), managing the type of fulfillment (`getType`, `setType`), managing the status of the FulfillmentGroup (`getStatus`, `setStatus`), and handling the total cost (`getTotal`, `setTotal`).

```java
public interface FulfillmentGroup extends Serializable, MultiTenantCloneable<FulfillmentGroup> {

    public Long getId();

    public void setId(Long id);

    public Order getOrder();

    public void setOrder(Order order);
    
    public void setSequence(Integer sequence);

    public Integer getSequence();

    public FulfillmentOption getFulfillmentOption();

    public void setFulfillmentOption(FulfillmentOption fulfillmentOption);

    public Address getAddress();

    public void setAddress(Address address);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
