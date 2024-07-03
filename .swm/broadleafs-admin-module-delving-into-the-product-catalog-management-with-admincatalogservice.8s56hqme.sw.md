---
title: >-
  Broadleafs Admin Module: Delving into the Product Catalog Management with
  AdminCatalogService
---
This document will cover the role and functionality of the `AdminCatalogService` in Broadleaf's admin module. We'll cover:

1. The purpose of `AdminCatalogService`
2. How `AdminCatalogService` manages product catalog operations
3. The interaction of `AdminCatalogService` with other components.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogService.java" line="27">

---

# Purpose of `AdminCatalogService`

`AdminCatalogService` is an interface that defines methods for managing product catalog operations. It includes methods for generating SKUs from product options, cloning a product, and more.

```java
public interface AdminCatalogService {

    /**
     * Clear out any Skus that are already attached to the Product
     * if there were any there and generate a new set of Skus based
     * on the permutations of ProductOptions attached to this Product
     *
     * @param productId - the Product to generate Skus from
     * @return the number of generated Skus from the ProductOption permutations
     * @deprecated use {@link #generateSkus(Long)}
     */
    @Deprecated
    public Integer generateSkusFromProduct(Long productId);

    /**
     * Create new Skus based on a product's ProductOptions by permutation and add them to existing ones.
     *
     * @param productId - Product ID to create SKUs
     * @return the map as ResponseBody
     */
    Map<String, Object> generateSkus(Long productId);
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/server/service/AdminCatalogServiceImpl.java" line="52">

---

# Implementation of `AdminCatalogService`

`AdminCatalogServiceImpl` is the class that implements the `AdminCatalogService` interface. It provides the actual logic for the methods defined in the interface.

```java
@Service("blAdminCatalogService")
public class AdminCatalogServiceImpl implements AdminCatalogService {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/action/AdminCatalogActionsController.java" line="50">

---

# Interaction with Other Components

`AdminCatalogService` is used in `AdminCatalogActionsController`. This controller is responsible for handling actions related to the product catalog in the admin module.

```java
    @Resource(name = "blAdminCatalogService")
    protected AdminCatalogService adminCatalogService;
    @Autowired
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
