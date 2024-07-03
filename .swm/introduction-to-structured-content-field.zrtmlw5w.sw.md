---
title: Introduction to Structured Content Field
---
A Structured Content Field holds the values for custom fields that are part of a Structured Content item. Each item maintains a list of its custom fields. The fields associated with an item are determined by the Field Definitions associated with the Structured Content Type. For example, a Structured Content Type might be configured to contain a field definition with a key of 'targetUrl'.

The Structured Content Field interface includes methods to get and set the primary key, field key, and value of the field. It also includes a clone method to create a deep copy of the object, cloning the field key and value fields and ignoring the auditable and id fields.

In the Broadleaf Commerce CMS Module, the Structured Content Field is used in various places such as the Structured Content Service Implementation, Structured Content Type Custom Persistence Handler, and the Structured Content Field Implementation. It is also defined as a bean in the CMS application context entity.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentField.java" line="39">

---

# StructuredContentField Interface

This is the StructuredContentField interface. It extends Serializable, Cloneable, and MultiTenantCloneable. It provides methods to get and set the ID, field key, and value of the custom field. It also provides a clone method to create a deep copy of the object.

```java
public interface StructuredContentField extends Serializable, Cloneable,MultiTenantCloneable<StructuredContentField> {

    /**
     * Gets the primary key.
     *
     * @return the primary key
     */
    @Nullable
    public Long getId();


    /**
     * Sets the primary key.
     *
     * @param id the new primary key
     */
    public void setId(@Nullable Long id);

    /**
     * Returns the fieldKey associated with this field.   The key used for a
     * <code>StructuredContentField</code> is determined by the associated
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentImpl.java" line="211">

---

# Usage of StructuredContentField

Here is an example of how StructuredContentField is used in the StructuredContentImpl class. The class maintains a map of StructuredContentFields, which can be accessed and modified using the provided methods.

```java
    @Transient
    protected Map<String, StructuredContentField> legacyStructuredContentFields = new HashMap<String, StructuredContentField>();

    @AdminPresentation(friendlyName = "StructuredContentImpl_Offline", order = 4,
        group = Presentation.Group.Name.Description, groupOrder = Presentation.Group.Order.Description)
    @Column(name = "OFFLINE_FLAG")
    @Index(name="SC_OFFLN_FLG_INDX", columnNames={"OFFLINE_FLAG"})
    protected Boolean offlineFlag = false;

    @Transient
    protected Map<String, String> fieldValuesMap = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
```

---

</SwmSnippet>

# StructuredContentField Interface

The StructuredContentField interface provides several key methods for managing the custom fields of a StructuredContent item.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentField.java" line="46">

---

## getId and setId

The `getId` and `setId` methods are used to get and set the primary key of the StructuredContentField respectively.

```java
    @Nullable
    public Long getId();


    /**
     * Sets the primary key.
     *
     * @param id the new primary key
     */
    public void setId(@Nullable Long id);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentField.java" line="69">

---

## getFieldKey and setFieldKey

The `getFieldKey` and `setFieldKey` methods are used to get and set the fieldKey associated with this field. The fieldKey is determined by the associated FieldDefinition that was used by the Content Management System to create this instance.

```java
    @Nonnull
    public String getFieldKey();

    /**
     * Sets the fieldKey.
     * @param fieldKey
     * @see org.broadleafcommerce.cms.field.domain.FieldDefinition
     */
    public void setFieldKey(@Nonnull String fieldKey);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentField.java" line="84">

---

## getValue and setValue

The `getValue` and `setValue` methods are used to get and set the value for this custom field.

```java
    public void setValue(@Nonnull String value);

    /**
     * Sets the value of this custom field.
     * @return
     */
    @Nonnull
    public String getValue();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentField.java" line="97">

---

## clone

The `clone` method is used to create a deep copy of this object. By default, it clones the fieldKey and value fields and ignores the auditable and id fields.

```java
    public StructuredContentField clone();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
