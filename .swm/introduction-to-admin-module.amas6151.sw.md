---
title: Introduction to Admin Module
---
The Admin Module in BroadleafCommerce-demo refers to a key component of the Broadleaf Commerce framework. It is responsible for providing administrative functionalities such as managing product catalogs, handling customer accounts, and overseeing orders. The module is defined in the `AdminModuleRegistration` class, where `MODULE_NAME` is set to 'Admin'. This module includes services like `AdminCatalogService` for managing product catalogs and controllers like `AdminCategoryController` for handling category-related operations. The Admin Module is a crucial part of the Broadleaf Commerce framework, enabling administrators to manage various aspects of the e-commerce platform.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/config/AdminModuleRegistration.java" line="25">

---

## AdminModuleRegistration Class

The AdminModuleRegistration class is a key component of the Admin Module. It implements the BroadleafModuleRegistration interface and defines the module name as 'Admin'. The getModuleName() method is used to retrieve this module name.

```java
public class AdminModuleRegistration implements BroadleafModuleRegistration {

    public static final String MODULE_NAME = "Admin";

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogService.java" line="27">

---

## AdminCatalogService Interface

The AdminCatalogService interface defines the services related to the product catalog in the Admin Module. It includes methods for generating SKUs from product options and cloning products.

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

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminCategoryController.java" line="37">

---

## Admin Controllers

The Admin Controllers, such as the AdminCategoryController, handle various administrative operations. For instance, the AdminCategoryController handles operations for the Category entity.

```java
@Controller("blAdminCategoryController")
@RequestMapping("/" + AdminCategoryController.SECTION_KEY)
public class AdminCategoryController extends AdminBasicEntityController {
    
    public static final String SECTION_KEY = "category";
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }



    @Override
```

---

</SwmSnippet>

# Admin Module Functions

This section will focus on the key functions of the Admin Module, specifically the AdminCatalogService and the AdminCategoryController and AdminProductController classes.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogServiceImpl.java" line="52">

---

## AdminCatalogService

The `AdminCatalogService` is an interface that defines methods for generating SKUs from a product, cloning a product, and other catalog-related operations. The `AdminCatalogServiceImpl` class implements this interface, providing the actual functionality.

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

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminCategoryController.java" line="37">

---

## AdminCategoryController

The `AdminCategoryController` class handles admin operations for the Category entity. It extends the `AdminBasicEntityController` class and overrides methods like `getSectionKey` and `modifyAddEntityForm` to provide specific functionality for handling categories.

```java
@Controller("blAdminCategoryController")
@RequestMapping("/" + AdminCategoryController.SECTION_KEY)
public class AdminCategoryController extends AdminBasicEntityController {
    
    public static final String SECTION_KEY = "category";
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }



    @Override
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminProductController.java" line="78">

---

## AdminProductController

The `AdminProductController` class handles admin operations for the Product entity. It extends the `AdminBasicEntityController` class and overrides methods like `getSectionKey` and `modifyAddEntityForm` to provide specific functionality for handling products.

```java
@Controller("blAdminProductController")
@RequestMapping("/" + AdminProductController.SECTION_KEY)
public class AdminProductController extends AdminBasicEntityController {

    public static final String SECTION_KEY = "product";
    public static final String DEFAULT_SKU_NAME = "defaultSku.name";
    public static final String SELECTIZE_NAME_PROPERTY = "name";
    public static final String PRODUCT_OPTIONS_COLLECTION_FIELD = "productOptions";
    private static final String PRODUCT_OPTION_ID_FIELD_KEY = "productOption.id";
    private static final String PRODUCT_ID_FIELD_KEY = "product.id";

    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
```

---

</SwmSnippet>

# Admin Module Endpoints

Admin Module Endpoints

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminProductController.java" line="78">

---

## AdminProductController

The AdminProductController is responsible for handling admin operations for the Product entity. It includes endpoints for viewing, adding, updating, and deleting products. It also includes endpoints for handling product collections, such as additional skus and product options.

```java
@Controller("blAdminProductController")
@RequestMapping("/" + AdminProductController.SECTION_KEY)
public class AdminProductController extends AdminBasicEntityController {

    public static final String SECTION_KEY = "product";
    public static final String DEFAULT_SKU_NAME = "defaultSku.name";
    public static final String SELECTIZE_NAME_PROPERTY = "name";
    public static final String PRODUCT_OPTIONS_COLLECTION_FIELD = "productOptions";
    private static final String PRODUCT_OPTION_ID_FIELD_KEY = "productOption.id";
    private static final String PRODUCT_ID_FIELD_KEY = "product.id";

    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminCategoryController.java" line="37">

---

## AdminCategoryController

The AdminCategoryController is responsible for handling admin operations for the Category entity. It includes endpoints for viewing and modifying the Category entity form.

```java
@Controller("blAdminCategoryController")
@RequestMapping("/" + AdminCategoryController.SECTION_KEY)
public class AdminCategoryController extends AdminBasicEntityController {
    
    public static final String SECTION_KEY = "category";
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }



    @Override
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
