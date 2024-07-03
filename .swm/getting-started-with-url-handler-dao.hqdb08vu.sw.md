---
title: Getting Started with URL Handler DAO
---
The URL Handler DAO (Data Access Object) is an interface that defines methods for interacting with URL Handler entities in the database. It provides methods to find a URL Handler by its URI, find all URL Handlers, save a URL Handler, find a URL Handler by its ID, and find all URL Handlers that match a regular expression. The URL Handler DAO is implemented by the URLHandlerDaoImpl class, which uses the Java Persistence API to interact with the database.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="28">

---

## URLHandlerDao Interface

The `URLHandlerDao` interface defines the contract for a DAO that manages URL handlers. It declares methods for common operations such as finding a URL handler by its URI or ID, saving a URL handler, and retrieving all URL handlers or all regex URL handlers.

```java
public interface URLHandlerDao {


    public URLHandler findURLHandlerByURI(String uri);

    /**
     * Gets all the URL handlers configured in the system
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();

    public URLHandler saveURLHandler(URLHandler handler);

    public URLHandler findURLHandlerById(Long id);

    public List<URLHandler> findAllRegexURLHandlers();

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URlHandlerDaoImpl.java" line="41">

---

## URLHandlerDao Implementation

The `URLHandlerDaoImpl` class is the implementation of the `URLHandlerDao` interface. It uses the `EntityManager` to interact with the database. Each method implementation corresponds to a database operation related to URL handlers.

```java
@Repository("blURLHandlerDao")
public class URlHandlerDaoImpl implements URLHandlerDao {

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Resource(name = "blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Override
    public URLHandler findURLHandlerByURI(String uri) {
        TypedQuery<URLHandler> query = em.createNamedQuery("BC_READ_BY_INCOMING_URL", URLHandler.class);
        query.setParameter("incomingURL", uri);
        query.setHint(QueryHints.HINT_CACHEABLE, true);

        List<URLHandler> results = query.getResultList();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="56">

---

## Using URLHandlerDao

The `URLHandlerDao` is used in the `URLHandlerServiceImpl` class. It is injected as a resource named `blURLHandlerDao`. This service class can then use the DAO to perform operations related to URL handlers.

```java
    @Resource(name = "blURLHandlerDao")
    protected URLHandlerDao urlHandlerDao;
```

---

</SwmSnippet>

# URL Handler DAO Functions

The URLHandlerDao interface and its implementation URlHandlerDaoImpl provide several key functions for handling URLs in the system.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="31">

---

## findURLHandlerByURI

The `findURLHandlerByURI` function is used to find a URL handler by its URI. It takes a string representing the URI as input and returns a URLHandler object.

```java
    public URLHandler findURLHandlerByURI(String uri);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="38">

---

## findAllURLHandlers

The `findAllURLHandlers` function is used to retrieve all URL handlers configured in the system. It returns a list of URLHandler objects.

```java
    public List<URLHandler> findAllURLHandlers();

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="40">

---

## saveURLHandler

The `saveURLHandler` function is used to save a URLHandler object. It takes a URLHandler object as input and returns the saved URLHandler object.

```java
    public URLHandler saveURLHandler(URLHandler handler);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="42">

---

## findURLHandlerById

The `findURLHandlerById` function is used to find a URL handler by its ID. It takes a Long representing the ID as input and returns a URLHandler object.

```java
    public URLHandler findURLHandlerById(Long id);

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="44">

---

## findAllRegexURLHandlers

The `findAllRegexURLHandlers` function is used to retrieve all URL handlers that use regular expressions. It returns a list of URLHandler objects.

```java
    public List<URLHandler> findAllRegexURLHandlers();

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
