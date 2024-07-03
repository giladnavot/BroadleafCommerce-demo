---
title: Understanding the Order Service
---
The Order Service in Broadleaf Commerce is a key component that handles interactions with shopping carts and completed orders. It provides methods for creating new orders for customers, finding orders by various parameters, and managing the status of orders. The service also supports operations for 'named' orders, commonly used for wishlists. The Order Service is designed to work with other components such as the Order Item Service, which manages individual items within an order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="55">

---

# Order Service Interface

This is the Order Service interface. It declares a set of methods that provide functionalities to interact with orders. These methods include creating new orders, finding orders by various parameters, and managing 'named' orders or wishlists.

```java
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
     * 
     * @param customer
     * @return the newly created order
     */
    public Order createNewCartForCustomer(Customer customer);

    /**
     * Creates a new Order for the given customer with the given name. Typically, this represents
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="102">

---

# Order Service Implementation

This is the implementation of the Order Service interface. It provides the actual logic for the methods declared in the interface. It uses various resources like OrderDao, CustomerService, PricingService, and others to perform the operations.

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

# Usage of Order Service

This is an example of how the Order Service is used in the codebase. Here, the Order Service is injected as a resource and used in the Checkout Service to manage the checkout process.

```java
    @Resource(name="blOrderService")
    protected OrderService orderService;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
