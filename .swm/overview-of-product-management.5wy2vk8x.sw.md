---
title: Overview of Product Management
---
Product Management in BroadleafCommerce-demo refers to the functionality of handling operations related to the 'Product' entity. This includes tasks such as creating, editing, and managing products. The 'Product' entity is represented by the 'Product' class, which is used throughout the codebase. The 'AdminProductController' class, for instance, handles admin operations for the 'Product' entity, including custom criteria for editing a product. The 'AdminProductTranslationExtensionHandler' class, on the other hand, deals with translating fields on the 'Product' entity.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminProductController.java" line="72">

---

# AdminProductController

The `AdminProductController` handles admin operations for the `Product` entity. It uses the `catalogService` to fetch product data based on the product ID.

```java
 * Handles admin operations for the {@link Product} entity. Editing a product requires custom criteria in order to properly
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/extension/AdminProductTranslationExtensionHandler.java" line="58">

---

# AdminProductTranslationExtensionHandler

The `AdminProductTranslationExtensionHandler` is used to handle translations for product fields. It checks if the field to be translated is associated with the `Product` class and starts with `defaultSkuPrefix`.

```java
     * If we are trying to translate a field on Product that starts with "defaultSku.", we really want to associate the
     * translation with Sku, its associated id, and the property name without "defaultSku."
     */
    @Override
    public ExtensionResultStatusType applyTransformation(TranslationForm form) {
        if (getTranslationEnabled()) {
            String defaultSkuPrefix = "defaultSku.";
            String unencodedPropertyName = JSCompatibilityHelper.unencode(form.getPropertyName());
            if (form.getCeilingEntity().equals(Product.class.getName()) && unencodedPropertyName.startsWith(defaultSkuPrefix)) {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
