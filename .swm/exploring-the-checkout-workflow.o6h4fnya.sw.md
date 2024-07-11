---
title: Exploring the Checkout Workflow
---
The Checkout Workflow in BroadleafCommerce-demo refers to a series of steps and processes that are executed when a customer completes a purchase. It is a crucial part of the e-commerce transaction process.

The workflow is implemented in the `org.broadleafcommerce.core.checkout.service.workflow` package. This package contains various classes and interfaces that define and manage the checkout process.

Classes like `ValidateCheckoutActivity`, `CheckoutProcessContextFactory`, `CheckoutSeed`, and others, play specific roles in the checkout process. For instance, `ValidateCheckoutActivity` is responsible for validating the checkout process, while `CheckoutSeed` holds the necessary data for the checkout process.

The `org.broadleafcommerce.core.workflow` package is also important in the context of the checkout workflow. It provides the `ProcessContext` and `BaseActivity` classes which are used to manage the workflow process and its activities respectively.

The checkout workflow is designed to be flexible and extensible, allowing for customization and extension to meet specific business requirements. This is facilitated by the use of extension handlers, such as `ValidateCheckoutActivityExtensionHandler`.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="24">

---

# CompositeActivity Class

The `CompositeActivity` class represents a single step in the workflow. It contains a `Processor` object, which represents the entire workflow. The `execute` method is used to perform the operations of the step, and the `setWorkflow` method is used to set the `Processor` object.

```java
public class CompositeActivity extends BaseActivity<ProcessContext<CheckoutSeed>> {

    private Processor workflow;

    /* (non-Javadoc)
     * @see org.broadleafcommerce.core.workflow.Activity#execute(org.broadleafcommerce.core.workflow.ProcessContext)
     */
    @Override
    public ProcessContext<CheckoutSeed> execute(ProcessContext<CheckoutSeed> context) throws Exception {
        ProcessContext<CheckoutSeed> subContext = (ProcessContext<CheckoutSeed>) workflow.doActivities(context.getSeedData());
        if (subContext.isStopped()) {
            context.stopProcess();
        }

        return context;
    }

    public Processor getWorkflow() {
        return workflow;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/SequenceProcessor.java" line="40">

---

# Processor Interface

The `Processor` interface represents the entire workflow. Its `doActivities` method is used to execute all steps in the workflow.

```java
    @Override
    public <P extends ProcessContext<U>> P doActivities() throws WorkflowException {
        return doActivities(null);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="45">

---

# ProcessContext Class

The `ProcessContext` class represents the context in which the workflow is executed. It contains methods such as `getSeedData` and `isStopped` which are used to manage the state of the workflow.

```java
    public boolean isStopped() {
        return stopEntireProcess;
    }

    public T getSeedData() {
        return seedData;
    }
```

---

</SwmSnippet>

# Workflow Functions

This section provides an overview of the key functions involved in the Workflow functionality of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="32">

---

## Workflow Execution

The `execute` function is the main function that drives the workflow. It takes a `ProcessContext` as input and executes the workflow activities on it. If the subContext is stopped, it stops the entire process.

```java
    public ProcessContext<CheckoutSeed> execute(ProcessContext<CheckoutSeed> context) throws Exception {
        ProcessContext<CheckoutSeed> subContext = (ProcessContext<CheckoutSeed>) workflow.doActivities(context.getSeedData());
        if (subContext.isStopped()) {
            context.stopProcess();
        }

        return context;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/SequenceProcessor.java" line="41">

---

## Workflow Activities

The `doActivities` function is called within the `execute` function. It performs the activities defined in the workflow.

```java
    public <P extends ProcessContext<U>> P doActivities() throws WorkflowException {
        return doActivities(null);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="49">

---

## Workflow Data

The `getSeedData` function is used to retrieve the data that is processed in the workflow.

```java
    public T getSeedData() {
        return seedData;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="45">

---

## Workflow Status

The `isStopped` function checks if the workflow process is stopped.

```java
    public boolean isStopped() {
        return stopEntireProcess;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="41">

---

## Workflow Configuration

The `getWorkflow` function retrieves the current workflow.

```java
    public Processor getWorkflow() {
        return workflow;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="45">

---

## Workflow Configuration

The `setWorkflow` function allows for the configuration of the workflow.

```java
    public void setWorkflow(Processor workflow) {
        this.workflow = workflow;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
