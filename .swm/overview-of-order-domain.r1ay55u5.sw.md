---
title: Overview of Order Domain
---
The Order Domain in BroadleafCommerce-demo refers to a set of classes and interfaces that define the structure and behavior of orders in the e-commerce framework. This includes entities like Order, OrderItem, and OrderAttribute among others. These classes are part of the org.broadleafcommerce.core.order.domain package and are crucial for managing the lifecycle of an order, from creation to fulfillment.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="18">

---

# Order Domain Structure

The `Order` class is the central class in the Order Domain. It represents an order in the system and contains various methods to manipulate and retrieve order data.

```java
package org.broadleafcommerce.core.order.domain;

import org.broadleafcommerce.common.audit.Auditable;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLock.java" line="27">

---

# OrderLock Interface

The `OrderLock` interface is used to synchronize operations on orders. It provides methods to get and set the order id, check if the order is locked, and manage the lock state.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderLockImpl.java" line="36">

---

# OrderLock Implementation

`OrderLockImpl` is the implementation of the `OrderLock` interface. It is used to create actual lock objects.

```java
public class OrderLockImpl implements OrderLock {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="438">

---

# Using OrderLock

Here is an example of how `OrderLock` is used. An instance of `OrderLock` is created and the order id is set using the `setOrderId` method.

```java
            try {
                OrderLock ol = (OrderLock) entityConfiguration.createEntityInstance(OrderLock.class.getName());
                ol.setOrderId(order.getId());
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
