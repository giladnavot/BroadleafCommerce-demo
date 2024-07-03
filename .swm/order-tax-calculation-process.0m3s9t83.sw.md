---
title: Order Tax Calculation Process
---
This document will cover the process of calculating and applying tax for an order in the BroadleafCommerce-demo repository. The process involves the following steps:

1. Calculating tax for an order
2. Handling taxes for fulfillment group items
3. Applying tax factor
4. Setting the type of shipping service
5. Adding the calculated tax to a distributed queue

```mermaid
graph TD;
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service
  calculateTaxForOrder:::mainFlowStyle --> handleFulfillmentGroupItemTaxes
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service
  calculateTaxForOrder:::mainFlowStyle --> handleFulfillmentGroupFeeTaxes
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service
  calculateTaxForOrder:::mainFlowStyle --> handleFulfillmentGroupTaxes
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service
  handleFulfillmentGroupTaxes:::mainFlowStyle --> applyTaxFactor
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  applyTaxFactor:::mainFlowStyle --> add
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service
  applyTaxFactor:::mainFlowStyle --> setType
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  setType --> put
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  put --> add
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  add --> writeToQueue
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  writeToQueue --> lockInterruptibly
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  writeToQueue --> tryLock
end

classDef mainFlowStyle color:#000000,fill:#7CB9F4
classDef rootsStyle color:#000000,fill:#00FFF4
classDef Style1 color:#000000,fill:#00FFAA
classDef Style2 color:#000000,fill:#FFFF00
classDef Style3 color:#000000,fill:#AA7CB9
```

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/tax/provider/SimpleTaxProvider.java" line="99">

---

# Calculating tax for an order

The `calculateTaxForOrder` function initiates the tax calculation process. It calls `handleFulfillmentGroupItemTaxes`, `handleFulfillmentGroupFeeTaxes`, and `handleFulfillmentGroupTaxes` to calculate taxes for different components of the order.

```java
    protected void handleFulfillmentGroupItemTaxes(FulfillmentGroup fulfillmentGroup) {
        for (FulfillmentGroupItem fgItem : fulfillmentGroup.getFulfillmentGroupItems()) {
            if (isItemTaxable(fgItem)) {
                applyTaxFactor(fgItem.getTaxes(), determineItemTaxRate(fulfillmentGroup.getAddress()), fgItem.getTotalItemTaxableAmount());
            }
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/tax/provider/SimpleTaxProvider.java" line="99">

---

# Handling taxes for fulfillment group items

`handleFulfillmentGroupItemTaxes` is responsible for calculating taxes for each item in the fulfillment group. It checks if the item is taxable and then applies the tax factor to the taxable amount of the item.

```java
    protected void handleFulfillmentGroupItemTaxes(FulfillmentGroup fulfillmentGroup) {
        for (FulfillmentGroupItem fgItem : fulfillmentGroup.getFulfillmentGroupItems()) {
            if (isItemTaxable(fgItem)) {
                applyTaxFactor(fgItem.getTaxes(), determineItemTaxRate(fulfillmentGroup.getAddress()), fgItem.getTotalItemTaxableAmount());
            }
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/tax/provider/SimpleTaxProvider.java" line="119">

---

# Applying tax factor

`applyTaxFactor` applies the calculated tax factor to the taxable amount. If the tax factor is non-zero, it either updates the existing tax record or creates a new one. If the tax factor is zero, it removes the existing tax record.

```java
    protected void applyTaxFactor(List<TaxDetail> taxes, BigDecimal taxFactor, Money taxMultiplier) {
        TaxDetail tax = findExistingTaxDetail(taxes);
        boolean shouldUpdateOrCreateTaxRecord = taxFactor != null && taxFactor.compareTo(BigDecimal.ZERO) != 0;
        boolean shouldRemoveTaxRecord = (taxFactor == null || taxFactor.compareTo(BigDecimal.ZERO) == 0) && tax != null;
        if (shouldUpdateOrCreateTaxRecord) {
            if (tax == null) {
                tax = entityConfig.createEntityInstance(TaxDetail.class.getName(), TaxDetail.class);
                tax.setType(TaxType.COMBINED);
                taxes.add(tax);
            }
            tax.setRate(taxFactor);
            tax.setAmount(taxMultiplier.multiply(taxFactor));
        } else if (shouldRemoveTaxRecord) {
            taxes.remove(tax);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/type/ShippingServiceType.java" line="1">

---

# Setting the type of shipping service

`setType` sets the type of the shipping service. This is important as different types of shipping services may have different tax rates.

```java
/*-
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util/queue/ZookeeperDistributedQueue.java" line="393">

---

# Adding the calculated tax to a distributed queue

The `put` function adds the calculated tax to a distributed queue. This queue can be used to process the tax information asynchronously.

```java
    @Override
    public void put(T e) throws InterruptedException {
        final ArrayList<T> elementsToAdd = new ArrayList<>();
        elementsToAdd.add(e);
        writeToQueue(elementsToAdd, -1L);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="flows"><sup>Powered by [Swimm](/)</sup></SwmMeta>
