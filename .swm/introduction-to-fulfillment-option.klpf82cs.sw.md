---
title: Introduction to Fulfillment Option
---
Fulfillment Option in BroadleafCommerce-demo refers to the different ways an order can be delivered to a customer. It's an integral part of the order management system, allowing the system to handle various delivery methods like shipping, pickup, digital download, etc. The `FulfillmentOption` interface and its implementation `FulfillmentOptionImpl` provide the structure for storing these options. The `FulfillmentOptionDao` interface and its implementation `FulfillmentOptionDaoImpl` provide the methods for interacting with the database, such as saving a fulfillment option, reading a fulfillment option by its ID, and retrieving all fulfillment options.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDao.java" line="29">

---

# FulfillmentOptionDao Interface

The FulfillmentOptionDao interface provides the necessary methods to interact with FulfillmentOption entities. It includes methods to read a FulfillmentOption by its ID, save a FulfillmentOption, read all FulfillmentOptions, and read all FulfillmentOptions by their FulfillmentType.

```java
public interface FulfillmentOptionDao {

    public FulfillmentOption readFulfillmentOptionById(final Long fulfillmentOptionId);

    public FulfillmentOption save(FulfillmentOption option);
    
    public List<FulfillmentOption> readAllFulfillmentOptions();

    public List<FulfillmentOption> readAllFulfillmentOptionsByFulfillmentType(FulfillmentType type);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDaoImpl.java" line="36">

---

# FulfillmentOptionDaoImpl Class

The FulfillmentOptionDaoImpl class is the implementation of the FulfillmentOptionDao interface. It uses the EntityManager to interact with the database and perform operations such as reading, saving, and retrieving FulfillmentOptions.

```java
@Repository("blFulfillmentOptionDao")
public class FulfillmentOptionDaoImpl implements FulfillmentOptionDao {

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Override
    public FulfillmentOption readFulfillmentOptionById(final Long fulfillmentOptionId) {
        return em.find(FulfillmentOptionImpl.class, fulfillmentOptionId);
    }

    @Override
    public FulfillmentOption save(FulfillmentOption option) {
        return em.merge(option);
    }

    @Override
    public List<FulfillmentOption> readAllFulfillmentOptions() {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/FulfillmentOptionServiceImpl.java" line="37">

---

# Using FulfillmentOptionDao in FulfillmentOptionServiceImpl

The FulfillmentOptionDao is used in the FulfillmentOptionServiceImpl class. An instance of FulfillmentOptionDao is injected using the @Resource annotation, which allows the service class to use the methods provided by the FulfillmentOptionDao interface.

```java
    @Resource(name = "blFulfillmentOptionDao")
    protected FulfillmentOptionDao fulfillmentOptionDao;
```

---

</SwmSnippet>

# Fulfillment Option Functions

This section provides an overview of the main functions in the FulfillmentOptionDao interface and its implementation.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDao.java" line="31">

---

## readFulfillmentOptionById

The `readFulfillmentOptionById` function retrieves a `FulfillmentOption` by its ID. It's used when there's a need to fetch specific fulfillment option details.

```java
    public FulfillmentOption readFulfillmentOptionById(final Long fulfillmentOptionId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDao.java" line="33">

---

## save

The `save` function is used to persist a `FulfillmentOption` entity to the database. It's used when creating or updating a fulfillment option.

```java
    public FulfillmentOption save(FulfillmentOption option);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDao.java" line="35">

---

## readAllFulfillmentOptions

The `readAllFulfillmentOptions` function retrieves all `FulfillmentOption` entities. It's used when there's a need to fetch all available fulfillment options.

```java
    public List<FulfillmentOption> readAllFulfillmentOptions();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentOptionDao.java" line="37">

---

## readAllFulfillmentOptionsByFulfillmentType

The `readAllFulfillmentOptionsByFulfillmentType` function retrieves all `FulfillmentOption` entities of a specific `FulfillmentType`. It's used when there's a need to fetch all available fulfillment options of a specific type.

```java
    public List<FulfillmentOption> readAllFulfillmentOptionsByFulfillmentType(FulfillmentType type);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
