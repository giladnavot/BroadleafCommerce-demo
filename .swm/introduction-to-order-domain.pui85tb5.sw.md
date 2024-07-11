---
title: Introduction to Order Domain
---
Domain in the Order package refers to the set of classes that define the data structure and behavior of Order related entities in the BroadleafCommerce-demo. These classes represent the business objects such as Order, OrderItem, OrderAttribute, etc., and their relationships. They are used to model the e-commerce business domain, encapsulating the business logic and rules associated with order processing.

Each class in the domain package corresponds to a specific entity in the order processing workflow. For instance, the Order class represents an order placed by a customer, the OrderItem class represents an individual item within an order, and the OrderAttribute class represents additional attributes associated with an order.

These domain classes are used throughout the application to manage and manipulate order data. They interact with the service layer, which provides the business logic, and the repository layer, which handles data storage and retrieval.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLock.java" line="22">

---

# OrderLock Interface

The `OrderLock` interface is an example of a domain class. It is used to synchronize operations on `Order` objects. It has methods to get and set the order id, check if the order is locked, get and set the last updated time, and get and set the key used to identify the creator of the lock.

```java
/**
 * Domain object used to synchronize {@link Order} operations.
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface OrderLock extends Serializable {

    /**
     * @return the id of the {@link Order} associated with this OrderLock
     */
    public Long getOrderId();

    /**
     * Sets the id of the {@link Order} associated with this OrderLock
     * 
     * @param orderId
     */
    public void setOrderId(Long orderId);

    /**
     * @return whether or not this OrderLock is currently locked
```

---

</SwmSnippet>

# OrderLock Interface Functions

The OrderLock interface in the Domain provides several key functions that are used to manage and manipulate order data in the BroadleafCommerce-demo.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLock.java" line="22">

---

## OrderLock Interface

The OrderLock interface provides several key functions. `getOrderId` and `setOrderId` are used to get and set the id of the Order associated with this OrderLock. `getLocked` and `setLocked` are used to get the lock state of this OrderLock and set the lock state respectively. `getLastUpdated` and `setLastUpdated` are used to get the last time this lock record was successfully altered and set the time of alteration respectively. `getKey` and `setKey` are used to get the key used to identify the creator of the lock and set a key identifying the creator of the lock respectively.

```java
/**
 * Domain object used to synchronize {@link Order} operations.
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface OrderLock extends Serializable {

    /**
     * @return the id of the {@link Order} associated with this OrderLock
     */
    public Long getOrderId();

    /**
     * Sets the id of the {@link Order} associated with this OrderLock
     * 
     * @param orderId
     */
    public void setOrderId(Long orderId);

    /**
     * @return whether or not this OrderLock is currently locked
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
