---
title: Overview of URL Processor
---
The URL Processor in BroadleafCommerce-demo refers to a set of classes that handle URL rewriting. These classes include `UrlRewriteProcessor` and `HrefUrlRewriteProcessor`. The `UrlRewriteProcessor` class is a Thymeleaf processor that processes a given URL through the `StaticAssetService` to determine the appropriate URL for the asset to be served from. The `HrefUrlRewriteProcessor` class is similar to `UrlRewriteProcessor` but handles href tags, mainly those that have a `useCdn=true` attribute or those that are inside a script tag.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/UrlRewriteProcessor.java" line="44">

---

# UrlRewriteProcessor

This is the `UrlRewriteProcessor` class. It is annotated with `@Component("blUrlRewriteProcessor")` which makes it a Spring bean and allows it to be autowired where needed. The `getModifiedAttributes` method is where the URL is rewritten based on the tag attributes and context.

```java
@Component("blUrlRewriteProcessor")
@ConditionalOnTemplating
public class UrlRewriteProcessor extends AbstractBroadleafAttributeModifierProcessor {

    @Resource(name = "blStaticAssetPathService")
    protected StaticAssetPathService staticAssetPathService;

    @Override
    public String getName() {
        return "src";
    }

    @Override
    public int getPrecedence() {
        return 1000;
    }

    @Override
    public BroadleafAttributeModifier getModifiedAttributes(String tagName, Map<String, String> tagAttributes, String attributeName,
            String attributeValue, BroadleafTemplateContext context) {
        Map<String, String> newAttributes = new HashMap<>();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/HrefUrlRewriteProcessor.java" line="39">

---

# HrefUrlRewriteProcessor

The `HrefUrlRewriteProcessor` class extends `UrlRewriteProcessor` and is used specifically for handling `href` tags. It checks if the tag is a `link` or if `useCDN` attribute is `true`, and modifies the `href` value accordingly.

```java
@Component("blHrefUrlRewriteProcessor")
@ConditionalOnTemplating
public class HrefUrlRewriteProcessor extends UrlRewriteProcessor {

    @Resource(name = "blStaticAssetPathService")
    protected StaticAssetPathService staticAssetPathService;

    private static final String LINK = "link";
    private static final String HREF = "href";

    @Override
    public String getName() {
        return HREF;
    }

    @Override
    public BroadleafAttributeModifier getModifiedAttributes(String tagName, Map<String, String> tagAttributes, String attributeName,
            String attributeValue, BroadleafTemplateContext context) {
        String useCDN = tagAttributes.get("useCDN");
        String hrefValue = attributeValue;
        if (LINK.equals(tagName) || StringUtils.equals("true", useCDN)) {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
