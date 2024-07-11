---
title: Overview of Order Service
---
In BroadleafCommerce-demo, a Service refers to a set of business operations that are performed by the application. These operations are defined in interfaces and implemented in classes. For example, the `OrderService` interface defines operations related to handling orders in the e-commerce application, such as creating a new cart for a customer, finding an order by its ID, or confirming an order. The `OrderServiceImpl` class provides the implementation for these operations.

The `OrderService` interface is used in various parts of the application, such as the `OfferServiceImpl` and `CheckoutServiceImpl` classes. This shows that the operations defined in the `OrderService` interface are essential for the functioning of the e-commerce application.

The `OrderServiceImpl` class uses other services like `OrderItemService` and `FulfillmentGroupService` to perform its operations. This shows that services in BroadleafCommerce-demo are designed to work together, each handling a specific part of the business logic.

The `OrderServiceImpl` class is annotated with `@Service("blOrderService")`, which indicates that it is a Spring service. This means that it can be automatically detected by Spring and used for dependency injection. This makes it easier to manage and test the application.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="47">

---

# OrderService Interface

The `OrderService` interface defines the operations related to handling orders in the e-commerce application. These include operations for creating a new cart for a customer, finding an order by its ID, and confirming an order.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="102">

---

# OrderServiceImpl Class

The `OrderServiceImpl` class provides the implementation for the operations defined in the `OrderService` interface. It uses other services like `OrderItemService` and `FulfillmentGroupService` to perform its operations. This shows that services in BroadleafCommerce-demo are designed to work together, each handling a specific part of the business logic.

```java
@Service("blOrderService")
@ManagedResource(objectName="org.broadleafcommerce:name=OrderService", description="Order Service", currencyTimeLimit=15)
public class OrderServiceImpl implements OrderService {
    private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);

    /* DAOs */
    @Resource(name = "blOrderPaymentDao")
    protected OrderPaymentDao paymentDao;
    
    @Resource(name = "blOrderDao")
    protected OrderDao orderDao;
    
    @Resource(name = "blOfferDao")
    protected OfferDao offerDao;

    /* Factories */
    @Resource(name = "blNullOrderFactory")
    protected NullOrderFactory nullOrderFactory;
    
    /* Services */
    @Resource(name = "blCustomerService")
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/CheckoutServiceImpl.java" line="52">

---

# Usage of OrderService

The `OrderService` is used in various parts of the application, such as the `CheckoutServiceImpl` class. This shows that the operations defined in the `OrderService` interface are essential for the functioning of the e-commerce application.

```java
    @Resource(name="blOrderService")
    protected OrderService orderService;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
