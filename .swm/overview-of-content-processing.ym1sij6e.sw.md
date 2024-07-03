---
title: Overview of Content Processing
---
Content Processing in BroadleafCommerce-demo refers to the functionality provided by the `ContentProcessor` class. This class is used to display structured content that is maintained with the Broadleaf CMS. It retrieves content based on various attributes such as content type, content name, maximum results, and field filters. The content is then sorted and filtered according to the specified parameters. The `ContentProcessor` class also provides methods to build MVEL parameters for content targeting rules and to determine if the current request is secure.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessor.java" line="95">

---

# ContentProcessor Class

The `ContentProcessor` class is the main class responsible for content processing. It contains methods for fetching and processing content based on various parameters such as content type, content name, maximum results, and field filters. It also handles sorting and filtering of the content items.

```java
@Component("blContentProcessor")
@ConditionalOnTemplating
public class ContentProcessor extends AbstractBroadleafVariableModifierProcessor {

    protected final Log LOG = LogFactory.getLog(getClass());
    public static final String REQUEST_DTO = "blRequestDTO";
    public static final String BLC_RULE_MAP_PARAM = "blRuleMap";

    @Resource(name = "blStructuredContentService")
    protected StructuredContentService structuredContentService;

    @Resource(name = "blStaticAssetService")
    protected StaticAssetService staticAssetService;

    @Resource(name = "blContentProcessorExtensionManager")
    protected ContentProcessorExtensionManager extensionManager;

    @Resource(name = "blContentDeepLinkService")
    protected ContentDeepLinkServiceImpl contentDeepLinkService;

    @Override
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessor.java" line="271">

---

# Content Retrieval

The `getContentItems` method is used to retrieve content items based on various parameters. It uses the `StructuredContentService` to fetch content items by name or type.

```java
    protected List<StructuredContentDTO> getContentItems(String contentName, Integer maxResults, HttpServletRequest request,
                                                         Map<String, Object> mvelParameters,
                                                         SandBox currentSandbox,
                                                         StructuredContentType structuredContentType,
                                                         Locale locale, String tagName, Map<String, String> tagAttributes,
                                                         Map<String, Object> newModelVars, BroadleafTemplateContext context) {
        List<StructuredContentDTO> contentItems;
        if (structuredContentType == null) {
            contentItems = structuredContentService.lookupStructuredContentItemsByName(contentName, locale, maxResults, mvelParameters, isSecure(request));
        } else {
            if (contentName == null || "".equals(contentName)) {
                contentItems = structuredContentService.lookupStructuredContentItemsByType(structuredContentType, locale, maxResults, mvelParameters, isSecure(request));
            } else {
                contentItems = structuredContentService.lookupStructuredContentItemsByName(structuredContentType, contentName, locale, maxResults, mvelParameters, isSecure(request));
            }
        }

        //add additional fields to the model
        extensionManager.getProxy().addAdditionalFieldsToModel(tagName, tagAttributes, newModelVars, context);

        return contentItems;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessor.java" line="139">

---

# Content Processing

The `populateModelVariables` method is used to process the content items. It handles sorting, filtering, and adding additional fields to the model. It also handles the creation of deep links for the content items.

```java
    @Override
    public Map<String, Object> populateModelVariables(String tagName, Map<String, String> tagAttributes, BroadleafTemplateContext context) {
        String contentType = tagAttributes.get("contentType");
        String contentName = tagAttributes.get("contentName");
        String maxResultsStr = tagAttributes.get("maxResults");

        if (StringUtils.isEmpty(contentType) && StringUtils.isEmpty(contentName)) {
            throw new IllegalArgumentException("The content processor must have a non-empty attribute value for 'contentType' or 'contentName'");
        }

        Integer maxResults = null;
        if (maxResultsStr != null) {
            maxResults = Ints.tryParse(maxResultsStr);
        }
        if (maxResults == null) {
            maxResults = Integer.MAX_VALUE;
        }

        String contentListVar = getAttributeValue(tagAttributes, "contentListVar", "contentList");
        String contentItemVar = getAttributeValue(tagAttributes, "contentItemVar", "contentItem");
        String numResultsVar = getAttributeValue(tagAttributes, "numResultsVar", "numResults");
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
