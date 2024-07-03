---
title: Introduction to Legacy Services
---
Legacy Services in the BroadleafCommerce-demo repository refer to older versions of services that have been deprecated but are still part of the codebase for backward compatibility. These services are primarily found in the `org.broadleafcommerce.core.order.service.legacy` package. They include `LegacyOrderService` and `LegacyCartService`, which extend the functionalities of the OrderService and provide methods for manipulating orders and carts. However, as these are deprecated, it's recommended to use the newer versions of these services for any new development.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderService.java" line="48">

---

## LegacyOrderService

The `LegacyOrderService` interface extends `OrderService` and provides several methods for managing orders. It is marked as deprecated, indicating that it should not be used for new development.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyCartService.java" line="34">

---

## LegacyCartService

The `LegacyCartService` interface extends `LegacyOrderService` and provides additional methods specifically for managing shopping carts. Like `LegacyOrderService`, it is also marked as deprecated.

```java
@Deprecated
public interface LegacyCartService extends LegacyOrderService {

    Order addAllItemsToCartFromNamedOrder(Order namedOrder) throws PricingException;
    
    Order addAllItemsToCartFromNamedOrder(Order namedOrder, boolean priceOrder) throws PricingException;

    OrderItem moveItemToCartFromNamedOrder(Order order, OrderItem orderItem) throws PricingException;
    
    OrderItem moveItemToCartFromNamedOrder(Order order, OrderItem orderItem, boolean priceOrder) throws PricingException;

    OrderItem moveItemToCartFromNamedOrder(Long customerId, String orderName, Long orderItemId, Integer quantity) throws PricingException;
    
    OrderItem moveItemToCartFromNamedOrder(Long customerId, String orderName, Long orderItemId, Integer quantity, boolean priceOrder) throws PricingException;

    Order moveAllItemsToCartFromNamedOrder(Order namedOrder) throws PricingException;
    
    Order moveAllItemsToCartFromNamedOrder(Order namedOrder, boolean priceOrder) throws PricingException;

    /**
     * Merge the anonymous cart with the customer's cart taking into
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderServiceImpl.java" line="78">

---

## LegacyOrderServiceImpl

The `LegacyOrderServiceImpl` class provides an implementation of the `LegacyOrderService` interface. It extends `OrderServiceImpl` and overrides several methods to provide the legacy functionality.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyCartServiceImpl.java" line="48">

---

## LegacyCartServiceImpl

The `LegacyCartServiceImpl` class provides an implementation of the `LegacyCartService` interface. It extends `LegacyOrderServiceImpl` and adds additional methods for managing shopping carts.

```java
@Deprecated
public class LegacyCartServiceImpl extends LegacyOrderServiceImpl implements LegacyCartService {

    @Resource(name="blCustomerService")
    protected CustomerService customerService;

    public Order addAllItemsToCartFromNamedOrder(Order namedOrder) throws PricingException {
        return addAllItemsToCartFromNamedOrder(namedOrder, true);
    }

    public Order addAllItemsToCartFromNamedOrder(Order namedOrder, boolean priceOrder) throws PricingException {
        Order cartOrder = orderDao.readCartForCustomer(namedOrder.getCustomer());
        if (cartOrder == null) {
            cartOrder = createNewCartForCustomer(namedOrder.getCustomer());
        }
        List<OrderItem> items = new ArrayList<OrderItem>(namedOrder.getOrderItems());
        for (int i = 0; i < items.size(); i++) {
            OrderItem orderItem = items.get(i);

            // only run pricing routines on the last item.
            boolean shouldPriceOrder = (priceOrder && (i == items.size() -1));
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
