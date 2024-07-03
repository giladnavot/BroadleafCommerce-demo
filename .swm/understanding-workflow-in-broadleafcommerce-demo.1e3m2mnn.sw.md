---
title: Understanding Workflow in BroadleafCommerce-demo
---
Workflow in BroadleafCommerce-demo refers to the sequence of processes through which a piece of work passes from initiation to completion. It is implemented using the `CartOperationRequest` class, which represents the basic context necessary for the execution of a particular order process workflow operation. This class is used in various activities such as adding, updating, and removing items from the cart, validating requests, and pricing orders. The `ProcessContext` interface is used to hold the state of an executing workflow and provides access to the seed data, which is an instance of `CartOperationRequest`. The `BaseActivity` class is the base class for all workflow activities and provides the default behavior for state transitions.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="33">

---

# CartOperationRequest

CartOperationRequest is a class that represents the context for executing an order process workflow operation. It contains the details of the operation such as the order, the item request, and flags to indicate whether the order should be priced. It also contains transient state that is set during the course of the workflow for use in subsequent workflow steps.

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

# Workflow Activities

AddWorkflowPriceOrderIfNecessaryActivity is an example of a workflow activity. It extends BaseActivity and overrides the execute method. The execute method is where the operation of the activity is performed. In this case, the activity checks if the order needs to be priced and if so, it prices the order.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="688">

---

# Workflow Execution

The workflow is executed by creating an instance of CartOperationRequest and passing it to the workflow engine. The workflow engine then executes the activities in the order they are defined. After the workflow is executed, the updated order is saved.

```java
            CartOperationRequest cartOpRequest = new CartOperationRequest(findOrderById(orderId), orderItemRequestDTO, currentAddition == numAdditionRequests);

            Session session = em.unwrap(Session.class);
            FlushMode current = session.getHibernateFlushMode();
            if (!autoFlushAddToCart) {
                //Performance measure. Hibernate will sometimes perform an autoflush when performing query operations and this can
                //be expensive. It is possible to avoid the autoflush if there's no concern about queries in the flow returning
                //incorrect results because something has not been flushed to the database yet.
                session.setHibernateFlushMode(FlushMode.MANUAL);
            }
            ProcessContext<CartOperationRequest> context;
            try {
                context = (ProcessContext<CartOperationRequest>) addItemWorkflow.doActivities(cartOpRequest);
            } finally {
```

---

</SwmSnippet>

# Workflow Functions

In this section, we will delve into the key functions of the Workflow: execute, getSeedData, and setSeedData.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/AddWorkflowVerifyFulfillmentGroupItemsActivity.java" line="39">

---

## Execute Function

The `execute` function is a key part of the Workflow. It is responsible for executing a specific workflow activity. In the context of the AddWorkflowVerifyFulfillmentGroupItemsActivity class, the `execute` function verifies the items in the fulfillment group during the addition of an item to the cart.

```java
    @Override
    public ProcessContext<CartOperationRequest> execute(ProcessContext<CartOperationRequest> context) throws Exception {
        CartOperationRequest request = context.getSeedData();
        
        request = fgItemStrategy.verify(request);
        
        context.setSeedData(request);
        return context;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="49">

---

## GetSeedData Function

The `getSeedData` function is used to retrieve the seed data from the context. This seed data is typically the initial input or state for the workflow process.

```java
    public T getSeedData() {
        return seedData;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="53">

---

## SetSeedData Function

The `setSeedData` function is used to set the seed data in the context. This is typically used to update the state of the workflow process.

```java
    public void setSeedData(T seedObject) {
        seedData = (T) seedObject;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
