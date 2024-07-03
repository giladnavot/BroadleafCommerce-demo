---
title: Understanding Fulfillment
---
Fulfillment in BroadleafCommerce-demo refers to the process of handling and delivering orders to customers. It is represented by the `FulfillmentOptionService` interface, which provides methods to read, save, and manage all fulfillment options. These options are used in various parts of the application, such as the checkout process, to determine how an order should be fulfilled. The `FulfillmentGroupRequest` class represents a request to add a fulfillment group to an order, containing details like the address, order, phone, and fulfillment option. The `addFulfillmentGroupToOrder` method in `FulfillmentGroupService` is responsible for adding a fulfillment group to an order, which involves setting the fulfillment option and type, and saving the updated order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/type/FulfillmentGroupStatusType.java" line="26">

---

# FulfillmentGroupStatusType Class

The `FulfillmentGroupStatusType` class is an extendible enumeration of fulfillment group status types. It contains different statuses like 'SHIPPED', 'CANCELLED', 'PROCESSING', 'FULFILLED', 'PARTIALLY_FULFILLED', 'DELIVERED', and 'PARTIALLY_DELIVERED'. Each status represents a different stage in the fulfillment process.

```java
/**
 * An extendible enumeration of fulfillment group status types.
 * 
 * @author aangus
 *
 */
public class FulfillmentGroupStatusType implements Serializable, BroadleafEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, FulfillmentGroupStatusType> TYPES = new LinkedHashMap<String, FulfillmentGroupStatusType>();
    
    /**
     * Use FULFILLED, PARTIALLY_FULFILLED, DELIVERED, or PARTIALLY_DELIVERED
     * @deprecated
     */
    @Deprecated
    public static final FulfillmentGroupStatusType SHIPPED = new FulfillmentGroupStatusType("SHIPPED", "Shipped");
    
    /**
     * CANCELLED: Used to indicate that the fulfillment group will not be shipped.
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/FulfillmentGroupServiceImpl.java" line="484">

---

# Using FulfillmentGroupStatusType

The `FulfillmentGroupStatusType` is used in the `FulfillmentGroupServiceImpl` class to find fulfillment groups by their status. This helps in filtering and managing orders based on their current status in the fulfillment process.

```java
    public List<FulfillmentGroup> findFulfillmentGroupsByStatus(
            FulfillmentGroupStatusType status, int start, int maxResults,
            boolean ascending) {
        return fulfillmentGroupDao.readFulfillmentGroupsByStatus(status, start, maxResults, ascending);
    }

    @Override
    public List<FulfillmentGroup> findFulfillmentGroupsByStatus(
            FulfillmentGroupStatusType status, int start, int maxResults) {
        return fulfillmentGroupDao.readFulfillmentGroupsByStatus(status, start, maxResults);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/call/FulfillmentGroupRequest.java" line="29">

---

# FulfillmentGroupRequest Class

The `FulfillmentGroupRequest` class is used to create a new fulfillment group. It contains information like the order, address, phone, fulfillment option, and fulfillment type. This class is used when adding a new fulfillment group to an order.

```java
public class FulfillmentGroupRequest {

    protected Address address;
    protected Order order;
    protected Phone phone;
    
    /**
     * Both of these fields uses are superceded by the FulfillmentOption paradigm
     */
    @Deprecated
    protected String method;
    @Deprecated
    protected String service;
    
    protected FulfillmentOption option;
    
    protected List<FulfillmentGroupItemRequest> fulfillmentGroupItemRequests = new ArrayList<FulfillmentGroupItemRequest>();

    protected FulfillmentType fulfillmentType;

    public Address getAddress() {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
