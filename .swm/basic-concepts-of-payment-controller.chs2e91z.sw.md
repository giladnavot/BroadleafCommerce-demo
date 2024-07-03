---
title: Basic Concepts of Payment Controller
---
The Payment Controller in BroadleafCommerce-demo refers to two abstract classes: `PaymentGatewayAbstractController` and `CustomerPaymentGatewayAbstractController`. These classes provide convenience methods and resource declarations to facilitate communication with the payment gateway.

`PaymentGatewayAbstractController` provides generic flows and operations that are common across payment gateway integration methods. It is designed to handle the final steps in checkout either via a request coming directly from a Payment Gateway or from some sort of tokenization mechanism client-side.

`CustomerPaymentGatewayAbstractController` on the other hand, is designed to facilitate payment gateway communication between the implementing module and the Spring injected customer profile engine. It provides flows to enable Credit Card tokenization in a PCI-Compliant manner with the ability to save it to a customer's profile.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="54">

---

# PaymentGatewayAbstractController

The `PaymentGatewayAbstractController` is an abstract class that provides common functionality for payment processing. It contains methods for applying payments to orders, initiating checkout, and processing payment responses. It also defines several abstract methods that must be implemented by any concrete class that extends it.

```java
public abstract class PaymentGatewayAbstractController extends BroadleafAbstractController {

    protected static final Log LOG = LogFactory.getLog(PaymentGatewayAbstractController.class);
    public static final String PAYMENT_PROCESSING_ERROR = "PAYMENT_PROCESSING_ERROR";

    protected static String baseRedirect = "redirect:/";
    protected static String baseErrorView = "/error";
    protected static String baseOrderReviewRedirect = "redirect:/checkout";
    protected static String baseConfirmationRedirect = "redirect:/confirmation";
    protected static String baseCartRedirect = "redirect:/cart";

    //Externalized Generic Payment Error Message
    protected static String processingErrorMessage = "cart.paymentProcessingError";
    protected static String cartReqAttributeNotProvidedMessage = "cart.requiredAttributeNotProvided";

    @Autowired(required=false)
    @Qualifier("blPaymentGatewayCheckoutService")
    protected PaymentGatewayCheckoutService paymentGatewayCheckoutService;

    @Resource(name = "blPaymentGatewayWebResponsePrintService")
    protected PaymentGatewayWebResponsePrintService webResponsePrintService;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="50">

---

# CustomerPaymentGatewayAbstractController

The `CustomerPaymentGatewayAbstractController` is another abstract class that extends `PaymentGatewayAbstractController`. It provides additional functionality specific to customer payments, such as applying customer tokens to profiles and creating customer payments from response DTOs.

```java
public abstract class CustomerPaymentGatewayAbstractController extends BroadleafAbstractController {

    protected static final Log LOG = LogFactory.getLog(CustomerPaymentGatewayAbstractController.class);

    @Resource(name = "blPaymentGatewayWebResponsePrintService")
    protected PaymentGatewayWebResponsePrintService webResponsePrintService;

    @Autowired(required=false)
    @Qualifier("blCustomerPaymentGatewayService")
    protected CustomerPaymentGatewayService customerPaymentGatewayService;

    public Long applyCustomerTokenToProfile(PaymentResponseDTO responseDTO) throws IllegalArgumentException {
        if (LOG.isErrorEnabled()) {
            if (customerPaymentGatewayService == null) {
                LOG.trace("applyCustomerTokenToProfile: CustomerPaymentGatewayService is null. Please check your configuration.");
            }
        }

        if (customerPaymentGatewayService != null) {
            return customerPaymentGatewayService.createCustomerPaymentFromResponseDTO(responseDTO, getConfiguration());
        }
```

---

</SwmSnippet>

# Payment Controller Functions

The Payment Controller in BroadleafCommerce-demo is responsible for handling the payment process during checkout. It provides several key functions that facilitate communication with the payment gateway and manage the payment process.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="76">

---

## applyPaymentToOrder

The `applyPaymentToOrder` function is used to apply a payment to an order. It takes a `PaymentResponseDTO` object as input, which contains the details of the payment response from the payment gateway. If the `paymentGatewayCheckoutService` is not null, it calls the `applyPaymentToOrder` method of the `paymentGatewayCheckoutService` with the `PaymentResponseDTO` and the configuration as parameters.

```java
    public Long applyPaymentToOrder(PaymentResponseDTO responseDTO) throws IllegalArgumentException {
        if (LOG.isErrorEnabled()) {
            if (paymentGatewayCheckoutService == null) {
                LOG.error("applyPaymentToOrder: PaymentCheckoutService is null. Please check your configuration.");
            }
        }

        if (paymentGatewayCheckoutService != null) {
            return paymentGatewayCheckoutService.applyPaymentToOrder(responseDTO, getConfiguration());
        }
        return null;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="89">

---

## initiateCheckout

The `initiateCheckout` function is used to initiate the checkout process for an order. It takes an `orderId` as input. If the `paymentGatewayCheckoutService` is not null and the `orderId` is not null, it calls the `initiateCheckout` method of the `paymentGatewayCheckoutService` with the `orderId` as the parameter.

```java
    public String initiateCheckout(Long orderId) throws Exception {
        String orderNumber = null;
        if (LOG.isErrorEnabled()) {
            if (paymentGatewayCheckoutService == null) {
                LOG.error("initiateCheckout: PaymentCheckoutService is null. Please check your configuration.");
            }
        }

        if (paymentGatewayCheckoutService != null && orderId != null) {
            orderNumber = paymentGatewayCheckoutService.initiateCheckout(orderId);
        }

        if (orderNumber == null) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("The result from calling initiateCheckout with paymentCheckoutService and orderId: " + orderId + " is null");
            }
        }

        return orderNumber;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="168">

---

## process

The `process` function is used to initiate the final steps in checkout either via a request coming directly from a Payment Gateway or from some sort of tokenization mechanism client-side. It translates the HTTP request to a `PaymentResponseDTO`, applies the payment to the order, and handles unsuccessful transactions.

```java
    public String process(Model model, HttpServletRequest request,
                          final RedirectAttributes redirectAttributes) throws PaymentException {
        Long orderPaymentId = null;

        try {
            PaymentResponseDTO responseDTO = getWebResponseService().translateWebResponse(request);
            if (LOG.isTraceEnabled()) {
                LOG.trace("HTTPRequest translated to Raw Response: " +  responseDTO.getRawResponse());
            }

            orderPaymentId = applyPaymentToOrder(responseDTO);

            if (!responseDTO.isSuccessful()) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("The Response DTO is marked as unsuccessful. Delegating to the " +
                            "payment module to handle an unsuccessful transaction");
                }

                handleUnsuccessfulTransaction(model, redirectAttributes, responseDTO);
                return getErrorViewRedirect();
            }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="61">

---

## applyCustomerTokenToProfile

The `applyCustomerTokenToProfile` function is used to apply a customer token to a profile. It takes a `PaymentResponseDTO` object as input, which contains the details of the payment response from the payment gateway. If the `customerPaymentGatewayService` is not null, it calls the `createCustomerPaymentFromResponseDTO` method of the `customerPaymentGatewayService` with the `PaymentResponseDTO` and the configuration as parameters.

```java
    public Long applyCustomerTokenToProfile(PaymentResponseDTO responseDTO) throws IllegalArgumentException {
        if (LOG.isErrorEnabled()) {
            if (customerPaymentGatewayService == null) {
                LOG.trace("applyCustomerTokenToProfile: CustomerPaymentGatewayService is null. Please check your configuration.");
            }
        }

        if (customerPaymentGatewayService != null) {
            return customerPaymentGatewayService.createCustomerPaymentFromResponseDTO(responseDTO, getConfiguration());
        }

        return null;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="92">

---

## createCustomerPayment

The `createCustomerPayment` function is used to initiate the creation of a saved payment token. It translates the HTTP request to a `PaymentResponseDTO`, applies the customer token to the profile, and handles exceptions.

```java
    public String createCustomerPayment(Model model, HttpServletRequest request,
                                        final RedirectAttributes redirectAttributes) throws PaymentException {

        try {
            PaymentResponseDTO responseDTO = getWebResponseService().translateWebResponse(request);
            if (LOG.isTraceEnabled()) {
                LOG.trace("HTTPRequest translated to Raw Response: " +  responseDTO.getRawResponse());
            }

            Long customerPaymentId = applyCustomerTokenToProfile(responseDTO);

            if (customerPaymentId != null) {
                return getCustomerPaymentViewRedirect(String.valueOf(customerPaymentId));
            }

        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("HTTPRequest - " + webResponsePrintService.printRequest(request));

                LOG.error("An exception was caught either from processing the response or saving the resulting " +
                        "payment token to the customer's profile - delegating to the payment module to handle any other " +
```

---

</SwmSnippet>

# Payment Controller Endpoints

Payment Controller Endpoints

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="76">

---

## applyPaymentToOrder

The `applyPaymentToOrder` method is used to apply a payment to an order. It takes a PaymentResponseDTO object as input, which contains the details of the payment. The method then calls the `applyPaymentToOrder` method of the `paymentGatewayCheckoutService` to apply the payment to the order. If the service is not available, it logs an error and returns null.

```java
    public Long applyPaymentToOrder(PaymentResponseDTO responseDTO) throws IllegalArgumentException {
        if (LOG.isErrorEnabled()) {
            if (paymentGatewayCheckoutService == null) {
                LOG.error("applyPaymentToOrder: PaymentCheckoutService is null. Please check your configuration.");
            }
        }

        if (paymentGatewayCheckoutService != null) {
            return paymentGatewayCheckoutService.applyPaymentToOrder(responseDTO, getConfiguration());
        }
        return null;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="92">

---

## createCustomerPayment

The `createCustomerPayment` method is used to create a customer payment. It takes a Model object, an HttpServletRequest, and RedirectAttributes as inputs. The method translates the HTTP request to a PaymentResponseDTO object, applies the customer token to the profile, and if successful, redirects to the customer payment view. If an exception occurs during the process, it is caught and handled appropriately.

```java
    public String createCustomerPayment(Model model, HttpServletRequest request,
                                        final RedirectAttributes redirectAttributes) throws PaymentException {

        try {
            PaymentResponseDTO responseDTO = getWebResponseService().translateWebResponse(request);
            if (LOG.isTraceEnabled()) {
                LOG.trace("HTTPRequest translated to Raw Response: " +  responseDTO.getRawResponse());
            }

            Long customerPaymentId = applyCustomerTokenToProfile(responseDTO);

            if (customerPaymentId != null) {
                return getCustomerPaymentViewRedirect(String.valueOf(customerPaymentId));
            }

        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("HTTPRequest - " + webResponsePrintService.printRequest(request));

                LOG.error("An exception was caught either from processing the response or saving the resulting " +
                        "payment token to the customer's profile - delegating to the payment module to handle any other " +
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
