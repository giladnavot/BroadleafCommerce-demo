---
title: Role of PaymentResponseDTO in Payment Processing
---
This document will cover the role of the `PaymentResponseDTO` class in processing payments in the BroadleafCommerce-demo repository. We'll cover:

1. The purpose of the `PaymentResponseDTO` class
2. How `PaymentResponseDTO` is used in the codebase
3. The functionality of `PaymentResponseDTO`

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/dto/PaymentResponseDTO.java" line="32">

---

# Purpose of the `PaymentResponseDTO` class

`PaymentResponseDTO` is a Data Transfer Object (DTO) that represents the response coming back from any call to the payment gateway. This can either wrap an API result call or a translated HTTP Web response. It can represent the results of a transaction, a request for a Secure Token, and more. The class contains various fields that hold information about the transaction, such as customer information, shipping and billing addresses, credit card details, gift cards, customer credits, the payment gateway type, the payment type, the transaction type, the order ID, the transaction amount, a payment token, and flags indicating whether the transaction was successful and whether the response was valid.

```java
/**
 * <p>The DTO object that represents the response coming back from any call to the Gateway.
 * This can either wrap an API result call or a translated HTTP Web response.
 * This can not only be the results of a transaction, but also a request for a Secure Token etc...</p>
 *
 * <p>Note: the success and validity flags are set to true by default, unless otherwise overridden by specific
 * gateway implementations</p>
 *
 * @author Elbert Bautista (elbertbautista)
 */
public class PaymentResponseDTO {

    /**
     * Any customer information that relates to this transaction
     */
    protected GatewayCustomerDTO<PaymentResponseDTO> customer;

    /**
     * If shipping information is captured on the gateway, the values sent back will be put here
     */
    protected AddressDTO<PaymentResponseDTO> shipTo;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/service/PaymentGatewayTransparentRedirectService.java" line="42">

---

# Usage of `PaymentResponseDTO` in the codebase

`PaymentResponseDTO` is used as a return type in various methods in the `PaymentGatewayTransparentRedirectService` interface. These methods are responsible for creating forms for authorizing payments, capturing payments, and managing customer payment tokens.

```java
    public PaymentResponseDTO createAuthorizeForm(PaymentRequestDTO requestDTO) throws PaymentException;

    public PaymentResponseDTO createAuthorizeAndCaptureForm(PaymentRequestDTO requestDTO) throws PaymentException;

    public PaymentResponseDTO createCustomerPaymentTokenForm(PaymentRequestDTO requestDTO) throws PaymentException;

    public PaymentResponseDTO updateCustomerPaymentTokenForm(PaymentRequestDTO requestDTO) throws PaymentException;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/service/AbstractPaymentGatewayTransactionService.java" line="26">

---

`PaymentResponseDTO` is also used as a return type in the `AbstractPaymentGatewayTransactionService` class. This class provides default implementations for the methods in the `PaymentGatewayTransactionService` interface, which throw `UnsupportedOperationException`. These methods are expected to be overridden by specific gateway implementations.

```java
    @Override
    public PaymentResponseDTO authorize(PaymentRequestDTO paymentRequestDTO) throws PaymentException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public PaymentResponseDTO capture(PaymentRequestDTO paymentRequestDTO) throws PaymentException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public PaymentResponseDTO authorizeAndCapture(PaymentRequestDTO paymentRequestDTO) throws PaymentException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public PaymentResponseDTO reverseAuthorize(PaymentRequestDTO paymentRequestDTO) throws PaymentException {
        throw new UnsupportedOperationException("Not Implemented");
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/dto/PaymentResponseDTO.java" line="271">

---

# Functionality of `PaymentResponseDTO`

The `isSuccessful` method returns a boolean indicating whether the transaction on the payment gateway was successful. This should be provided by the gateway alone.

```java
    public boolean isSuccessful() {
        return successful;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/dto/PaymentResponseDTO.java" line="275">

---

The `isValid` method returns a boolean indicating whether the response was tampered with. This is used to verify that the response that was received on the endpoint (which is intended to only be invoked from the payment gateway) actually came from the gateway and was not otherwise maliciously invoked by a third party.

```java
    public boolean isValid() {
        return valid;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/payment/dto/PaymentResponseDTO.java" line="192">

---

The `amount` method sets the amount that was sent back from the gateway if this was a transaction request and returns the `PaymentResponseDTO` instance.

```java
    public PaymentResponseDTO amount(Money amount) {
        this.amount = amount;
        return this;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
