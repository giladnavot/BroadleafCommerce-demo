---
title: Overview of Data Access Objects in Catalog
---
Data Access Object (Dao) in the Catalog package of BroadleafCommerce-demo refers to a design pattern that provides an abstract interface to the database. In this repository, the Dao is used to isolate the application/business layer from the persistence layer (usually a relational database, but it could be any other persistence mechanism) using an abstract API.

The Dao provides methods to perform CRUD operations on the database, thus hiding the details of the database operations from the rest of the application. For instance, the CategoryDao interface provides methods to read, save, and retrieve categories from the database.

The Dao pattern is heavily used in the BroadleafCommerce-demo repository, particularly within the Catalog package. It is implemented in various classes such as CategoryDao, ProductDao, and SkuDao. These classes provide the necessary methods to interact with the respective database tables.

The Dao classes are used throughout the application, for example, in services like CategorySiteMapGenerator, CatalogServiceImpl, and RelatedProductsServiceImpl. These services use the Dao to interact with the database, thus keeping the database operations isolated from the business logic.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/CategoryDao.java" line="36">

---

# CategoryDao

The CategoryDao interface provides methods to read, save, and retrieve categories from the database. It is used in various services to perform these operations.

```java
public interface CategoryDao {

    /**
     * Retrieve a {@code Category} instance by its primary key
     *
     * @param categoryId the primary key of the {@code Category}
     * @return the {@code Category}  at the specified primary key
     */
    @Nonnull
    public Category readCategoryById(@Nonnull Long categoryId);

    /**
     * Retrieves a List of Category IDs
     *
     * @param categoryIds
     * @return
     */
    public List<Category> readCategoriesByIds(List<Long> categoryIds);

    /**
     * Retrieve a {@link Category} instance by the external id
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/ProductDao.java" line="36">

---

# ProductDao

The ProductDao interface provides methods to read, save, and retrieve products from the database. It is used in various services to perform these operations.

```java
public interface ProductDao {

    /**
     * Retrieve a {@code Product} instance by its primary key
     *
     * @param productId the primary key of the product
     * @return the product instance at the specified primary key
     */
    @Nonnull
    public Product readProductById(@Nonnull Long productId);
    
    public Product readProductByExternalId(String externalId);
    
    /**
     * Retrieves a list of Product instances by their primary keys
     * 
     * @param productIds the list of primary keys for products
     * @return the list of products specified by the primary keys
     */
    public List<Product> readProductsByIds(@Nonnull List<Long> productIds);

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/service/CategorySiteMapGenerator.java" line="58">

---

# Using Dao in Services

The CategoryDao is used in the CategorySiteMapGenerator service to interact with the Category database table. The service uses the Dao to perform the necessary database operations, thus keeping the database operations isolated from the business logic.

```java
    protected CategoryDao categoryDao;

    @Value("${category.site.map.generator.row.limit}")
    protected int rowLimit;

    public CategorySiteMapGenerator(Environment env) {
        this.env = env;
    }

    @Override
    public boolean canHandleSiteMapConfiguration(SiteMapGeneratorConfiguration siteMapGeneratorConfiguration) {
        return SiteMapGeneratorType.CATEGORY.equals(siteMapGeneratorConfiguration.getSiteMapGeneratorType());
    }

    @Override
    public void addSiteMapEntries(SiteMapGeneratorConfiguration smgc, SiteMapBuilder siteMapBuilder) {

        if (CategorySiteMapGeneratorConfiguration.class.isAssignableFrom(smgc.getClass())) {
            CategorySiteMapGeneratorConfiguration categorySMGC = (CategorySiteMapGeneratorConfiguration) smgc;

            // Recursively construct the category SiteMap URLs
```

---

</SwmSnippet>

# DAO Functions

In this section, we will discuss the main functions of the DAO classes in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/CategoryDao.java" line="44">

---

## readCategoryById

The `readCategoryById` function is used to retrieve a Category instance by its primary key. It is used in various services like `LegacyOrderServiceImpl`, `CatalogServiceImpl`, and `RelatedProductsServiceImpl` to fetch a category based on its ID.

```java
    @Nonnull
    public Category readCategoryById(@Nonnull Long categoryId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/CategoryDao.java" line="63">

---

## save

The `save` function is used to persist a Category instance to the datastore. It is used in the `CategoryDaoImpl` class to merge the state of the given category with the current persistent context.

```java
     * Retrieve a {@code Category} instance by its name.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/ProductDao.java" line="44">

---

## readProductById

The `readProductById` function is used to retrieve a Product instance by its primary key. It is used in services like `LegacyOrderServiceImpl`, `CatalogServiceImpl`, and `RelatedProductsServiceImpl` to fetch a product based on its ID.

```java
    @Nonnull
    public Product readProductById(@Nonnull Long productId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/CategoryXrefDao.java" line="44">

---

## readXrefsByCategoryId

The `readXrefsByCategoryId` function is used to retrieve all the category relationships for which the passed in Category primary key is a parent. It is used in the `CategoryXrefDaoImpl` class to fetch a list of child category relationships for the parent primary key.

```java
    @Nonnull
    public List<CategoryXref> readXrefsByCategoryId(@Nonnull Long categoryId);
```

---

</SwmSnippet>

# DAO Endpoints

Exploring DAO Endpoints

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/ProductDao.java" line="44">

---

## readProductById

The `readProductById` method is an endpoint that retrieves a `Product` instance by its primary key. It takes a `Long` type parameter which represents the primary key of the product and returns the corresponding `Product` instance.

```java
    @Nonnull
    public Product readProductById(@Nonnull Long productId);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/dao/ProductDao.java" line="63">

---

## save

The `save` method is an endpoint that persists a `Product` instance to the datastore. It takes a `Product` instance as a parameter and returns the updated state of the `Product` instance after being persisted.

```java
    @Nonnull
    public Product save(@Nonnull Product product);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
