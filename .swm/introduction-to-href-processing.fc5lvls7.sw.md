---
title: Introduction to Href Processing
---
Href Processing in BroadleafCommerce-demo refers to the functionality of handling href tags within the application. This is mainly done by the `HrefUrlRewriteProcessor` class, which extends the `UrlRewriteProcessor` class. This processor is responsible for modifying the attributes of href tags, especially those with a `useCdn=true` attribute or those inside a script tag. The modification process involves checking if the tag name equals 'link' or if the 'useCDN' attribute is set to true. Depending on these conditions, the href value is either set to the full asset path or parsed path. The modified href value is then returned as a new attribute.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/HrefUrlRewriteProcessor.java" line="39">

---

# HrefUrlRewriteProcessor Class

This is the HrefUrlRewriteProcessor class where the href processing takes place. It extends the UrlRewriteProcessor class and provides custom processing for href tags.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/HrefUrlRewriteProcessor.java" line="54">

---

# getModifiedAttributes Method

This is the 'getModifiedAttributes' method that is overridden to provide custom processing for href tags. It checks the 'useCDN' attribute and depending on its value, retrieves the href value as a full asset path or parses it.

```java
    @Override
    public BroadleafAttributeModifier getModifiedAttributes(String tagName, Map<String, String> tagAttributes, String attributeName,
            String attributeValue, BroadleafTemplateContext context) {
        String useCDN = tagAttributes.get("useCDN");
        String hrefValue = attributeValue;
        if (LINK.equals(tagName) || StringUtils.equals("true", useCDN)) {
            hrefValue = super.getFullAssetPath(tagName, attributeValue, context);
        } else {
            hrefValue = super.parsePath(attributeValue, context);
        }
        Map<String, String> newAttributes = new HashMap<>();
        newAttributes.put(HREF, hrefValue);
        return new BroadleafAttributeModifier(newAttributes);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/HrefUrlRewriteProcessor.java" line="49">

---

# getName Method

The 'getName' method is used to return the constant 'HREF'. This is used to identify the processor.

```java
    @Override
    public String getName() {
        return HREF;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
