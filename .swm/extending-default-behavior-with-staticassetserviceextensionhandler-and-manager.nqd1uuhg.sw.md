---
title: Extending Default Behavior with StaticAssetServiceExtensionHandler and Manager
---
This document will cover the use of `StaticAssetServiceExtensionHandler` and `StaticAssetServiceExtensionManager` in extending the default behavior and managing extensions. We'll cover:

1. The role of `StaticAssetServiceExtensionHandler`.
2. The role of `StaticAssetServiceExtensionManager`.
3. How these classes interact with the `StaticAsset` entity.
4. The extension points provided by these classes.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceExtensionHandler.java" line="27">

---

# The Role of `StaticAssetServiceExtensionHandler`

`StaticAssetServiceExtensionHandler` is an interface that extends `ExtensionHandler`. It provides a method `fileExists` that can be implemented to extend the default behavior of checking if a file exists.

```java
public interface StaticAssetServiceExtensionHandler extends ExtensionHandler {

    public ExtensionResultStatusType fileExists(String fileName, ExtensionResultHolder holder);

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceExtensionManager.java" line="28">

---

# The Role of `StaticAssetServiceExtensionManager`

`StaticAssetServiceExtensionManager` is a class that extends `ExtensionManager` and manages `StaticAssetServiceExtensionHandler` instances. It is annotated with `@Component` to indicate that it is a Spring Bean and can be autowired into other classes.

```java
@Component("blStaticAssetServiceExtensionManager")
public class StaticAssetServiceExtensionManager extends ExtensionManager<StaticAssetServiceExtensionHandler> {

    public StaticAssetServiceExtensionManager() {
        super(StaticAssetServiceExtensionHandler.class);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceImpl.java" line="109">

---

# Interaction with the `StaticAsset` Entity

The `StaticAssetService` uses the `StaticAsset` entity to perform operations like finding a static asset by its ID or reading all static assets. The `StaticAssetServiceExtensionHandler` and `StaticAssetServiceExtensionManager` can be used to extend these operations.

```java
    @Override
    public StaticAsset findStaticAssetById(Long id) {
        return staticAssetDao.readStaticAssetById(id);
    }

    @Override
    public List<StaticAsset> readAllStaticAssets() {
        return staticAssetDao.readAllStaticAssets();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceImpl.java" line="85">

---

# Extension Points

The `StaticAssetServiceExtensionManager` is autowired into the `StaticAssetServiceImpl` class, providing an extension point for the service. This allows custom behavior to be added to the service by adding new `StaticAssetServiceExtensionHandler` implementations to the `StaticAssetServiceExtensionManager`.

```java
    @Resource(name = "blStaticAssetMultiTenantExtensionManager")
    protected StaticAssetMultiTenantExtensionManager staticAssetExtensionManager;

    @Value("${should.accept.non.image.asset:true}")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
