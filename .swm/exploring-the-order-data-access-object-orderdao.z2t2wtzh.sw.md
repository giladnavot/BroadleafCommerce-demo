---
title: Exploring the Order Data Access Object (OrderDao)
---
The OrderDao interface in the BroadleafCommerce-demo repository is a Data Access Object (DAO) that provides an abstraction of the underlying data access implementation for the Order domain object. It defines methods for common CRUD operations such as read, save, delete, and create, as well as more specific operations related to orders like reading orders by IDs, reading batch orders, and managing order locks.

The OrderDao interface is implemented by the OrderDaoImpl class. This class provides the actual implementation of the data access methods defined in the OrderDao interface. It uses the EntityManager for interacting with the database and performs operations like querying the database, persisting changes, and managing transactions.

The OrderDao interface is used in various parts of the application, such as the OrderService, LegacyOrderServiceImpl, and OrderState classes. These classes use the OrderDao to perform data access operations related to orders. For example, the OrderService class uses the OrderDao to acquire and release locks on orders.

The OrderDao interface is also used in the configuration of the application. The 'blOrderDao' bean is defined in the application context configuration file and is injected into classes that require access to order data.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDao.java" line="28">

---

# OrderDao Interface

The OrderDao interface provides methods for CRUD operations on Order objects, as well as more specific operations such as reading orders by IDs, reading batch orders, and managing order locks.

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

The OrderDaoImpl class provides the actual implementation of the OrderDao interface. It uses the EntityManager for interacting with the database and performs operations like querying the database, persisting changes, and managing transactions.

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

# Usage of OrderDao

The OrderDao is used in various parts of the application, such as the OrderService class. Here, the OrderDao is used to acquire and release locks on orders.

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

<SwmSnippet path="/core/broadleaf-framework-web/src/main/resources/bl-framework-web-applicationContext.xml" line="58">

---

# Configuration of OrderDao

The 'blOrderDao' bean is defined in the application context configuration file. This bean is an instance of the OrderDao and is injected into classes that require access to order data.

```xml
            <aop:pointcut id="orderRetrievalMethod"
                          expression="execution(* org.broadleafcommerce.core.order.dao.OrderDao.readCartForCustomer(org.broadleafcommerce.profile.core.domain.Customer))"/>
            <aop:around method="processOrderRetrieval" pointcut-ref="orderRetrievalMethod"/>
```

---

</SwmSnippet>

# DAO Functions

In this section, we will discuss the main functions of the DAO in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="85">

---

## readOrderById

The `readOrderById` function is used to read an order by its ID. It creates a query to fetch the order from the database and sets the order ID as a parameter to the query. It also sets hints to make the query cacheable. If the order is not found, it logs a warning message.

```java
    @Override
    public Order readOrderById(final Long orderId) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM OrderImpl o WHERE o.id= :orderId", Order.class);
        query.setParameter("orderId", orderId);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Order");
        Order order = null;
        try {
            order = query.getSingleResult();
        } catch (NoResultException e) {
            LOG.warn(String.format("Could not find order by ID %s", orderId));
        }
        return order;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="214">

---

## save

The `save` function is used to save an order. It uses the `merge` method of the EntityManager to merge the state of the given order into the current persistence context.

```java
    @Override
    public Order save(final Order order) {
        Order response = em.merge(order);
        //em.flush();
        return response;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="302">

---

## submitOrder

The `submitOrder` function is used to submit an order. It sets the status of the order to SUBMITTED and then saves the order.

```java
    @Override
    public Order submitOrder(final Order cartOrder) {
        cartOrder.setStatus(OrderStatus.SUBMITTED);
        return save(cartOrder);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="308">

---

## create

The `create` function is used to create a new order. It uses the `createEntityInstance` method of the EntityConfiguration to create a new instance of the Order class.

```java
    @Override
    public Order create() {
        final Order order = ((Order) entityConfiguration.createEntityInstance("org.broadleafcommerce.core.order.domain.Order"));

        return order;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderDaoImpl.java" line="424">

---

## acquireLock

The `acquireLock` function is used to acquire a lock on an order. It first checks if there is a lock for the order. If there is no lock, it tries to create one. If there is a lock, it tries to update the status from unlocked to locked. If the update is successful, it means that the lock has been acquired.

```java
    @Override
    public boolean acquireLock(Order order) {
        String orderLockKey = getOrderLockKey();
        // First, we'll see if there's a record of a lock for this order
        Query q = em.createNamedQuery("BC_ORDER_LOCK_READ");
        q.setParameter("orderId", order.getId());
        q.setParameter("key", orderLockKey);
        q.setHint(QueryHints.HINT_CACHEABLE, false);
        Long count = (Long) q.getSingleResult();

        if (count == 0L) {
            // If there wasn't a lock, we'll try to create one. It's possible that another thread is attempting the
            // same thing at the same time, so we might get a constraint violation exception here. That's ok. If we 
            // successfully inserted a record, that means that we are the owner of the lock right now.
            try {
                OrderLock ol = (OrderLock) entityConfiguration.createEntityInstance(OrderLock.class.getName());
                ol.setOrderId(order.getId());
                ol.setLocked(true);
                ol.setKey(orderLockKey);
                ol.setLastUpdated(System.currentTimeMillis());
                em.persist(ol);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
