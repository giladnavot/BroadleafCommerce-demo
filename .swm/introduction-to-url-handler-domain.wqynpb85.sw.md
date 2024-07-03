---
title: Introduction to URL Handler Domain
---
The URL Handler Domain in BroadleafCommerce-demo refers to a set of classes and interfaces that handle URL redirection and forwarding within the application. It includes classes like `URLHandlerDTO`, `URLHandlerImpl`, and interfaces like `URLHandler`. These classes and interfaces are used to define and manipulate URL handlers, which are responsible for redirecting or forwarding incoming URLs to new URLs. They also provide functionalities to check if the incoming URL is a regex expression and to set the URL redirect type.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandler.java" line="25">

---

# URLHandler Interface

The URLHandler interface defines the methods that are used to manage URL handling. It includes methods to get and set the incoming URL, the new URL, the URL redirect type, and whether the incoming URL is a regular expression.

```java
public interface URLHandler extends Serializable, MultiTenantCloneable<URLHandler> {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String getIncomingURL();

    public abstract void setIncomingURL(String incomingURL);

    public abstract String getNewURL();

    public abstract void setNewURL(String newURL);

    public abstract URLRedirectType getUrlRedirectType();

    public abstract void setUrlRedirectType(URLRedirectType redirectType);

    /**
     * Indicates if the value returned by <code>getIncomingURL()</code> is a regex expression
     * rather than a concrete URI.  Default is false.
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerImpl.java" line="63">

---

# URLHandlerImpl Class

The URLHandlerImpl class is an implementation of the URLHandler interface. It provides the functionality to manage URL handling, including setting the incoming URL, the new URL, the URL redirect type, and whether the incoming URL is a regular expression.

```java
public class URLHandlerImpl implements URLHandler, Locatable, AdminMainEntity, ProfileEntity, URLHandlerAdminPresentation {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "URLHandlerID")
    @GenericGenerator(
            name = "URLHandlerID",
            strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name = "segment_value", value = "URLHandlerImpl"),
                    @Parameter(name = "entity_name", value = "org.broadleafcommerce.cms.url.domain.URLHandlerImpl")
            }
    )
    @Column(name = "URL_HANDLER_ID")
    @AdminPresentation(friendlyName = "URLHandlerImpl_ID", order = 1, group = GroupName.General, visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long id;

    @AdminPresentation(friendlyName = "URLHandlerImpl_incomingURL", order = 1, group = GroupName.General, prominent = true,
            helpText = "urlHandlerIncoming_help", defaultValue = "")
    @Column(name = "INCOMING_URL", nullable = false)
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerDTO.java" line="30">

---

# URLHandlerDTO Class

The URLHandlerDTO class is another implementation of the URLHandler interface. It provides a bean representation of a URLHandler and is used to create non-entity instances of the DTO to cache.

```java
public class URLHandlerDTO implements URLHandler {

    private static final long serialVersionUID = 1L;
    protected Long id = null;
    protected String incomingURL = "";
    protected String newURL;
    protected String urlRedirectType;
    protected boolean isRegex = false;

    public URLHandlerDTO(String newUrl, URLRedirectType redirectType) {
        setUrlRedirectType(redirectType);
        setNewURL(newUrl);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerService.java" line="30">

---

# URLHandlerService Interface

The URLHandlerService interface provides the methods to find a URLHandler by its URI and to get all URLHandlers. It is used to manage and retrieve URLHandlers.

```java
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

# URL Handler Domain Functions

This section will provide an overview of the main functions in the URL Handler Domain.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerImpl.java" line="117">

---

## getIncomingURL and setIncomingURL

The `getIncomingURL` and `setIncomingURL` methods are used to retrieve and set the incoming URL respectively. The incoming URL is the URL that needs to be handled or redirected.

```java
    public String getIncomingURL() {
        return incomingURL;
    }

    @Override
    public void setIncomingURL(String incomingURL) {
        this.incomingURL = incomingURL;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerImpl.java" line="127">

---

## getNewURL and setNewURL

The `getNewURL` and `setNewURL` methods are used to retrieve and set the new URL respectively. The new URL is the URL to which the incoming URL will be redirected or forwarded.

```java
    public String getNewURL() {
        return newURL;
    }

    @Override
    public void setNewURL(String newURL) {
        this.newURL = newURL;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerImpl.java" line="137">

---

## getUrlRedirectType and setUrlRedirectType

The `getUrlRedirectType` and `setUrlRedirectType` methods are used to retrieve and set the URL redirect type respectively. The redirect type determines how the incoming URL will be handled, i.e., whether it will be redirected permanently (301), temporarily (302), or forwarded.

```java
    public URLRedirectType getUrlRedirectType() {
        return URLRedirectType.getInstance(urlRedirectType);
    }

    @Override
    public void setUrlRedirectType(URLRedirectType redirectType) {
        this.urlRedirectType = redirectType.getType();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/domain/URLHandlerImpl.java" line="147">

---

## isRegexHandler and setRegexHandler

The `isRegexHandler` and `setRegexHandler` methods are used to check if the incoming URL is a regex expression and set the regex handler respectively. If the incoming URL is a regex expression, it means that the URL contains special characters that need to be interpreted in a special way.

```java
    public boolean isRegexHandler() {
        if (isRegex == null) {
            if (hasRegExCharacters(getIncomingURL())) {
                return true;
            }
            return false;
        }
        return isRegex;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
