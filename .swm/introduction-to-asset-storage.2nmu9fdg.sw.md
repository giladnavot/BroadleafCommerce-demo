---
title: Introduction to Asset Storage
---
Asset Storage in BroadleafCommerce-demo refers to the functionality of storing and managing static assets such as images, CSS, and JavaScript files. This is primarily handled by the `StaticAssetStorageService` interface and its implementations. The service provides methods for creating, reading, updating, and deleting assets. While it was common to store assets in the database in earlier versions of Broadleaf, the preferred method since version 3.0 is to store assets on a shared filesystem. This change improves performance and scalability.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="33">

---

## StaticAssetStorageService Interface

The StaticAssetStorageService interface provides the methods for managing static assets. It includes methods for creating, reading, updating, and deleting assets.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/file/StaticAssetViewController.java" line="59">

---

## Using StaticAssetStorageService

The StaticAssetStorageService is used in various parts of the application, such as the StaticAssetViewController. Here, it is injected as a resource named 'blStaticAssetStorageService'.

```java
    protected StaticAssetStorageService staticAssetStorageService;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="121">

---

## Creating and Deleting Assets

The 'createStaticAssetStorageFromFile' and 'createStaticAssetStorage' methods are used to create a new asset. The 'delete' method is used to remove an asset from the database or filesystem.

```java
    void createStaticAssetStorageFromFile(MultipartFile file, StaticAsset staticAsset) throws IOException;
    
    /**
     * Similar to {@link #createStaticAssetStorageFromFile(MultipartFile, StaticAsset)} except that this does not depend on
     * an uploaded file and can support more generic use cases
     * 
     * @param fileInputStream the input stream of the uploaded file
     * @param staticAsset the {@link StaticAsset} entity to obtain storage information from like file size and the file name,
     * usually created from {@link StaticAssetService#createStaticAsset(InputStream, String, long, Map)}
     * @throws IOException
     * @see {@link StaticAssetService#createStaticAsset(InputStream, String, long, Map)}
     */
    void createStaticAssetStorage(InputStream fileInputStream, StaticAsset staticAsset) throws IOException;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="44">

---

## Reading and Updating Assets

The 'findStaticAssetStorageById' and 'readStaticAssetStorageByStaticAssetId' methods are used to read an existing asset. The 'save' method is used to update an existing asset.

```java
    StaticAssetStorage findStaticAssetStorageById(Long id);

    /**
     * @deprecated   Use createStaticAssetStorageFromFile instead.
     * @return
     */
    @Deprecated
    StaticAssetStorage create();

    /**
     * Returns a StaticAssetStorage object using the id of a related StaticAsset.   
     * Assumes that the asset is stored in the Database.
     * 
     * Storing Assets in the DB is not the preferred mechanism for Broadleaf as of 3.0 so in most cases, this 
     * method would not be used by Broadleaf implementations.
     * 
     * @param id
     * @return
     */
    StaticAssetStorage readStaticAssetStorageByStaticAssetId(Long id);
```

---

</SwmSnippet>

# Asset Storage Functions

This section provides an overview of the main functions related to Asset Storage in BroadleafCommerce-demo.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageServiceImpl.java" line="123">

---

## findStaticAsset

The `findStaticAsset` function is used to find a static asset by its full URL. It calls the `findStaticAssetByFullUrl` function of the `StaticAssetService` to perform the actual search.

```java
    protected StaticAsset findStaticAsset(String fullUrl) {
        StaticAsset staticAsset = staticAssetService.findStaticAssetByFullUrl(fullUrl);

        return staticAsset;
    }

    protected boolean shouldUseSharedFile(InputStream is) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageServiceImpl.java" line="423">

---

## createStaticAssetStorageFromFile

The `createStaticAssetStorageFromFile` function is used to create a static asset storage from a file. It calls the `createStaticAssetStorage` function with the input stream of the file and the static asset as parameters.

```java
    @Transactional("blTransactionManagerAssetStorageInfo")
    @Override
    public void createStaticAssetStorageFromFile(MultipartFile file, StaticAsset staticAsset) throws IOException {
        createStaticAssetStorage(file.getInputStream(), staticAsset);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageServiceImpl.java" line="429">

---

## createStaticAssetStorage

The `createStaticAssetStorage` function is used to create a static asset storage from an input stream. Depending on the storage type of the static asset, it either saves the asset to the database or the file system.

```java
    @Transactional("blTransactionManagerAssetStorageInfo")
    @Override
    public void createStaticAssetStorage(InputStream fileInputStream, StaticAsset staticAsset) throws IOException {
        if (StorageType.DATABASE.equals(staticAsset.getStorageType())) {
            StaticAssetStorage storage = create();
            storage.setStaticAssetId(staticAsset.getId());
            Blob uploadBlob = createBlob(fileInputStream, staticAsset.getFileSize());
            storage.setFileData(uploadBlob);
            save(storage);
        } else if (StorageType.FILESYSTEM.equals(staticAsset.getStorageType())) {
            FileWorkArea tempWorkArea = broadleafFileService.initializeWorkArea();
            // Convert the given URL from the asset to a system-specific suitable file path
            String destFileName = FilenameUtils.normalize(tempWorkArea.getFilePathLocation() + File.separator + FilenameUtils.separatorsToSystem(staticAsset.getFullUrl()));

            InputStream input = fileInputStream;
            byte[] buffer = new byte[getFileBufferSize()];

            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                if (!destFile.getParentFile().mkdirs()) {
                    if (!destFile.getParentFile().exists()) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageServiceImpl.java" line="315">

---

## save

The `save` function is used to persist a static asset storage. It calls the `save` function of the `StaticAssetStorageDao` to perform the actual saving.

```java
    @Override
    public StaticAssetStorage save(final StaticAssetStorage assetStorage) {
        final StaticAssetStorage[] storage = new StaticAssetStorage[1];
        transUtil.runTransactionalOperation(new StreamCapableTransactionalOperationAdapter() {
            @Override
            public void execute() {
                storage[0] = staticAssetStorageDao.save(assetStorage);
            }
        }, RuntimeException.class);
        return storage[0];
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageServiceImpl.java" line="327">

---

## delete

The `delete` function is used to remove a static asset storage. It calls the `delete` function of the `StaticAssetStorageDao` to perform the actual deletion.

```java
    @Override
    public void delete(final StaticAssetStorage assetStorage) {
        transUtil.runTransactionalOperation(new StreamCapableTransactionalOperationAdapter() {
            @Override
            public void execute() {
                staticAssetStorageDao.delete(assetStorage);
            }
        }, RuntimeException.class);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
