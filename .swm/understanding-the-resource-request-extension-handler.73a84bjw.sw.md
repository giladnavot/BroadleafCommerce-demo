---
title: Understanding the Resource Request Extension Handler
---
The Resource Request Extension Handler in BroadleafCommerce-demo is an interface that provides extension points for dealing with requests for resources. It extends the ExtensionHandler interface and defines two methods: `getModifiedResource` and `getOverrideResource`. These methods are designed to populate the `RESOURCE_ATTR` field in the `ExtensionResultHolder` map with an instance of `Resource` if the value of the resource has been modified or if there is an override resource available for the current path, respectively.

The `AbstractResourceRequestExtensionHandler` class is an implementation of the `ResourceRequestExtensionHandler` interface. It provides default implementations for the `getModifiedResource` and `getOverrideResource` methods, both of which return `ExtensionResultStatusType.NOT_HANDLED`. This indicates that by default, the handler does not modify or override the resource.

The `ResourceRequestExtensionHandler` is used in various parts of the codebase, such as the `ResourceRequestExtensionManager` and `AbstractGeneratedResourceHandler` classes. In these classes, the handler is used to retrieve a modified or overridden resource, if any, for a given path.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionHandler.java" line="30">

---

# ResourceRequestExtensionHandler Interface

This is the `ResourceRequestExtensionHandler` interface. It extends the `ExtensionHandler` interface and provides two methods: `getModifiedResource` and `getOverrideResource`. These methods are designed to be implemented by classes that wish to provide custom behavior for modifying or overriding resources.

```java
public interface ResourceRequestExtensionHandler extends ExtensionHandler {
    
    public static final String RESOURCE_ATTR = "RESOURCE_ATTR";
    
    /**
     * Populates the RESOURCE_ATTR field in the ExtensionResultHolder map with an instance of
     * {@link Resource} if the value of the modified resource.
     * 
     * @param path
     * @param erh
     * @return the {@link ExtensionResultStatusType}
     */
    public ExtensionResultStatusType getModifiedResource(String path, ExtensionResultHolder erh);

    /**
     * Populates the RESOURCE_ATTR field in the ExtensionResultHolder map with an instance of
     * {@link Resource} if there is an override resource available for the current path.
     * 
     * @param path
     * @param erh
     * @return the {@link ExtensionResultStatusType}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/AbstractResourceRequestExtensionHandler.java" line="25">

---

# AbstractResourceRequestExtensionHandler Class

This is the `AbstractResourceRequestExtensionHandler` class. It extends the `AbstractExtensionHandler` class and implements the `ResourceRequestExtensionHandler` interface. By default, it does not handle any requests, as indicated by the `ExtensionResultStatusType.NOT_HANDLED` return value in the `getModifiedResource` and `getOverrideResource` methods. This class can be extended to provide custom behavior for resource requests.

```java
public class AbstractResourceRequestExtensionHandler extends AbstractExtensionHandler 
        implements ResourceRequestExtensionHandler {

    @Override
    public ExtensionResultStatusType getModifiedResource(String path, ExtensionResultHolder erh) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }

    @Override
    public ExtensionResultStatusType getOverrideResource(String path, ExtensionResultHolder erh) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionHandler.java" line="32">

---

# RESOURCE_ATTR Constant

The `RESOURCE_ATTR` constant is used as a key in the `ExtensionResultHolder` map to store the modified or override resource. This allows the resource to be retrieved later in the request handling process.

```java
    public static final String RESOURCE_ATTR = "RESOURCE_ATTR";
```

---

</SwmSnippet>

# Resource Request Extension Handler Functions

The Resource Request Extension Handler has two main functions: getModifiedResource and getOverrideResource.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionHandler.java" line="42">

---

## getModifiedResource

The `getModifiedResource` function is used to populate the RESOURCE_ATTR field in the ExtensionResultHolder map with an instance of Resource if the value of the modified resource. It takes a path and an ExtensionResultHolder as parameters and returns an ExtensionResultStatusType.

```java
    public ExtensionResultStatusType getModifiedResource(String path, ExtensionResultHolder erh);

    /**
     * Populates the RESOURCE_ATTR field in the ExtensionResultHolder map with an instance of
     * {@link Resource} if there is an override resource available for the current path.
     * 
     * @param path
     * @param erh
     * @return the {@link ExtensionResultStatusType}
     */
    public ExtensionResultStatusType getOverrideResource(String path, ExtensionResultHolder erh);

}

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/ResourceRequestExtensionHandler.java" line="52">

---

## getOverrideResource

The `getOverrideResource` function is used to populate the RESOURCE_ATTR field in the ExtensionResultHolder map with an instance of Resource if there is an override resource available for the current path. It also takes a path and an ExtensionResultHolder as parameters and returns an ExtensionResultStatusType.

```java
    public ExtensionResultStatusType getOverrideResource(String path, ExtensionResultHolder erh);

}

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
