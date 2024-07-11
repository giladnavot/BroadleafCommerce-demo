---
title: Exploring the Payment Domain
---
Domain in the Payment package refers to the data model layer of the Broadleaf Commerce application. It contains classes that represent the core business objects in the payment processing flow, such as OrderPayment, PaymentTransaction, and PaymentResponseItem.

These classes are used to capture and manage all the details related to a payment transaction. For example, OrderPayment represents a payment associated with an order, PaymentTransaction represents a specific transaction for a payment, and PaymentResponseItem holds the response details from a payment gateway.

The domain package also includes secure subpackage that contains classes for handling sensitive payment information. For instance, CreditCardPaymentInfoImpl class is used to store and manage credit card payment details in a secure way.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/domain/secure/Referenced.java" line="28">

---

# Domain in Payment Package

This is an example of a domain interface in the payment package. The `Referenced` interface is used to store extra-secure data such as credit card, bank accounts, and gift card data. It provides methods to get and set the id, reference number, and encryption module. Entities that implement this interface should be stored in a completely separate database under strict PCI compliance.

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

# Secure Subpackage in Domain

The domain package also includes a secure subpackage that contains classes for handling sensitive payment information. These classes, like `CreditCardPaymentInfoImpl`, are used to store and manage payment details in a secure way. They are part of the `blSecurePU` persistence unit, which is under strict PCI compliance.

# Domain Functions

This section will cover the main functions of the Domain in the Broadleaf Commerce application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/rest/api/wrapper/WrapperAdditionalFields.java" line="1">

---

## OrderPayment

The `OrderPayment` class represents a payment associated with an order. It is used to capture and manage all the details related to a payment transaction.

```java
/*-
 * #%L
 * BroadleafCommerce Common Libraries
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */

package org.broadleafcommerce.common.rest.api.wrapper;

import java.util.List;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/rest/api/wrapper/WrapperAdditionalFields.java" line="101">

---

## PaymentTransaction

The `PaymentTransaction` class represents a specific transaction for a payment. It is used to capture and manage all the details related to a payment transaction.

```java

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/rest/api/wrapper/WrapperAdditionalFields.java" line="201">

---

## PaymentResponseItem

The `PaymentResponseItem` class holds the response details from a payment gateway. It is used to capture and manage all the details related to a payment transaction.

```java

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
