---
title: Exploring Content Extension in Broadleaf Commerce
---
Content Extension in Broadleaf Commerce is a mechanism that allows for customization and extension of the content processing capabilities. It is implemented through the `ContentProcessorExtensionHandler` interface and its abstract implementation `AbstractContentProcessorExtensionHandler`. These provide methods to add additional fields to the model, add deep links based on extension fields, and post-process deep links. The `ContentProcessorExtensionManager` manages these extensions and controls the flow of execution among them.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessorExtensionHandler.java" line="34">

---

# ContentProcessorExtensionHandler Interface

This is the `ContentProcessorExtensionHandler` interface that developers can implement to extend the content processing system. It provides methods like `addAdditionalFieldsToModel`, `addExtensionFieldDeepLink`, and `postProcessDeepLinks` that can be overridden to customize the behavior of the content processor.

```java
public interface ContentProcessorExtensionHandler extends ExtensionHandler {

    /**
     * This method will add any additional attributes to the model that the extension needs
     *
     * @return - ExtensionResultStatusType
     */
    public ExtensionResultStatusType addAdditionalFieldsToModel(String tagName, Map<String, String> tagAttributes, Map<String, Object> newModelVars, BroadleafTemplateContext context);

    /**
     * Provides a hook point for an extension of content processor to optionally add in deep links
     * for a content item based on its extension fields
     * @param links
     * @param arguments
     * @param element
     * @return ExtensionResultStatusType
     */
    public ExtensionResultStatusType addExtensionFieldDeepLink(List<DeepLink> links, String tagName, Map<String, String> tagAttributes, BroadleafTemplateContext context);

    /**
     * Provides a hook point to allow extension handlers to modify the generated deep links.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessorExtensionManager.java" line="30">

---

# ContentProcessorExtensionManager Class

The `ContentProcessorExtensionManager` class extends `ExtensionManager` and manages the `ContentProcessorExtensionHandler` instances. It ensures that the handlers are invoked correctly during content processing.

```java
public class ContentProcessorExtensionManager extends ExtensionManager<ContentProcessorExtensionHandler> {

    public ContentProcessorExtensionManager() {
        super(ContentProcessorExtensionHandler.class);
    }

    @Override
    public boolean continueOnHandled() {
        return true;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/AbstractContentProcessorExtensionHandler.java" line="34">

---

# AbstractContentProcessorExtensionHandler Class

The `AbstractContentProcessorExtensionHandler` class is an abstract implementation of `ContentProcessorExtensionHandler`. Developers can extend this class to create their own handlers and override the methods they need.

```java
public abstract class AbstractContentProcessorExtensionHandler extends AbstractExtensionHandler implements ContentProcessorExtensionHandler {

    @Override
    public ExtensionResultStatusType addAdditionalFieldsToModel(String tagName, Map<String, String> tagAttributes, Map<String, Object> newModelVars, BroadleafTemplateContext context) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }

    @Override
    public ExtensionResultStatusType addExtensionFieldDeepLink(List<DeepLink> links, String tagName, Map<String, String> tagAttributes, BroadleafTemplateContext context) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }

    @Override
    public ExtensionResultStatusType postProcessDeepLinks(List<DeepLink> links) {
```

---

</SwmSnippet>

# Content Extension Functions

The Content Extension in BroadleafCommerce-demo provides functionalities for extending the content processing capabilities of the application.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/AbstractContentProcessorExtensionHandler.java" line="37">

---

## addAdditionalFieldsToModel

The `addAdditionalFieldsToModel` method is used to add any additional attributes to the model that the extension needs. It takes in the tag name, tag attributes, new model variables, and the context as parameters and returns an ExtensionResultStatusType.

```java
    public ExtensionResultStatusType addAdditionalFieldsToModel(String tagName, Map<String, String> tagAttributes, Map<String, Object> newModelVars, BroadleafTemplateContext context) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/AbstractContentProcessorExtensionHandler.java" line="42">

---

## addExtensionFieldDeepLink

The `addExtensionFieldDeepLink` method provides a hook point for an extension of content processor to optionally add in deep links for a content item based on its extension fields. It takes in the links, tag name, tag attributes, and the context as parameters and returns an ExtensionResultStatusType.

```java
    public ExtensionResultStatusType addExtensionFieldDeepLink(List<DeepLink> links, String tagName, Map<String, String> tagAttributes, BroadleafTemplateContext context) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/AbstractContentProcessorExtensionHandler.java" line="47">

---

## postProcessDeepLinks

The `postProcessDeepLinks` method provides a hook point to allow extension handlers to modify the generated deep links. It takes in the links as a parameter and returns an ExtensionResultStatusType.

```java
    public ExtensionResultStatusType postProcessDeepLinks(List<DeepLink> links) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
