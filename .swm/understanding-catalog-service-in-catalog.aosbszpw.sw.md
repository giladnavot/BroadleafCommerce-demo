---
title: Understanding Catalog Service in Catalog
---
The CatalogService in BroadleafCommerce-demo is an interface that defines methods for managing and retrieving catalog data, such as products and categories. It provides methods for saving and finding products and categories, as well as methods for finding products by various criteria such as name, category, and search criteria.

The CatalogServiceImpl class is the implementation of the CatalogService interface. It uses various DAOs (Data Access Objects) to interact with the database and perform the operations defined in the CatalogService interface. These operations include saving products and categories, finding products and categories by their IDs, names, or other criteria, and removing products and categories.

The CatalogService is used throughout the BroadleafCommerce-demo application to manage and retrieve catalog data. It is used in various components such as the CategoryHandlerMapping, ProductHandlerMapping, and SkuHandlerMapping, as well as in various services such as the DatabaseSearchServiceImpl, InventoryServiceImpl, and OrderItemServiceImpl.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/RelatedProductsServiceImpl.java" line="37">

---

# RelatedProductsService

The `RelatedProductsService` is an example of a Service in the BroadleafCommerce-demo repository. It is annotated with `@Service` to indicate that it's a Service component. This service provides a method `findRelatedProducts` for finding a product's related products.

```java
@Service("blRelatedProductsService")
/*
 * Service that provides method for finding a product's related products.   
 */
public class RelatedProductsServiceImpl implements RelatedProductsService {
    
    @Resource(name="blCategoryDao")
    protected CategoryDao categoryDao;

    @Resource(name="blProductDao")
    protected ProductDao productDao;
    
    @Resource(name="blCatalogService")
    protected CatalogService catalogService;

    @Override
    public List<? extends PromotableProduct> findRelatedProducts(RelatedProductDTO relatedProductDTO) {
        Product product = lookupProduct(relatedProductDTO);
        Category category = lookupCategory(relatedProductDTO);      
        
        if (RelatedProductTypeEnum.FEATURED.equals(relatedProductDTO.getType())) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/CatalogService.java" line="36">

---

# CatalogService

The `CatalogService` is another example of a Service. It is an interface that defines methods for managing and retrieving catalog data, such as products and categories. The actual implementation of these methods would be provided in a class that implements this interface.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
