---
title: >-
  Working Mechanism of Field, FieldEntity and IndexField in Field Index
  Management
---
This document will cover the interaction between Field, FieldEntity, and IndexField in managing the field index in the BroadleafCommerce-demo repository. The topics covered include:

1. The role of IndexFieldDao in managing the field index.
2. The interaction between Field, FieldEntity, and IndexField.
3. The process of attaching indexable document fields.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/dao/IndexFieldDao.java" line="28">

---

# The Role of IndexFieldDao

`IndexFieldDao` is an interface that provides methods to interact with the database search fields. It provides methods to read IndexField instances associated with a given field or field ID, find all IndexFields associated with a given field ID or entity type, and read all searchable IndexFields based on the entity type.

```java
/**
 * DAO used to interact with the database search fields
 *
 * @author Nick Crum (ncrum)
 */
public interface IndexFieldDao {

    /**
     * Returns the IndexField instance associated with the given field parameter, or null if non exists.
     *
     * @param field the Field we are looking for the IndexField for
     * @return a IndexField instance for the given field
     */
    public IndexField readIndexFieldForField(Field field);

    /**
     * Returns the IndexField instance associated with the given field parameter, or null if non exists.
     *
     * @param fieldId the Field we are looking for the IndexField for
     * @return a IndexField instance for the given field
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/IndexField.java" line="54">

---

# Interaction between Field, FieldEntity, and IndexField

`IndexField` provides methods to get and set the `Field` for this search field and to set the searchable field types for this search field. The `Field` is associated with the `IndexField` instance.

```java
    /**
     * Gets the field for this search field
     *
     * @return
     */
    public Field getField();

    /**
     * Sets the field for this search field
     *
     * @param field
     */
    public void setField(Field field);

    /**
     * Gets the searchable field types for this search field
     *
     * @return
     */
    public List<IndexFieldType> getFieldTypes();

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/FieldEntity.java" line="38">

---

`FieldEntity` provides a method to get all lookup types, which is used to find all IndexFields based on the entity type.

```java
    private static final long serialVersionUID = 1L;

    private static final Map<String, FieldEntity> TYPES = new LinkedHashMap<String, FieldEntity>();

    public static final FieldEntity PRODUCT = new FieldEntity("PRODUCT", "Product");
    public static final FieldEntity SKU = new FieldEntity("SKU", "Sku");
    public static final FieldEntity CUSTOMER = new FieldEntity("CUSTOMER", "Customer");
    public static final FieldEntity CATEGORY = new FieldEntity("CATEGORY", "Category");
    public static final FieldEntity ORDER = new FieldEntity("ORDER", "Order");
    public static final FieldEntity ORDERITEM = new FieldEntity("ORDER_ITEM", "Order Item");
    public static final FieldEntity OFFER = new FieldEntity("OFFER", "Offer");
    public static final FieldEntity FULFILLMENT_ORDER = new FieldEntity("FULFILLMENT_ORDER", "Fulfillment Order");

    public static FieldEntity getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;
    protected List<String> additionalLookupTypes = new ArrayList<>();;

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/index/SolrIndexServiceImpl.java" line="515">

---

# Attaching Indexable Document Fields

The `attachIndexableDocumentFields` method in `SolrIndexServiceImpl` is used to attach indexable document fields. It iterates over the fields, gets the property values for each field type, and adds a field to the document for each property value.

```java
    @Override
    public void attachIndexableDocumentFields(SolrInputDocument document, Indexable indexable, List<IndexField> fields, List<Locale> locales) {
        for (IndexField indexField : fields) {
            try {
                // If we find an IndexField entry for this field, then we need to store it in the index
                if (indexField != null) {
                    List<IndexFieldType> searchableFieldTypes = indexField.getFieldTypes();

                    // For each of its search field types, get the property values, and add a field to the document for each property value
                    for (IndexFieldType sft : searchableFieldTypes) {
                        FieldType fieldType = sft.getFieldType();
                        Map<String, Object> propertyValues = getPropertyValues(indexable, indexField.getField(), fieldType, locales);

                        ExtensionResultStatusType result = extensionManager.getProxy().populateDocumentForIndexField(document, indexField, fieldType, propertyValues);

                        if (ExtensionResultStatusType.NOT_HANDLED.equals(result)) {
                            // Build out the field for every prefix
                            for (Entry<String, Object> entry : propertyValues.entrySet()) {
                                String prefix = entry.getKey();
                                prefix = StringUtils.isBlank(prefix) ? prefix : prefix + "_";

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
