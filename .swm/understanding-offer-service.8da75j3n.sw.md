---
title: Understanding Offer Service
---
OfferService is an interface that defines the contract for the Offer Service in the Broadleaf Commerce framework. It provides methods to manage and manipulate offers in the system.

The OfferService is implemented by the OfferServiceImpl class. This class provides the actual implementation of the methods defined in the OfferService interface.

The OfferService is used throughout the Broadleaf Commerce framework to manage offers. For example, it is used in the OrderService to apply offers to orders, and in the OfferAuditService to record the usage of offers.

The OfferService provides methods to save new offers or update existing ones, find all offers, lookup offers by code, and apply offers to orders among others. These methods provide the core functionality for managing offers in the Broadleaf Commerce framework.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/OfferService.java" line="45">

---

# OfferService Interface

This is the OfferService interface. It declares a set of methods for managing offers in the system. These include methods for saving offers, finding all offers, looking up offers by code, and applying offers to orders.

```java
public interface OfferService {

    /**
     * Returns all offers
     * @return all offers
     */
    List<Offer> findAllOffers();

    /**
     * Save a new offer or updates an existing offer
     * @param offer
     * @return the offer
     */
    Offer save(Offer offer);

    /**
     * Saves a new Offer or updates an existing Offer that belongs to an OfferCode, then saves or updates the OfferCode
     * @param offerCode
     * @return the offerCode
     */
    OfferCode saveOfferCode(OfferCode offerCode);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/OfferServiceImpl.java" line="75">

---

# OfferServiceImpl Class

This is the OfferServiceImpl class, which implements the OfferService interface. It provides the actual implementation of the methods declared in the OfferService interface.

```java
@Service("blOfferService")
public class OfferServiceImpl implements OfferService {
    
    private static final Log LOG = LogFactory.getLog(OfferServiceImpl.class);

    // should be called outside of Offer service after Offer service is executed
    @Resource(name="blCustomerOfferDao")
    protected CustomerOfferDao customerOfferDao;

    @Resource(name="blOfferCodeDao")
    protected OfferCodeDao offerCodeDao;
    
    @Resource(name="blOfferAuditService")
    protected OfferAuditService offerAuditService;

    @Resource(name="blOfferDao")
    protected OfferDao offerDao;
    
    @Resource(name="blOrderOfferProcessor")
    protected OrderOfferProcessor orderOfferProcessor;
    
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderServiceImpl.java" line="134">

---

# Usage of OfferService

Here is an example of how the OfferService is used in the OrderServiceImpl class. The 'offerService' is injected as a resource and can be used to apply offers to orders.

```java
    @Resource(name = "blOfferService")
    protected OfferService offerService;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
