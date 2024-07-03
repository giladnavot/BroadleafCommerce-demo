---
title: Introduction to Asset Management
---
Asset Management in BroadleafCommerce-demo refers to the handling of static assets such as images, CSS, and JavaScript files. This is primarily handled by the `StaticAssetService` and `StaticAssetStorageService` interfaces. These services provide methods for creating, reading, updating, and deleting assets. While assets can be stored in the database, the preferred method since Broadleaf 3.0 is to store them on a shared filesystem. This approach improves performance and scalability.

The `StaticAssetService` interface provides methods for creating and managing `StaticAsset` objects. These objects represent a static asset, such as an image or a CSS file. The service provides methods to create a `StaticAsset` from a file or an input stream, find a `StaticAsset` by its ID or URL, and add, update, or delete a `StaticAsset`.

The `StaticAssetStorageService` interface provides methods for managing the storage of static assets. It includes methods to find a `StaticAssetStorage` object by its ID, create a `StaticAssetStorage` object, and save or delete a `StaticAssetStorage` object. The `StaticAssetStorage` object represents the storage details of a static asset.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetService.java" line="32">

---

# StaticAssetService Interface

The `StaticAssetService` interface provides the methods for managing static assets. It includes methods for creating, reading, updating, and deleting static assets.

```java
public interface StaticAssetService {


    public StaticAsset findStaticAssetById(Long id);
    
    public List<StaticAsset> readAllStaticAssets();

    public StaticAsset findStaticAssetByFullUrl(String fullUrl);

    Long findTotalStaticAssetCount();

    /**
     * <p>
     * Used when uploading a file to Broadleaf.    This method will create the corresponding 
     * asset.   
     * 
     * <p>
     * Depending on the the implementation, the actual asset may be saved to the DB or to 
     * the file system.    The default implementation {@link StaticAssetServiceImpl} has a 
     * environment properties that determine this behavior <code>asset.use.filesystem.storage</code>
     * 
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceImpl.java" line="64">

---

# StaticAssetServiceImpl Class

The `StaticAssetServiceImpl` class is the implementation of the `StaticAssetService` interface. It provides the concrete implementation of the methods defined in the interface.

```java
@Service("blStaticAssetService")
public class StaticAssetServiceImpl implements StaticAssetService {

    private static final Log LOG = LogFactory.getLog(StaticAssetServiceImpl.class);
    private static final String UPLOAD_FILE_EXTENSION_EXCEPTION = "java.io.IOException: Invalid extension type of file.";

    @Resource(name = "blImageArtifactProcessor")
    protected ImageArtifactProcessor imageArtifactProcessor;

    @Value("${asset.use.filesystem.storage}")
    protected boolean storeAssetsOnFileSystem = false;

    @Resource(name = "blStaticAssetDao")
    protected StaticAssetDao staticAssetDao;

    @Resource(name = "blStaticAssetStorageService")
    protected StaticAssetStorageService staticAssetStorageService;

    @Resource(name = "blStaticAssetPathService")
    protected StaticAssetPathService staticAssetPathService;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/admin/web/controller/AdminAssetUploadController.java" line="116">

---

# Usage of StaticAssetService

Here, the `StaticAssetService` is used in the `AdminAssetUploadController` to create a new static asset from a file that is being uploaded.

```java
        StaticAsset staticAsset = staticAssetService.createStaticAssetFromFile(file, properties);
        staticAssetStorageService.createStaticAssetStorageFromFile(file, staticAsset);

        String staticAssetUrlPrefix = staticAssetService.getStaticAssetUrlPrefix();
        if (staticAssetUrlPrefix != null && !staticAssetUrlPrefix.startsWith("/")) {
            staticAssetUrlPrefix = "/" + staticAssetUrlPrefix;
        }

        String assetUrl =  staticAssetUrlPrefix + staticAsset.getFullUrl();

        responseMap.put("adminDisplayAssetUrl", request.getContextPath() + assetUrl);
        responseMap.put("assetUrl", assetUrl);
        responseMap.put("altText", staticAsset.getAltText());

        if (staticAsset instanceof ImageStaticAssetImpl) {
            responseMap.put("image", Boolean.TRUE);
            responseMap.put("assetThumbnail", assetUrl + "?smallAdminThumbnail");
            responseMap.put("assetLarge", assetUrl + "?largeAdminThumbnail");
        } else {
            responseMap.put("image", Boolean.FALSE);
        }
```

---

</SwmSnippet>

# Asset Management Endpoints

Asset Management Services

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetService.java" line="35">

---

## findStaticAssetById

The `findStaticAssetById` method is used to retrieve a StaticAsset object by its ID. This method is part of the StaticAssetService interface.

```java
    public StaticAsset findStaticAssetById(Long id);
    
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetService.java" line="69">

---

## createStaticAssetFromFile

The `createStaticAssetFromFile` method is used to create a StaticAsset object from a file. This method takes a MultipartFile and a Map of properties as parameters. This method is part of the StaticAssetService interface.

```java
    public StaticAsset createStaticAssetFromFile(MultipartFile file, Map<String, String> properties);
    
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
