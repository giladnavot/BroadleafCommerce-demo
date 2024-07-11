---
title: Overview of Catalog Domain
---
The 'Domain' in the Catalog refers to the package 'org.broadleafcommerce.core.catalog.domain' which contains the core domain classes and interfaces that define the catalog structure in Broadleaf Commerce. These classes include entities like 'Product', 'Category', 'Sku', and others. These entities represent the key components of an e-commerce catalog.

For instance, the 'Product' interface is used to hold data for a Product, which is a general description of an item that can be sold. It provides methods to get and set product details like name, description, and availability dates. The 'Product' interface is implemented by the 'ProductImpl' class.

The 'Product' entity is used across the application, for example, in the 'OrderItemServiceImpl' class to find all products in a request, or in the 'CatalogServiceImpl' class to find a product by its ID.

The 'setCategory' method in the 'Product' interface allows setting the category that contains the product. This is an example of how the domain entities are interconnected.

The 'Indexable' interface in the same package is another important component. It is used for entities that need to be indexed for search functionality.

In summary, the 'Domain' in the Catalog is a crucial part of the Broadleaf Commerce framework, defining the structure and behavior of the e-commerce catalog.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/Product.java" line="35">

---

# Product Interface

The 'Product' interface is used to hold data for a Product, which is a general description of an item that can be sold. It provides methods to get and set product details like name, description, and availability dates.

```java
/**
 * Implementations of this interface are used to hold data for a Product.  A product is a general description
 * of an item that can be sold (for example: a hat).  Products are not sold or added to a cart.  {@link Sku}s
 * which are specific items (for example: a XL Blue Hat) are sold or added to a cart.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Product is persisted.  If you just want to add additional fields then you should extend {@link ProductImpl}.
 *
 * @author btaylor
 * @see {@link ProductImpl},{@link Sku}, {@link Category}
 */
public interface Product extends Serializable, MultiTenantCloneable<Product>, Indexable {

    /**
     * The id of the Product.
     *
     * @return the id of the Product
     */
    @Override
    public Long getId();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/ProductImpl.java" line="94">

---

# Product Implementation

The 'ProductImpl' class implements the 'Product' interface. It provides the implementation for the methods defined in the 'Product' interface.

```java
/**
 * The Class ProductImpl is the default implementation of {@link Product}. A
 * product is a general description of an item that can be sold (for example: a
 * hat). Products are not sold or added to a cart. {@link Sku}s which are
 * specific items (for example: a XL Blue Hat) are sold or added to a cart. <br>
 * <br>
 * If you want to add fields specific to your implementation of
 * BroadLeafCommerce you should extend this class and add your fields. If you
 * need to make significant changes to the ProductImpl then you should implement
 * your own version of {@link Product}. <br>
 * <br>
 * This implementation uses a Hibernate implementation of JPA configured through
 * annotations. The Entity references the following tables: BLC_PRODUCT,
 * BLC_PRODUCT_SKU_XREF, BLC_PRODUCT_IMAGE
 *
 * @author btaylor
 * @see {@link Product}, {@link SkuImpl}, {@link CategoryImpl}
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@javax.persistence.Table(name = "BLC_PRODUCT")
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/Category.java" line="36">

---

# Category Interface

The 'Category' interface is used to hold data about a Category, which is a group of products. It provides methods to get and set category details like name, url, and attributes.

```java
/**
 * Implementations of this interface are used to hold data about a Category.  A category is a group of products.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Category is persisted.  If you just want to add additional fields then you should extend {@link CategoryImpl}.
 *
 * @see {@link CategoryImpl}
 * @author btaylor
 * @author Jeff Fischer
 * 
 */
public interface Category extends Serializable, MultiTenantCloneable<Category> {

    /**
     * Gets the primary key.
     * 
     * @return the primary key
     */
    @Nullable
    public Long getId();
```

---

</SwmSnippet>

# Product and Category Entities

Let's delve into the functions of the 'Product' and 'Category' entities in the Catalog Domain.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/Product.java" line="35">

---

## Product Entity

The 'Product' interface is used to hold data for a Product, which is a general description of an item that can be sold. It provides methods to get and set product details like name, description, and availability dates. The 'Product' interface is implemented by the 'ProductImpl' class. The 'Product' entity is used across the application, for example, in the 'OrderItemServiceImpl' class to find all products in a request, or in the 'CatalogServiceImpl' class to find a product by its ID.

```java
/**
 * Implementations of this interface are used to hold data for a Product.  A product is a general description
 * of an item that can be sold (for example: a hat).  Products are not sold or added to a cart.  {@link Sku}s
 * which are specific items (for example: a XL Blue Hat) are sold or added to a cart.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Product is persisted.  If you just want to add additional fields then you should extend {@link ProductImpl}.
 *
 * @author btaylor
 * @see {@link ProductImpl},{@link Sku}, {@link Category}
 */
public interface Product extends Serializable, MultiTenantCloneable<Product>, Indexable {

    /**
     * The id of the Product.
     *
     * @return the id of the Product
     */
    @Override
    public Long getId();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/Category.java" line="36">

---

## Category Entity

The 'Category' interface is used to hold data about a Category, which is a group of products. It provides methods to get and set category details. The 'Category' interface is implemented by the 'CategoryImpl' class. The 'Category' entity is used across the application, for example, in the 'Product' interface to set the category that contains the product. This is an example of how the domain entities are interconnected.

```java
/**
 * Implementations of this interface are used to hold data about a Category.  A category is a group of products.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Category is persisted.  If you just want to add additional fields then you should extend {@link CategoryImpl}.
 *
 * @see {@link CategoryImpl}
 * @author btaylor
 * @author Jeff Fischer
 * 
 */
public interface Category extends Serializable, MultiTenantCloneable<Category> {

    /**
     * Gets the primary key.
     * 
     * @return the primary key
     */
    @Nullable
    public Long getId();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
