---
title: Introduction to Service Calls
---
In the BroadleafCommerce-demo repository, 'Call' refers to a set of operations or methods that are used to manipulate or retrieve data related to a specific service. These operations are encapsulated within the 'call' package under the 'service' directory.

The 'Call' in the 'Service' context is used to perform actions on various entities of the e-commerce application. For instance, the 'setPhone' and 'getPhone' methods are part of the 'FulfillmentGroupRequest' class, which is used to handle requests related to fulfillment groups in the order service.

These methods, 'setPhone' and 'getPhone', are used to set and retrieve the phone information for a fulfillment group respectively. They are used in the 'addFulfillmentGroupToOrder' method in both 'FulfillmentGroupServiceImpl' and 'LegacyOrderServiceImpl' classes.

In summary, 'Call' in the 'Service' context is a crucial part of the BroadleafCommerce-demo repository, enabling the manipulation and retrieval of data for various services in the e-commerce application.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/call/FulfillmentGroupRequest.java" line="9">

---

# Call in Service Context

This is an example of a 'Call' in the 'Service' context. The 'FulfillmentGroupRequest' class contains methods to set and retrieve phone information for a fulfillment group. These methods are used in the 'addFulfillmentGroupToOrder' method in both 'FulfillmentGroupServiceImpl' and 'LegacyOrderServiceImpl' classes.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
