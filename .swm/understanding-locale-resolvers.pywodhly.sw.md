---
title: Understanding Locale Resolvers
---
Locale Resolvers in BroadleafCommerce-demo, specifically the `BroadleafLocaleResolver` interface and its implementation `BroadleafLocaleResolverImpl`, are responsible for determining the Locale to use for the current request. This is crucial for internationalization and localization in the application, as it allows the system to adapt to different languages and regional differences. The `resolveLocale` method is used to determine the Locale, checking for a request attribute, a request parameter, and the session, in that order. If no Locale is found, it defaults to the site's default Locale. The resolved Locale is then set in the request attributes, overriding Spring's cookie resolver.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafLocaleResolver.java" line="30">

---

# BroadleafLocaleResolver Interface

The BroadleafLocaleResolver interface defines the contract for Locale Resolvers in BroadleafCommerce-demo. It has two methods, resolveLocale(HttpServletRequest request) and resolveLocale(WebRequest request), to resolve the locale from an HttpServletRequest or a WebRequest respectively.

```java
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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafLocaleResolverImpl.java" line="39">

---

# BroadleafLocaleResolverImpl Class

The BroadleafLocaleResolverImpl class is the implementation of the BroadleafLocaleResolver interface. It resolves the locale by checking the request attribute, request parameter, session attribute, and finally the default locale in that order.

```java
@Component("blLocaleResolver")
public class BroadleafLocaleResolverImpl implements BroadleafLocaleResolver {
    private final Log LOG = LogFactory.getLog(BroadleafLocaleResolverImpl.class);
    
    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_VAR = "blLocale";

    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_CODE_PARAM = "blLocaleCode";

    /**
     * Attribute indicating that the LOCALE was pulled from session.   Other filters may want to 
     * behave differently if this is the case.
     */
    public static String LOCALE_PULLED_FROM_SESSION = "blLocalePulledFromSession";

    @Resource(name = "blLocaleService")
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafRequestProcessor.java" line="72">

---

# Using Locale Resolvers

Locale Resolvers are used by injecting them into the classes where locale resolution is needed. Here, the BroadleafLocaleResolver is injected into the BroadleafRequestProcessor class.

```java
    @Resource(name = "blLocaleResolver")
    protected BroadleafLocaleResolver localeResolver;
```

---

</SwmSnippet>

# Locale Resolvers

This section explains the function of Locale Resolvers in the BroadleafCommerce-demo repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafLocaleResolverImpl.java" line="39">

---

## Locale Resolvers

The `resolveLocale` function is a key part of Locale Resolvers. It is responsible for determining the locale to use for the current request. The function first checks for a request attribute, then for a request parameter, and finally checks the session. If the locale is still not found, it uses the default locale. This function is crucial for ensuring that the correct locale is used for each request, allowing for proper internationalization and localization of the site.

```java
@Component("blLocaleResolver")
public class BroadleafLocaleResolverImpl implements BroadleafLocaleResolver {
    private final Log LOG = LogFactory.getLog(BroadleafLocaleResolverImpl.class);
    
    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_VAR = "blLocale";

    /**
     * Parameter/Attribute name for the current language
     */
    public static String LOCALE_CODE_PARAM = "blLocaleCode";

    /**
     * Attribute indicating that the LOCALE was pulled from session.   Other filters may want to 
     * behave differently if this is the case.
     */
    public static String LOCALE_PULLED_FROM_SESSION = "blLocalePulledFromSession";

    @Resource(name = "blLocaleService")
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
