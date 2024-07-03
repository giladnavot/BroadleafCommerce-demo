---
title: Understanding Order DAO
---
The Order Data Access Object (DAO) is an interface that provides methods for interacting with Order data in the database. It provides methods to read, save, delete, and submit orders. It also provides methods to read orders by various parameters such as by ID, by external ID, by customer, by order number, and by date range. The Order DAO is used in various services throughout the application to perform operations related to orders.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDao.java" line="28">

---

# OrderDao Interface

The `OrderDao` interface defines the contract for an Order DAO with methods for reading, saving, deleting, and submitting orders. These methods are implemented in the `OrderDaoImpl` class.

```java
public interface OrderDao {

    Order readOrderById(Long orderId);

    Order readOrderByIdIgnoreCache(Long orderId);
    
    List<Order> readOrdersByIds(List<Long> orderIds);

    /**
     * Reads a batch list of orders from the DB.  The status is optional and can be null.  If no status 
     * is provided, then all order will be read.  Otherwise, only orders with that status will be read.
     * @param start
     * @param pageSize
     * @param statuses
     * @return
     */
    List<Order> readBatchOrders(int start, int pageSize, List<OrderStatus> statuses);

    /**
     * Reads a batch list of orders from the DB starting at the ID passed in.  The lookup sorts by ID so the proper
     * starting point will be used.  The status is optional and can be null.  If no status is provided, then all order
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="67">

---

# OrderDao Implementation

The `OrderDaoImpl` class provides the implementation of the `OrderDao` interface. It uses the `EntityManager` for interacting with the database. The `@Repository` annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.

```java
@Repository("blOrderDao")
public class OrderDaoImpl implements OrderDao {

    private static final Log LOG = LogFactory.getLog(OrderDaoImpl.class);
    private static final String ORDER_LOCK_KEY = UUID.randomUUID().toString();

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Resource(name = "blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Resource(name = "blOrderDaoExtensionManager")
    protected OrderDaoExtensionManager extensionManager;

    @Resource(name = "blStreamingTransactionCapableUtil")
    protected StreamingTransactionCapableUtil transUtil;

    @Override
    public Order readOrderById(final Long orderId) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM OrderImpl o WHERE o.id= :orderId", Order.class);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="599">

---

# Using OrderDao

The `OrderDao` is used in the `OrderService` class. The service class calls the methods defined in the DAO to perform operations on the Order objects. This is an example of how the DAO is used in the service layer of the application.

```java
    /**
     * @see OrderDao#acquireLock(Order)
     * @param order
     * @return whether or not the lock was acquired
     */
    public boolean acquireLock(Order order);

    /**
     * @see OrderDao#releaseLock(Order)
     * @param order
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
