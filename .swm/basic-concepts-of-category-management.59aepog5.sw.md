---
title: Basic Concepts of Category Management
---
Category Management in BroadleafCommerce-demo refers to the organization and handling of product categories within the e-commerce framework. It involves the creation, modification, and deletion of categories, as well as the management of parent-child relationships between categories. This is facilitated through various classes and interfaces such as `CategoryParentCategoryFieldPersistenceProviderExtensionHandler` and `CategoryParentCategoryFieldPersistenceProviderExtensionManager`. These classes provide methods for managing parent categories and default categories, ensuring the correct hierarchical structure of categories within the e-commerce site.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/persistence/module/provider/CategoryParentCategoryFieldPersistenceProvider.java" line="43">

---

## CategoryParentCategoryFieldPersistenceProvider Class

This class manages the default CategoryXref reference for a Category instance through the 'defaultParentCategory' pseudo field. It provides methods for populating and extracting values, checking dirty state, and getting the default category.

```java
/**
 * This field persistence provider manages the default CategoryXref reference for a Category instance through
 * the "defaultParentCategory" pseudo field.
 *
 * @author Jeff Fischer
 */
@Component("blCategoryParentCategoryFieldPersistenceProvider")
@Scope("prototype")
public class CategoryParentCategoryFieldPersistenceProvider extends FieldPersistenceProviderAdapter {

    @Resource(name="blCategoryParentCategoryFieldPersistenceProviderExtensionManager")
    protected CategoryParentCategoryFieldPersistenceProviderExtensionManager extensionManager;

    @Override
    public MetadataProviderResponse populateValue(PopulateValueRequest populateValueRequest, Serializable instance) {
        if (!canHandlePersistence(populateValueRequest, instance)) {
            return MetadataProviderResponse.NOT_HANDLED;
        }
        boolean handled = false;
        if (extensionManager != null) {
            ExtensionResultStatusType result = extensionManager.getProxy().manageParentCategory(populateValueRequest.getProperty(), (Category) instance);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/persistence/module/provider/extension/CategoryParentCategoryFieldPersistenceProviderExtensionHandler.java" line="25">

---

## CategoryParentCategoryFieldPersistenceProviderExtensionHandler Interface

This interface provides an extension handler for the CategoryParentCategoryFieldPersistenceProvider. It defines the `manageParentCategory` method for special handling of the parent category of a category.

```java
/**
 * Extension handler for {@link org.broadleafcommerce.admin.server.service.persistence.module.provider.CategoryParentCategoryFieldPersistenceProvider}
 *
 * @author Jeff Fischer
 */
public interface CategoryParentCategoryFieldPersistenceProviderExtensionHandler extends ExtensionHandler {

    /**
     * Perform any special handling for the parent category of a category
     *
     * @param property
     * @param category
     * @return
     */
    ExtensionResultStatusType manageParentCategory(Property property, Category category);

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
