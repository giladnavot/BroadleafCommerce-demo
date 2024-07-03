---
title: Getting Started with Workflow
---
Workflow in BroadleafCommerce-demo refers to a sequence of activities that are executed in the checkout process. It is represented by the `Processor` interface and its implementations. The `CompositeActivity` class is a key part of the workflow, as it executes a series of activities defined in a `Processor`. This execution can be stopped at any point if a condition is met. The `Workflow` is set and retrieved using the `setWorkflow` and `getWorkflow` methods respectively.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="24">

---

## CompositeActivity Class

The CompositeActivity class is a key part of the workflow. It contains the execute method which initiates and manages the sequence of activities during the checkout process. It also contains the setWorkflow method which is used to set the workflow processor.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="32">

---

## Execute Method

The execute method is where the workflow is initiated. It calls the doActivities method to start the sequence of activities. It also checks if the process is stopped and if so, it stops the entire process.

```java
    public ProcessContext<CheckoutSeed> execute(ProcessContext<CheckoutSeed> context) throws Exception {
        ProcessContext<CheckoutSeed> subContext = (ProcessContext<CheckoutSeed>) workflow.doActivities(context.getSeedData());
        if (subContext.isStopped()) {
            context.stopProcess();
        }

        return context;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/SequenceProcessor.java" line="40">

---

## doActivities Method

The doActivities method is called by the execute method. It is responsible for executing the sequence of activities in the workflow.

```java
    @Override
    public <P extends ProcessContext<U>> P doActivities() throws WorkflowException {
        return doActivities(null);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="49">

---

## getSeedData Method

The getSeedData method is used to retrieve the data that is used in the workflow process.

```java
    public T getSeedData() {
        return seedData;
    }
```

---

</SwmSnippet>

# Workflow Functions

This section will cover the key functions of the workflow in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="24">

---

## CompositeActivity

The `CompositeActivity` class extends `BaseActivity` and represents a composite activity in the workflow. It contains a `Processor` object that represents the workflow to be executed.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="32">

---

## execute

The `execute` method is used to execute the workflow. It takes a `ProcessContext` object as input and returns a `ProcessContext` object. The method calls the `doActivities` method of the workflow with the seed data from the context. If the subcontext is stopped, the process is stopped.

```java
    public ProcessContext<CheckoutSeed> execute(ProcessContext<CheckoutSeed> context) throws Exception {
        ProcessContext<CheckoutSeed> subContext = (ProcessContext<CheckoutSeed>) workflow.doActivities(context.getSeedData());
        if (subContext.isStopped()) {
            context.stopProcess();
        }

        return context;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/SequenceProcessor.java" line="40">

---

## doActivities

The `doActivities` method is used to execute the activities in the workflow. It can take an optional parameter, which is not used in the `execute` method of `CompositeActivity`.

```java
    @Override
    public <P extends ProcessContext<U>> P doActivities() throws WorkflowException {
        return doActivities(null);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="49">

---

## getSeedData

The `getSeedData` method is used to get the seed data from the context. The seed data is used as input for the `doActivities` method of the workflow.

```java
    public T getSeedData() {
        return seedData;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/DefaultProcessContextImpl.java" line="45">

---

## isStopped

The `isStopped` method is used to check if the process is stopped. If the process is stopped, the `execute` method of `CompositeActivity` stops the process.

```java
    public boolean isStopped() {
        return stopEntireProcess;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/checkout/service/workflow/CompositeActivity.java" line="41">

---

## getWorkflow and setWorkflow

The `getWorkflow` and `setWorkflow` methods are used to get and set the workflow of the `CompositeActivity`, respectively.

```java
    public Processor getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Processor workflow) {
        this.workflow = workflow;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
