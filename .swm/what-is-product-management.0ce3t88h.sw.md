---
title: What is Product Management
---
Product Management in BroadleafCommerce-demo refers to the functionality that allows the handling and organization of products within the e-commerce platform. This includes managing product categories, handling product options, and managing the parent category of a product. For instance, the `ProductParentCategoryFieldPersistenceProviderExtensionHandler` interface provides a method `manageParentCategory` for special handling of the parent category of a product. Similarly, the `SkuLookupByProductCustomPersistenceHandler` class provides a `fetch` method to retrieve product SKUs. The `ProductCustomPersistenceHandler` class provides an `inspect` method to build out extra fields for product options.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/handler/SkuCustomPersistenceHandler.java" line="199">

---

## Inspect Method

The `inspect` method is used to build out extra fields for product options. It retrieves the product ID and checks if it's valid. If it is, it fetches the product options associated with that product ID and creates new fields for each of them. If the product ID is not valid, it fetches all product options and creates new fields for each of them. The method also handles caching of the properties for performance optimization.

```java
    /**
     * Build out the extra fields for the product options
     */
    @Override
    public DynamicResultSet inspect(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, InspectHelper helper) throws ServiceException {
        try {
            PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
            Map<MergedPropertyType, Map<String, FieldMetadata>> allMergedProperties = new HashMap<>();

            String productIdStr = getOwningProductId(persistencePackage.getSectionCrumbs());
            String cacheKey = skuMetadataCacheService.buildCacheKey(productIdStr);

            Map<String, FieldMetadata> properties = null;
            boolean useCache = skuMetadataCacheService.useCache();
            if (useCache) {
                properties = skuMetadataCacheService.getFromCache(cacheKey);
            }
            if (properties == null) {
                //Grab the default properties for the Sku
                properties = helper.getSimpleMergedProperties(SkuImpl.class.getName(), persistencePerspective);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/handler/SkuLookupByProductCustomPersistenceHandler.java" line="86">

---

## Fetch Method

The `fetch` method is used to fetch product data based on certain criteria. It finds the criteria for product ID and fetches the SKUs associated with the product IDs. It then updates the product option fields for the fetched SKUs.

```java
    @Override
    public DynamicResultSet fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto, DynamicEntityDao
            dynamicEntityDao, RecordHelper helper) throws ServiceException {

        // find criteria for productId
        FilterAndSortCriteria productIdCriteria = cto.getCriteriaMap().get("productId");
        List<Serializable> skusFromProducts = new ArrayList<>();
        if (productIdCriteria != null && CollectionUtils.isNotEmpty(productIdCriteria.getFilterValues())) {
            List<String> products = productIdCriteria.getFilterValues();
            for (String productIdString : products) {
                Long productId = Long.parseLong(productIdString);
                Product product = catalogService.findProductById(productId);
                skusFromProducts.addAll(product.getAllSellableSkus());
            }
        }

        PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
        Map<String, FieldMetadata> SkuMetadata = helper.getSimpleMergedProperties(Sku.class.getName(), persistencePerspective);
        Entity[] entities = helper.getRecords(SkuMetadata, skusFromProducts);

        skuPersistenceHandler.updateProductOptionFieldsForFetch(skusFromProducts, entities);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
