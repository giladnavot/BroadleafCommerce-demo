---
title: What is the Checkout Process
---
The Checkout in BroadleafCommerce-demo refers to the process of finalizing an order. It is managed by the `CheckoutService` interface, which defines the `performCheckout` method. This method executes the `blCheckoutWorkflow`, saving the order before and after the workflow execution, allowing activities to modify the order and related entities.

The `CheckoutService` is implemented in `CheckoutServiceImpl` and is used in various parts of the application such as `DefaultPaymentGatewayCheckoutService` and `AbstractCheckoutController`.

The `performCheckout` method returns a `CheckoutResponse`, which contains the finalized order. If there are any exceptions while executing any of the activities in the workflow or if the given order has already been checked out, a `CheckoutException` is thrown.

The `CheckoutResponse` is used in several places in the codebase, including `CheckoutException`, `CheckoutServiceImpl`, and `DefaultPaymentGatewayCheckoutService`.

The `CheckoutSeed` class implements the `CheckoutResponse` interface and represents the initial state of the checkout process. It contains the order and user-defined fields.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/CheckoutService.java" line="25">

---

# CheckoutService Interface

The `CheckoutService` interface defines the `performCheckout` method. This method executes the `blCheckoutWorkflow`, saving the order before and after the workflow execution, allowing activities to modify the order and related entities.

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

`CheckoutServiceImpl` is the implementation of `CheckoutService`. It uses the `blCheckoutWorkflow` to perform the checkout process. If the order has already been completed, it throws a `CheckoutException`.

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

`CheckoutResponse` is the response returned by the `performCheckout` method. It contains the finalized order.

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

`CheckoutSeed` represents the initial state of the checkout process. It implements the `CheckoutResponse` interface and contains the order and user-defined fields.

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
