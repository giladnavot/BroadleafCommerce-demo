---
title: Getting Started with Static Asset
---
A Static Asset in Broadleaf Commerce refers to any file that is used for display on the site, such as images, CSS, or JavaScript files. These assets can be managed through the Broadleaf admin tool. The StaticAsset interface provides methods to get and set properties of the asset such as its ID, name, URL, file size, and MIME type. There are also specialized types of static assets, such as ImageStaticAsset, which extends StaticAsset and adds properties for image width and height.

StaticAssetStorage is another important interface in the handling of static assets. It represents the storage data for a static asset, including the file data itself and the ID of the associated StaticAsset. This allows Broadleaf to handle storage of the asset data separately from the asset's properties, which can be useful for performance and scalability, especially when assets are stored in a database.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/StaticAsset.java" line="31">

---

# StaticAsset Interface

This is the StaticAsset interface. It defines methods for getting and setting properties of a static asset. These properties include the asset's ID, name, URL, file size, mime type, and file extension.

```java
public interface StaticAsset extends Serializable, MultiTenantCloneable<StaticAsset> {

    /**
     * Returns the id of the static asset.
     * @return
     */
    public Long getId();

    /**
     * Sets the id of the static asset.    
     * @param id
     */
    public void setId(Long id);


    /**
     * The name of the static asset.
     * @return
     */
    public String getName();

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceImpl.java" line="109">

---

# StaticAsset Usage

Here is an example of how the StaticAsset interface is used. In the StaticAssetServiceImpl class, the findStaticAssetById and readAllStaticAssets methods return StaticAsset instances retrieved from the database.

```java
    @Override
    public StaticAsset findStaticAssetById(Long id) {
        return staticAssetDao.readStaticAssetById(id);
    }

    @Override
    public List<StaticAsset> readAllStaticAssets() {
        return staticAssetDao.readAllStaticAssets();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/admin/web/controller/AdminAssetUploadController.java" line="116">

---

# StaticAsset Creation

This is an example of creating a new StaticAsset. The createStaticAssetFromFile method of the StaticAssetService is used to create a new StaticAsset from a file, and then the StaticAssetStorageService is used to store the asset.

```java
        StaticAsset staticAsset = staticAssetService.createStaticAssetFromFile(file, properties);
        staticAssetStorageService.createStaticAssetStorageFromFile(file, staticAsset);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
