---
title: Exploring Offer Management
---
In the BroadleafCommerce-demo, an Offer is a crucial component of the e-commerce framework. It represents a promotional deal or discount that can be applied to items in a customer's shopping cart. The Offer is defined in the Offer interface and its implementation is provided in the OfferImpl class.

The Offer interface provides methods to determine if an offer is automatically added for consideration or if it can be combined with other offers. It also allows setting and getting the offer details.

The OfferService interface provides methods for managing offers, such as saving a new offer or updating an existing one, applying offers to an order, and building a list of offers that apply to a particular order.

The OfferTier interface represents a tier and amount combination for an offer. For example, an offer might allow a 10% off if a user purchases 1 -5 but then allow 15% off if they purchase more than 5.

The ItemOfferProcessor interface provides methods to review an item level offer against the list of discountable items from the order. If the offer applies, it is added to the qualifiedItemOffers list.

The AdvancedOfferPromotionMessageXref interface is used to associate an offer with a promotion message. It provides methods to get and set the offer and the promotion message.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/domain/Offer.java" line="37">

---

# Offer Interface

The Offer interface provides methods to set and get the details of an offer, such as its name, description, type, discount type, and value. It also provides methods to determine if an offer is automatically added for consideration or if it can be combined with other offers.

```java
public interface Offer extends Status, Serializable,MultiTenantCloneable<Offer> {

    public void setId(Long id);

    public Long getId();

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public OfferType getType();

    public void setType(OfferType offerType);

    public OfferDiscountType getDiscountType();

    public void setDiscountType(OfferDiscountType type);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/OfferService.java" line="171">

---

# OfferService Interface

The OfferService interface provides methods for managing offers, such as saving a new offer or updating an existing one, applying offers to an order, and building a list of offers that apply to a particular order.

```java
    ItemOfferProcessor getItemOfferProcessor();

    void setItemOfferProcessor(ItemOfferProcessor itemOfferProcessor);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/ItemOfferProcessor.java" line="43">

---

# ItemOfferProcessor Interface

The ItemOfferProcessor interface provides methods to review an item level offer against the list of discountable items from the order. If the offer applies, it is added to the qualifiedItemOffers list.

```java
    public void filterItemLevelOffer(PromotableOrder order, List<PromotableCandidateItemOffer> qualifiedItemOffers, Offer offer);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/domain/AdvancedOfferPromotionMessageXref.java" line="46">

---

# AdvancedOfferPromotionMessageXref Interface

The AdvancedOfferPromotionMessageXref interface is used to associate an offer with a promotion message. It provides methods to get and set the offer and the promotion message.

```java
    Offer getOffer();

    /**
     * Sets the Offer
     * @param offer
     */
    void setOffer(Offer offer);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
