---
title: Basic Concepts of Domain
---
In BroadleafCommerce-demo, 'Domain' refers to a set of related classes that represent the business objects of the application. These classes are typically organized in packages that reflect their functionality. For instance, the 'payment' domain contains classes that handle payment-related operations, such as 'OrderPayment', 'PaymentTransaction', and 'PaymentLog'. Similarly, the 'secure' subdomain within the 'payment' domain contains classes that deal with secure payment methods like 'BankAccountPayment', 'CreditCardPayment', and 'GiftCardPayment'.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/domain/secure/Referenced.java" line="28">

---

# Referenced Interface

The `Referenced` interface is part of the domain model of the BroadleafCommerce-demo. It is used to represent secure payment information. The interface defines methods for getting and setting the ID, reference number, and encryption module. Implementations of this interface should be stored in a separate, secure database for PCI compliance. The `Referenced` interface is linked to the `Order` domain via the `OrderPayment`'s reference number.

```java
/**
 * <p>The main interface used to store extra-secure data such as credit card, bank accounts and gift card data. All entities
 * that implement this interface should be stored in a completely separate database under strict PCI compliance. Broadleaf
 * provides the ability for this in the blSecurePU persistence unit, which all implementing entities are members of.</p>
 *
 * <p>Entities that implement this {@link Referenced} interface should not be instantiated directly but rather be instaniated
 * via {@link SecureOrderPaymentService#create(org.broadleafcommerce.core.payment.service.type.PaymentType)}</p>
 * 
 * <p>In the common case, this is rarely used as most implementors will NOT want to deal with the liability and extra PCI
 * requirements associated with storing sensitive payment data. Consider integrating with a payment provider that takes
 * care of PCI-sensitive data instead.</p>
 *
 * @see {@link CreditCardPayment}
 * @see {@link GiftCardPayment}
 * @see {@link BankAccountPayment}
 * @author Phillip Verheyden (phillipuniverse)
 */
public interface Referenced extends Serializable {

    public Long getId();
    
```

---

</SwmSnippet>

Let's look at the functions in the 'WrapperAdditionalFields.java' file.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
