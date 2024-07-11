---
title: Understanding Inventory Management
---
Inventory in BroadleafCommerce-demo refers to the management of stock or goods that are used in the e-commerce application. It is handled by the `InventoryService` interface, which provides methods to check and adjust the current inventory of a SKU (Stock Keeping Unit).

The `InventoryService` interface provides methods such as `retrieveQuantityAvailable`, `isAvailable`, `decrementInventory`, and `incrementInventory`. These methods are used to retrieve the available quantity of a SKU, check if a certain quantity of a SKU is available, decrease the inventory of a SKU, and increase the inventory of a SKU respectively.

The `InventoryType` class is an enumeration that specifies whether inventory should be checked or not. It has three types: `ALWAYS_AVAILABLE`, `UNAVAILABLE`, and `CHECK_QUANTITY`. `ALWAYS_AVAILABLE` means the SKU is always considered available, `UNAVAILABLE` means the SKU is not available, and `CHECK_QUANTITY` means the availability of the SKU depends on its quantity.

The `InventoryService` is implemented by the `InventoryServiceImpl` class and the `ContextualInventoryService` interface. The `ContextualInventoryService` provides the same methods as `InventoryService` but with optional, additional context information.

The `InventoryService` is used in various parts of the application such as the `Sku` class, `UncacheableDataProcessor` class, and `InventoryServiceExtensionHandler` interface. It is mainly used to manage the inventory of SKUs in the application.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryService.java" line="29">

---

# InventoryService Interface

The `InventoryService` interface provides methods to manage the inventory of SKUs. It includes methods to retrieve the available quantity of a SKU, check if a certain quantity of a SKU is available, decrease the inventory of a SKU, and increase the inventory of a SKU.

```java
/**
 * <p>This basic inventory service checks and adjusts the current inventory of a sku. All Skus will be considered 
 * generally unavailable from an inventory perspective if {@link Sku#isAvaliable()} returns false or if {@link Sku#isActive()}
 * returns false.</p>
 * 
 * <p>Skus with an InventoryType of null or 'ALWAYS_AVAILABLE' will be considered undefined from an inventory perspective, and will generally 
 * be considered available.  However, a request for available quantities of Skus with a null or 'ALWAYS_AVAILABLE' inventory type will 
 * return null (as the {@link Sku} is available but no inventory strategy is defined).</p>
 * 
 * <p>For most implementations outside of the very basic inventory case, you will actually want to use the {@link ContextualInventoryService}.
 * This is the version of the service that is invoked from the checkout workflow in {@link DecrementInventoryActivity} and
 * where the main checks for inventory are in the {@link CheckAddAvailabilityActivity}</p>
 * 
 * @author Kelly Tisdell
 * @author Phillip Verheyden (phillipuniverse)
 */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/type/InventoryType.java" line="26">

---

# InventoryType Class

The `InventoryType` class is an enumeration that specifies whether inventory should be checked or not. It has three types: `ALWAYS_AVAILABLE`, `UNAVAILABLE`, and `CHECK_QUANTITY`. `ALWAYS_AVAILABLE` means the SKU is always considered available, `UNAVAILABLE` means the SKU is not available, and `CHECK_QUANTITY` means the availability of the SKU depends on its quantity.

```java
/**
 * Enumeration to specify whether inventory should be checked or not.
 * 
 * @author Kelly Tisdell
 */
public class InventoryType implements Serializable, BroadleafEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, InventoryType> TYPES = new LinkedHashMap<String, InventoryType>();
    
    public static final InventoryType ALWAYS_AVAILABLE  = new InventoryType("ALWAYS_AVAILABLE", "Always Available");
    public static final InventoryType UNAVAILABLE  = new InventoryType("UNAVAILABLE", "Unavailable");
    public static final InventoryType CHECK_QUANTITY = new InventoryType("CHECK_QUANTITY", "Check Quantity");
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryServiceImpl.java" line="57">

---

# InventoryServiceImpl Class

The `InventoryServiceImpl` class implements the `InventoryService` interface. It provides the actual implementation of the methods declared in the `InventoryService` interface.

```java
@Service("blInventoryService")
public class InventoryServiceImpl implements ContextualInventoryService {

    private static final Log LOG = LogFactory.getLog(InventoryServiceImpl.class);
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;
    
    @Resource(name = "blInventoryServiceExtensionManager")
    protected InventoryServiceExtensionManager extensionManager;

    @Autowired
    protected ApplicationContext applicationContext;

    @Value("${enable.weave.use.default.sku.inventory:false}")
    protected boolean enableUseDefaultSkuInventory = false;

    @Override
    public boolean checkBasicAvailablility(Sku sku) {
        if(sku != null) {
            if (sku.isActive() && !InventoryType.UNAVAILABLE.equals(sku.getInventoryType())) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/ContextualInventoryService.java" line="29">

---

# ContextualInventoryService Interface

The `ContextualInventoryService` interface extends the `InventoryService` interface. It provides the same methods as `InventoryService` but with optional, additional context information.

```java
/**
 * Provides the same methods from {@link InventoryService} but with optional, additional context information. This context
 * can then be passed on to an {@link InventoryServiceExtensionHandler}
 * 
 * @author Phillip Verheyden (phillipuniverse)
 * @see {@link InventoryService}
 * @see {@link InventoryServiceExtensionHandler}
 * @see {@link CheckAddAvailabilityActivity}
 * @see {@link DecrementInventoryActivity}
 */
public interface ContextualInventoryService extends InventoryService {
```

---

</SwmSnippet>

# Inventory System Functions

This section will cover the main functions of the Inventory system in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryService.java" line="45">

---

## InventoryService Interface

The InventoryService interface provides the main methods for managing inventory. These include methods for retrieving the available quantity of a SKU, checking if a certain quantity of a SKU is available, decreasing the inventory of a SKU, and increasing the inventory of a SKU.

```java
public interface InventoryService {

    /**
     * <p>Retrieves the quantity available for a given <b>sku</b>. May return null if no inventory is maintained 
     * for the given <b>sku</b> when Sku.getInventoryType() == null
     * or the {@link InventoryType} of the given <b>sku</b> is {@link InventoryType#ALWAYS_AVAILABLE}. Effectively, if the quantity returned is null, inventory is 
     * undefined, which most likely means it is available.  However, rather than returning an arbitrary integer values (like Integer.MAX_VALUE), 
     * which has specific meaning, we return null as this can be interpreted by the client to mean whatever they define it as (including 
     * infinitely available), which is the most likely scenario.</p>
     * 
     * <p>In practice, this is a convenience method to wrap {@link #retrieveQuantitiesAvailable(Collection)}</p>
     * 
     * @param
     * @return <b>null</b> if there is no inventory strategy defined (meaning, {@link Sku#getInventoryType()} is null or
     * {@link InventoryType#ALWAYS_AVAILABLE}). Otherwise, this returns the quantity of the {@link Sku}
     * {@see ContextualInventoryService#retrieveQuantityAvailable(Sku, Map)}
     */
    public Integer retrieveQuantityAvailable(Sku sku);

    /**
     * <p>Retrieves the quantity available for a given <b>sku</b>. May return null if no inventory is maintained 
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/type/InventoryType.java" line="31">

---

## InventoryType Class

The InventoryType class is an enumeration that specifies whether inventory should be checked or not. It has three types: ALWAYS_AVAILABLE, UNAVAILABLE, and CHECK_QUANTITY. ALWAYS_AVAILABLE means the SKU is always considered available, UNAVAILABLE means the SKU is not available, and CHECK_QUANTITY means the availability of the SKU depends on its quantity.

```java
public class InventoryType implements Serializable, BroadleafEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, InventoryType> TYPES = new LinkedHashMap<String, InventoryType>();
    
    public static final InventoryType ALWAYS_AVAILABLE  = new InventoryType("ALWAYS_AVAILABLE", "Always Available");
    public static final InventoryType UNAVAILABLE  = new InventoryType("UNAVAILABLE", "Unavailable");
    public static final InventoryType CHECK_QUANTITY = new InventoryType("CHECK_QUANTITY", "Check Quantity");

    public static InventoryType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;

    public InventoryType() {
        //do nothing
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryServiceImpl.java" line="58">

---

## InventoryServiceImpl Class

The InventoryServiceImpl class implements the InventoryService interface and the ContextualInventoryService interface. The ContextualInventoryService interface provides the same methods as InventoryService but with optional, additional context information.

```java
public class InventoryServiceImpl implements ContextualInventoryService {

    private static final Log LOG = LogFactory.getLog(InventoryServiceImpl.class);
    
    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;
    
    @Resource(name = "blInventoryServiceExtensionManager")
    protected InventoryServiceExtensionManager extensionManager;

    @Autowired
    protected ApplicationContext applicationContext;

    @Value("${enable.weave.use.default.sku.inventory:false}")
    protected boolean enableUseDefaultSkuInventory = false;

    @Override
    public boolean checkBasicAvailablility(Sku sku) {
        if(sku != null) {
            if (sku.isActive() && !InventoryType.UNAVAILABLE.equals(sku.getInventoryType())) {
                return true;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
