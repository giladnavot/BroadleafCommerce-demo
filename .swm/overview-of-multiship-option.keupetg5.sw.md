---
title: Overview of Multiship Option
---
The Multiship Option in BroadleafCommerce-demo refers to a feature that allows customers to send different items in their order to different shipping addresses. This is particularly useful in scenarios where a customer is shopping for gifts and wants to send them directly to the recipients. The `OrderMultishipOptionDao` interface and its implementation `OrderMultishipOptionDaoImpl` provide the necessary methods to manage these options, such as saving a new option, retrieving options associated with a specific order or order item, creating a new option instance, and deleting options.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="39">

---

# Saving a Multiship Option

The `save` method is used to save a given `OrderMultishipOption`. The method returns the newly saved instance from Hibernate.

```java
    public OrderMultishipOption save(final OrderMultishipOption orderMultishipOption);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="47">

---

# Reading Multiship Options

The `readOrderMultishipOptions` method is used to retrieve all `OrderMultishipOptions` associated with a given order.

```java
    public List<OrderMultishipOption> readOrderMultishipOptions(Long orderId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="65">

---

# Creating a new Multiship Option

The `create` method is used to create a new `OrderMultishipOption` instance. The default Broadleaf implementation uses the `EntityConfiguration` to create the appropriate implementation class based on the current configuration.

```java
    public OrderMultishipOption create();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="72">

---

# Deleting Multiship Options

The `deleteAll` method is used to remove all of the `OrderMultishipOptions` in the list permanently.

```java
    public void deleteAll(List<OrderMultishipOption> options);
```

---

</SwmSnippet>

# Multiship Option Functions

The Multiship Option functions are primarily concerned with the creation, retrieval, and deletion of OrderMultishipOptions.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="39">

---

## Save Function

The `save` function is used to save a given OrderMultishipOption. It returns the newly saved instance from Hibernate.

```java
    public OrderMultishipOption save(final OrderMultishipOption orderMultishipOption);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="47">

---

## ReadOrderMultishipOptions Function

The `readOrderMultishipOptions` function is used to retrieve all associated OrderMultishipOptions for a given order.

```java
    public List<OrderMultishipOption> readOrderMultishipOptions(Long orderId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="55">

---

## ReadOrderItemOrderMultishipOptions Function

The `readOrderItemOrderMultishipOptions` function is used to retrieve all associated OrderMultishipOptions for a given OrderItem.

```java
    public List<OrderMultishipOption> readOrderItemOrderMultishipOptions(Long orderItemId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="65">

---

## Create Function

The `create` function is used to create a new OrderMultishipOption instance. It uses the EntityConfiguration to create the appropriate implementation class based on the current configuration.

```java
    public OrderMultishipOption create();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/OrderMultishipOptionDao.java" line="72">

---

## DeleteAll Function

The `deleteAll` function is used to remove all of the OrderMultishipOptions in the list permanently.

```java
    public void deleteAll(List<OrderMultishipOption> options);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
