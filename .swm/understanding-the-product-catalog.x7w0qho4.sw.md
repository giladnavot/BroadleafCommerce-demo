---
title: Understanding the Product Catalog
---
The Catalog in BroadleafCommerce-demo refers to the collection of products and their associated details that are available for purchase on the e-commerce platform. It is managed through the CatalogService, which provides a variety of methods for creating, retrieving, updating, and deleting products, categories, and SKUs.

The CatalogService interface, located in the core module, defines the operations that can be performed on the catalog. These operations include saving a product, finding a product by its ID or external ID, finding products by name, and finding active products by category, among others.

The CatalogService is implemented by various classes throughout the codebase. For example, it is used in the CategoryHandlerMapping, ProductHandlerMapping, and SkuHandlerMapping classes in the web module to map requests to the appropriate products or categories.

The CatalogService is also used in various service classes in the core module, such as the DatabaseSearchServiceImpl, InventoryServiceImpl, and OrderItemServiceImpl, to perform operations related to search, inventory management, and order item management, respectively.

The CatalogService relies on various domain classes to represent the entities in the catalog. These include the Product, Category, and Sku classes, among others. These classes are located in the org.broadleafcommerce.core.catalog.domain package.

In summary, the Catalog in BroadleafCommerce-demo is a crucial component of the e-commerce platform, enabling the management and presentation of products to customers. It is implemented through the CatalogService and associated domain classes.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/CatalogService.java" line="36">

---

# CatalogService Interface

The CatalogService interface, located in the core module, defines the operations that can be performed on the catalog. These operations include saving a product, finding a product by its ID or external ID, finding products by name, and finding active products by category, among others.

```java
public interface CatalogService {

    Product saveProduct(Product product);

    Product findProductById(Long productId);
    
    Product findProductByExternalId(String externalId);

    List<Product> findProductsByName(String searchName);

    /**
     * Find a subset of {@code Product} instances whose name starts with
     * or is equal to the passed in search parameter.  Res
     * @param searchName
     * @param limit the maximum number of results
     * @param offset the starting point in the record set
     * @return the list of product instances that fit the search criteria
     */
    List<Product> findProductsByName(String searchName, int limit, int offset);

    List<Product> findActiveProductsByCategory(Category category);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/catalog/CategoryHandlerMapping.java" line="59">

---

# CatalogService Implementations

The CatalogService is implemented by various classes throughout the codebase. For example, it is used in the CategoryHandlerMapping, ProductHandlerMapping, and SkuHandlerMapping classes in the web module to map requests to the appropriate products or categories.

```java
    @Resource(name = "blCatalogService")
    private CatalogService catalogService;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/Product.java" line="36">

---

# Catalog Domain Classes

The CatalogService relies on various domain classes to represent the entities in the catalog. These include the Product, Category, and Sku classes, among others. These classes are located in the org.broadleafcommerce.core.catalog.domain package.

```java
 * Implementations of this interface are used to hold data for a Product.  A product is a general description
 * of an item that can be sold (for example: a hat).  Products are not sold or added to a cart.  {@link Sku}s
 * which are specific items (for example: a XL Blue Hat) are sold or added to a cart.
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
