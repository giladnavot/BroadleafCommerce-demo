---
title: Understanding the Checkout Process
---
Checkout in BroadleafCommerce-demo refers to the process of finalizing an order. It involves executing the `blCheckoutWorkflow` which saves the order before and after execution, allowing activities to modify the various entities related to the order. This process is handled by the `CheckoutService` interface, specifically the `performCheckout` method. The method is thread-safe, meaning two requests cannot attempt to checkout the same order simultaneously. If any exceptions occur during the workflow execution or if the order has already been checked out, a `CheckoutException` is thrown. The result of the checkout process is a `CheckoutResponse`, which contains the final state of the order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/CheckoutService.java" line="25">

---

# CheckoutService Interface

The `CheckoutService` interface defines the `performCheckout` method. This method is responsible for executing the checkout workflow and saving the order both before and after the workflow execution. It throws a `CheckoutException` if there are any exceptions during the workflow execution or if the order has already been checked out.

```java
public interface CheckoutService {

    /**
     * <p>Checks out an order by executing the blCheckoutWorkflow. The <b>order</b> is saved both before and after the workflow
     * is executed so that activities can modify the various entities on and related to the <b>order</b>.</p>
     * 
     * <p>This method is also thread-safe; 2 requests cannot attempt to check out the same <b>order</b></p>
     * 
     * @param order the order to be checked out
     * @return
     * @throws CheckoutException if there are any exceptions while executing any of the activities in the workflow (assuming
     * that the workflow does not already have a preconfigured error handler) or if the given <b>order</b> has already been
     * checked out (in Broadleaf terms this means the <b>order</b> has already been changed to {@link OrderStatus#SUBMITTED})
     */
    public CheckoutResponse performCheckout(Order order) throws CheckoutException;
    
}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/CheckoutServiceImpl.java" line="42">

---

# CheckoutServiceImpl Class

The `CheckoutServiceImpl` class is the implementation of the `CheckoutService` interface. It uses the `checkoutWorkflow` processor to execute the checkout workflow. The `performCheckout` method in this class first checks if the order has already been checked out by calling the `hasOrderBeenCompleted` method. If the order has not been checked out, it creates a `CheckoutSeed` object and passes it to the `checkoutWorkflow` processor.

```java
@Service("blCheckoutService")
public class CheckoutServiceImpl implements CheckoutService {

    @Resource(name="blCheckoutWorkflow")
    protected Processor<CheckoutSeed, CheckoutSeed> checkoutWorkflow;

    @Autowired
    @Qualifier("blApplicationEventPublisher")
    protected BroadleafApplicationEventPublisher eventPublisher;

    @Resource(name="blOrderService")
    protected OrderService orderService;

    @Override
    public CheckoutResponse performCheckout(Order order) throws CheckoutException {
        // Immediately fail if this order has already been checked out previously
        if (hasOrderBeenCompleted(order)) {
            throw new CheckoutException("This order has already been submitted or cancelled, unable to checkout order -- id: " + order.getId(), new CheckoutSeed(order, new HashMap<String, Object>()));
        }
        
        CheckoutSeed seed = null;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CheckoutResponse.java" line="22">

---

# CheckoutResponse Interface

The `CheckoutResponse` interface defines the `getOrder` method. This method is used to retrieve the order after the checkout process.

```java
public interface CheckoutResponse {

    public Order getOrder();

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CheckoutSeed.java" line="25">

---

# CheckoutSeed Class

The `CheckoutSeed` class implements the `CheckoutResponse` interface. It contains the order and any user-defined fields. The `CheckoutSeed` object is used to carry the order and any additional information through the checkout workflow.

```java
public class CheckoutSeed implements CheckoutResponse {

    protected Order order;
    protected Map<String, Object> userDefinedFields = new HashMap<>();

    public CheckoutSeed(Order order, Map<String, Object> userDefinedFields) {
        this.order = order;
        this.userDefinedFields = userDefinedFields;
    }

    @Override
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    public Map<String, Object> getUserDefinedFields() {
        return userDefinedFields;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
