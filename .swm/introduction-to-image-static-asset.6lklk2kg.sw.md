---
title: Introduction to Image Static Asset
---
Image Static Asset in Broadleaf Commerce refers to a specific type of static asset that represents an image. It is an interface that extends the StaticAsset interface, adding methods to get and set the width and height of the image. The ImageStaticAssetImpl class is the implementation of this interface, and it is annotated as an entity, meaning instances of this class can be persisted to the database. The width and height properties are stored in the database as columns, and these properties are read-only in the admin interface.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/ImageStaticAsset.java" line="28">

---

# ImageStaticAsset Interface

This is the ImageStaticAsset interface. It extends the StaticAsset interface and adds two methods: getWidth() and getHeight(). These methods are used to get the width and height of the image asset respectively.

```java
public interface ImageStaticAsset extends StaticAsset {

    public Integer getWidth();

    public void setWidth(Integer width);

    public Integer getHeight();

    public void setHeight(Integer height);
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/ImageStaticAssetImpl.java" line="39">

---

# ImageStaticAssetImpl Class

This is the ImageStaticAssetImpl class. It implements the ImageStaticAsset interface and provides concrete implementations for the getWidth() and getHeight() methods. It also includes fields for width and height, which are annotated with @Column to map them to corresponding columns in the BLC_IMG_STATIC_ASSET table.

```java
public class ImageStaticAssetImpl extends StaticAssetImpl implements ImageStaticAsset {

    @Column(name ="WIDTH")
    @AdminPresentation(friendlyName = "ImageStaticAssetImpl_Width",
            order = FieldOrder.LAST + 1000,
            group = GroupName.File_Details,
            readOnly = true)
    protected Integer width;

    @Column(name ="HEIGHT")
    @AdminPresentation(friendlyName = "ImageStaticAssetImpl_Height",
            order = FieldOrder.LAST + 2000,
            group = GroupName.File_Details,
            readOnly = true)
    protected Integer height;

    @Override
    public Integer getWidth() {
        return width;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetServiceImpl.java" line="294">

---

# Using ImageStaticAsset in StaticAssetServiceImpl

Here, an instance of ImageStaticAssetImpl is created when a new image asset is being created. The width and height of the image are set using the setWidth() and setHeight() methods of the ImageStaticAsset interface.

```java
            newAsset = new ImageStaticAssetImpl();
            ((ImageStaticAsset) newAsset).setWidth(metadata.getWidth());
            ((ImageStaticAsset) newAsset).setHeight(metadata.getHeight());
```

---

</SwmSnippet>

# ImageStaticAsset Class Functions

This section will cover the main functions of the ImageStaticAsset class.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/ImageStaticAssetImpl.java" line="39">

---

## ImageStaticAsset Class

The ImageStaticAssetImpl class extends the StaticAssetImpl class and implements the ImageStaticAsset interface. It represents an image asset in the Broadleaf Commerce CMS. It has two main properties: width and height, which represent the dimensions of the image. These properties can be accessed and modified using the getWidth, setWidth, getHeight, and setHeight methods.

```java
public class ImageStaticAssetImpl extends StaticAssetImpl implements ImageStaticAsset {

    @Column(name ="WIDTH")
    @AdminPresentation(friendlyName = "ImageStaticAssetImpl_Width",
            order = FieldOrder.LAST + 1000,
            group = GroupName.File_Details,
            readOnly = true)
    protected Integer width;

    @Column(name ="HEIGHT")
    @AdminPresentation(friendlyName = "ImageStaticAssetImpl_Height",
            order = FieldOrder.LAST + 2000,
            group = GroupName.File_Details,
            readOnly = true)
    protected Integer height;

    @Override
    public Integer getWidth() {
        return width;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/ImageStaticAssetImpl.java" line="55">

---

## getWidth and setWidth Methods

The getWidth and setWidth methods are used to get and set the width of the image asset, respectively. The width is stored as an Integer.

```java
    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/domain/ImageStaticAssetImpl.java" line="65">

---

## getHeight and setHeight Methods

The getHeight and setHeight methods are used to get and set the height of the image asset, respectively. The height is stored as an Integer.

```java
    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
