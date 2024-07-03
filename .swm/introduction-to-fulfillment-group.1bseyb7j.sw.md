---
title: Introduction to Fulfillment Group
---
A Fulfillment Group in Broadleaf Commerce represents a group of items from an order that are shipped together to the same location. It is a key component in the order fulfillment process. The `FulfillmentGroupDao` interface provides methods for creating, reading, updating, and deleting Fulfillment Groups. It also provides methods for reading Fulfillment Groups by their status or by their association with an order. The `FulfillmentGroupItemDao` interface, on the other hand, provides similar operations for the items within a Fulfillment Group.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentGroupDao.java" line="27">

---

## FulfillmentGroupDao Interface

The `FulfillmentGroupDao` interface provides the methods for interacting with Fulfillment Groups. It includes methods for creating a new Fulfillment Group (`create`), reading a Fulfillment Group by its ID (`readFulfillmentGroupById`), saving a Fulfillment Group (`save`), and deleting a Fulfillment Group (`delete`). It also provides methods for reading Fulfillment Groups based on their fulfillment status (`readUnfulfilledFulfillmentGroups`, `readPartiallyFulfilledFulfillmentGroups`, `readUnprocessedFulfillmentGroups`, `readFulfillmentGroupsByStatus`).

```java
public interface FulfillmentGroupDao {

    public FulfillmentGroup readFulfillmentGroupById(Long fulfillmentGroupId);

    public FulfillmentGroup save(FulfillmentGroup fulfillmentGroup);

    public FulfillmentGroup readDefaultFulfillmentGroupForOrder(Order order);

    public void delete(FulfillmentGroup fulfillmentGroup);

    public FulfillmentGroup createDefault();

    public FulfillmentGroup create();

    public FulfillmentGroupFee createFulfillmentGroupFee();
    
    /**
     * Reads FulfillmentGroups whose status is not FULFILLED or DELIVERED.
     * @param start
     * @param maxResults
     * @return
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderServiceImpl.java" line="83">

---

## Using FulfillmentGroupDao in Services

The `FulfillmentGroupDao` is injected into services like `LegacyOrderServiceImpl` to be used for handling Fulfillment Groups within the service methods.

```java
    @Resource(name = "blFulfillmentGroupDao")
    protected FulfillmentGroupDao fulfillmentGroupDao;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentGroupDaoImpl.java" line="34">

---

## FulfillmentGroupDao Implementation

`FulfillmentGroupDaoImpl` is the implementation of the `FulfillmentGroupDao` interface, providing the actual logic for the interface methods.

```java
@Repository("blFulfillmentGroupDao")
public class FulfillmentGroupDaoImpl implements FulfillmentGroupDao {
```

---

</SwmSnippet>

# Fulfillment Group Functions

The Fulfillment Group in BroadleafCommerce-demo is a crucial part of the order management system. It handles various aspects of order fulfillment, including reading, saving, and deleting fulfillment groups, creating default fulfillment groups, and managing fulfillment group fees.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentGroupDao.java" line="27">

---

## Fulfillment Group Functions

The FulfillmentGroupDao interface declares several methods for managing Fulfillment Groups. These include methods for reading a Fulfillment Group by its ID, saving a Fulfillment Group, deleting a Fulfillment Group, creating a default Fulfillment Group, creating a Fulfillment Group, and creating a Fulfillment Group Fee. It also includes methods for reading unfulfilled, partially fulfilled, unprocessed, and status-specific Fulfillment Groups.

```java
public interface FulfillmentGroupDao {

    public FulfillmentGroup readFulfillmentGroupById(Long fulfillmentGroupId);

    public FulfillmentGroup save(FulfillmentGroup fulfillmentGroup);

    public FulfillmentGroup readDefaultFulfillmentGroupForOrder(Order order);

    public void delete(FulfillmentGroup fulfillmentGroup);

    public FulfillmentGroup createDefault();

    public FulfillmentGroup create();

    public FulfillmentGroupFee createFulfillmentGroupFee();
    
    /**
     * Reads FulfillmentGroups whose status is not FULFILLED or DELIVERED.
     * @param start
     * @param maxResults
     * @return
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentGroupItemDao.java" line="25">

---

## Fulfillment Group Item Functions

The FulfillmentGroupItemDao interface declares several methods for managing Fulfillment Group Items. These include methods for reading a Fulfillment Group Item by its ID, saving a Fulfillment Group Item, deleting a Fulfillment Group Item, and creating a Fulfillment Group Item. It also includes a method for reading Fulfillment Group Items for a specific Fulfillment Group.

```java
public interface FulfillmentGroupItemDao {

    FulfillmentGroupItem readFulfillmentGroupItemById(Long fulfillmentGroupItemId);

    FulfillmentGroupItem save(FulfillmentGroupItem fulfillmentGroupItem);

    List<FulfillmentGroupItem> readFulfillmentGroupItemsForFulfillmentGroup(FulfillmentGroup fulfillmentGroup);

    void delete(FulfillmentGroupItem fulfillmentGroupItem);

    FulfillmentGroupItem create();
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
