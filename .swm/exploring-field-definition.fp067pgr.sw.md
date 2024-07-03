---
title: Exploring Field Definition
---
Field Definition in BroadleafCommerce-demo refers to a specific structure used to define fields in the content management system. It includes properties such as name, field type, order, and security level. Field Definitions are used in Field Groups, which are collections of Field Definitions. They are used extensively throughout the codebase, particularly in the content management module, to define and manipulate the fields of various entities.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldDefinition.java" line="35">

---

## Field Definition Properties

Here we can see some of the properties of a Field Definition, such as its name and type. The `getName` and `getFieldType` methods are used to retrieve these properties, while the `setName` and `setFieldType` methods are used to set them.

```java
    public String getName();

    public void setName(String name);

    public SupportedFieldType getFieldType();

    String getFieldTypeVal();

    public void setFieldType(SupportedFieldType fieldType);

    void setFieldType(String fieldType);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldGroup.java" line="47">

---

## Using Field Definitions in Field Groups

Field Definitions are used in Field Groups, as shown by the `getFieldDefinitions` and `setFieldDefinitions` methods. These methods allow you to retrieve and set the Field Definitions that are part of a Field Group.

```java
    public List<FieldDefinition> getFieldDefinitions();

    public void setFieldDefinitions(List<FieldDefinition> fieldDefinitions);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/admin/server/handler/StructuredContentTypeCustomPersistenceHandler.java" line="172">

---

## Using Field Definitions in the CMS

In the CMS, Field Definitions are used in various ways. For example, in the `fetchDynamicEntity` method, Field Definitions are retrieved from a Field Group and used to create a new Property.

```java
        for (FieldGroup fieldGroup : structuredContent.getStructuredContentType().getStructuredContentFieldTemplate().getFieldGroups()) {
            for (FieldDefinition def : fieldGroup.getFieldDefinitions()) {
                Property property = new Property();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
