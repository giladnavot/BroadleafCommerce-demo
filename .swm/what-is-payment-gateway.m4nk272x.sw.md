---
title: What is Payment Gateway
---
A Payment Gateway in BroadleafCommerce-demo refers to a system that handles payment transactions by transferring key information from their merchant interface to the front-end processor or bank. It is implemented in the codebase as a set of classes and interfaces that handle different aspects of the payment process. For instance, the `PaymentGatewayType` is an enumeration used to identify the type of payment gateway being used. The `PaymentGatewayResolver` is a service used to determine the appropriate payment gateway for a given transaction. The `PaymentGatewayFieldExtensionHandler` and `PaymentGatewayFieldExtensionManager` are used to handle and manage extensions to the payment gateway fields respectively. The `PaymentGatewayFieldVariableExpression` class is used to map field names to their corresponding values in the payment gateway.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/PaymentGatewayFieldExtensionHandler.java" line="29">

---

## PaymentGatewayFieldExtensionHandler Interface

The `PaymentGatewayFieldExtensionHandler` interface is used to map field names. It has a method `mapFieldName` that takes a field name key and a map of field names as parameters.

```java
public interface PaymentGatewayFieldExtensionHandler extends ExtensionHandler {

    public ExtensionResultStatusType mapFieldName(String fieldNameKey, Map<String, String> fieldNameMap);

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="36">

---

## AbstractPaymentGatewayFieldExtensionHandler Class

The `AbstractPaymentGatewayFieldExtensionHandler` class has a `paymentGatewayResolver` field. This field is used to resolve the payment gateway that will be used for the transaction.

```java
    @Resource(name = "blPaymentGatewayResolver")
    protected PaymentGatewayResolver paymentGatewayResolver;

    public abstract String getCreditCardHolderName();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="71">

---

## PaymentGatewayType Enum

The `getHandlerType` method returns a `PaymentGatewayType` enum, which represents the type of payment gateway to be used.

```java
    public abstract PaymentGatewayType getHandlerType();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/PaymentGatewayFieldExtensionManager.java" line="28">

---

## PaymentGatewayFieldExtensionManager Class

The `PaymentGatewayFieldExtensionManager` class extends the `ExtensionManager` and manages the `PaymentGatewayFieldExtensionHandler` instances. It ensures that the extension handlers continue even after a handler has been executed.

```java
public class PaymentGatewayFieldExtensionManager extends ExtensionManager<PaymentGatewayFieldExtensionHandler> {

    public PaymentGatewayFieldExtensionManager() {
        super(PaymentGatewayFieldExtensionHandler.class);
    }

    @Override
    public boolean continueOnHandled() {
        return true;
    }

}
```

---

</SwmSnippet>

# Payment Gateway Functions

This section provides an overview of the key functions related to the Payment Gateway in BroadleafCommerce-demo.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="39">

---

## getCreditCardHolderName

The `getCreditCardHolderName` function is an abstract method that is expected to be implemented by subclasses to return the name of the credit card holder. It is used within the `mapFieldName` method to map the field name key to the actual credit card holder's name.

```java
    public abstract String getCreditCardHolderName();
    public abstract String getCreditCardType();
    public abstract String getCreditCardNum();
    public abstract String getCreditCardExpDate();
    public abstract String getCreditCardExpMonth();
    public abstract String getCreditCardExpYear();
    public abstract String getCreditCardCvv();

    public abstract String getBillToAddressFirstName();
    public abstract String getBillToAddressLastName();
    public abstract String getBillToAddressCompanyName();
    public abstract String getBillToAddressLine1();
    public abstract String getBillToAddressLine2();
    public abstract String getBillToAddressCityLocality();
    public abstract String getBillToAddressStateRegion();
    public abstract String getBillToAddressPostalCode();
    public abstract String getBillToAddressCountryCode();
    public abstract String getBillToAddressPhone();
    public abstract String getBillToAddressEmail();

    public abstract String getShipToAddressFirstName();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="40">

---

## getCreditCardType

The `getCreditCardType` function is an abstract method that is expected to be implemented by subclasses to return the type of the credit card. It is used within the `mapFieldName` method to map the field name key to the actual credit card type.

```java
    public abstract String getCreditCardType();
    public abstract String getCreditCardNum();
    public abstract String getCreditCardExpDate();
    public abstract String getCreditCardExpMonth();
    public abstract String getCreditCardExpYear();
    public abstract String getCreditCardCvv();

    public abstract String getBillToAddressFirstName();
    public abstract String getBillToAddressLastName();
    public abstract String getBillToAddressCompanyName();
    public abstract String getBillToAddressLine1();
    public abstract String getBillToAddressLine2();
    public abstract String getBillToAddressCityLocality();
    public abstract String getBillToAddressStateRegion();
    public abstract String getBillToAddressPostalCode();
    public abstract String getBillToAddressCountryCode();
    public abstract String getBillToAddressPhone();
    public abstract String getBillToAddressEmail();

    public abstract String getShipToAddressFirstName();
    public abstract String getShipToAddressLastName();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="41">

---

## getCreditCardNum

The `getCreditCardNum` function is an abstract method that is expected to be implemented by subclasses to return the credit card number. It is used within the `mapFieldName` method to map the field name key to the actual credit card number.

```java
    public abstract String getCreditCardNum();
    public abstract String getCreditCardExpDate();
    public abstract String getCreditCardExpMonth();
    public abstract String getCreditCardExpYear();
    public abstract String getCreditCardCvv();

    public abstract String getBillToAddressFirstName();
    public abstract String getBillToAddressLastName();
    public abstract String getBillToAddressCompanyName();
    public abstract String getBillToAddressLine1();
    public abstract String getBillToAddressLine2();
    public abstract String getBillToAddressCityLocality();
    public abstract String getBillToAddressStateRegion();
    public abstract String getBillToAddressPostalCode();
    public abstract String getBillToAddressCountryCode();
    public abstract String getBillToAddressPhone();
    public abstract String getBillToAddressEmail();

    public abstract String getShipToAddressFirstName();
    public abstract String getShipToAddressLastName();
    public abstract String getShipToAddressCompanyName();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/expression/AbstractPaymentGatewayFieldExtensionHandler.java" line="73">

---

## mapFieldName

The `mapFieldName` function is a key method that maps field name keys to actual values. It uses the `isHandlerCompatible` method to check if the handler type is compatible, and then maps various field name keys to their actual values, such as credit card holder name, credit card type, and credit card number.

```java
    @Override
    public ExtensionResultStatusType mapFieldName(String fieldNameKey, Map<String, String> fieldNameMap) {

        if (paymentGatewayResolver.isHandlerCompatible(getHandlerType())) {
            //-------------------------
            // Credit Card Fields
            //-------------------------

            if ("creditCard.creditCardHolderName".equals(fieldNameKey)){
                fieldNameMap.put( fieldNameKey,
                        getCreditCardHolderName() != null ? getCreditCardHolderName() : fieldNameKey);
                return ExtensionResultStatusType.HANDLED_CONTINUE;
            }

            if ("creditCard.creditCardType".equals(fieldNameKey)){
                fieldNameMap.put( fieldNameKey,
                        getCreditCardType() != null ? getCreditCardType() : fieldNameKey);
                return ExtensionResultStatusType.HANDLED_CONTINUE;
            }

            if ("creditCard.creditCardNum".equals(fieldNameKey)){
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
