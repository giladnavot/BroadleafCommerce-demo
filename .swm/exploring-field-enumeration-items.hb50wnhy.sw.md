---
title: Exploring Field Enumeration Items
---
Field Enumeration Items in BroadleafCommerce-demo refer to the individual items within a field enumeration. These items are represented by the `FieldEnumerationItemImpl` class. Each item has an ID, a name, a friendly name, and a field order. The field order determines the order in which the items appear within the enumeration. The `FieldEnumerationItemImpl` class also contains a reference to the `FieldEnumeration` it belongs to, allowing for a hierarchical structure of field enumerations and their items.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldEnumerationItemImpl.java" line="45">

---

# FieldEnumerationItemImpl Class

The `FieldEnumerationItemImpl` class is the implementation of the `FieldEnumerationItem` interface. It defines the properties of a Field Enumeration Item, including its ID, name, friendly name, order, and the Field Enumeration it belongs to.

```java
public class FieldEnumerationItemImpl implements FieldEnumerationItem {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "FieldEnumerationItemId")
    @GenericGenerator(
        name="FieldEnumerationItemId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="FieldEnumerationItemImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.field.domain.FieldEnumerationItemImpl")
        }
    )
    @Column(name = "FLD_ENUM_ITEM_ID")
    protected Long id;

    @Column (name = "NAME")
    protected String name;

    @Column (name = "FRIENDLY_NAME")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
