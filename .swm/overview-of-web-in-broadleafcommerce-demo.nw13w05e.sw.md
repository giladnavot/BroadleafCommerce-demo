---
title: Overview of Web in BroadleafCommerce-demo
---
In BroadleafCommerce-demo, 'Web' refers to the web layer of the application, specifically the admin module. This layer is responsible for handling HTTP requests and responses, and it interacts with the underlying service and data layers. It is implemented using Spring MVC, a component of the Spring Framework that provides a model-view-controller architecture for building web applications.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/inventory/AdminInventoryBasicOperationsController.java" line="9">

---

# Admin Web Interface

This file is part of the web interface for managing inventory. It provides the controller for basic operations related to inventory management.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/config/AdminWebConfig.java" line="9">

---

# Web Configuration

This file provides the configuration for the admin web interface. It sets up the necessary settings and dependencies for the web interface to function correctly.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/rulebuilder/service/OrderFieldServiceImpl.java" line="9">

---

# Rule Builder Services

This file is part of the rule builder services, which are accessed through the web interface. It provides the service for managing order fields in the rule builder.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
```

---

</SwmSnippet>

# Inventory and Catalog Operations

Inventory and Catalog Operations

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/inventory/AdminInventoryBasicOperationsController.java" line="48">

---

## AdminInventoryBasicOperationsController

The `AdminInventoryBasicOperationsController` class defines endpoints for basic inventory operations. It includes a `showSelectCollectionItem` method which is mapped to the `/com.broadleafcommerce.inventory.advanced.domain.InventoryImpl/{collectionField:.*}/select` URL and handles GET requests. This method is used to show a selected collection item in the inventory.

```java
@Controller("blAdminInventoryBasicOperationsController")
@RequestMapping("/com.broadleafcommerce.inventory.advanced.domain.InventoryImpl")
public class AdminInventoryBasicOperationsController extends AdminBasicOperationsController {


    @Resource(name = "blCatalogService")
    protected CatalogService catalogService;

    @RequestMapping(value = "/{collectionField:.*}/select", method = RequestMethod.GET)
    public String showSelectCollectionItem(HttpServletRequest request, HttpServletResponse response, Model model,
                                           @PathVariable Map<String, String> pathVars,
                                           @PathVariable String collectionField, @RequestParam(required = false) String requestingEntityId,
                                           @RequestParam(required = false) boolean dynamicField,
                                           @RequestParam MultiValueMap<String, String> requestParams) throws Exception {
        String view = super.showSelectCollectionItem(request, response, model, pathVars, "com.broadleafcommerce.inventory.advanced.domain.InventoryImpl", collectionField, requestingEntityId, dynamicField, requestParams);
        String queryString = requestParams.entrySet().stream().flatMap(
                k -> k.getValue().stream().map(v -> new AbstractMap.SimpleEntry<String, String>(k.getKey(), v))
        ).map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"));
        if (!queryString.contains("fulfillmentType=") && StringUtils.isNotEmpty(request.getParameter("inventoryParameter"))) {
            List<SectionCrumb> sectionCrumbs = this.getSectionCrumbs(request, (String) null, (String) null);
            if (CollectionUtils.isNotEmpty(sectionCrumbs) && sectionCrumbs.get(0).getSectionIdentifier().equals("inventory")) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/action/AdminCatalogActionsController.java" line="47">

---

## AdminCatalogActionsController

The `AdminCatalogActionsController` class defines endpoints for catalog actions. It includes a `generateSkus` method which is mapped to the `product/{productId}/{skusFieldName}/generate-skus` URL and handles GET requests. This method is used to generate a list of Skus for a particular product and its product options.

```java
@Controller("blAdminCatalogActionsController")
public class AdminCatalogActionsController extends AdminAbstractController {

    @Resource(name = "blAdminCatalogService")
    protected AdminCatalogService adminCatalogService;
    @Autowired
    protected MessageSource messageSource;

    /**
     * Invokes a separate service to generate a list of Skus for a particular {@link Product} and that {@link Product}'s
     * Product Options
     */
    @RequestMapping(value = "product/{productId}/{skusFieldName}/generate-skus",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody Map<String, Object> generateSkus(HttpServletRequest request, HttpServletResponse response, Model model,
                                                          @PathVariable(value = "productId") Long productId,
                                                          @PathVariable(value = "skusFieldName") String skusFieldName) {
        Map<String, Object> responseBody = adminCatalogService.generateSkus(productId);

        String url = request.getRequestURL().toString();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
