---
title: Exploring the Role of OfferService in the Order Processing Workflow
---
This document will cover the role and usage of the `OfferService` in the order processing workflow within the BroadleafCommerce-demo repository. The key areas we will focus on are:

1. The role of `OfferService` in the order processing workflow.
2. How `OfferService` interacts with other services in the workflow.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="146">

---

# Role of OfferService in the order processing workflow

`OfferService` is part of the order processing workflow, as indicated by the `addItemWorkflow` resource. This workflow is responsible for adding items to an order.

```java
    /* Workflows */
    @Resource(name = "blAddItemWorkflow")
    protected Processor addItemWorkflow;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/AddWorkflowPriceOrderIfNecessaryActivity.java" line="28">

---

# Interaction of OfferService with other services

`OfferService` interacts with other services such as `OrderItemService` and `OrderMultishipOptionService` during the order processing workflow. These services are used to manage order items and multi-ship options respectively.

```java
import org.broadleafcommerce.core.order.service.OrderItemService;
import org.broadleafcommerce.core.order.service.OrderMultishipOptionService;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
