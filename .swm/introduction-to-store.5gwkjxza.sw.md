---
title: Introduction to Store
---
In BroadleafCommerce-demo, a Store refers to a physical or virtual retail location where products are sold. It is represented by the `Store` interface and its implementation `StoreImpl`. The `Store` interface provides methods to get and set store properties such as name, number, hours, and location (latitude and longitude). The `StoreService` interface provides methods to read, save, and find stores. The `StoreDao` interface, implemented by `StoreDaoImpl`, interacts with the database to perform CRUD operations on stores.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/domain/Store.java" line="25">

---

# Store Interface

This is the Store interface. It declares methods for getting and setting properties of a store, such as id, name, store number, opening status, store hours, address, longitude, and latitude.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/service/StoreService.java" line="26">

---

# StoreService Interface

This is the StoreService interface. It declares methods for interacting with Store objects, such as reading a store by its ID or name, saving a store, or finding stores by address.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/dao/StoreDao.java" line="24">

---

# StoreDao Interface

This is the StoreDao interface. It declares methods for interacting with the database to manage Store objects.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/domain/StoreImpl.java" line="50">

---

# StoreImpl Class

This is the StoreImpl class. It implements the Store interface and provides the actual implementation of the methods declared in the interface.

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

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/store/service/StoreServiceImpl.java" line="33">

---

# StoreServiceImpl Class

This is the StoreServiceImpl class. It implements the StoreService interface and uses the StoreDao to interact with the database.

```java
@Service("blStoreService")
public class StoreServiceImpl implements StoreService {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
