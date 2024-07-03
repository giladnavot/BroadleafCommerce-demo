---
title: Overview of Tax Type
---
TaxType in BroadleafCommerce-demo refers to the different types of taxes that can be applied in the e-commerce context. It is an extensible enumeration of tax detail types, which includes CITY, STATE, DISTRICT, COUNTY, COUNTRY, SHIPPING, and COMBINED. Each of these types represents a specific tax that can be applied to an order. For example, CITY represents city tax, STATE represents state tax, and so on. The COMBINED type is used to represent total taxes owed.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="38">

---

# TaxType Constants

These are the static constants provided by the TaxType class. Each constant represents a specific type of tax. For example, CITY represents city tax, STATE represents state tax, and so on.

```java
    public static final TaxType CITY = new TaxType("CITY", "City");
    public static final TaxType STATE = new TaxType("STATE", "State");
    public static final TaxType DISTRICT = new TaxType("DISTRICT", "District");
    public static final TaxType COUNTY = new TaxType("COUNTY", "County");
    public static final TaxType COUNTRY = new TaxType("COUNTRY", "Country");
    public static final TaxType SHIPPING = new TaxType("SHIPPING", "Shipping");

    // Used by SimpleTaxProvider to represent total taxes owed.
    public static final TaxType COMBINED = new TaxType("COMBINED", "Combined");
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="59">

---

# TaxType Constructor

This is the constructor of the TaxType class. It takes a type string and a friendlyType string as parameters. The type string is used to identify the type of tax, and the friendlyType string is used for display purposes.

```java
    public TaxType(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="48">

---

# getInstance Method

The getInstance method is used to retrieve an instance of TaxType based on the type string. It returns the TaxType instance from the TYPES map that matches the provided type string.

```java
    public static TaxType getInstance(final String type) {
        return TYPES.get(type);
    }
```

---

</SwmSnippet>

# TaxType Functions

This section will cover the main functions of the TaxType class.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="48">

---

## getInstance

The `getInstance` function is a static method that returns a TaxType instance based on the provided type string. It uses the TYPES map to look up the corresponding TaxType.

```java
    public static TaxType getInstance(final String type) {
        return TYPES.get(type);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="64">

---

## getType

The `getType` function returns the type of the TaxType instance. This is a string that represents the type of tax, such as 'CITY', 'STATE', 'COUNTRY', etc.

```java
    public String getType() {
        return type;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxType.java" line="68">

---

## getFriendlyType

The `getFriendlyType` function returns a user-friendly string that describes the tax type. This could be used for display purposes in a user interface.

```java
    public String getFriendlyType() {
        return friendlyType;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
