---
title: Exploring URL Handler Service
---
The URL Handler Service in Broadleaf Commerce is a key component that manages URL routing within the application. It provides functionality to match incoming URLs with appropriate handlers, which can be either a page, a product, or a category. The service provides methods to find a URL handler by its URI, find all URL handlers, save a URL handler to the database, and remove a URL handler from the cache. It also provides a method to find all regex URL handlers, which are URL handlers that use regular expressions to match URLs. The URL Handler Service is designed to be efficient and performant, even when dealing with a large number of URL handlers.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerService.java" line="28">

---

# URLHandlerService Interface

This is the URLHandlerService interface. It declares several methods for managing URL handlers, including finding a URL handler by its URI or ID, saving a URL handler to the database, retrieving all URL handlers, and managing the URL handler cache.

```java
public interface URLHandlerService {

    /**
     * Checks the passed in URL to determine if there is a matching URLHandler.
     * Returns null if no handler was found.
     *
     * @param uri
     * @return
     */
    public URLHandler findURLHandlerByURI(String uri);

    /**
     * Be cautious when calling this.  If there are a large number of records, this can cause performance and
     * memory issues.
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();

    /**
     * Persists the URLHandler to the DB.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="47">

---

# URLHandlerServiceImpl Class

This is the URLHandlerServiceImpl class, which implements the URLHandlerService interface. It provides the actual implementation of the methods declared in the interface. It also includes additional methods and fields for managing the URL handler cache and handling regular expressions in URLs.

```java
@Service("blURLHandlerService")
public class URLHandlerServiceImpl implements URLHandlerService {

    protected static final String REGEX_SPECIAL_CHARS_PATTERN = "([\\[\\]\\.\\|\\?\\*\\+\\(\\)\\\\~`\\!@#%&\\-_+={}'\"\"<>:;, \\/])"; //other than ^ and $
    //This is just a placeholder object to allow us to cache a URI that does not have a URL handler.
    protected static final NullURLHandler NULL_URL_HANDLER = new NullURLHandler();
    private static final Log LOG = LogFactory.getLog(URLHandlerServiceImpl.class);
    protected Cache<String,URLHandler> urlHandlerCache;

    @Resource(name = "blURLHandlerDao")
    protected URLHandlerDao urlHandlerDao;

    @Resource(name = "blStatisticsService")
    protected StatisticsService statisticsService;
    
    @Resource(name = "blCacheManager")
    protected CacheManager cacheManager;

    protected Map<String, Pattern> urlPatternMap = new EfficientLRUMap<String, Pattern>(2000);

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="74">

---

# findURLHandlerByURI Method

This is the findURLHandlerByURI method. It checks the passed in URL to determine if there is a matching URLHandler. If no handler is found, it returns null. It first checks the cache for a matching URL handler. If no handler is found in the cache, it checks the database for an exact match. If still no handler is found, it checks for a regular expression match. If a handler is found, it is added to the cache.

```java
    @Override
    public URLHandler findURLHandlerByURI(String uri) {

        //This allows clients or implementors to manipulate the URI, for example making it all lower case.
        //The default implementation simply does not manipulate the URI in any way, but simply returns
        //what is passed in.
        uri = manipulateUri(uri);

        URLHandler handler = null;

        Site site = null;
        if (BroadleafRequestContext.getBroadleafRequestContext() != null) {
            site = BroadleafRequestContext.getBroadleafRequestContext().getNonPersistentSite();
        }

        String key = buildURLHandlerCacheKey(site, uri);

        //See if this is in cache first, but only if we are in production
        if (BroadleafRequestContext.getBroadleafRequestContext().isProductionSandBox()) {
            handler = getUrlHandlerFromCache(key);
        }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="141">

---

# saveURLHandler Method

This is the saveURLHandler method. It persists the URLHandler to the database and returns the persisted URLHandler.

```java
    @Override
    @Transactional("blTransactionManager")
    public URLHandler saveURLHandler(URLHandler handler) {
        return urlHandlerDao.saveURLHandler(handler);
    }
```

---

</SwmSnippet>

# URL Handler Service Functions

The URLHandlerService is a key component in the Broadleaf Commerce framework. It provides several functions to handle URL-related operations.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="74">

---

## findURLHandlerByURI

The `findURLHandlerByURI` function checks the passed in URL to determine if there is a matching URLHandler. It returns null if no handler was found. This function is used to find a URL handler based on the provided URI.

```java
    @Override
    public URLHandler findURLHandlerByURI(String uri) {

        //This allows clients or implementors to manipulate the URI, for example making it all lower case.
        //The default implementation simply does not manipulate the URI in any way, but simply returns
        //what is passed in.
        uri = manipulateUri(uri);

        URLHandler handler = null;

        Site site = null;
        if (BroadleafRequestContext.getBroadleafRequestContext() != null) {
            site = BroadleafRequestContext.getBroadleafRequestContext().getNonPersistentSite();
        }

        String key = buildURLHandlerCacheKey(site, uri);

        //See if this is in cache first, but only if we are in production
        if (BroadleafRequestContext.getBroadleafRequestContext().isProductionSandBox()) {
            handler = getUrlHandlerFromCache(key);
        }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="131">

---

## findAllURLHandlers

The `findAllURLHandlers` function returns a list of all URLHandlers. However, it should be used with caution as it can cause performance and memory issues if there are a large number of records.

```java
    @Override
    public List<URLHandler> findAllURLHandlers() {
        return urlHandlerDao.findAllURLHandlers();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="141">

---

## saveURLHandler

The `saveURLHandler` function persists the URLHandler to the database. It is used to save or update a URLHandler.

```java
    @Override
    @Transactional("blTransactionManager")
    public URLHandler saveURLHandler(URLHandler handler) {
        return urlHandlerDao.saveURLHandler(handler);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="126">

---

## findURLHandlerById

The `findURLHandlerById` function finds a URLHandler by its ID. It is used to retrieve a URLHandler based on its unique identifier.

```java
    @Override
    public URLHandler findURLHandlerById(Long id) {
        return urlHandlerDao.findURLHandlerById(id);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="136">

---

## findAllRegexURLHandlers

The `findAllRegexURLHandlers` function returns a list of all regex URLHandlers. It is assumed to be a relatively small list of regex URLHandlers. Having a large number of records here can cause performance problems.

```java
    @Override
    public List<URLHandler> findAllRegexURLHandlers() {
        return urlHandlerDao.findAllRegexURLHandlers();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="219">

---

## buildURLHandlerCacheKey

The `buildURLHandlerCacheKey` function builds a cache key for a URLHandler based on the site and request URI. This key is used to cache the URLHandler for faster retrieval.

```java
    @Override
    public String buildURLHandlerCacheKey(Site site, String requestUri) {
        StringBuilder key = new StringBuilder();
        if (site != null) {
            key.append("site:").append(site.getId()).append('_');
        }

        //make sure the uri part of the key is always lower case for consistency when dealing with the cache
        key.append(StringUtils.lowerCase(requestUri));

        return key.toString();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="184">

---

## removeURLHandlerFromCache

The `removeURLHandlerFromCache` function removes a URLHandler from the cache using the provided map key. It is used to invalidate a cached URLHandler.

```java
    @Override
    public Boolean removeURLHandlerFromCache(String mapKey) {
        Boolean success = Boolean.FALSE;
        if (mapKey != null) {
            Object e = getUrlHandlerCache().get(mapKey);

            if (e != null) {
                success = Boolean.valueOf(getUrlHandlerCache().remove(mapKey));
            }
        }

        return success;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
