---
title: Understanding the Structured Content Service Extension
---
The Structured Content Service Extension in Broadleaf Commerce is an interface that provides methods to modify and extend the functionality of the Structured Content Service. It is part of the Broadleaf Commerce CMS module and is designed to handle structured content, which is a type of content that is managed and displayed in a consistent and uniform manner. The extension handler allows for further modification of fields when parsing a StructuredContent into a StructuredContentDTO. It also allows an extension handler to modify the list of structured content items, for example, to alter the order of the passed-in list.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceExtensionHandler.java" line="33">

---

# StructuredContentServiceExtensionHandler Interface

This is the StructuredContentServiceExtensionHandler interface. It extends the ExtensionHandler interface and provides two methods: `populateAdditionalStructuredContentFields` and `modifyStructuredContentDtoList`.

```java
public interface StructuredContentServiceExtensionHandler extends ExtensionHandler {

    /**
     * Further modifies the fields when parsing a {@link StructuredContent} into a {@link StructuredContentDTO}. This method
     * will be invoked at the end of {@link StructuredContentServiceImpl#buildFieldValues(StructuredContent, StructuredContentDTO, boolean)}.
     * 
     * Note that even though this method should return an {@link ExtensionResultStatusType}, modifications should be made to
     * the {@link StructuredContentDTO} by using information from the {@link StructuredContent}.
     * 
     * @param sc the {@link StructuredContent} that should further be wrapped into the <b>dto</b>
     * @param dto the DTO that has already been mostly populated by Broadleaf. At this stage, this parameter will have all of
     * the properties from the default {@link StructuredContentDTO} already parsed
     * @param secure whether or not the request is secure
     * @return the result of executing this extension handler
     * 
     * @see {@link StructuredContentServiceImpl#buildFieldValues(StructuredContent, StructuredContentDTO, boolean)}
     * @see {@link StructuredContentServiceImpl#buildStructuredContentDTO(StructuredContent, boolean)}
     */
    public ExtensionResultStatusType populateAdditionalStructuredContentFields(StructuredContent sc, StructuredContentDTO dto, boolean secure);

    
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceExtensionHandler.java" line="51">

---

# populateAdditionalStructuredContentFields Method

The `populateAdditionalStructuredContentFields` method is used to further modify the fields when parsing a StructuredContent into a StructuredContentDTO. It takes in a StructuredContent, a StructuredContentDTO, and a boolean indicating whether the request is secure.

```java
    public ExtensionResultStatusType populateAdditionalStructuredContentFields(StructuredContent sc, StructuredContentDTO dto, boolean secure);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceExtensionHandler.java" line="64">

---

# modifyStructuredContentDtoList Method

The `modifyStructuredContentDtoList` method allows an extension handler to modify the list of structured content items. It takes in a list of StructuredContentDTOs and an ExtensionResultHolder.

```java
    public ExtensionResultStatusType modifyStructuredContentDtoList(List<StructuredContentDTO> structuredContentList,
            ExtensionResultHolder resultHolder);
```

---

</SwmSnippet>

# Functions of StructuredContentServiceExtensionHandler

The StructuredContentServiceExtensionHandler interface provides two main functions: populateAdditionalStructuredContentFields and modifyStructuredContentDtoList.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceExtensionHandler.java" line="35">

---

## populateAdditionalStructuredContentFields

The `populateAdditionalStructuredContentFields` function is used to further modify the fields when parsing a StructuredContent into a StructuredContentDTO. This method is invoked at the end of `StructuredContentServiceImpl#buildFieldValues`. Even though this method should return an `ExtensionResultStatusType`, modifications should be made to the `StructuredContentDTO` by using information from the `StructuredContent`.

```java
    /**
     * Further modifies the fields when parsing a {@link StructuredContent} into a {@link StructuredContentDTO}. This method
     * will be invoked at the end of {@link StructuredContentServiceImpl#buildFieldValues(StructuredContent, StructuredContentDTO, boolean)}.
     * 
     * Note that even though this method should return an {@link ExtensionResultStatusType}, modifications should be made to
     * the {@link StructuredContentDTO} by using information from the {@link StructuredContent}.
     * 
     * @param sc the {@link StructuredContent} that should further be wrapped into the <b>dto</b>
     * @param dto the DTO that has already been mostly populated by Broadleaf. At this stage, this parameter will have all of
     * the properties from the default {@link StructuredContentDTO} already parsed
     * @param secure whether or not the request is secure
     * @return the result of executing this extension handler
     * 
     * @see {@link StructuredContentServiceImpl#buildFieldValues(StructuredContent, StructuredContentDTO, boolean)}
     * @see {@link StructuredContentServiceImpl#buildStructuredContentDTO(StructuredContent, boolean)}
     */
    public ExtensionResultStatusType populateAdditionalStructuredContentFields(StructuredContent sc, StructuredContentDTO dto, boolean secure);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentServiceExtensionHandler.java" line="54">

---

## modifyStructuredContentDtoList

The `modifyStructuredContentDtoList` function allows an extension handler to modify the list of structured content items. For example, it can be used to alter the order of the passed-in list. The `ExtensionResultHolder`, if non-null, should contain a replacement list to use.

```java
    /**
     * Allows an extension handler to modify the list of structured content items.   For example to alter the order of the
     * passed in list.   
     * 
     * The {@link ExtensionResultHolder} if non null should contain a replacement list to use. 
     * 
     * @param structuredContentList
     * @param resultHolder
     * @return
     */
    public ExtensionResultStatusType modifyStructuredContentDtoList(List<StructuredContentDTO> structuredContentList,
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
