---
title: Overview of Payment Service
---
The Payment Service in BroadleafCommerce-demo refers to a set of operations that manage the payment process within the e-commerce framework. It is represented by the `OrderPaymentService` interface, which defines methods for creating, reading, updating, and deleting payment-related entities such as `OrderPayment`, `PaymentTransaction`, and `PaymentLog`.

The `OrderPaymentService` interface is implemented by the `OrderPaymentServiceImpl` class. This class provides concrete implementations for the methods defined in the interface. It uses the `OrderPaymentDao` to interact with the database and perform CRUD operations on the payment-related entities.

The `OrderPaymentService` is used in various parts of the application, such as the checkout process, payment gateway interaction, and order management. It is responsible for handling the payment details of an order, including saving payment transactions, creating payment logs, and managing the lifecycle of an order payment.

The `OrderPaymentService` also provides methods for creating new instances of `OrderPayment`, `PaymentTransaction`, and `PaymentLog`. These methods are used throughout the application to create new payment-related entities when needed.

In case of an unsuccessful transaction, the `OrderPaymentService` provides a method to mark a payment as invalid. This is done by setting the `archived` status of the payment and its transactions to 'Y', indicating that they are no longer active.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/OrderPaymentService.java" line="9">

---

# OrderPaymentService

The `OrderPaymentService` interface defines the contract for a service that manages `OrderPayment` entities. It provides methods for creating, reading, updating, and deleting `OrderPayment` entities.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/OrderPaymentServiceImpl.java" line="9">

---

# OrderPaymentServiceImpl

The `OrderPaymentServiceImpl` class is an implementation of the `OrderPaymentService` interface. It provides concrete implementations for the methods defined in the interface, using the `OrderPaymentDao` to interact with the database.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.payment.service;

import org.apache.commons.collections4.MapUtils;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.payment.PaymentAdditionalFieldType;
import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.time.SystemTime;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.payment.dao.OrderPaymentDao;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/DefaultPaymentGatewayCheckoutService.java" line="9">

---

# Using the OrderPaymentService

The `OrderPaymentService` is used in various parts of the application, such as the checkout process, payment gateway interaction, and order management. It is responsible for handling the payment details of an order, including saving payment transactions, creating payment logs, and managing the lifecycle of an order payment.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */

package org.broadleafcommerce.core.payment.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.i18n.domain.ISOCountry;
import org.broadleafcommerce.common.i18n.service.ISOService;
import org.broadleafcommerce.common.payment.PaymentAdditionalFieldType;
import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.broadleafcommerce.common.payment.dto.AddressDTO;
import org.broadleafcommerce.common.payment.dto.GatewayCustomerDTO;
```

---

</SwmSnippet>

# Payment Services

Payment Services in Broadleaf Commerce

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/DefaultPaymentGatewayCheckoutService.java" line="91">

---

## applyPaymentToOrder

The `applyPaymentToOrder` method is used to apply a payment to an order. It takes a PaymentResponseDTO and a PaymentGatewayConfiguration as parameters. The method first checks if the payment response is valid and if the payment gateway configuration is not null. It then retrieves the order associated with the payment and checks its status. If the order is in process, the method creates a new OrderPayment and populates it with the details from the payment response. The method then saves the OrderPayment and returns its ID.

```java
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
            GatewayCustomerDTO<PaymentResponseDTO> gatewayCustomer = responseDTO.getCustomer();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/payment/service/DefaultCustomerPaymentGatewayService.java" line="63">

---

## createCustomerPaymentFromResponseDTO

The `createCustomerPaymentFromResponseDTO` method is used to create a new CustomerPayment from a payment response. It takes a PaymentResponseDTO and a PaymentGatewayConfiguration as parameters. The method first validates the payment response and the payment gateway configuration. It then retrieves the customer associated with the payment and checks if the payment is a new default payment method. If it is, the method clears the default payment status of the customer. The method then creates a new CustomerPayment, populates it with the details from the payment response, and saves it. The method returns the ID of the newly created CustomerPayment.

```java
    public Long createCustomerPaymentFromResponseDTO(PaymentResponseDTO responseDTO, PaymentGatewayConfiguration config)
            throws IllegalArgumentException {
        validateResponseAndConfig(responseDTO, config);

        Long customerId = Long.parseLong(responseDTO.getCustomer().getCustomerId());
        Customer customer = customerService.readCustomerById(customerId);

        if (customer != null) {
            if (isNewDefaultPaymentMethod(responseDTO)) {
                customerPaymentService.clearDefaultPaymentStatus(customer);
            }

            CustomerPayment customerPayment = customerPaymentService.create();
            populateCustomerPayment(customerPayment, responseDTO, config);
            customerPayment.setCustomer(customer);

            customerPayment = customerPaymentService.saveCustomerPayment(customerPayment);
            customer.getCustomerPayments().add(customerPayment);
            return customerPayment.getId();
        }

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
