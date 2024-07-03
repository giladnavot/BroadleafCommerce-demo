---
title: Understanding URL Rewriting
---
URL Rewriting is a technique used in BroadleafCommerce-demo to modify the URL structure of resources served from the server. It is implemented in the `UrlRewriteProcessor` class, which is a Thymeleaf processor. This class processes the given URL through the `StaticAssetService`'s `convertAssetPath` method to determine the appropriate URL for the asset to be served from. The `UrlRewriteProcessor` class also contains methods to handle different types of requests, such as checking if the request is secure, if it's an admin request, or if the request is for an image tag. The URL rewriting process is crucial for serving static assets correctly and securely in the application.

# URL Rewriting Functions

This section discusses the main functions involved in URL rewriting in the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="69">

---

## getFullAssetPath

The `getFullAssetPath` function is the main function for URL rewriting. It takes the tag name, attribute value, and context as parameters. It checks if the request is secure, parses the path, checks if the tag is an image tag and if the request is from an admin, and if the extension is not an image extension, it modifies the asset path. Finally, it converts the asset path using the `staticAssetPathService`.

```java
    protected String getFullAssetPath(String tagName, String attributeValue, BroadleafTemplateContext context) {
        HttpServletRequest request = BroadleafRequestContext.getBroadleafRequestContext().getRequest();
        boolean secureRequest = true;
        if (request != null) {
            secureRequest = isRequestSecure(request);
        }

        String assetPath = parsePath(attributeValue, context);

        String extension = getFileExtension(assetPath);
        if (isImageTag(tagName) && isAdminRequest() && !isImageExtension(extension)) {
            String defaultFileTypeImagePath = getDefaultFileTypeImagePath(extension);
            String queryString = getQueryString(assetPath);

            assetPath = parsePath(defaultFileTypeImagePath + queryString, context);
        }

        // We are forcing an evaluation of @{} from Thymeleaf above which will automatically add a contextPath,
        // no need to add it twice
        return staticAssetPathService.convertAssetPath(assetPath, null, secureRequest);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="94">

---

## isRequestSecure

The `isRequestSecure` function checks if the current request is secure. It returns true if the request scheme is HTTPS or if the request is secure.

```java
    protected boolean isRequestSecure(HttpServletRequest request) {
        return ("HTTPS".equalsIgnoreCase(request.getScheme()) || request.isSecure());
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="98">

---

## isImageTag

The `isImageTag` function checks if the given tag name is an image tag. It returns true if the tag name is 'img'.

```java
    protected boolean isImageTag(String tagName) {
        return "img".equals(tagName);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="102">

---

## isAdminRequest

The `isAdminRequest` function checks if the current request is from an admin. It returns true if the current request context is admin.

```java
    protected boolean isAdminRequest() {
        return BroadleafRequestContext.getBroadleafRequestContext().getAdmin();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="106">

---

## isImageExtension

The `isImageExtension` function checks if the given extension is an image extension. It returns true if the extension is contained in the list of image extensions.

```java
    protected Boolean isImageExtension(String extension) {
        String imageExtensions = BLCSystemProperty.resolveSystemProperty("admin.image.file.extensions");

        return imageExtensions.contains(extension);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="112">

---

## parsePath

The `parsePath` function parses the given attribute value and context. It modifies the attribute value if it starts with a '/', and then parses the expression using the context.

```java
    protected String parsePath(String attributeValue, BroadleafTemplateContext context) {
        String newAttributeValue = attributeValue;
        if (newAttributeValue.startsWith("/")) {
            newAttributeValue = "@{ " + newAttributeValue + " }";
        }
        return (String) context.parseExpression(newAttributeValue);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="161">

---

## getQueryString

The `getQueryString` function retrieves the query string from the given asset path. It returns the substring from the last index of '?' in the asset path, or an empty string if '?' is not found.

```java
    protected String getQueryString(String assetPath) {
        int queryStartIndex = assetPath.lastIndexOf("?");

        return (queryStartIndex > 0) ? assetPath.substring(queryStartIndex) : "";
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
