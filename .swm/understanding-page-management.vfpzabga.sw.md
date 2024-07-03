---
title: Understanding Page Management
---
Page Management in Broadleaf Commerce involves the creation, modification, and organization of web pages for an e-commerce site. It includes defining page templates, setting page attributes, and applying page rules. Page templates define the layout and structure of a page. Page attributes store additional information for a page, such as metadata. Page rules determine when a page should be displayed based on certain conditions. The system also provides utilities for handling page-related operations, such as filtering inactive pages.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/domain/Page.java" line="136">

---

## Page and PageImpl

The `Page` interface provides methods to get and set additional attributes of a page. These methods are implemented in `PageImpl`.

```java
    public Map<String, PageAttribute> getAdditionalAttributes();

    public void setAdditionalAttributes(Map<String, PageAttribute> additionalAttributes);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/domain/PageAttribute.java" line="46">

---

## PageAttribute and PageAttributeImpl

`PageAttribute` interface provides methods to get and set a `Page` object. These methods are implemented in `PageAttributeImpl`.

```java
    public Page getPage();

    /**
     * Sets the {@link Page}
     * @param page
     */
    public void setPage(Page page);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceUtility.java" line="99">

---

## PageServiceUtility

`PageServiceUtility` uses the `getAdditionalAttributes` method of `Page` to retrieve the attributes of a page and add them to a `PageDTO` object.

```java
        for (Entry<String, PageAttribute> entry : page.getAdditionalAttributes().entrySet()) {
            pageDTO.getPageAttributes().put(entry.getKey(), entry.getValue().getValue());
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageQueryExtensionManager.java" line="30">

---

## PageQueryExtensionManager and PageServiceExtensionManager

`PageQueryExtensionManager` extends `ExtensionManager` to provide additional functionality for handling pages. Similarly, `PageServiceExtensionManager` extends `ExtensionManager` to provide additional services for pages.

```java
@Service("blPageQueryExtensionManager")
public class PageQueryExtensionManager extends ExtensionManager<SparselyPopulatedQueryExtensionHandler> {

    public PageQueryExtensionManager() {
        super(SparselyPopulatedQueryExtensionHandler.class);
    }

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
