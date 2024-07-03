---
title: Exploring Category Management
---
Category Management in BroadleafCommerce-demo refers to the administrative operations performed on the 'Category' entity. These operations are handled by the 'AdminCategoryController' class. This class extends the 'AdminBasicEntityController' and is responsible for managing categories in the e-commerce platform. It includes functionalities such as adding a new category, modifying an existing category, and managing the URLs associated with each category. The 'AdminCategoryController' uses the 'CatalogService' to interact with the underlying catalog data.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminCategoryController.java" line="33">

---

# AdminCategoryController

The `AdminCategoryController` class handles admin operations for the `Category` entity. It provides methods for managing categories, such as creating, editing, and deleting categories.

```java
 * Handles admin operations for the {@link Category} entity.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/extension/ParentCategorySortExtensionHandler.java" line="50">

---

# ParentCategorySortExtensionHandler

The `ParentCategorySortExtensionHandler` class provides an extension point for modifying the list grid of products. It can be used to disable sorting of products by their parent category, as per the `allowProductParentCategorySorting` configuration.

```java
    public ExtensionResultStatusType modifyListGrid(String className, ListGrid listGrid) {
        if (Product.class.getName().equals(className) && !allowProductParentCategorySorting) {
            for (Field f : listGrid.getHeaderFields()) {
                if (f.getName().equals("defaultCategory")) {
                    f.setFilterSortDisabled(true);
                    return ExtensionResultStatusType.HANDLED;
                }
            }
        }
        return ExtensionResultStatusType.NOT_HANDLED;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
