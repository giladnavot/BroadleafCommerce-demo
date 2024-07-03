---
title: Understanding Fulfillment Group Fee
---
The Fulfillment Group Fee in Broadleaf Commerce refers to additional charges that may be applied to a Fulfillment Group during the order fulfillment process. This could include handling fees, insurance fees, or any other additional charges that need to be applied at the fulfillment group level. The FulfillmentGroupFee interface provides methods to get and set the amount of the fee, the name of the fee, and whether the fee is taxable. It also provides methods to get and set the taxes applied to the fee, and to calculate the total tax for the fee.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/dao/FulfillmentGroupDaoImpl.java" line="83">

---

## Creating a Fulfillment Group Fee

Here we see the creation of a new Fulfillment Group Fee. This is typically done when a new fee needs to be applied to a Fulfillment Group.

```java
    @Override
    public FulfillmentGroupFee createFulfillmentGroupFee() {
        return ((FulfillmentGroupFee) entityConfiguration.createEntityInstance("org.broadleafcommerce.core.order.domain.FulfillmentGroupFee"));
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroupFee.java" line="36">

---

## Setting Properties of a Fulfillment Group Fee

Here we see the methods for setting the amount and name of a Fulfillment Group Fee. These properties are typically set based on the type of fee being applied.

```java
    public Money getAmount();

    public void setAmount(Money amount);

    public String getName();

    public void setName(String name);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroupImpl.java" line="713">

---

## Adding a Fulfillment Group Fee to a Fulfillment Group

Here we see a Fulfillment Group Fee being added to a Fulfillment Group. This is typically done after the Fee has been created and its properties set.

```java
    @Override
    public void addFulfillmentGroupFee(FulfillmentGroupFee fulfillmentGroupFee) {
        if (fulfillmentGroupFees == null) {
            fulfillmentGroupFees = new ArrayList<FulfillmentGroupFee>();
        }
```

---

</SwmSnippet>

# FulfillmentGroupFee Interface Functions

The FulfillmentGroupFee interface provides several methods to manage and manipulate fees associated with a fulfillment group.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroupFee.java" line="26">

---

## FulfillmentGroupFee Interface

The FulfillmentGroupFee interface provides methods to get and set the ID, associated FulfillmentGroup, amount, name, reporting code, taxable status, taxes, and total tax of a fulfillment group fee. These methods allow for the manipulation and retrieval of important information related to a fee within a fulfillment group.

```java
public interface FulfillmentGroupFee extends Serializable, MultiTenantCloneable<FulfillmentGroupFee> {

    public Long getId();

    public void setId(Long id);

    public FulfillmentGroup getFulfillmentGroup();

    public void setFulfillmentGroup(FulfillmentGroup fulfillmentGroup);

    public Money getAmount();

    public void setAmount(Money amount);

    public String getName();

    public void setName(String name);

    public String getReportingCode();

    public void setReportingCode(String reportingCode);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
