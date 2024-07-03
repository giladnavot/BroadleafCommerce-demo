---
title: Getting Started with Static Asset Storage
---
Static Asset Storage in Broadleaf Commerce refers to the mechanism for storing and managing static assets such as images, CSS, and JavaScript files. The `StaticAssetStorageService` interface provides methods for interacting with these assets, including creating, reading, updating, and deleting them. However, since Broadleaf 3.0, the preferred method for storing assets is on a shared filesystem rather than in the database. This approach improves performance and scalability.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="33">

---

# StaticAssetStorageService Interface

The `StaticAssetStorageService` interface is the main entry point for interacting with Static Asset Storage. It provides methods for creating, saving, finding, and deleting static assets.

```java
public interface StaticAssetStorageService {

    /**
     * Returns a StaticAssetStorage object.   Assumes that the asset is stored in the Database.
     * 
     * Storing Assets in the DB is not the preferred mechanism for Broadleaf as of 3.0 so in most cases, this 
     * method would not be used by Broadleaf implementations.
     * 
     * @param id
     * @return
     */
    StaticAssetStorage findStaticAssetStorageById(Long id);

    /**
     * @deprecated   Use createStaticAssetStorageFromFile instead.
     * @return
     */
    @Deprecated
    StaticAssetStorage create();

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="65">

---

# Using the save method

The `save` method is used to persist a static asset to the database. However, since Broadleaf 3.0, the preferred method for storing assets is on a shared filesystem.

```java
    /**
     * Persists a static asset to the database.   Not typically used since Broadleaf 3.0 as the 
     * preferred method for storing assets is on a shared-filesystem.
     * 
     * @param assetStorage
     * @return
     */
    StaticAssetStorage save(StaticAssetStorage assetStorage);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="74">

---

# Using the delete method

The `delete` method is used to remove a static asset from the database. Similar to the `save` method, it is not typically used since Broadleaf 3.0 as the preferred method for storing assets is on a shared filesystem.

```java
    /**
     * Removes a static asset from the database.   Not typically used since Broadleaf 3.0 as the 
     * preferred method for storing assets is on a shared-filesystem.
     * 
     * @param assetStorage
     */
    void delete(StaticAssetStorage assetStorage);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="111">

---

# Using the createStaticAssetStorageFromFile method

The `createStaticAssetStorageFromFile` method is used to persist the file to the DB or FileSystem according to the staticAsset's StorageType. It is typically used when a file is uploaded from a Controller like the AdminAssetUploadController.

```java
    /**
     * Persists the file to the DB or FileSystem according to the staticAsset's StorageType. Typically, the 
     * MultipartFile is passed in from a Controller like the AdminAssetUploadController 
     *  
     * @param file the uploaded file from the Spring controller
     * @param staticAsset the {@link StaticAsset} entity to obtain storage information from like file size and the file name,
     * usually created from {@link StaticAssetService#createStaticAssetFromFile(MultipartFile, Map)}
     * @throws IOException
     * @see {@link StaticAssetService#createStaticAssetFromFile(MultipartFile, Map)}
     */
    void createStaticAssetStorageFromFile(MultipartFile file, StaticAsset staticAsset) throws IOException;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
