---
title: Understanding Order Management
---
Order Management in BroadleafCommerce-demo refers to the functionality that allows the handling of customer orders in the system. It involves creating new orders, modifying existing orders, and managing the status of orders. This is facilitated by the `OrderService` interface and its implementations, which provide methods for interacting with shopping carts and completed orders. The `OrderService` also provides methods for creating new orders for customers, finding orders by various parameters, and cancelling orders. The `LegacyOrderServiceImpl` class is a deprecated implementation of the `OrderService` interface, which should no longer be used.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="55">

---

# OrderService Interface

The OrderService interface is the primary interface for managing orders in the BroadleafCommerce-demo. It provides methods for creating new orders, retrieving existing orders, and managing order items.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderService.java" line="48">

---

# LegacyOrderService Interface

The LegacyOrderService interface extends the OrderService interface and provides additional methods for managing orders. However, it is marked as deprecated and should not be used in new implementations.

```java
@Deprecated
public interface LegacyOrderService extends OrderService {

    public FulfillmentGroup findDefaultFulfillmentGroupForOrder(Order order);

    /**
     * Note: This method will automatically associate the given <b>order</b> to the given <b>itemRequest</b> such that
     * then resulting {@link OrderItem} will already have an {@link Order} associated to it.
     * 
     * @param order
     * @param itemRequest
     * @return
     * @throws PricingException
     */
    public OrderItem addGiftWrapItemToOrder(Order order, GiftWrapOrderItemRequest itemRequest) throws PricingException;

    /**
     * Used to create dynamic bundles groupings of order items.
     * Typically not used with ProductBundles which should instead
     * call addProductToOrder.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="102">

---

# OrderServiceImpl Class

The OrderServiceImpl class is an implementation of the OrderService interface. It provides the actual logic for the methods declared in the OrderService interface.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderServiceImpl.java" line="78">

---

# LegacyOrderServiceImpl Class

The LegacyOrderServiceImpl class is an implementation of the LegacyOrderService interface. It provides the actual logic for the methods declared in the LegacyOrderService interface. However, it is marked as deprecated and should not be used in new implementations.

```java
@Deprecated
public class LegacyOrderServiceImpl extends OrderServiceImpl implements LegacyOrderService {

    private static final Log LOG = LogFactory.getLog(LegacyOrderServiceImpl.class);

    @Resource(name = "blFulfillmentGroupDao")
    protected FulfillmentGroupDao fulfillmentGroupDao;

    @Resource(name = "blFulfillmentGroupItemDao")
    protected FulfillmentGroupItemDao fulfillmentGroupItemDao;

    @Resource(name = "blOrderItemService")
    protected OrderItemService orderItemService;

    @Resource(name = "blOrderItemDao")
    protected OrderItemDao orderItemDao;

    @Resource(name = "blSkuDao")
    protected SkuDao skuDao;

    @Resource(name = "blProductDao")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
