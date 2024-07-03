---
title: Overview of Dynamic Price Discrete Order Item
---
The `DynamicPriceDiscreteOrderItem` is an interface in the Broadleaf Commerce framework that extends the `DiscreteOrderItem` interface. It represents a specific type of order item in the e-commerce system that has a dynamic price. This means that the price of these items can change based on various factors, such as quantity, customer type, or promotional offers. The `DynamicPriceDiscreteOrderItem` is used in various parts of the codebase, including the `OrderItemVisitor` and `OrderItemVisitorAdapter` classes, which handle different types of order items in the system. It's also used in the `OrderItemType` class to define the type of the order item.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/DynamicPriceDiscreteOrderItem.java" line="25">

---

# DynamicPriceDiscreteOrderItem Interface

This is the declaration of the DynamicPriceDiscreteOrderItem interface. It extends the DiscreteOrderItem interface.

```java
public interface DynamicPriceDiscreteOrderItem extends DiscreteOrderItem {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/manipulation/OrderItemVisitor.java" line="32">

---

# Usage in OrderItemVisitor

DynamicPriceDiscreteOrderItem is used as a parameter type in the visit method of the OrderItemVisitor interface. This allows the visit method to accept objects of type DynamicPriceDiscreteOrderItem.

```java
    public void visit(DynamicPriceDiscreteOrderItem dynamicPriceDiscreteOrderItem) throws PricingException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/DynamicPriceDiscreteOrderItemImpl.java" line="39">

---

# Implementation in DynamicPriceDiscreteOrderItemImpl

DynamicPriceDiscreteOrderItem is implemented in the DynamicPriceDiscreteOrderItemImpl class. This class is a specific type of DiscreteOrderItem that can have a dynamic price.

```java
public class DynamicPriceDiscreteOrderItemImpl extends DiscreteOrderItemImpl implements DynamicPriceDiscreteOrderItem {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/resources/bl-framework-applicationContext-entity.xml" line="41">

---

# Bean Definition in ApplicationContext

A bean of type DynamicPriceDiscreteOrderItem is defined in the application context. The class used for this bean is DynamicPriceDiscreteOrderItemImpl.

```xml
    <bean id="org.broadleafcommerce.core.order.domain.DynamicPriceDiscreteOrderItem" class="org.broadleafcommerce.core.order.domain.DynamicPriceDiscreteOrderItemImpl" scope="prototype"/>
```

---

</SwmSnippet>

# DynamicPriceDiscreteOrderItem Interface and Its Usage

DynamicPriceDiscreteOrderItem Interface

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/DynamicPriceDiscreteOrderItem.java" line="25">

---

## DynamicPriceDiscreteOrderItem

The `DynamicPriceDiscreteOrderItem` interface extends `DiscreteOrderItem`. It doesn't define any methods or fields of its own, but it is used as a type in several other parts of the codebase.

```java
public interface DynamicPriceDiscreteOrderItem extends DiscreteOrderItem {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/manipulation/OrderItemVisitor.java" line="32">

---

## Usage in OrderItemVisitor

`DynamicPriceDiscreteOrderItem` is used as a parameter type in the `visit` method of the `OrderItemVisitor` interface. This suggests that `DynamicPriceDiscreteOrderItem` instances can be visited by an `OrderItemVisitor`.

```java
    public void visit(DynamicPriceDiscreteOrderItem dynamicPriceDiscreteOrderItem) throws PricingException;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/type/OrderItemType.java" line="39">

---

## Usage in OrderItemType

`DynamicPriceDiscreteOrderItem` is used in the `OrderItemType` class to define a constant for externally priced discrete order items. This suggests that `DynamicPriceDiscreteOrderItem` instances can represent externally priced discrete order items.

```java
    public static final OrderItemType EXTERNALLY_PRICED  = new OrderItemType("org.broadleafcommerce.core.order.domain.DynamicPriceDiscreteOrderItem", "Externally Priced Discrete Order Item");
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
