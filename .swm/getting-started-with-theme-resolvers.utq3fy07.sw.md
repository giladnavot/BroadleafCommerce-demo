---
title: Getting started with Theme Resolvers
---
Theme Resolvers in BroadleafCommerce-demo refer to the functionality of determining the theme used by Broadleaf Commerce for the current request. The `BroadleafThemeResolver` interface is responsible for this task. It has a method `resolveTheme` that takes a `WebRequest` as a parameter and returns a `Theme` object. For a single site installation, this method should return a theme whose path and name are empty strings. There is also a `NullBroadleafThemeResolver` class that implements the `BroadleafThemeResolver` interface and always returns a default `Theme` object. This is typically used for non-multi-site implementations of Broadleaf Commerce.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafThemeResolver.java" line="26">

---

## BroadleafThemeResolver Interface

The `BroadleafThemeResolver` interface is the primary contract for theme resolution in Broadleaf Commerce. It declares the `resolveTheme` method, which is responsible for determining the theme for the current request.

```java
/**
 * Responsible for returning the theme used by Broadleaf Commerce for the current request.
 * For a single site installation, this should return a theme whose path and name are empty string.
 *
 * @author bpolster
 */
public interface BroadleafThemeResolver {
    
    public static final String BRC_THEME_CHANGE_STATUS = "themeChangeStatus";
    
    /**
     * 
     * @deprecated Use {@link #resolveTheme(WebRequest)} instead
     */
    @Deprecated
    public Theme resolveTheme(HttpServletRequest request, Site site);
    
    public Theme resolveTheme(WebRequest request);
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/NullBroadleafThemeResolver.java" line="34">

---

## Implementing BroadleafThemeResolver

Here's an example of a class implementing `BroadleafThemeResolver`. The `NullBroadleafThemeResolver` returns a default `Theme` for all requests. This is typically used in non-multi-site implementations of Broadleaf Commerce.

```java
public class NullBroadleafThemeResolver implements BroadleafThemeResolver {
    private final Theme theme = new ThemeDTO();

    @Override
    public Theme resolveTheme(HttpServletRequest request, Site site) {
        return resolveTheme(new ServletWebRequest(request));
    }
    
    @Override
    public Theme resolveTheme(WebRequest request) {
        return theme;
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafThemeResolverFilter.java" line="42">

---

## Using BroadleafThemeResolver

The `BroadleafThemeResolverFilter` is an example of how `BroadleafThemeResolver` is used. This filter is responsible for setting up the theme used by Broadleaf Commerce components for each request. It uses the `BroadleafThemeProcessor` to process the theme for the current request.

```java
/**
 * Responsible for setting up the theme used by Broadleaf Commerce components.  This Filter is specifically
 * placed after the CustomerState is established to that a Content Targeter altering the Theme can make
 * use of Customer/CustomerSegment attributes.
 *
 * @author Stanislav Fedorov
 */
@Component("blThemeResolverFilter")
@ConditionalOnNotAdmin
public class BroadleafThemeResolverFilter extends AbstractIgnorableOncePerRequestFilter {

    private final Log LOG = LogFactory.getLog(getClass());

    // Properties to manage URLs that will not be processed by this filter.
    private static final String BLC_ADMIN_GWT = "org.broadleafcommerce.admin";
    private static final String BLC_ADMIN_PREFIX = "blcadmin";
    private static final String BLC_ADMIN_SERVICE = ".service";

    private Set<String> ignoreSuffixes;

    @Autowired
```

---

</SwmSnippet>

# Theme Resolvers in Broadleaf Commerce

The Broadleaf Commerce framework uses Theme Resolvers to manage and apply themes to the application. The main functions involved in this process are located in the BroadleafThemeResolverFilter and BroadleafThemeResolver classes.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafThemeResolverFilter.java" line="42">

---

## BroadleafThemeResolverFilter

The `BroadleafThemeResolverFilter` class is responsible for setting up the theme used by Broadleaf Commerce components. This filter is placed after the CustomerState is established so that a Content Targeter altering the Theme can make use of Customer/CustomerSegment attributes. It contains methods like `doFilterInternalUnlessIgnored` which checks if the URL should be processed and then processes the theme.

```java
/**
 * Responsible for setting up the theme used by Broadleaf Commerce components.  This Filter is specifically
 * placed after the CustomerState is established to that a Content Targeter altering the Theme can make
 * use of Customer/CustomerSegment attributes.
 *
 * @author Stanislav Fedorov
 */
@Component("blThemeResolverFilter")
@ConditionalOnNotAdmin
public class BroadleafThemeResolverFilter extends AbstractIgnorableOncePerRequestFilter {

    private final Log LOG = LogFactory.getLog(getClass());

    // Properties to manage URLs that will not be processed by this filter.
    private static final String BLC_ADMIN_GWT = "org.broadleafcommerce.admin";
    private static final String BLC_ADMIN_PREFIX = "blcadmin";
    private static final String BLC_ADMIN_SERVICE = ".service";

    private Set<String> ignoreSuffixes;

    @Autowired
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafThemeResolver.java" line="32">

---

## BroadleafThemeResolver

The `BroadleafThemeResolver` interface is responsible for returning the theme used by Broadleaf Commerce for the current request. For a single site installation, this should return a theme whose path and name are empty string. It contains methods like `resolveTheme` which determines the theme for the current request.

```java
public interface BroadleafThemeResolver {
    
    public static final String BRC_THEME_CHANGE_STATUS = "themeChangeStatus";
    
    /**
     * 
     * @deprecated Use {@link #resolveTheme(WebRequest)} instead
     */
    @Deprecated
    public Theme resolveTheme(HttpServletRequest request, Site site);
    
    public Theme resolveTheme(WebRequest request);
}

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
