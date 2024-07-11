---
title: Getting started with Payment Processing
---
Payment in BroadleafCommerce-demo refers to the process of handling transactions related to the purchase of goods or services within the e-commerce platform. It involves various components such as `OrderPayment`, `PaymentTransaction`, and `PaymentLog`.

`OrderPayment` represents a payment associated with an order. It holds information about the payment type, gateway, amount, and related transactions. Each `OrderPayment` should have at least one `PaymentTransaction` when an order has gone through checkout.

`PaymentTransaction` represents an individual transaction about a particular payment. It holds data about the transaction type, amount, success status, and other additional fields. It makes sense only in the context of an `OrderPayment`.

`PaymentLog` is a deprecated entity that was used to log payment-related events. Now, payment logs should be captured as raw responses in `PaymentTransaction` line items.

The `DefaultPaymentGatewayCheckoutService` class provides the core implementation of the `PaymentGatewayCheckoutService`. It handles the application of payments to orders, marking payments as invalid, and handling unsuccessful transactions.

The `OrderPaymentService` interface provides methods for creating, reading, saving, and deleting `OrderPayment` and `PaymentTransaction` instances. It also provides methods for creating `CustomerPayment` from `PaymentTransaction` and populating `CustomerPayment` tokens.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/domain/OrderPayment.java" line="36">

---

# OrderPayment

OrderPayment represents a payment associated with an order. It holds information about the payment type, gateway, amount, and related transactions. Each OrderPayment should have at least one PaymentTransaction when an order has gone through checkout.

```java
/**
 * <p>This entity is designed to deal with payments associated to an {@link Order} and is <i>usually</i> unique for a particular
 * amount, {@link PaymentGatewayType} and {@link PaymentType} combination. This is immediately invalid for scenarios where multiple payments of the
 * same {@link PaymentType} should be supported (like paying with 2 {@link PaymentType#CREDIT_CARD} or 2 {@link PaymentType#GIFT_CARD}).
 * That said, even though the use case might be uncommon in, Broadleaf does not actively prevent that situation from occurring
 * online payments it is very common in point of sale systems.</p>
 * 
 * <p>Once an {@link OrderPayment} is created, various {@link PaymentTransaction}s can be applied to this payment as
 * denoted by {@link PaymentTransactionType}. <b>There should be at least 1 {@link PaymentTransaction} for every
 * {@link OrderPayment} that is associated with an {@link Order} that has gone through checkout</b> (which means that
 * {@link Order#getStatus()} is {@link OrderStatus#SUBMITTED}).</p>
 * 
 * <p>{@link OrderPayment}s are not actually deleted from the database but rather are only soft-deleted (archived = true)</p>
 * 
 * @see {@link PaymentTransactionType}
 * @see {@link PaymentTransaction}
 * @see {@link PaymentType}
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface OrderPayment extends Serializable, Status, MultiTenantCloneable<OrderPayment> {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/domain/PaymentTransaction.java" line="33">

---

# PaymentTransaction

PaymentTransaction represents an individual transaction about a particular payment. It holds data about the transaction type, amount, success status, and other additional fields. It makes sense only in the context of an OrderPayment.

```java
/**
 * <p>Used to store individual transactions about a particular payment. While an {@link OrderPayment} holds data like what the
 * user might be paying with and the total amount they will be paying (like credit card and $10), a {@link PaymentTransaction}
 * is more about what happens with that particular payment. Thus, {@link PaymentTransaction}s do not make sense by
 * themselves and ONLY make sense in the context of an {@link OrderPayment}.</p>
 * 
 * <p>For instance, the user might say they want to pay $10 but rather than capture the payment at order checkout, there
 * might first be a transaction for {@link PaymentTransactionType#AUTHORIZE} and then when the item is shipped there is
 * another {@link PaymentTransaction} for {@link PaymentTransactionType#CAPTURE}.</p>
 * 
 * <p>In the above case, this also implies that a {@link PaymentTransaction} can have a <b>parent transaction</b> (retrieved
 * via {@link #getParentTransaction()}). The parent transaction will only be set in the following cases:</p>
 * 
 * <ul>
 *  <li>{@link PaymentTransactionType#CAPTURE} -> {@link PaymentTransactionType#AUTHORIZE}</li>
 *  <li>{@link PaymentTransactionType#REFUND} -> {@link PaymentTransactionType#CAPTURE} OR {@link PaymentTransactionType#SETTLED}</li>
 *  <li>{@link PaymentTransactionType#SETTLED} -> {@link PaymentTransactionType#CAPTURE}</li>
 *  <li>{@link PaymentTransactionType#VOID} -> {@link PaymentTransactionType#CAPTURE}</li>
 *  <li>{@link PaymentTransactionType#REVERSE_AUTH} -> {@link PaymentTransactionType#AUTHORIZE}</li>
 * </ul>
 * 
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/DefaultPaymentGatewayCheckoutService.java" line="90">

---

# DefaultPaymentGatewayCheckoutService

The DefaultPaymentGatewayCheckoutService class provides the core implementation of the PaymentGatewayCheckoutService. It handles the application of payments to orders, marking payments as invalid, and handling unsuccessful transactions.

```java
    @Override
    public Long applyPaymentToOrder(PaymentResponseDTO responseDTO, PaymentGatewayConfiguration config) {
        
        //Payments can ONLY be parsed into Order Payments if they are 'valid'
        if (!responseDTO.isValid()) {
            throw new IllegalArgumentException("Invalid payment responses cannot be parsed into the order payment domain");
        }
        
        if (config == null) {
            throw new IllegalArgumentException("Config service cannot be null");
        }
        
        Long orderId = Long.parseLong(responseDTO.getOrderId());
        Order order = orderService.findOrderById(orderId);
        
        if (!OrderStatus.IN_PROCESS.equals(order.getStatus()) && !OrderStatus.CSR_OWNED.equals(order.getStatus()) && !OrderStatus.QUOTE.equals(order.getStatus())) {
            throw new IllegalArgumentException("Cannot apply another payment to an Order that is not IN_PROCESS or CSR_OWNED");
        }
        
        Customer customer = order.getCustomer();
        if (customer.isAnonymous()) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/OrderPaymentService.java" line="32">

---

# OrderPaymentService

The OrderPaymentService interface provides methods for creating, reading, saving, and deleting OrderPayment and PaymentTransaction instances. It also provides methods for creating CustomerPayment from PaymentTransaction and populating CustomerPayment tokens.

```java
    public OrderPayment save(OrderPayment payment);

    public PaymentTransaction save(PaymentTransaction transaction);

    public PaymentLog save(PaymentLog log);

    public OrderPayment readPaymentById(Long paymentId);

    public List<OrderPayment> readPaymentsForOrder(Order order);

    public OrderPayment create();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
