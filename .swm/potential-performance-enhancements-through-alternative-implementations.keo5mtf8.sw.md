---
title: Potential Performance Enhancements through Alternative Implementations
---
This document will cover the topic of how alternative implementations can enhance performance in the BroadleafCommerce-demo repository. We'll cover:

1. The use of BroadleafSystemEvent for performance optimization.
2. The impact of large number of records on performance in URLHandlerService.
3. The role of RollbackHandler in maintaining system performance.
4. The use of GenericOperation for executing operations in a particular context.
5. The impact of alternative implementations on system performance in BroadleafControllerUtility.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/event/BroadleafSystemEvent.java" line="24">

---

# BroadleafSystemEvent for Performance Optimization

The BroadleafSystemEvent class is used to communicate with the ScheduledJobsAndEvents module without having a dependency on it. It is designed to optimize performance by combining multiple events into one, as indicated in the `isUniversal` method.

```java
/**
 * <p>
 * A BroadleafApplicationEvent used so that we can communicate with the ScheduledJobsAndEvents module without having a dependency on it.
 * By publishing a Spring Event with this detail, the ScheduledJobsAndEvents module will listen for this event and create a corresponding
 * com.broadleafcommerce.jobsevents.domain.SystemEvent to be consumed.
 *
 * <p>
 * To send an event, inject the {@link ApplicationContext} and publish the event:
 *
 * <pre>
 * {@literal @}Autowired
 * private ApplicationContext appCtx;
 *
 * ...
 *
 * appCtx.publishEvent(new BroadleafSystemEvent("CONSUMER_TYPE", BroadleafEventScopeType.VM, BroadleafEventWorkerType.SITE, true);
 * </pre>
 *
 * @see ApplicationContext#publishEvent(org.springframework.context.ApplicationEvent)
 * @author Jay Aisenbrey (cja769)
 */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerService.java" line="25">

---

# Impact of Large Number of Records on Performance in URLHandlerService

The URLHandlerService interface warns about potential performance and memory issues when dealing with a large number of records. This is particularly relevant for the `findAllURLHandlers` and `findAllRegexURLHandlers` methods.

```java
/**
 * Created by bpolster.
 */
public interface URLHandlerService {

    /**
     * Checks the passed in URL to determine if there is a matching URLHandler.
     * Returns null if no handler was found.
     *
     * @param uri
     * @return
     */
    public URLHandler findURLHandlerByURI(String uri);

    /**
     * Be cautious when calling this.  If there are a large number of records, this can cause performance and
     * memory issues.
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/workflow/state/RollbackHandler.java" line="25">

---

# Role of RollbackHandler in Maintaining System Performance

The RollbackHandler interface is responsible for performing compensating operations to revert the state of the activity to what it was prior to execution. This helps in maintaining system performance in the event of a workflow execution problem.

```java
/**
 * Implementations are responsible for performing compensating operations to revert the state of the activity to what it
 * was prior to execution. Activity, ProcessContext and stateConfiguration variables can be used to gather the necessary
 * information to successfully perform the compensating operation.
 *
 * @author Jeff Fischer
 */
public interface RollbackHandler<T extends ProcessContext<?>> {

    /**
     * Rollback the state of the activity to what it was prior to execution.
     *
     * @param activity The Activity instance whose state is being reverted
     * @param processContext The ProcessContext for the workflow
     * @param stateConfiguration Any user-defined state configuration associated with the RollbackHandler
     * @throws RollbackFailureException if there is a failure during the execution of the rollback
     */
    public void rollbackState(Activity<T> activity,
            T processContext, Map<String, Object> stateConfiguration) throws RollbackFailureException;

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/util/GenericOperation.java" line="26">

---

# Use of GenericOperation for Executing Operations

The GenericOperation interface allows for a generic operation that can be executed in a particular context. This provides flexibility in executing operations and can potentially enhance performance.

```java
public interface GenericOperation<R> {

    /**
     * Returns R, the return value and throws T, the Throwable.  Use {@link Void} as the return type 
     * and return null if void is the expected return type.
     * @return
     * @throws T
     */
    public R execute() throws Exception;
    
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/BroadleafControllerUtility.java" line="28">

---

# Impact of Alternative Implementations on System Performance in BroadleafControllerUtility

The BroadleafControllerUtility class provides methods to determine if a request was invoked via an AJAX call. This can enhance performance by allowing the server to respond differently to AJAX requests compared to regular HTTP requests.

```java
/**
 * Commonly used Broadleaf Controller operations.
 * - ajaxRedirects
 * - isAjaxRequest
 * - ajaxRender   
 * 
 * BroadleafAbstractController provides convenience methods for this functionality.
 * Implementors who are not able (or willing) to have their Controllers extend
 * BroadleafAbstractController can utilize this utility class to achieve some of
 * the same benefits.
 * 
 * 
 * @author bpolster
 */
public class BroadleafControllerUtility {
    protected static final Log LOG = LogFactory.getLog(BroadleafControllerUtility.class);
    
    public static final String BLC_REDIRECT_ATTRIBUTE = "blc_redirect";
    public static final String BLC_AJAX_PARAMETER = "blcAjax";
    
    /**
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
