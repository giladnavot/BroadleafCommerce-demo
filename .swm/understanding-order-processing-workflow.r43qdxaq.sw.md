---
title: Understanding Order Processing Workflow
---
Workflow in the BroadleafCommerce-demo refers to the sequence of processes through which a piece of work passes from initiation to completion. In the context of the Service layer, it specifically refers to the order processing workflow.

The order processing workflow is implemented using the `CartOperationRequest` class and various activities that extend `BaseActivity`. The `CartOperationRequest` class represents the basic context necessary for the execution of a particular order process workflow operation.

The workflow is designed to handle different operations such as adding, updating, and removing items from the cart, as well as validating requests and pricing orders. Each operation is handled by a specific activity class, such as `AddOrderItemActivity`, `UpdateOrderItemActivity`, `RemoveOrderItemActivity`, `ValidateAddRequestActivity`, and `AddWorkflowPriceOrderIfNecessaryActivity`.

These activities are orchestrated by the `CartOperationProcessContextFactory` class, which creates a `ProcessContext` for each `CartOperationRequest`. The `ProcessContext` is then passed through the workflow, with each activity performing its specific operation.

The workflow also involves strategies, represented by the `FulfillmentGroupItemStrategy` interface, which define how fulfillment group items are handled during the workflow. These strategies are implemented by classes like `FulfillmentGroupItemStrategyImpl` and `NullFulfillmentGroupItemStrategyImpl`.

In summary, the workflow in the Service layer of the BroadleafCommerce-demo is a well-structured process that ensures efficient and accurate order processing.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationRequest.java" line="33">

---

# CartOperationRequest

The `CartOperationRequest` class represents the basic context necessary for the execution of a particular order process workflow operation. It contains information about the order, the item request, and other details necessary for processing the order.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/AddWorkflowPriceOrderIfNecessaryActivity.java" line="44">

---

# Workflow Activities

Each operation in the workflow is handled by a specific activity class. For example, the `AddWorkflowPriceOrderIfNecessaryActivity` class handles the operation of adding the price to the order if necessary. It extends the `BaseActivity` class and overrides the `execute` method to implement the specific operation.

```java
/**
 * As of Broadleaf version 3.1.0, saves of individual aspects of an Order (such as OrderItems and FulfillmentGroupItems) no
 * longer happen in their respective activities. Instead, we will now handle these saves in this activity exclusively.
 *
 * This provides the ability for an implementation to not require a transactional wrapper around the entire workflow and
 * instead only requires it around this particular activity. This is only recommended if there are long running steps in
 * the workflow, such as an external service call to check availability.
 *
 * @author Andre Azzolini (apazzolini)
 */
@Component("blAddWorkflowPriceOrderIfNecessaryActivity")
public class AddWorkflowPriceOrderIfNecessaryActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {

    public static final int ORDER = 5000;

    @Resource(name = "blOrderService")
    protected OrderService orderService;

    @Resource(name = "blOrderItemService")
    protected OrderItemService orderItemService;

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/CartOperationProcessContextFactory.java" line="32">

---

# Workflow Execution

The `CartOperationProcessContextFactory` class orchestrates the execution of the workflow. It creates a `ProcessContext` for each `CartOperationRequest` and passes it through the workflow.

```java
public class CartOperationProcessContextFactory implements ProcessContextFactory<CartOperationRequest, CartOperationRequest> {

    /**
     * Creates the necessary context for cart operations
     */
    public ProcessContext<CartOperationRequest> createContext(CartOperationRequest seedData) throws WorkflowException {
        ProcessContext<CartOperationRequest> context = new DefaultProcessContextImpl<>();
        context.setSeedData(seedData);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/strategy/FulfillmentGroupItemStrategy.java" line="41">

---

# FulfillmentGroupItemStrategy

The workflow also involves strategies, represented by the `FulfillmentGroupItemStrategy` interface, which define how fulfillment group items are handled during the workflow. These strategies are implemented by classes like `FulfillmentGroupItemStrategyImpl` and `NullFulfillmentGroupItemStrategyImpl`.

```java
    public CartOperationRequest onItemAdded(CartOperationRequest request) throws PricingException;

    public CartOperationRequest onItemUpdated(CartOperationRequest request) throws PricingException;
    
    public CartOperationRequest onItemRemoved(CartOperationRequest request) throws PricingException;
    
    public CartOperationRequest verify(CartOperationRequest request) throws PricingException;
```

---

</SwmSnippet>

# Order Processing Workflow

This section provides an overview of the main functions involved in the order processing workflow in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/add/AddOrderItemActivity.java" line="34">

---

## AddOrderItemActivity

The `AddOrderItemActivity` class is responsible for adding an item to the order. It extends `BaseActivity` and overrides the `execute` method to perform the addition operation.

```java
@Component("blAddOrderItemActivity")
public class AddOrderItemActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {
    
    public static final int ORDER = 3000;
    
    @Resource(name = "blOrderService")
    protected OrderService orderService;
    
    @Resource(name = "blOrderItemService")
    protected OrderItemService orderItemService;
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Resource(name = "blGenericEntityDao")
    protected GenericEntityDao genericEntityDao;
    
    public AddOrderItemActivity() {
        setOrder(ORDER);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/update/UpdateOrderItemActivity.java" line="34">

---

## UpdateOrderItemActivity

The `UpdateOrderItemActivity` class is responsible for updating an item in the order. It extends `BaseActivity` and overrides the `execute` method to perform the update operation.

```java
public class UpdateOrderItemActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {

    public static final int ORDER = 3000;
    
    @Resource(name = "blOrderService")
    protected OrderService orderService;
    
    public UpdateOrderItemActivity() {
        setOrder(ORDER);
    }
    
    @Override
    public ProcessContext<CartOperationRequest> execute(ProcessContext<CartOperationRequest> context) throws Exception {
        CartOperationRequest request = context.getSeedData();
        OrderItemRequestDTO orderItemRequestDTO = request.getItemRequest();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/remove/RemoveOrderItemActivity.java" line="39">

---

## RemoveOrderItemActivity

The `RemoveOrderItemActivity` class is responsible for removing an item from the order. It extends `BaseActivity` and overrides the `execute` method to perform the removal operation.

```java
@Component("blRemoveOrderItemActivity")
public class RemoveOrderItemActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {

    public static final int ORDER = 4000;
    
    @Resource(name = "blOrderService")
    protected OrderService orderService;
    
    @Resource(name = "blOrderItemService")
    protected OrderItemService orderItemService;
    
    public RemoveOrderItemActivity() {
        setOrder(ORDER);
    }
    
    @Override
    public ProcessContext<CartOperationRequest> execute(ProcessContext<CartOperationRequest> context) throws Exception {
        CartOperationRequest request = context.getSeedData();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/add/ValidateAddRequestActivity.java" line="36">

---

## ValidateAddRequestActivity

The `ValidateAddRequestActivity` class is responsible for validating an add request. It extends `BaseActivity` and overrides the `execute` method to perform the validation.

```java
import org.broadleafcommerce.core.order.service.ProductOptionValidationService;
import org.broadleafcommerce.core.order.service.call.ConfigurableOrderItemRequest;
import org.broadleafcommerce.core.order.service.call.NonDiscreteOrderItemRequestDTO;
import org.broadleafcommerce.core.order.service.call.OrderItemRequestDTO;
import org.broadleafcommerce.core.order.service.exception.MinQuantityNotFulfilledException;
import org.broadleafcommerce.core.order.service.exception.RequiredAttributeNotProvidedException;
import org.broadleafcommerce.core.order.service.workflow.CartOperationRequest;
import org.broadleafcommerce.core.order.service.workflow.add.extension.ValidateAddRequestActivityExtensionManager;
import org.broadleafcommerce.core.order.service.workflow.service.OrderItemRequestValidationService;
import org.broadleafcommerce.core.workflow.ActivityMessages;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/AddWorkflowPriceOrderIfNecessaryActivity.java" line="54">

---

## AddWorkflowPriceOrderIfNecessaryActivity

The `AddWorkflowPriceOrderIfNecessaryActivity` class is responsible for pricing the order if necessary. It extends `BaseActivity` and overrides the `execute` method to perform the pricing operation.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
