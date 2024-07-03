---
title: Introduction to Inventory
---
Inventory in BroadleafCommerce-demo refers to the stock of goods or SKUs (Stock Keeping Units) available for sale. It is managed through the `InventoryService` interface, which provides methods to check and adjust the current inventory of a SKU. The `InventoryType` class defines the type of inventory for a SKU, which can be 'Always Available', 'Unavailable', or 'Check Quantity'. The `ContextualInventoryService` interface extends `InventoryService` and provides additional context information. Inventory is a crucial part of the e-commerce framework as it helps in managing the availability of products for sale.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryService.java" line="45">

---

# InventoryService Interface

The InventoryService interface provides methods to manage the inventory. It includes methods to retrieve the quantity available for a given SKU, check if a given quantity is available for a particular SKU, decrement the inventory, and increment the inventory.

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

# InventoryType Class

The InventoryType class provides different types of inventory like ALWAYS_AVAILABLE, UNAVAILABLE, and CHECK_QUANTITY. These types can be used to handle the product availability in different scenarios.

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

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/processor/UncacheableDataProcessor.java" line="81">

---

# Usage of InventoryService

The InventoryService is used here to manage the inventory of the products. It is annotated with the name 'blInventoryService'.

```java
    @Resource(name = "blInventoryService")
    protected InventoryService inventoryService;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/SkuImpl.java" line="434">

---

# Usage of InventoryType

The InventoryType is used here to set the type of inventory for a SKU. It is used in conjunction with the InventoryService to manage the inventory of the products.

```java
        this.id = id;
    }

```

---

</SwmSnippet>

# Inventory Management Functions

This section provides an overview of the main functions related to inventory management in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/inventory/service/InventoryService.java" line="45">

---

## InventoryService Interface

The InventoryService interface provides methods for managing inventory quantities for Skus. It includes methods for retrieving available quantities, checking basic availability, decrementing and incrementing inventory.

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

The InventoryType class defines the types of inventory strategies available. It includes ALWAYS_AVAILABLE, UNAVAILABLE, and CHECK_QUANTITY. The class also provides methods for getting the type and friendly type of the inventory.

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

The InventoryServiceImpl class implements the InventoryService interface and provides the actual logic for managing inventory. It includes methods for checking basic availability, retrieving available quantities, decrementing and incrementing inventory.

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
