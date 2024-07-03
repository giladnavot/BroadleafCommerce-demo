---
title: Getting Started with Static Asset Description
---
StaticAssetDescription in BroadleafCommerce-demo refers to a specific interface and its implementation that are used to handle descriptions of static assets in the CMS module of the Broadleaf Commerce framework. It provides methods to get and set the ID, description, and long description of a static asset. It also includes a method to clone the entity, allowing for the creation of a new StaticAssetDescription instance with the same properties. This functionality is crucial for managing static assets, such as images or documents, that are used across the e-commerce platform.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescription.java" line="27">

---

# StaticAssetDescription Interface

This is the StaticAssetDescription interface. It declares methods for getting and setting the id, description, and long description of a static asset. It also declares a method for cloning the StaticAssetDescription.

```java
public interface StaticAssetDescription extends Serializable, MultiTenantCloneable<StaticAssetDescription> {

    public Long getId();

    public void setId(Long id);

    public String getDescription();

    public void setDescription(String description);

    public String getLongDescription();

    public void setLongDescription(String longDescription);

    public StaticAssetDescription cloneEntity();
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescriptionImpl.java" line="53">

---

# StaticAssetDescriptionImpl Class

This is the StaticAssetDescriptionImpl class which implements the StaticAssetDescription interface. It provides the implementation for the methods declared in the interface. It also includes the cloneEntity method which creates a new instance of StaticAssetDescriptionImpl with the same values.

```java
public class StaticAssetDescriptionImpl implements StaticAssetDescription {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "StaticAssetDescriptionId")
    @GenericGenerator(
        name="StaticAssetDescriptionId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="StaticAssetDescriptionImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.file.domain.StaticAssetDescriptionImpl")
        }
    )
    @Column(name = "STATIC_ASSET_DESC_ID")
    protected Long id;

    @Column (name = "DESCRIPTION")
    @AdminPresentation(friendlyName = "StaticAssetDescriptionImpl_Description", prominent = true)
    protected String description;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescription.java" line="35">

---

# setDescription Method

This is the setDescription method. It is used to set the description of the static asset.

```java
    public void setDescription(String description);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescription.java" line="33">

---

# getDescription Method

This is the getDescription method. It is used to get the description of the static asset.

```java
    public String getDescription();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescription.java" line="41">

---

# cloneEntity Method

This is the cloneEntity method. It is used to create a new instance of StaticAssetDescription with the same values.

```java
    public StaticAssetDescription cloneEntity();
```

---

</SwmSnippet>

# Static Asset Description Functions

The StaticAssetDescription interface and its implementation provide methods for managing descriptions of static assets.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescription.java" line="27">

---

## StaticAssetDescription Interface

The StaticAssetDescription interface defines the methods that must be implemented by any class that manages static asset descriptions. These methods include getters and setters for the ID, description, and long description of the asset, as well as a method for cloning the asset description.

```java
public interface StaticAssetDescription extends Serializable, MultiTenantCloneable<StaticAssetDescription> {

    public Long getId();

    public void setId(Long id);

    public String getDescription();

    public void setDescription(String description);

    public String getLongDescription();

    public void setLongDescription(String longDescription);

    public StaticAssetDescription cloneEntity();
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAssetDescriptionImpl.java" line="53">

---

## StaticAssetDescriptionImpl Class

The StaticAssetDescriptionImpl class is the implementation of the StaticAssetDescription interface. It provides the functionality for managing static asset descriptions, including retrieving and setting the ID, description, and long description of the asset, and cloning the asset description.

```java
public class StaticAssetDescriptionImpl implements StaticAssetDescription {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "StaticAssetDescriptionId")
    @GenericGenerator(
        name="StaticAssetDescriptionId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="StaticAssetDescriptionImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.file.domain.StaticAssetDescriptionImpl")
        }
    )
    @Column(name = "STATIC_ASSET_DESC_ID")
    protected Long id;

    @Column (name = "DESCRIPTION")
    @AdminPresentation(friendlyName = "StaticAssetDescriptionImpl_Description", prominent = true)
    protected String description;

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
