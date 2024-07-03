---
title: Understanding the Site Concept
---
In BroadleafCommerce-demo, a 'Site' refers to an instance of an e-commerce store. It is a central concept in the framework, representing the entity for which customers interact with. The 'Site' object contains all the necessary information about a specific store, such as its domain name, identifier, and whether it's a template site or not.

The 'Site' object is managed by the 'SiteService' and 'SiteDao' interfaces. These interfaces provide methods for creating, retrieving, and saving 'Site' instances. For example, 'SiteService' has methods like 'createSite()', 'retrieveSiteById()', and 'saveAndReturnPersisted()' which respectively create a new site, retrieve a site by its ID, and save updates to a site while returning the persistent instance.

The 'Site' object can also be cloned using the 'clone()' method, providing a deep copy of the site that is not bound by the entity manager scope. This is useful when you need a copy of the site that is not attached to a Hibernate session.

The 'SiteDao' interface provides additional methods for retrieving and managing 'Site' instances, such as 'retrieveDefaultSite()' for getting the default site, and 'readAllActiveSites()' for getting a list of all active sites in the system.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/domain/Site.java" line="31">

---

# Site Interface

This is the 'Site' interface. It defines methods to get and set site-specific properties such as id, name, identifier type and value, resolution type, catalogs, and default locale. It also includes methods to clone the site and check if it's a template site or deactivated.

```java
public interface Site extends Serializable, Status {

    /**
     * Unique/internal id for a site.
     * @return
     */
    public Long getId();

    /**
     * Sets the internal id for a site.
     * @param id
     */
    public void setId(Long id);

    /**
     * The display name for a site.
     * @return
     */
    public String getName();

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/dao/SiteDao.java" line="26">

---

# SiteDao Interface

The 'SiteDao' interface provides methods to create, retrieve, and save 'Site' instances. It also provides methods to retrieve all active sites and catalogs.

```java
public interface SiteDao {

    /**
     * Creates an instance of Site based on the class matching the bean id of 
     * "org.broadleafcommerce.common.site.domain.Site"
     * 
     * @return
     */
    public Site create();

    /**
     * Finds a site by its id.
     * @param id
     * @return
     */
    public Site retrieve(Long id);

    /**
     * Finds a site by its domain or domain prefix.
     * @param domain
     * @param prefix
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="42">

---

# SiteService Interface

The 'SiteService' interface provides methods to create, retrieve, and save 'Site' instances. It also provides methods to retrieve the default site.

```java
public interface SiteService {

    /**
     * Creates an instance of Site.   Default implementation delegates to {@link SiteDao#create()}.
     * 
     * @return
     */
    public Site createSite();

    /**
     * Find a site by its id and returns a non-persistent version
     * @param id
     * @return
     * @deprecated use {@link #retrieveNonPersistentSiteById(Long)} instead
     */
    @Deprecated
    public Site retrieveSiteById(Long id);
    
    /**
     * Retrieves a site by its primary key disconnected from a Hibernate session
     */
```

---

</SwmSnippet>

# Site Functions

The 'Site' in BroadleafCommerce-demo refers to the e-commerce site that is being managed by the Broadleaf framework. It has several functions that are implemented across multiple files in the repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="44">

---

## createSite

The `createSite` function is used to create an instance of Site. The default implementation delegates to `SiteDao#create()`. This function is essential for setting up a new site in the Broadleaf framework.

```java
    /**
     * Creates an instance of Site.   Default implementation delegates to {@link SiteDao#create()}.
     * 
     * @return
     */
    public Site createSite();
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="57">

---

## retrieveSiteById

The `retrieveSiteById` function is used to find a site by its id. It returns a non-persistent version of the site. This function is useful for fetching site details when only the id is known.

```java
    @Deprecated
    public Site retrieveSiteById(Long id);
    
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="76">

---

## retrieveSiteByDomainName

The `retrieveSiteByDomainName` function is used to find a site by its domain and returns a non-persistent version. This function is useful when you need to fetch site details based on the domain name.

```java
    @Deprecated
    public Site retrieveSiteByDomainName(String domain);

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="110">

---

## save

The `save` function is used to persist the site changes. This function is essential for updating the site details in the Broadleaf framework.

```java
    @Deprecated
    public Site save(Site site);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/site/service/SiteService.java" line="136">

---

## retrieveDefaultSite

The `retrieveDefaultSite` function is used to retrieve the non-persistent version of the default site. This function is useful when you need to fetch the default site details.

```java
    @Deprecated
    public Site retrieveDefaultSite();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
