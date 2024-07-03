---
title: Getting Started with Order Lock
---
OrderLock is a domain object used to synchronize operations on Order objects in the Broadleaf Commerce framework. It is an interface that extends Serializable, indicating that its instances can be converted into a byte stream and recovered later. The OrderLock interface provides methods to get and set the ID of the associated Order, check if the OrderLock is currently locked, set the lock state, get and set the last time the lock record was successfully altered, and get and set a key used to identify the creator of the lock.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLock.java" line="27">

---

# OrderLock Interface

This is the OrderLock interface. It defines the methods that must be implemented by any class that wishes to act as an OrderLock. These methods allow for the setting and getting of the Order ID, lock state, last updated time, and key.

```java
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
     */
    public Boolean getLocked();

    /**
     * Sets the lock state of this OrderLock
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDao.java" line="92">

---

# Usage of OrderLock

Here is an example of how OrderLock is used. In the OrderDao class, the lock is attempted to be acquired before performing operations on an Order. If the lock cannot be acquired, it means another operation is currently modifying the Order.

```java
     * This method will attempt to update the {@link OrderLock} object table for the given order to mark it as
     * locked, provided the OrderLock record for the given order was not already locked. It will return true or
     * false depending on whether or not the lock was able to be acquired.
```

---

</SwmSnippet>

# OrderLock Interface Functions

The OrderLock interface provides several methods to manage the lock state of an order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLock.java" line="27">

---

## OrderLock Interface

The OrderLock interface provides methods to manage the lock state of an order. It includes methods to get and set the order ID (`getOrderId`, `setOrderId`), check if the order is locked and set the lock state (`getLocked`, `setLocked`), get and set the last updated time (`getLastUpdated`, `setLastUpdated`), and get and set the key used to identify the creator of the lock (`getKey`, `setKey`).

```java
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
     */
    public Boolean getLocked();

    /**
     * Sets the lock state of this OrderLock
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
