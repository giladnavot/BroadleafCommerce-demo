---
title: Understanding Pricing Workflow
---
Workflow in Pricing refers to the sequence of processes through which a piece of work passes from initiation to completion. It involves various activities and components that work together to calculate and adjust prices for items in an order.

The workflow is implemented in the 'org.broadleafcommerce.core.pricing.service.workflow' package. This package contains several classes, each representing a specific activity in the pricing workflow. These activities include calculating offer changes, consolidating fulfillment fees, calculating total prices, adjusting order payments, and more.

Each activity class extends the 'BaseActivity' class and overrides the 'execute' method. This method contains the logic for that specific activity. For example, the 'FulfillmentItemPricingActivity' class calculates the total price for each item in an order.

The workflow also handles exceptions through the 'WorkflowException' class. If an error occurs during the workflow, a 'WorkflowException' is thrown, allowing the system to handle the error gracefully.

The 'ProcessContext' class is used to pass data between different activities in the workflow. It contains the data that is being processed, such as the order, and is passed as an argument to the 'execute' method of each activity.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/CompositeActivity.java" line="43">

---

# Workflow Implementation

The `setWorkflow` method in the `CompositeActivity` class is used to set the workflow processor. This processor is responsible for executing the workflow.

```java
    public void setWorkflow(Processor workflow) {
        this.workflow = workflow;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/OfferActivity.java" line="28">

---

# ProcessContext

The `ProcessContext` class is used to pass data between different activities in the workflow. It is imported in the `OfferActivity` class, which is one of the activities in the pricing workflow.

```java
import org.broadleafcommerce.core.workflow.ProcessContext;
```

---

</SwmSnippet>

# Workflow Functions in Pricing Service

This section will cover the main functions of the Workflow in the pricing service of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/FulfillmentGroupPricingActivity.java" line="33">

---

## FulfillmentGroupPricingActivity

The `FulfillmentGroupPricingActivity` class calculates the total fulfillment costs for all the FulfillmentGroups on an Order and updates the Order with the total price of all the FulfillmentGroups.

```java
/**
 * Called during the pricing workflow to compute all of the fulfillment costs
 * for all of the FulfillmentGroups on an Order and updates Order with the
 * total price of all of the FufillmentGroups
 * 
 * @author Phillip Verheyden
 * @see {@link FulfillmentGroup}, {@link Order}
 */
@Component("blFulfillmentGroupPricingActivity")
public class FulfillmentGroupPricingActivity extends BaseActivity<ProcessContext<Order>> {

    public static final int ORDER = 5000;
    
    @Resource(name = "blFulfillmentPricingService")
    private FulfillmentPricingService fulfillmentPricingService;

    public FulfillmentGroupPricingActivity() {
        setOrder(ORDER);
    }
    
    public void setFulfillmentPricingService(FulfillmentPricingService fulfillmentPricingService) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/TotalActivity.java" line="20">

---

## TotalActivity

The `TotalActivity` class is part of the pricing workflow. It extends the `BaseActivity` class and is likely used to calculate the total price of an order.

```java
import org.broadleafcommerce.common.currency.util.BroadleafCurrencyUtils;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.core.order.domain.FulfillmentGroup;
import org.broadleafcommerce.core.order.domain.FulfillmentGroupFee;
import org.broadleafcommerce.core.order.domain.FulfillmentGroupItem;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.TaxDetail;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;
import org.springframework.stereotype.Component;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/FulfillmentItemPricingActivity.java" line="26">

---

## FulfillmentItemPricingActivity

The `FulfillmentItemPricingActivity` class extends the `BaseActivity` class and is likely used to calculate the total price for each item in an order.

```java
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;
import org.springframework.stereotype.Component;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/TaxActivity.java" line="21">

---

## TaxActivity

The `TaxActivity` class extends the `BaseActivity` class and is likely used to calculate the tax for an order.

```java
import org.broadleafcommerce.core.pricing.service.TaxService;
import org.broadleafcommerce.core.pricing.service.module.TaxModule;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;
import org.springframework.stereotype.Component;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
