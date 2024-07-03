---
title: Understanding Field Group Implementation
---
Field Group Implementation in Broadleaf Commerce refers to the way fields are grouped together for structured content. This is implemented in the `FieldGroupImpl` class. The class contains a list of `FieldDefinition` objects, which define the individual fields within the group. The `FieldGroupImpl` class also contains methods to get and set these field definitions, as well as other properties such as the group name and whether the group is initially collapsed. The `FieldGroupImpl` class is used in various parts of the codebase, including the `PageTemplateFieldGroupXrefImpl`, `StructuredContentFieldGroupXrefImpl`, and `FieldDefinitionImpl` classes.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldGroupImpl.java" line="51">

---

# FieldGroupImpl Class

The FieldGroupImpl class is the main implementation of the Field Group. It contains properties such as `id`, `name`, `initCollapsedFlag`, `fieldDefinitions`, and `fieldGroupXrefs`. It also provides getter and setter methods for these properties.

```java
public class FieldGroupImpl implements FieldGroup, ProfileEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "FieldGroupId")
    @GenericGenerator(
        name="FieldGroupId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="FieldGroupImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.field.domain.FieldGroupImpl")
        }
    )
    @Column(name = "FLD_GROUP_ID")
    protected Long id;

    @Column (name = "NAME")
    protected String name;

    @Column (name = "INIT_COLLAPSED_FLAG")
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldGroupImpl.java" line="80">

---

# Field Definitions

Field definitions are managed using the `fieldDefinitions` property, which is a list of FieldDefinition objects. The `getFieldDefinitions` and `setFieldDefinitions` methods are used to retrieve and set the field definitions respectively.

```java
    protected List<FieldDefinition> fieldDefinitions = new ArrayList<FieldDefinition>();

    @Column (name = "IS_MASTER_FIELD_GROUP")
    protected Boolean isMasterFieldGroup = false;

    @OneToMany(targetEntity = StructuredContentFieldGroupXrefImpl.class, mappedBy = "fieldGroup", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blCMSElements")
    @OrderBy("groupOrder")
    @BatchSize(size = 20)
    @ClonePolicyCollectionOverride
    protected List<StructuredContentFieldGroupXref> fieldGroupXrefs = new ArrayList<StructuredContentFieldGroupXref>();

    @Override
    public List<StructuredContentFieldGroupXref> getFieldGroupXrefs() {
        return fieldGroupXrefs;
    }

    @Override
    public void setFieldGroupXrefs(List<StructuredContentFieldGroupXref> fieldGroupXrefs) {
        this.fieldGroupXrefs = fieldGroupXrefs;
    }
```

---

</SwmSnippet>

# FieldGroupImpl Class Overview

This section provides an overview of the main functions in the FieldGroupImpl class.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldGroupImpl.java" line="51">

---

## FieldGroupImpl Class

The FieldGroupImpl class is a part of the Broadleaf Commerce CMS Module. It is used to manage field groups in the content management system. The class implements the FieldGroup and ProfileEntity interfaces. It contains several methods for getting and setting properties such as id, name, initCollapsedFlag, fieldDefinitions, and isMasterFieldGroup. It also contains methods for managing field group cross-references and creating or retrieving copy instances.

```java
public class FieldGroupImpl implements FieldGroup, ProfileEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "FieldGroupId")
    @GenericGenerator(
        name="FieldGroupId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="FieldGroupImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.field.domain.FieldGroupImpl")
        }
    )
    @Column(name = "FLD_GROUP_ID")
    protected Long id;

    @Column (name = "NAME")
    protected String name;

    @Column (name = "INIT_COLLAPSED_FLAG")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
