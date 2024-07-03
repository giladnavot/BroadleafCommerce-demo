---
title: Overview of Order Multiship Option
---
The OrderMultishipOption represents a set of options for an OrderItem in an Order in the multiship context. It is used to store current multiship settings for an Order without having to generate the necessary FulfillmentGroups and FulfillmentGroupItems. It can also be used to re-create the multiship set should the Order change. The OrderMultishipOption is associated with an Order, an OrderItem, an Address, and a FulfillmentOption.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderMultishipOption.java" line="23">

---

# OrderMultishipOption Interface

This is the OrderMultishipOption interface. It provides methods to set and get the associated Order, OrderItem, Address, and FulfillmentOption. It is used to represent a set of options for an OrderItem in an Order in the multiship context.

```java
/**
 * Represents a given set of options for an OrderItem in an Order in the 
 * multiship context. This class is used to store current multiship settings
 * for an Order without having to generate the necessary FulfillmentGroups and
 * FulfillmentGroupItems. It also can be used to re-create the multiship set
 * should the Order change
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface OrderMultishipOption extends MultiTenantCloneable<OrderMultishipOption>{

    /**
     * Returns the internal id of this OrderMultishipOption
     * 
     * @return the internal id
     */
    public Long getId();

    /**
     * Sets the internal id of this OrderMultishipOption
     * 
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderMultishipOptionServiceImpl.java" line="59">

---

# Usage of OrderMultishipOption

This is an example of how the OrderMultishipOption interface is used. The OrderMultishipOptionServiceImpl class provides methods to save an OrderMultishipOption, find OrderMultishipOptions for a given order, and create a new OrderMultishipOption.

```java
    @Override
    public OrderMultishipOption save(OrderMultishipOption orderMultishipOption) {
        return orderMultishipOptionDao.save(orderMultishipOption);
    }

    @Override
    public List<OrderMultishipOption> findOrderMultishipOptions(Long orderId) {
        return orderMultishipOptionDao.readOrderMultishipOptions(orderId);
    }
    
    @Override
    public List<OrderMultishipOption> findOrderItemOrderMultishipOptions(Long orderItemId) {
        return orderMultishipOptionDao.readOrderItemOrderMultishipOptions(orderItemId);
```

---

</SwmSnippet>

# OrderMultishipOption Interface

The OrderMultishipOption interface provides several methods to manage the multiship options of an order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderMultishipOption.java" line="32">

---

## OrderMultishipOption Interface

The OrderMultishipOption interface provides methods to get and set the internal id, associated Order, associated OrderItem, associated Address, and associated FulfillmentOption. Each of these methods plays a crucial role in managing the multiship options of an order.

```java
public interface OrderMultishipOption extends MultiTenantCloneable<OrderMultishipOption>{

    /**
     * Returns the internal id of this OrderMultishipOption
     * 
     * @return the internal id
     */
    public Long getId();

    /**
     * Sets the internal id of this OrderMultishipOption
     * 
     * @param id the internal id
     */
    public void setId(Long id);

    /**
     * Returns the Order associated with this OrderMultishipOption
     * 
     * @return the associated Order
     */
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
