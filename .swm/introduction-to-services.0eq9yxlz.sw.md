---
title: Introduction to Services
---
Services in BroadleafCommerce-demo refer to the business logic layer of the application. They are responsible for handling tasks such as data retrieval, data manipulation, and business operations. For instance, the CatalogService is responsible for operations related to the product catalog, such as retrieving product details or updating product information. Another example is the ProductCustomPersistenceHandlerExtensionHandler interface, which defines methods for managing product data during various stages of a product's lifecycle, such as adding, updating, or removing a product.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/SkuMetadataCacheService.java" line="30">

---

# SKU Metadata Cache Service

The `SkuMetadataCacheService` is an example of a service that provides caching functionality for SKU metadata. It has a method for building cache keys, which is used by other methods in the service.

```java
     * Whether or not to use the cache. If they cache is configured to be used but is
     * past the metadata TTL, the cache is cleared out from this method
     */
    boolean useCache();
    
    /**
     * Not generally used but could be useful in some scenarios if you need to invalidate the entire cache
     * @return the cache, keyed by {@link #buildCacheKey(String)}
     */
    Map<String, Map<String, FieldMetadata>> getEntireCache();

    /**
     * Builds out the cache key to use for the other methods
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/handler/SkuCustomPersistenceHandler.java" line="117">

---

# SKU Custom Persistence Handler

The `SkuCustomPersistenceHandler` is an example of a service that handles custom persistence logic for SKUs. It uses configuration values to determine its behavior, and it has a method for performing SKU lookups.

```java
    @Value("${use.to.one.lookup.sku.product.option.value:false}")
    protected boolean useToOneLookupSkuProductOptionValue = false;

    @Resource(name ="blSkuMetadataCacheService")
    protected SkuMetadataCacheService skuMetadataCacheService;

    @Resource(name="blAdornedTargetListPersistenceModule")
    protected PersistenceModule adornedPersistenceModule;

    @Resource(name = "blSkuCustomPersistenceHandlerExtensionManager")
    protected SkuCustomPersistenceHandlerExtensionManager extensionManager;

    @Value("${enable.weave.use.default.sku.inventory:false}")
    protected boolean enableUseDefaultSkuInventory = false;


    /**
     * This represents the field that all of the product option values will be stored in. This would be used in the case
     * where there are a bunch of product options and displaying each option as a grid header would have everything
     * squashed together. Filtering on this field is currently unsupported.
     */
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogService.java" line="36">

---

# Admin Catalog Service

The `AdminCatalogService` is an example of a service that provides functionality related to the admin catalog. It has a method for generating SKUs, which is marked as deprecated, indicating that there is a newer method that should be used instead.

```java
     * @deprecated use {@link #generateSkus(Long)}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
