---
title: Exploring Offer Service Types
---
In the BroadleafCommerce-demo repository, 'Type' within the 'Service' context, specifically in the 'offer.service.type' package, refers to different types of offers that can be made within the e-commerce framework. These types are represented as classes, such as 'OfferType', 'OfferRuleType', 'StackabilityType', and others.

Each 'Type' class in the 'offer.service.type' package extends the 'BroadleafEnumerationType' class, which is a common type used across the Broadleaf framework for creating extensible enums.

The 'OfferType' class, for instance, has a 'type' field that represents the type of the offer. This field is manipulated using the 'setType' method, which also updates a static 'TYPES' map that keeps track of all offer types.

The 'type' field is a string that uniquely identifies the type of offer. The 'setType' method ensures that each 'type' is unique by checking if the 'TYPES' map already contains the 'type' before adding it.

In addition to the 'type' field, the 'OfferType' class also has a 'friendlyType' field for a more human-readable representation of the type, and an 'order' field that determines the order in which the offer types are processed.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/type/OfferType.java" line="32">

---

# OfferType Class

The 'OfferType' class has a 'type' field that represents the type of the offer. This field is manipulated using the 'setType' method, which also updates a static 'TYPES' map that keeps track of all offer types.

```java
    private static final long serialVersionUID = 1L;

    private static final Map<String, OfferType> TYPES = new LinkedHashMap<String, OfferType>();
    public static final OfferType ORDER_ITEM = new OfferType("ORDER_ITEM", "Order Item", 1000);
    public static final OfferType ORDER = new OfferType("ORDER", "Order", 2000);
    public static final OfferType FULFILLMENT_GROUP = new OfferType("FULFILLMENT_GROUP", "Fulfillment Group", 3000);


    public static OfferType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;
    private int order;    
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/type/OfferType.java" line="58">

---

# setType Method

The 'setType' method is used to set the 'type' field of the 'OfferType' class. It also checks if the 'TYPES' map already contains the 'type' before adding it to ensure uniqueness.

```java
    public void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/type/OfferType.java" line="65">

---

# getType Method

The 'getType' method is used to retrieve the 'type' field of the 'OfferType' class.

```java
    public String getType() {
        return type;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
