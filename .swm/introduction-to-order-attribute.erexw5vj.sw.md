---
title: Introduction to Order Attribute
---
The OrderAttribute in BroadleafCommerce-demo refers to a feature that allows for arbitrary data to be persisted with the order. It is an interface that extends Serializable and MultiTenantCloneable, indicating that instances of OrderAttribute can be serialized and cloned. The OrderAttribute interface defines methods for getting and setting the ID, value, name, and associated order. This flexibility allows developers to add any additional information to an order that may be necessary for business requirements.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderAttribute.java" line="29">

---

# OrderAttribute Interface

This is the OrderAttribute interface. It defines methods for getting and setting the id, value, associated order, and name of the OrderAttribute.

```java
public interface OrderAttribute extends Serializable, MultiTenantCloneable<OrderAttribute> {

    /**
     * Gets the id.
     * 
     * @return the id
     */
    Long getId();

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    void setId(Long id);

    /**
     * Gets the value.
     * 
     * @return the value
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderImpl.java" line="264">

---

# Using OrderAttribute

Here is an example of how OrderAttribute is used in the OrderImpl class. An Order object maintains a map of OrderAttributes, which can be set and retrieved using the provided methods.

```java
        group = GroupName.Advanced, order = FieldOrder.ATTRIBUTES)
    protected Map<String,OrderAttribute> orderAttributes = new HashMap<>();
    
    @ManyToOne(targetEntity = BroadleafCurrencyImpl.class)
    @JoinColumn(name = "CURRENCY_CODE")
    @AdminPresentation(excluded = true)
    protected BroadleafCurrency currency;

    @ManyToOne(targetEntity = LocaleImpl.class)
    @JoinColumn(name = "LOCALE_CODE")
    @AdminPresentation(excluded = true)
    protected Locale locale;

    @Column(name = "TAX_OVERRIDE")
    protected Boolean taxOverride;

    @Transient
    protected List<ActivityMessageDTO> orderMessages;

    @Override
    public Long getId() {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderAttributeImpl.java" line="60">

---

# OrderAttribute Implementation

This is the implementation of the OrderAttribute interface. It provides the concrete implementation of the methods defined in the interface.

```java
@AdminPresentationClass(friendlyName = "OrderAttributeImpl_baseProductAttribute")
public class OrderAttributeImpl implements OrderAttribute {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator= "OrderAttributeId")
    @GenericGenerator(
        name="OrderAttributeId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="OrderAttributeImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.core.catalog.domain.OrderAttributeImpl")
        }
    )
    @Column(name = "ORDER_ATTRIBUTE_ID")
    protected Long id;
    
    @Column(name = "NAME", nullable=false)
    @AdminPresentation(friendlyName = "OrderAttributeImpl_Attribute_Name", order=1000, prominent=true)
    protected String name;
```

---

</SwmSnippet>

# OrderAttribute Interface Functions

The OrderAttribute interface provides several methods for managing order attributes.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/OrderAttribute.java" line="29">

---

## OrderAttribute Interface

The OrderAttribute interface provides methods for getting and setting the ID, value, associated order, and name of an order attribute. These methods allow for arbitrary data to be persisted with the order, enhancing the flexibility and extensibility of the order management system.

```java
public interface OrderAttribute extends Serializable, MultiTenantCloneable<OrderAttribute> {

    /**
     * Gets the id.
     * 
     * @return the id
     */
    Long getId();

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    void setId(Long id);

    /**
     * Gets the value.
     * 
     * @return the value
     */
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
