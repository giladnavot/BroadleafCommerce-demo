---
title: Understanding the Resource Request Extension Manager
---
The ResourceRequestExtensionManager is a class that extends the ExtensionManager with a specific type of ResourceRequestExtensionHandler. It is annotated as a Service, meaning it is a Spring-managed bean and can be autowired into other components. The ResourceRequestExtensionManager is responsible for managing the lifecycle of ResourceRequestExtensionHandlers, which are used to handle resource requests in the application.

The ResourceRequestExtensionManager has a method called 'continueOnHandled'. This method is overridden from the ExtensionManager class and it returns false. This means that the processing of handlers stops as soon as one handler returns a 'handled' status. In other words, the first handler to handle a request will be the only one to process it.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionManager.java" line="24">

---

# ResourceRequestExtensionManager Class

This is the ResourceRequestExtensionManager class. It extends the ExtensionManager class and is annotated with @Service, indicating that it is a Spring service. The class has a constructor that calls the superclass constructor with ResourceRequestExtensionHandler.class as an argument, indicating that this manager is for handling extensions of resource requests.

```java
public class ResourceRequestExtensionManager extends ExtensionManager<ResourceRequestExtensionHandler>{

    public ResourceRequestExtensionManager() {
        super(ResourceRequestExtensionHandler.class);
    }

    /**
     * The first handler to return a handled status will win
     */
    @Override
    public boolean continueOnHandled() {
        return false;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionManager.java" line="33">

---

# continueOnHandled Method

This is the continueOnHandled method of the ResourceRequestExtensionManager class. It overrides the method from the superclass and returns false, indicating that the processing of handlers should stop once a handler has handled the request.

```java
    @Override
    public boolean continueOnHandled() {
        return false;
    }
```

---

</SwmSnippet>

# ResourceRequestExtensionManager Functions

The ResourceRequestExtensionManager class

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionManager.java" line="24">

---

## ResourceRequestExtensionManager Class

The ResourceRequestExtensionManager class extends the ExtensionManager class. It is annotated as a Service, which means it is an object that Spring instantiates, assembles, and manages. The class has a constructor that calls the super constructor with ResourceRequestExtensionHandler.class as an argument, indicating that this manager is specifically for handling resource requests.

```java
public class ResourceRequestExtensionManager extends ExtensionManager<ResourceRequestExtensionHandler>{

    public ResourceRequestExtensionManager() {
        super(ResourceRequestExtensionHandler.class);
    }

    /**
     * The first handler to return a handled status will win
     */
    @Override
    public boolean continueOnHandled() {
        return false;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionManager.java" line="33">

---

## continueOnHandled Method

The continueOnHandled method is overridden in this class. This method determines whether the manager should continue processing handlers once a handler has been successfully handled. In this case, the method returns false, indicating that processing should stop once a handler has been successfully handled.

```java
    @Override
    public boolean continueOnHandled() {
        return false;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
