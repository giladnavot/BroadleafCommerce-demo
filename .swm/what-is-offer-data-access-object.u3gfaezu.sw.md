---
title: What is Offer Data Access Object
---
OfferDao refers to the Data Access Object (DAO) for the Offer entity. It provides an abstraction of the underlying data access implementation for retrieving and managing Offer objects directly from the database.

OfferDao provides methods for common operations such as save, delete, and create. These methods are used to persist, remove, and instantiate Offer objects respectively.

In addition to these common operations, OfferDao also provides methods for specific queries related to Offer objects, such as readAllOffers, readOfferById, and readOffersByAutomaticDeliveryType. These methods are used to retrieve Offer objects based on specific conditions.

OfferDao is used in various services throughout the application, such as OfferServiceImpl, OfferServiceUtilitiesImpl, and OrderServiceImpl. These services use the methods provided by OfferDao to interact with the Offer data.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/OfferDao.java" line="33">

---

# OfferDao

This is the OfferDao interface. It declares methods for CRUD operations and specific queries on Offer objects. The actual implementation of these methods is done in the OfferDaoImpl class.

```java
public interface OfferDao {

    ProratedOrderItemAdjustment save(ProratedOrderItemAdjustment adjustment);

    List<Offer> readAllOffers();

    Offer readOfferById(Long offerId);

    List<Offer> readOffersByAutomaticDeliveryType();

    Offer save(Offer offer);

    void delete(Offer offer);

    Offer create();

    CandidateOrderOffer createCandidateOrderOffer();
    
    CandidateItemOffer createCandidateItemOffer();

    CandidateFulfillmentGroupOffer createCandidateFulfillmentGroupOffer();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/OfferDaoImpl.java" line="53">

---

# OfferDaoImpl

This is the OfferDaoImpl class. It provides the implementation for the methods declared in the OfferDao interface. It uses the EntityManager for interacting with the database.

```java
@Repository("blOfferDao")
public class OfferDaoImpl implements OfferDao {

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Value("${query.dateResolution.offer:10000}")
    protected Long currentDateResolution;

    protected Date cachedDate = SystemTime.asDate();

    protected Date getCurrentDateAfterFactoringInDateResolution() {
        Date returnDate = SystemTime.getCurrentDateWithinTimeResolution(cachedDate, getCurrentDateResolution());
        if (returnDate != cachedDate) {
            if (SystemTime.shouldCacheDate()) {
                cachedDate = returnDate;
            }
        }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/OfferCodeDao.java" line="25">

---

# OfferCodeDao

This is the OfferCodeDao interface. It declares methods for CRUD operations and specific queries on OfferCode objects.

```java
public interface OfferCodeDao {

    public OfferCode readOfferCodeById(Long offerCode);

    public List<OfferCode> readOfferCodesByIds(Collection<Long> offerCodeIds);

    public OfferCode readOfferCodeByCode(String code);

    public OfferCode save(OfferCode offerCode);

    public void delete(OfferCode offerCodeId);

    public OfferCode create();

    public Boolean offerCodeIsUsed(OfferCode code);

    public List<OfferCode> readAllOfferCodesByCode(String code);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/CustomerOfferDao.java" line="25">

---

# CustomerOfferDao

This is the CustomerOfferDao interface. It declares methods for CRUD operations and specific queries on CustomerOffer objects.

```java
public interface CustomerOfferDao {

    public CustomerOffer readCustomerOfferById(Long customerOfferId);

    public List<CustomerOffer> readCustomerOffersByCustomer(Customer customer);

    public CustomerOffer save(CustomerOffer customerOffer);

    public void delete(CustomerOffer customerOffer);

    public CustomerOffer create();
}
```

---

</SwmSnippet>

# DAO Functions

This section provides an overview of the main functions in the DAO classes of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/OfferDao.java" line="33">

---

## OfferDao

OfferDao provides an interface for managing Offer entities. It declares methods for creating, saving, and deleting Offer entities, as well as for retrieving Offer entities based on specific conditions.

```java
public interface OfferDao {

    ProratedOrderItemAdjustment save(ProratedOrderItemAdjustment adjustment);

    List<Offer> readAllOffers();

    Offer readOfferById(Long offerId);

    List<Offer> readOffersByAutomaticDeliveryType();

    Offer save(Offer offer);

    void delete(Offer offer);

    Offer create();

    CandidateOrderOffer createCandidateOrderOffer();
    
    CandidateItemOffer createCandidateItemOffer();

    CandidateFulfillmentGroupOffer createCandidateFulfillmentGroupOffer();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/OfferCodeDao.java" line="25">

---

## OfferCodeDao

OfferCodeDao provides an interface for managing OfferCode entities. It declares methods for creating, saving, and deleting OfferCode entities, as well as for retrieving OfferCode entities based on specific conditions.

```java
public interface OfferCodeDao {

    public OfferCode readOfferCodeById(Long offerCode);

    public List<OfferCode> readOfferCodesByIds(Collection<Long> offerCodeIds);

    public OfferCode readOfferCodeByCode(String code);

    public OfferCode save(OfferCode offerCode);

    public void delete(OfferCode offerCodeId);

    public OfferCode create();

    public Boolean offerCodeIsUsed(OfferCode code);

    public List<OfferCode> readAllOfferCodesByCode(String code);

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/dao/CustomerOfferDao.java" line="25">

---

## CustomerOfferDao

CustomerOfferDao provides an interface for managing CustomerOffer entities. It declares methods for creating, saving, and deleting CustomerOffer entities, as well as for retrieving CustomerOffer entities based on specific conditions.

```java
public interface CustomerOfferDao {

    public CustomerOffer readCustomerOfferById(Long customerOfferId);

    public List<CustomerOffer> readCustomerOffersByCustomer(Customer customer);

    public CustomerOffer save(CustomerOffer customerOffer);

    public void delete(CustomerOffer customerOffer);

    public CustomerOffer create();
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
