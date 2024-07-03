---
title: Exploring Cart Operations
---
Cart Operations in Broadleaf Commerce refer to the actions performed on a shopping cart, such as adding, updating, or removing items. These operations are encapsulated in the `CartOperationRequest` class, which provides the necessary context for executing these operations. This class contains information about the order, the item request, and whether the order needs to be priced. It also maintains state during the workflow for use in subsequent steps, such as the order item and the quantity delta of the order item. The `CartOperationRequest` is used extensively across the codebase, in classes like `AddWorkflowPriceOrderIfNecessaryActivity`, `ValidateAddRequestActivity`, and `FulfillmentGroupItemStrategy`, among others.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="33">

---

# CartOperationRequest Class

The `CartOperationRequest` class is the main class used for Cart Operations. It contains fields such as `itemRequest`, `order`, `priceOrder`, and `orderItem`, which are used to represent and manipulate the items within a user's shopping cart.

```java
/**
 * This class represents the basic context necessary for the execution
 * of a particular order process workflow operation.
 * 
 * @author apazzolini
 */
public class CartOperationRequest {

    protected OrderItemRequestDTO itemRequest;
    
    protected Order order;
    
    protected boolean priceOrder;
    
    // Set during the course of the workflow for use in subsequent workflow steps
    protected OrderItem orderItem;
    
    // Set during the course of the workflow for use in subsequent workflow steps
    protected Integer orderItemQuantityDelta;
    
    protected List<Long[]> multishipOptionsToDelete = new ArrayList<Long[]>();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/AddWorkflowPriceOrderIfNecessaryActivity.java" line="54">

---

# CartOperationRequest Usage

`CartOperationRequest` is used in the `AddWorkflowPriceOrderIfNecessaryActivity` class to execute the necessary operations when adding an item to the cart.

```java
@Component("blAddWorkflowPriceOrderIfNecessaryActivity")
public class AddWorkflowPriceOrderIfNecessaryActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {

    public static final int ORDER = 5000;

    @Resource(name = "blOrderService")
    protected OrderService orderService;

    @Resource(name = "blOrderItemService")
    protected OrderItemService orderItemService;

    @Resource(name = "blFulfillmentGroupItemDao")
    protected FulfillmentGroupItemDao fgItemDao;

    @Resource(name = "blOrderMultishipOptionService")
    protected OrderMultishipOptionService orderMultishipOptionService;

    public AddWorkflowPriceOrderIfNecessaryActivity() {
        setOrder(ORDER);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/strategy/FulfillmentGroupItemStrategy.java" line="20">

---

# CartOperationRequest in FulfillmentGroupItemStrategy

`CartOperationRequest` is also used in the `FulfillmentGroupItemStrategy` class, where it is used in methods such as `onItemAdded`, `onItemUpdated`, `onItemRemoved`, and `verify` to manage the items within a user's shopping cart.

```java
import org.broadleafcommerce.core.order.service.workflow.CartOperationRequest;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;

/**
 * The methods in this class are invoked by the add and update item to cart workflows.
 * Broadleaf provides two implementations, the default FulfillmentGroupItemStrategyImpl 
 * and also a strategy that does nothing to FulifllmentGroupItems, which can be configured
 * by injecting the NullFulfillmentGroupItemStrategyImpl class as the "blFulfillmentGroupItemStrategy"
 * bean.
 * 
 * The null strategy would be the approach taken prior to 2.0, where the user was required
 * to manage FulfillmentGroups and FulfillmentGroupItems by themselves. However, the new default
 * implmentation takes care of this for you by ensuring that FG Items and OrderItems stay in sync.
 * 
 * Note that even the null strategy <b>WILL</b> remove FulfillmentGroupItems if their corresponding
 * OrderItem is removed to prevent orphaned records.
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface FulfillmentGroupItemStrategy {

```

---

</SwmSnippet>

# CartOperationRequest Class Functions

Let's take a closer look at the CartOperationRequest class and its key functions.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="57">

---

## CartOperationRequest Constructor

The CartOperationRequest constructor is used to create a new instance of the CartOperationRequest class. It takes in an Order object, an OrderItemRequestDTO object, and a boolean value for priceOrder. The constructor sets the order, item request, and price order for the CartOperationRequest object.

```java
    public CartOperationRequest(Order order, OrderItemRequestDTO itemRequest, boolean priceOrder) {
        setOrder(order);
        sortAllDescendantChildItems(itemRequest);
        setItemRequest(itemRequest);
        setPriceOrder(priceOrder);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="95">

---

## setOrder Function

The setOrder function is used to set the Order object for the CartOperationRequest. This Order object represents the order that the cart operation is being performed on.

```java
    public void setOrder(Order order) {
        this.order = order;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="111">

---

## setOrderItem Function

The setOrderItem function is used to set the OrderItem object for the CartOperationRequest. This OrderItem object represents the item that the cart operation is being performed on.

```java
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="87">

---

## setItemRequest Function

The setItemRequest function is used to set the OrderItemRequestDTO object for the CartOperationRequest. This OrderItemRequestDTO object contains the details of the item request for the cart operation.

```java
    public void setItemRequest(OrderItemRequestDTO itemRequest) {
        this.itemRequest = itemRequest;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
