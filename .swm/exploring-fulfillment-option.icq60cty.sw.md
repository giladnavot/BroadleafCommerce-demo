---
title: Exploring Fulfillment Option
---
A FulfillmentOption in BroadleafCommerce-demo refers to a specific type of fulfillment implementation. It holds information about a particular type of fulfillment implementation and is used to specify which shipping method a user wants. For instance, a site can ship with both UPS and Fedex. They will import both the Fedex and UPS third-party modules, each of which will have a unique definition of FulfillmentOption. When the user goes to check out, they will then see a list with "Overnight" and "2 day" in it. A FulfillmentPricingProvider can then be used to estimate the fulfillment cost for that particular option.

FulfillmentOptions are inherently related to FulfillmentProcessors, in that specific types of FulfillmentOption implementations should also have a FulfillmentPricingProvider that can handle operations for pricing a FulfillmentGroup. Typical third-party implementations of this paradigm would have a 1 FulfillmentOption entity implementation and 1 FulfillmentPricingProvider implementation for that particular service.

FulfillmentOption also has properties such as name, long description, and whether to use flat rates. The name is displayed to the user when they select the FulfillmentOption for their order. The long description provides more information about the option they are selecting. The use of flat rates tells the FulfillmentPricingProvider whether it should try to use the flat rate cost for a Sku rather than try to factor that Sku into its shipping calculation.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentOption.java" line="27">

---

# FulfillmentOption Interface

This is the FulfillmentOption interface. It defines several methods to get and set properties such as the ID, name, description, and fulfillment type of a fulfillment option. It also includes methods to get and set whether to use flat rates for shipping calculation and the tax code and taxability of the option.

```java
/**
 * A FulfillmentOption is used to hold information about a particular type of Fulfillment implementation.
 * Third-party fulfillment implementations should extend this to provide their own configuration options
 * particular to that implementation. For instance, a UPS shipping calculator might want an admin user to be
 * able to specify which type of UPS shipping this FulfillmentOption represents.
 * <br />
 * <br />
 * This entity will be presented to the user to allow them to specify which shipping they want. A possible
 * scenario is that say a site can ship with both UPS and Fedex. They will import both the Fedex and UPS
 * third-party modules, each of which will have a unique definition of FulfillmentOption (for instance,
 * FedexFulfillmentOption and UPSFulfillmentOption). Let's say the site can do 2-day shipping with UPS,
 * and next-day shipping with Fedex. What they would do in the admin is create an instance of FedexFulfillmentOption
 * entity and give it the name "Overnight" (along with any needed Fedex configuration properties), then create an instance of
 * UPSFulfillmentOption and give it the name "2 Day". When the user goes to check out, they will then see a list
 * with "Overnight" and "2 day" in it. A FulfillmentPricingProvider can then be used to estimate the fulfillment cost
 * (and calculate the fulfillment cost) for that particular option.
 * <br />
 * <br />
 * FulfillmentOptions are also inherently related to FulfillmentProcessors, in that specific types of FulfillmentOption
 * implementations should also have a FulfillmentPricingProvider that can handle operations (estimation and calculation) for
 * pricing a FulfillmentGroup. Typical third-party implementations of this paradigm would have a 1 FulfillmentOption
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentGroup.java" line="35">

---

# FulfillmentOption Usage in FulfillmentGroup

FulfillmentOption is used in the FulfillmentGroup class. A FulfillmentGroup represents a group of order items that are shipped to the same address using the same fulfillment option. The getFulfillmentOption and setFulfillmentOption methods are used to associate a FulfillmentOption with a FulfillmentGroup.

```java
 * items multiple ways (ship some overnight, deliver some with digital download). This constraint means
 * that a FulfillmentGroup is unique based on an Address and FulfillmentOption combination. This
 * also means that in the common case for Orders that are being delivered to a single Address and
 * a single way (shipping everything express; ie a single FulfillmentOption) then there will be
 * only 1 FulfillmentGroup for that Order.
 * 
 * @author Phillip Verheyden
 * @see {@link Order}, {@link FulfillmentOption}, {@link Address}, {@link FulfillmentGroupItem}
 */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderMultishipOption.java" line="95">

---

# FulfillmentOption Usage in OrderMultishipOption

FulfillmentOption is also used in the OrderMultishipOption class. An OrderMultishipOption represents a shipping option for a specific order item in a multi-ship order. The getFulfillmentOption and setFulfillmentOption methods are used to associate a FulfillmentOption with an OrderMultishipOption.

```java
    /**
     * Gets the associated FulfillmentOption with this OrderMultishipOption
     * 
     * @return the associated FulfillmentOption
     */
    public FulfillmentOption getFulfillmentOption();

    /**
     * Sets the associated FulfillmentOption with this OrderMultishipOption
     * 
     * @param fulfillmentOption the associated FulfillmentOption
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/FulfillmentOptionService.java" line="31">

---

# FulfillmentOption Usage in FulfillmentOptionService

The FulfillmentOptionService interface defines several methods for managing FulfillmentOption instances, including reading a FulfillmentOption by ID, saving a FulfillmentOption, and reading all FulfillmentOptions or all FulfillmentOptions of a specific type.

```java
    public FulfillmentOption readFulfillmentOptionById(Long fulfillmentOptionId);

    public FulfillmentOption save(FulfillmentOption option);

    public List<FulfillmentOption> readAllFulfillmentOptions();

    public List<FulfillmentOption> readAllFulfillmentOptionsByFulfillmentType(FulfillmentType type);
```

---

</SwmSnippet>

# FulfillmentOption Interface

The FulfillmentOption interface provides several methods that are crucial for the functioning of the FulfillmentOption in the Broadleaf Commerce framework.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/FulfillmentOption.java" line="58">

---

## FulfillmentOption Interface

The FulfillmentOption interface defines several methods that are essential for the functioning of the FulfillmentOption. These include methods to get and set the ID, name, long description, use of flat rates, fulfillment type, tax code, and taxability of the FulfillmentOption. Each of these methods plays a crucial role in defining the characteristics of a FulfillmentOption and how it operates within the Broadleaf Commerce framework.

```java
public interface FulfillmentOption extends Serializable, MultiTenantCloneable<FulfillmentOption> {
    
    public Long getId();
    
    public void setId(Long id);
    
    /**
     * Gets the name displayed to the user when they selected the FulfillmentOption for
     * their order. This might be "2-day" or "Super-saver shipping"
     * 
     * @return the display name for this option
     */
    public String getName();

    /**
     * Set the display name for this option that will be shown to the user to select from
     * such as "2-day" or "Express" or "Super-saver shipping"
     * 
     * @param name - the display name for this option
     */
    public void setName(String name);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
