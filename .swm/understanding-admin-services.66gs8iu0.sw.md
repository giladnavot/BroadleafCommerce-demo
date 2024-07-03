---
title: Understanding Admin Services
---
Admin Services in BroadleafCommerce-demo, such as the `AdminCatalogService`, provide functionalities for managing the catalog of the e-commerce application. These services are responsible for tasks like generating SKUs from products, cloning products, and more. The `AdminCatalogService` is implemented by the `AdminCatalogServiceImpl` class, which is annotated as a Spring service. This service is used in various parts of the application, such as in the `AdminCatalogActionsController`.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogService.java" line="27">

---

# AdminCatalogService Interface

The `AdminCatalogService` interface defines the contract for the service. It declares methods like `generateSkusFromProduct`, `generateSkus`, and `cloneProduct` which are implemented in the `AdminCatalogServiceImpl` class.

```java
public interface AdminCatalogService {

    /**
     * Clear out any Skus that are already attached to the Product
     * if there were any there and generate a new set of Skus based
     * on the permutations of ProductOptions attached to this Product
     *
     * @param productId - the Product to generate Skus from
     * @return the number of generated Skus from the ProductOption permutations
     * @deprecated use {@link #generateSkus(Long)}
     */
    @Deprecated
    public Integer generateSkusFromProduct(Long productId);

    /**
     * Create new Skus based on a product's ProductOptions by permutation and add them to existing ones.
     *
     * @param productId - Product ID to create SKUs
     * @return the map as ResponseBody
     */
    Map<String, Object> generateSkus(Long productId);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogServiceImpl.java" line="52">

---

# AdminCatalogServiceImpl Class

The `AdminCatalogServiceImpl` class is the implementation of the `AdminCatalogService` interface. It is annotated with `@Service("blAdminCatalogService")` to indicate that it is a service bean. It uses resources like `CatalogService`, `SkuDao`, and `AdminCatalogServiceExtensionManager` to perform its operations.

```java
@Service("blAdminCatalogService")
public class AdminCatalogServiceImpl implements AdminCatalogService {

    private static final Log LOG = LogFactory.getLog(AdminCatalogServiceImpl.class);

    public static String NO_SKUS_GENERATED_KEY = "noSkusGenerated";
    public static String MAX_SKU_GENERATION_KEY = "maxSkuGenerated";
    public static String NO_PRODUCT_OPTIONS_GENERATED_KEY = "noProductOptionsConfigured";
    public static String FAILED_SKU_GENERATION_KEY = "errorNeedAllowedValue";
    public static String NUMBER_SKUS_GENERATED_KEY = "numberSkusGenerated";
    public static String INCONSISTENT_PERMUTATIONS_KEY = "inconsistentPermutations";

    @Value("${product.sku.generation.max:400}")
    protected int skuMaxGeneration;

    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Resource(name = "blSkuDao")
    protected SkuDao skuDao;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/action/AdminCatalogActionsController.java" line="51">

---

# Usage of AdminCatalogService

`AdminCatalogService` is used in `AdminCatalogActionsController` by injecting it using the `@Resource` annotation. It can then be used to call the methods defined in the service.

```java
    protected AdminCatalogService adminCatalogService;
    @Autowired
```

---

</SwmSnippet>

# Admin Services Functions

The Admin Services in BroadleafCommerce-demo are responsible for managing various aspects of the e-commerce platform. This includes catalog management, security, user management, and entity services. Some of the key functions include generating SKUs from a product, cloning a product, and persisting SKU permutations.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogServiceImpl.java" line="79">

---

## generateSkusFromProduct

The `generateSkusFromProduct` function is used to generate SKUs for a product. It takes a product ID as input and generates SKUs based on the product options attached to the product. It returns the number of SKUs generated.

```java
    @Override
    public Integer generateSkusFromProduct(Long productId) {
        Product product = catalogService.findProductById(productId);

        if (CollectionUtils.isEmpty(product.getProductOptionXrefs())) {
            return -1;
        }

        List<List<ProductOptionValue>> allPermutations = generatePermutations(0, new ArrayList<ProductOptionValue>(), product.getProductOptions());

        // return -2 to indicate that one of the Product Options used in Sku generation has no Allowed Values
        if (allPermutations == null) {
            return -2;
        }

        LOG.info("Total number of permutations: " + allPermutations.size());
        LOG.debug(allPermutations);

        //determine the permutations that I already have Skus for
        List<List<ProductOptionValue>> previouslyGeneratedPermutations = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(product.getAdditionalSkus())) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogServiceImpl.java" line="61">

---

## cloneProduct

The `cloneProduct` function is used to create a new product along with a new SKU for the default SKU, along with new SKUs for all of the additional SKUs. This is achieved by detaching the entities from the persistent session, resetting the primary keys, and then saving the entity.

```java
    public static String NUMBER_SKUS_GENERATED_KEY = "numberSkusGenerated";
    public static String INCONSISTENT_PERMUTATIONS_KEY = "inconsistentPermutations";
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/extension/DefaultAdminCatalogExtensionHandler.java" line="65">

---

## persistSkuPermutation

The `persistSkuPermutation` function is used to customize the persistence of generated SKU permutations based on product options. It takes a product, a list of product option value permutations, and an extension result holder as inputs. It iterates through the permutations and persists them as new SKU instances in the product.

```java
    @Override
    public ExtensionResultStatusType persistSkuPermutation(Product product, List<List<ProductOptionValue>>
            permutationsToGenerate, ExtensionResultHolder<Integer> erh) {
        int numPermutationsCreated = 0;
        //For each permutation, I need them to map to a specific Sku
        for (List<ProductOptionValue> permutation : permutationsToGenerate) {
            if (permutation.isEmpty()) continue;
            Sku permutatedSku = catalogService.createSku();
            permutatedSku.setProduct(product);
            permutatedSku.setProductOptionValues(permutation);
            permutatedSku = catalogService.saveSku(permutatedSku);
            product.getAdditionalSkus().add(permutatedSku);
            numPermutationsCreated++;
        }
        if (numPermutationsCreated != 0) {
            catalogService.saveProduct(product);
        }
        erh.setResult(numPermutationsCreated);
        return ExtensionResultStatusType.HANDLED;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
