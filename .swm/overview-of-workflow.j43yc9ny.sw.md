---
title: Overview of Workflow
---
Workflow in BroadleafCommerce-demo refers to a series of activities that are performed to complete a specific task. It is represented by the `Processor` interface and its implementations. The `setWorkflow` and `getWorkflow` methods in `CompositeActivity.java` are used to set and retrieve the workflow respectively. The `ProcessContext` interface is used to hold the state of the workflow and is passed between activities. The `BaseActivity` class is a base implementation of an activity in the workflow. The `WorkflowException` is thrown when there is an error during the execution of the workflow.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/CompositeActivity.java" line="43">

---

# Setting up a Workflow

Here, the `setWorkflow` method is used to assign a Processor instance to the `workflow` variable. This Processor instance represents the workflow that will be used in this composite activity.

```java
    public void setWorkflow(Processor workflow) {
        this.workflow = workflow;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/OfferActivity.java" line="28">

---

# Workflow in Action

The `ProcessContext` class is part of the workflow package and is used to maintain the state of the workflow as it progresses. It is imported here for use in the `OfferActivity` class, indicating that this class is part of a workflow.

```java
import org.broadleafcommerce.core.workflow.ProcessContext;
```

---

</SwmSnippet>

# Workflow Functions

Workflow Functions

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/CompositeActivity.java" line="39">

---

## getWorkflow

The `getWorkflow` function is used to retrieve the current workflow processor. This processor is responsible for managing the sequence of activities in the workflow.

```java
    public Processor getWorkflow() {
        return workflow;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/CompositeActivity.java" line="43">

---

## setWorkflow

The `setWorkflow` function is used to set the workflow processor. This allows the system to change the sequence of activities in the workflow dynamically.

```java
    public void setWorkflow(Processor workflow) {
        this.workflow = workflow;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/FulfillmentGroupPricingActivity.java" line="57">

---

## execute

The `execute` function is a key part of the workflow. It is responsible for executing the current activity in the workflow. In this case, it calculates the cost for each fulfillment group in the order.

```java
    @Override
    public ProcessContext<Order> execute(ProcessContext<Order> context) throws Exception {
        Order order = context.getSeedData();

        /*
         * 1. Get FGs from Order
         * 2. take each FG and call shipping module with the shipping svc
         * 3. add FG back to order
         */

        Money totalFulfillmentCharges = BroadleafCurrencyUtils.getMoney(BigDecimal.ZERO, order.getCurrency());
        for (FulfillmentGroup fulfillmentGroup : order.getFulfillmentGroups()) {
            if (fulfillmentGroup != null) {
                if (!fulfillmentGroup.getShippingOverride()) {
                    fulfillmentGroup = fulfillmentPricingService.calculateCostForFulfillmentGroup(fulfillmentGroup);
                }
                if (fulfillmentGroup.getFulfillmentPrice() != null) {
                    totalFulfillmentCharges = totalFulfillmentCharges.add(fulfillmentGroup.getFulfillmentPrice());
                }
            }
        }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
