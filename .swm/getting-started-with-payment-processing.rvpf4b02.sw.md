---
title: Getting started with Payment Processing
---
Payment Processing in BroadleafCommerce-demo refers to the process of handling transactions between the customer and the payment gateway. This process is facilitated by several classes and methods in the codebase. The `PaymentGatewayAbstractController` and `CustomerPaymentGatewayAbstractController` classes provide generic flows and operations that are common across payment gateway integration methods. The `createCustomerPayment` method in `CustomerPaymentGatewayAbstractController` is used to initiate the creation of a saved payment token. The `PaymentGatewayWebResponseService` is used to parse an incoming HTTPServletRequest into a `PaymentResponseDTO`, which is then used by the customer profile engine to save a token to the user's account. The `PaymentGatewayFieldVariableExpression` class is a Thymeleaf Variable Expression implementation for Payment Gateway Specific fields. The `PaymentGatewayCheckoutService` is usually invoked from the controller that listens to the endpoint hit by the external payment provider.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/PaymentGatewayAbstractController.java" line="40">

---

# PaymentGatewayAbstractController

The `PaymentGatewayAbstractController` class provides generic flows and operations that are common across payment gateway integration methods. It provides methods to apply payment to an order, initiate checkout, and process payment responses.

```java
/**
 * <p>Abstract controller that provides convenience methods and resource declarations to facilitate payment gateway
 * communication between the implementing module and the Spring injected checkout engine. This class provides
 * generic flows and operations that are common across payment gateway integration methods.
 * You may notice that this intentionally resides in "common" as this supports the use case where an implementing module
 * can be used outside the scope of Broadleaf's "core" commerce engine.</p>
 *
 * <p>If used in conjunction with the core framework, Broadleaf provides all the necessary spring resources, such as
 * "blPaymentGatewayCheckoutService" that are needed for this class. If you are using the common jars without the framework
 * dependency, you will either have to implement the blPaymentGatewayCheckoutService yourself, or override the
 * "applyPaymentToOrder" and the "markPaymentAsInvalid" methods accordingly.</p>
 *
 * @author Elbert Bautista (elbertbautista)
 */
public abstract class PaymentGatewayAbstractController extends BroadleafAbstractController {

    protected static final Log LOG = LogFactory.getLog(PaymentGatewayAbstractController.class);
    public static final String PAYMENT_PROCESSING_ERROR = "PAYMENT_PROCESSING_ERROR";

    protected static String baseRedirect = "redirect:/";
    protected static String baseErrorView = "/error";
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="36">

---

# CustomerPaymentGatewayAbstractController

The `CustomerPaymentGatewayAbstractController` class extends `PaymentGatewayAbstractController` and provides additional methods to handle customer payments. It provides methods to create customer payments, apply customer tokens to profiles, and handle payment processing exceptions.

```java
/**
 * <p>Abstract controller that provides convenience methods and resource declarations to facilitate payment gateway
 * communication between the implementing module and the Spring injected customer profile engine. This class provides
 * flows to enable Credit Card tokenization in a PCI-Compliant manner (e.g. through a mechanism like Transparent Redirect)
 * with the ability to save it to a customer's profile.
 * </p>
 *
 * <p>If used in conjunction with the core framework, Broadleaf provides all the necessary spring resources, such as
 * "blCustomerPaymentGatewayService" that are needed for this class. If you are using the common jars without the framework
 * dependency, you will either have to implement the blCustomerPaymentGatewayService yourself in order to
 * save the token to your implementing customer profile system.</p>
 *
 * @author Elbert Bautista (elbertbautista)
 */
public abstract class CustomerPaymentGatewayAbstractController extends BroadleafAbstractController {

    protected static final Log LOG = LogFactory.getLog(CustomerPaymentGatewayAbstractController.class);

    @Resource(name = "blPaymentGatewayWebResponsePrintService")
    protected PaymentGatewayWebResponsePrintService webResponsePrintService;

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/processor/TRCreditCardExtensionHandler.java" line="28">

---

# TRCreditCardExtensionHandler

The `TRCreditCardExtensionHandler` interface provides methods to create transparent redirect forms for credit card payments. It allows payment gateways to generate either an Authorize or Authorize and Capture Form based on their configuration.

```java
/**
 * @author Elbert Bautista (elbertbautista)
 */
public interface TRCreditCardExtensionHandler extends ExtensionHandler {

    /**
     * <p>The implementing modules should take into consideration the passed in configuration settings map
     * and call their implementing TransparentRedirectService to generate either an Authorize
     * or Authorize and Capture Form. The decision should be based on the implementing
     * PaymentGatewayConfiguration.isPerformAuthorizeAndCapture();
     * </p>
     * <p>
     * This method accepts a RequestDTO that represents the order along with a map of
     * gateway-specific configuration settings.
     * The hidden values and the form action will be placed on the passed in formParameters
     * variable. The keys to that map can be retrieved by calling the following methods.
     * getFormActionKey, getFormHiddenParamsKey.
     * </p>
     *
     * @param formParameters
     * @param requestDTO
```

---

</SwmSnippet>

# Payment Processing Endpoints

Payment Processing Endpoints

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="61">

---

## applyCustomerTokenToProfile

The `applyCustomerTokenToProfile` method is used to apply a customer's payment token to their profile. It takes a `PaymentResponseDTO` as an argument, which contains the details of the payment response. If the `customerPaymentGatewayService` is not null, it creates a customer payment from the response DTO and returns the ID of the customer payment.

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
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/controller/CustomerPaymentGatewayAbstractController.java" line="92">

---

## createCustomerPayment

The `createCustomerPayment` method is used to create a customer payment. It takes a `Model`, `HttpServletRequest`, and `RedirectAttributes` as arguments. The method translates the HTTP request to a `PaymentResponseDTO`, applies the customer token to the profile, and if successful, redirects to the customer payment view. If an exception occurs during the process, it is caught and handled accordingly.

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
