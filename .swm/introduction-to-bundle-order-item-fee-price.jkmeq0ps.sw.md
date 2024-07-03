---
title: Introduction to Bundle Order Item Fee Price
---
The `BundleOrderItemFeePrice` is an interface in the Broadleaf Commerce framework that represents the fee price associated with a bundle order item. It provides methods to get and set properties such as the ID, the associated bundle order item, the amount of the fee, the name of the fee, whether the fee is taxable, and a reporting code. It also provides a method to clone the `BundleOrderItemFeePrice` object.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="25">

---

# BundleOrderItemFeePrice Interface

This is the BundleOrderItemFeePrice interface. It extends Serializable and MultiTenantCloneable, and provides several methods for interacting with the pricing details of a bundle order item.

```java
public interface BundleOrderItemFeePrice extends Serializable, MultiTenantCloneable<BundleOrderItemFeePrice> {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract BundleOrderItem getBundleOrderItem();

    public abstract void setBundleOrderItem(BundleOrderItem bundleOrderItem);

    public abstract Money getAmount();

    public abstract void setAmount(Money amount);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Boolean isTaxable();

    public abstract void setTaxable(Boolean isTaxable);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="31">

---

# getBundleOrderItem Method

The getBundleOrderItem method is used to retrieve the bundle order item associated with the pricing details.

```java
    public abstract BundleOrderItem getBundleOrderItem();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="33">

---

# setBundleOrderItem Method

The setBundleOrderItem method is used to set the bundle order item associated with the pricing details.

```java
    public abstract void setBundleOrderItem(BundleOrderItem bundleOrderItem);

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="35">

---

# getAmount Method

The getAmount method is used to retrieve the amount of the pricing details.

```java
    public abstract Money getAmount();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="37">

---

# setAmount Method

The setAmount method is used to set the amount of the pricing details.

```java
    public abstract void setAmount(Money amount);

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="51">

---

# clone Method

The clone method is used to create a copy of the BundleOrderItemFeePrice object.

```java
    public BundleOrderItemFeePrice clone();

```

---

</SwmSnippet>

# BundleOrderItemFeePrice Interface

The BundleOrderItemFeePrice interface provides several methods to manage the properties of a bundle order item fee price.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/BundleOrderItemFeePrice.java" line="25">

---

## BundleOrderItemFeePrice Interface

The BundleOrderItemFeePrice interface provides methods to get and set the ID, bundle order item, amount, name, taxable status, and reporting code of a bundle order item fee price. It also provides a clone method to create a copy of the BundleOrderItemFeePrice instance.

```java
public interface BundleOrderItemFeePrice extends Serializable, MultiTenantCloneable<BundleOrderItemFeePrice> {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract BundleOrderItem getBundleOrderItem();

    public abstract void setBundleOrderItem(BundleOrderItem bundleOrderItem);

    public abstract Money getAmount();

    public abstract void setAmount(Money amount);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Boolean isTaxable();

    public abstract void setTaxable(Boolean isTaxable);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
