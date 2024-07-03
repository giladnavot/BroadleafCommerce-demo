---
title: Understanding the Web Layer
---
In the BroadleafCommerce-demo repository, 'Web' refers to the web layer of the application, which is responsible for handling HTTP requests and responses. It includes components such as controllers, filters, and request processors. The web layer interacts with the underlying service and data layers to process user requests and generate responses.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafSiteResolver.java" line="32">

---

For instance, the BroadleafSiteResolver interface is part of the web layer. It is responsible for resolving the site for the current web request. This is crucial in a multi-site e-commerce application where each request needs to be associated with a specific site.

```java
public interface BroadleafSiteResolver  {

    public static final String SELECTED_SITE_URL_PARAM = "selectedSite";

    /**
     * 
     * @deprecated Use {@link #resolveSite(WebRequest)} instead
     */
    @Deprecated
    public Site resolveSite(HttpServletRequest request) throws SiteNotFoundException;

    /**
     * @see #resolveSite(WebRequest, boolean)
     */
    public Site resolveSite(WebRequest request) throws SiteNotFoundException;

    /**
     * Resolves a site for the given WebRequest. Implementations should throw a {@link SiteNotFoundException}
     * when a site could not be resolved unless the allowNullSite parameter is set to true.
     * 
     * @param request
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafRequestContext.java" line="60">

---

Another key component is the BroadleafRequestContext class. It holds various objects related to the current request, such as the current site, locale, currency, and theme. This context is thread-bound, meaning it's specific to the current request thread.

```java
/**
 * Convenient holder class for various objects to be automatically available on thread local without invoking the various
 * services yourself
 * 
 * @see {@link BroadleafRequestProcessor}
 */
public class BroadleafRequestContext {
    
    protected static final Log LOG = LogFactory.getLog(BroadleafRequestContext.class);
    
    private static final ThreadLocal<BroadleafRequestContext> BROADLEAF_REQUEST_CONTEXT = ThreadLocalManager.createThreadLocal(BroadleafRequestContext.class, false);
    
    /**
     * Returns the current, thread-bound {@link BroadleafRequestContext}.  This creates and binds one if it does not exist.
     * 
     * This is the same as calling {@link BroadleafRequestContext#getBroadleafRequestContext(boolean)} with the value true.
     * 
     * @return
     */
    public static BroadleafRequestContext getBroadleafRequestContext() {
        return getBroadleafRequestContext(true);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafWebRequestProcessor.java" line="55">

---

The BroadleafWebRequestProcessor interface is used for processing web requests. It provides methods to process a request and perform post-processing tasks. This interface is implemented by various components to handle specific types of requests.

```java
public interface BroadleafWebRequestProcessor {

    /**
     * Process the current request. Examples would be setting the currently logged in customer on the request or handling
     * anonymous customers in session
     * 
     * @param request
     */
    public void process(WebRequest request);
    
    /**
     * Should be called if work needs to be done after the request has been processed.
     * 
     * @param request
     */
    public void postProcess(WebRequest request);

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafSiteResolver.java" line="26">

---

# BroadleafSiteResolver

The `BroadleafSiteResolver` interface is responsible for resolving the site for a given web request. It has methods like `resolveSite(WebRequest request)` and `resolveSite(final WebRequest request, final boolean allowNullSite)` for this purpose.

```java
/**
 * Responsible for returning the site used by Broadleaf Commerce for the current request.
 * For a single site installation, this will typically return null.
 *
 * @author bpolster
 */
public interface BroadleafSiteResolver  {

    public static final String SELECTED_SITE_URL_PARAM = "selectedSite";

    /**
     * 
     * @deprecated Use {@link #resolveSite(WebRequest)} instead
     */
    @Deprecated
    public Site resolveSite(HttpServletRequest request) throws SiteNotFoundException;

    /**
     * @see #resolveSite(WebRequest, boolean)
     */
    public Site resolveSite(WebRequest request) throws SiteNotFoundException;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafLocaleResolver.java" line="25">

---

# BroadleafLocaleResolver

The `BroadleafLocaleResolver` interface is responsible for resolving the locale for a given web request. It has methods like `resolveLocale(HttpServletRequest request)` and `resolveLocale(WebRequest request)` for this purpose.

```java
/**
 * Responsible for returning the Locale to use for the current request.
 *
 * @author bpolster
 */
public interface BroadleafLocaleResolver  {

    /**
     * @deprecated Use {@link #resolveLocale(WebRequest)} instead
     */
    @Deprecated
    public Locale resolveLocale(HttpServletRequest request);

    public Locale resolveLocale(WebRequest request);

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafWebRequestProcessor.java" line="28">

---

# BroadleafWebRequestProcessor

The `BroadleafWebRequestProcessor` interface is used for processing web requests. It has methods like `process(WebRequest request)` and `postProcess(WebRequest request)` for processing and post-processing web requests respectively.

```java
/**
 * Generic interface that should be used for processing requests from Servlet Filters, Spring interceptors or Portlet
 * filters. Note that the actual type of the request passed in should be something that extends {@link NativeWebRequest}.
 * 
 * Example usage by a Servlet Filter:
 * 
 * <pre>
 *   public class SomeServletFilter extends GenericFilterBean {
 *      &#064;Resource(name="blCustomerStateRequestProcessor")
 *      protected BroadleafWebRequestProcessor processor;
 *      
 *      public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
 *          processor.process(new ServletWebRequest(request, response));
 *      }
 *   }
 * </pre>
 * 
 * <p>Also note that you should always instantiate the {@link WebRequest} with as much information available. In the above
 * example, this means using both the {@link HttpServletRequest} and {@link HttpServletResponse} when instantiating the
 * {@link ServletWebRequest}</p>
 * 
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/ThemeUrlEncodingFilter.java" line="82">

---

# encodeURL method

The `encodeURL` method in `ThemeUrlEncodingFilter` class is used to encode URLs of .js and .css files by adding the themeConfigId as a parameter. This helps in using the correct theme for subsequent requests.

```java
        /**
         * Provide special encoding of .js and .css files - specifically providing the themeConfigId so that 
         * subsequent requests can use the correct theme
         */
        @Override
        public String encodeURL(String url) {
            BroadleafRequestContext brc = BroadleafRequestContext.getBroadleafRequestContext();
            Object themeChanged = brc.getAdditionalProperties().get(BroadleafThemeResolver.BRC_THEME_CHANGE_STATUS);
            if (themeChanged != null && Boolean.TRUE.equals(themeChanged)) {
                if (url.contains(".js") || url.contains(".css")) {
                    //WebRequest request = brc.getWebRequest();
                    Theme theme = brc.getTheme();
                    try {
                        url = new URIBuilder(url).addParameter("themeConfigId", theme.getId().toString()).build().toString();
                    } catch (URISyntaxException e) {
                        LOG.error(String.format("URI syntax error building %s with parameter %s and themeId %s", url, "themeConfigId", theme.getId().toString()));
                    }
                }
            }
            return wrappedResponse.encodeURL(url);
        }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
