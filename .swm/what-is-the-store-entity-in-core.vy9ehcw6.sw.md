---
title: What is the Store Entity in Core
---
Store in BroadleafCommerce-demo refers to a key entity in the e-commerce framework. It represents a physical or virtual retail store. It is defined in the `Store` interface and implemented in the `StoreImpl` class, both located in the `org.broadleafcommerce.core.store.domain` package.

The `Store` interface defines the basic properties of a store, such as its ID, name, store number, opening status, store hours, address, and geographical coordinates (latitude and longitude).

The `StoreImpl` class provides the implementation of these properties. It also includes annotations for data persistence and admin presentation, which are part of Broadleaf's framework features.

The `StoreService` interface, located in the `org.broadleafcommerce.core.store.service` package, defines the operations that can be performed on a `Store`, such as reading a store by its ID or name, saving a store, and finding stores by address.

The `StoreServiceImpl` class provides the implementation of these operations. It uses the `StoreDao` data access object to interact with the database.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/domain/Store.java" line="25">

---

# Store Interface

The Store interface defines the basic properties of a store, such as its ID, name, store number, opening status, store hours, address, and geographical coordinates.

```java
public interface Store extends Status, Serializable{

    public Long getId();
    public void setId(Long id);

    public String getName();
    public void setName(String name);

    public String getStoreNumber();
    public void setStoreNumber(String storeNumber);

    public Boolean getOpen();
    public void setOpen(Boolean open);

    public String getStoreHours();
    public void setStoreHours(String storeHours);
    
    public Address getAddress();
    public void setAddress(Address address);
    
    public Double getLongitude();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/domain/StoreImpl.java" line="50">

---

# Store Implementation

The StoreImpl class provides the implementation of the Store interface. It includes annotations for data persistence and admin presentation, which are part of Broadleaf's framework features.

```java
@SQLDelete(sql="UPDATE BLC_STORE SET ARCHIVED = 'Y' WHERE STORE_ID = ?")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "StoreImpl_baseStore")
@Inheritance(strategy = InheritanceType.JOINED)
public class StoreImpl implements Store {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "StoreId")
    @GenericGenerator(
            name="StoreId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="StoreImpl"),
                    @Parameter(name="entity_name", value="org.broadleafcommerce.core.store.domain.StoreImpl")
            }
    )
    @Column(name = "STORE_ID", nullable = false)
    @AdminPresentation(friendlyName = "StoreImpl_Store_ID", visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long id;

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/service/StoreService.java" line="26">

---

# Store Service Interface

The StoreService interface defines the operations that can be performed on a Store, such as reading a store by its ID or name, saving a store, and finding stores by address.

```java
public interface StoreService {

    public Store readStoreById(Long id);

    public Store readStoreByStoreName(String storeName);

    /**
     * @deprecated use {@link #readStoreByStoreName(String)} instead.
     *
     * @param storeCode
     * @return
     */
    @Deprecated
    public Store readStoreByStoreCode(String storeCode);

    public Store saveStore(Store store);

    public Map<Store,Double> findStoresByAddress(Address searchAddress, double distance);

    public List<Store> readAllStores();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/service/StoreServiceImpl.java" line="33">

---

# Store Service Implementation

The StoreServiceImpl class provides the implementation of the StoreService interface. It uses the StoreDao data access object to interact with the database.

```java
@Service("blStoreService")
public class StoreServiceImpl implements StoreService {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/dao/StoreDao.java" line="24">

---

# Store Data Access Object

The StoreDao interface defines the database operations for the Store entity. It is used by the StoreServiceImpl to interact with the database.

```java
public interface StoreDao {

    public Store readStoreById(Long id);

    public Store readStoreByStoreName(final String storeName);

    /**
     * @deprecated use {@link #readStoreByStoreName(String)} instead
     *
     * @param storeCode
     * @return
     */
    @Deprecated
    public Store readStoreByStoreCode(final String storeCode);

    public List<Store> readAllStores();

    public List<Store> readAllStoresByState(final String state);

    public Store save(Store store);

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
