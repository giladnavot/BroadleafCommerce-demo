---
title: Understanding Workflow in Broadleaf Commerce
---
Workflow in BroadleafCommerce-demo refers to a sequence of activities that are executed to perform specific operations. It is a key component of the Broadleaf Commerce framework, which is designed to facilitate the development of commerce-driven sites. The workflow is managed by the Workflow Processor, which keeps track of an ordered collection of activities. Each activity in the workflow is an operation that should be executed. Activities can be executed based on their order and their specific conditions. If an error occurs during the execution of an activity, the workflow can handle the error and perform a rollback operation to revert the changes made by the activity.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/Activity.java" line="43">

---

# Activity Interface

The Activity interface defines the methods that each activity in a workflow must implement. This includes the execute method, which performs the activity's task, and the shouldExecute method, which determines if the activity should be executed based on the current process context.

```java
public interface Activity<T extends ProcessContext<?>> extends BeanNameAware, Ordered {

    /**
     * Called by the encompassing processor to activate
     * the execution of the Activity
     * 
     * @param context - process context for this workflow
     * @return resulting process context
     * @throws Exception
     */
    public T execute(T context) throws Exception;

    /**
     * Determines if an activity should execute based on the current values in the {@link ProcessContext}. For example, a
     * context might have both an {@link Order} as well as a String 'status' of what the order should be changed to. It is
     * possible that an activity in a workflow could only deal with a particular status change, and thus could return false
     * from this method.
     * 
     * @param context
     * @return
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/WorkflowException.java" line="22">

---

# WorkflowException Class

The WorkflowException class is used to handle errors that occur during the execution of a workflow. It extends the BroadleafException class, and can be thrown when an error occurs in an activity.

```java
public class WorkflowException extends BroadleafException {

    private static final long serialVersionUID = 1L;

    public WorkflowException() {
        super();
    }

    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowException(String message) {
        super(message);
    }

    public WorkflowException(Throwable cause) {
        super(cause);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/SequenceProcessor.java" line="45">

---

# SequenceProcessor Class

The SequenceProcessor class is responsible for executing the activities in a workflow in the defined sequence. It checks if each activity should be executed, executes it if necessary, and handles any exceptions that are thrown.

```java
    @Override
    public <P extends ProcessContext<U>> P doActivities(T seedData) throws WorkflowException {
        if (LOG.isDebugEnabled()) {
            LOG.debug(getBeanName() + " processor is running..");
        }
        ActivityStateManager activityStateManager = getBeanFactory().getBean(ActivityStateManager.class, "blActivityStateManager");
        if (activityStateManager == null) {
            throw new IllegalStateException("Unable to find an instance of ActivityStateManager registered under bean id blActivityStateManager");
        }
        ProcessContext<U> context = null;
        
        RollbackStateLocal rollbackStateLocal = new RollbackStateLocal();
        rollbackStateLocal.setThreadId(String.valueOf(Thread.currentThread().getId()));
        rollbackStateLocal.setWorkflowId(getBeanName());
        RollbackStateLocal.setRollbackStateLocal(rollbackStateLocal);
        
        try {
            //retrieve injected by Spring
            List<Activity<ProcessContext<U>>> activities = getActivities();

            //retrieve a new instance of the Workflow ProcessContext
```

---

</SwmSnippet>

# Workflow Functions

In the context of BroadleafCommerce-demo, the Workflow functions are a crucial part of the e-commerce framework. They handle the flow of processes and manage exceptions during the workflow execution. The main functions in this context are WorkflowException, Activity, and ProcessContext.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/WorkflowException.java" line="22">

---

## WorkflowException

The `WorkflowException` class extends `BroadleafException`. It is a custom exception class used in the workflow process. It is thrown when there is an exception during the workflow execution. It has several constructors to handle different types of exceptions.

```java
public class WorkflowException extends BroadleafException {

    private static final long serialVersionUID = 1L;

    public WorkflowException() {
        super();
    }

    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowException(String message) {
        super(message);
    }

    public WorkflowException(Throwable cause) {
        super(cause);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/Activity.java" line="22">

---

## Activity

The `Activity` interface defines the structure of an activity in the workflow. An activity is a single unit of work in the workflow. It has methods like `execute` to perform the activity and `shouldExecute` to check if the activity should be executed based on the current context. It also has methods to handle errors during the execution of the activity.

```java
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * <p>
 * Interface to be used for workflows in Broadleaf. Usually implementations will subclass {@link BaseActivity}.
 * </p>
 * 
 * Important note: if you are writing a 3rd-party integration module or adding a module outside of the Broadleaf core, your
 * activity should implement the {@link ModuleActivity} interface as well. This ensures that there is proper logging
 * for users that are using your module so that they know exactly what their final workflow configuration looks like.
 *
 * @author Phillip Verheyden (phillipuniverse)
 * @param <T>
 * @see {@link BaseActivity}
 * @see {@link ModuleActivity}
 * @see {@link BaseProcessor}
 * @see {@link SequenceProcessor}
 */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/ProcessContext.java" line="22">

---

## ProcessContext

The `ProcessContext` interface represents the context of the workflow process. It provides methods to stop the process, check if the process is stopped, and set and get seed data. The seed data is the initial data for the workflow.

```java
public interface ProcessContext<T> extends Serializable {

    /**
     * Activly informs the workflow process to stop processing
     * no further activities will be executed
     *
     * @return whether or not the stop process call was successful
     */
    public boolean stopProcess();

    /**
     * Is the process stopped
     *
     * @return whether or not the process is stopped
     */
    public boolean isStopped();

    /**
     * Provide seed information to this ProcessContext, usually
     * provided at time of workflow kickoff by the containing
     * workflow processor.
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
