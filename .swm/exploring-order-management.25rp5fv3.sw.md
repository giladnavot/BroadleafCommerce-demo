---
title: Exploring Order Management
---
In the BroadleafCommerce-demo repository, 'Order' refers to a key entity that represents a customer's order in the e-commerce system. It is associated with the 'OrderItem' interface, which defines the structure and behavior of an item in an order. An OrderItem has properties like id, retail price, sale price, and it belongs to an order.

The 'OrderService' interface provides methods for interacting with and manipulating 'Order' objects. It includes methods for creating new orders, finding orders by various parameters, and updating the state of an order.

The 'OrderAttribute' interface allows for arbitrary data to be persisted with the order. It has methods to set and get attributes like name, value, and the associated order.

The 'OrderItem' interface has a method 'setOrder(Order order)' which sets the order for the order item. Similarly, the 'OrderAttribute' interface has a method 'setOrder(Order order)' to set the associated order.

The 'OrderService' interface provides methods 'findOrderById(Long orderId)' and 'findOrderByOrderNumber(String orderNumber)' to look up an Order by its database id and order number respectively.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/Order.java" line="45">

---

# Order Interface

The 'Order' interface defines the structure and behavior of an order in the e-commerce system. It includes methods to get and set various properties of an order, such as id, name, customer, status, order items, and more. It also provides methods to calculate the subtotal, total, and other price-related information of the order.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderItem.java" line="32">

---

# OrderItem Interface

The 'OrderItem' interface defines the structure and behavior of an item in an order. It includes methods to get and set various properties of an order item, such as id, retail price, sale price, and the associated order. It also provides methods to calculate the total price and adjustments of the order item.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="47">

---

# OrderService Interface

The 'OrderService' interface provides methods for interacting with and manipulating 'Order' objects. It includes methods for creating new orders, finding orders by various parameters, and updating the state of an order.

```java
/**
 * The general interface for interacting with shopping carts and completed Orders.
 * In Broadleaf Commerce, a Cart and an Order are the same thing. A "cart" becomes 
 * an order after it has been submitted.
 *
 * Most of the methods in this order are used to modify the cart. However, it is also
 * common to use this service for "named" orders (aka wishlists).
 */
public interface OrderService {

    /**
     * Creates a new Order for the given customer. Generally, you will want to use the customer
     * that is on the current request, which can be grabbed by utilizing the CustomerState 
     * utility class.
     * 
     * The default Broadleaf implementation of this method will provision a new Order in the 
     * database and set the current customer as the owner of the order. If the customer has an
     * email address associated with their profile, that will be copied as well. If the customer
     * is a new, anonymous customer, his username will be set to his database id.
     * 
     * @see org.broadleafcommerce.profile.web.core.CustomerState#getCustomer()
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
