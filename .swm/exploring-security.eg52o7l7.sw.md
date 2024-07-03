---
title: Exploring Security
---
Security in BroadleafCommerce-demo refers to the measures taken to protect the application and its data. This includes mechanisms to prevent unauthorized access and data breaches. The security features are implemented using various classes and interfaces such as `SecurityFilter`, `CookieUtils`, and `PasswordUtils`. These classes handle tasks like CSRF token validation, cookie management, and password generation. The `SecurityFilter` class, for instance, checks the validity of CSRF tokens on every POST request and validates tokens to prevent stale page submissions. The `CookieUtils` interface provides methods for managing cookies, including setting and invalidating cookie values. The `PasswordUtils` class provides utility methods for password-related operations.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/security/util/ServerCookie.java" line="68">

---

# appendCookieValue Method

The `appendCookieValue` method is used to securely append cookie values. It takes several parameters including the cookie name, value, path, domain, and security settings, and appends these to the cookie header. It also handles version-specific information and ensures secure cookie handling based on the provided parameters.

```java
    // TODO RFC2965 fields also need to be passed
    public static void appendCookieValue( StringBuffer headerBuf,
            int version,
            String name,
            String value,
            String path,
            String domain,
            String comment,
            int maxAge,
            boolean isSecure,
            boolean isHttpOnly)
    {
        StringBuffer buf = new StringBuffer();
        // Servlet implementation checks name
        buf.append( name );
        buf.append("=");
        // Servlet implementation does not check anything else

        version = maybeQuote2(version, buf, value,true);

        // Add version 1 specific information
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/security/util/CookieUtils.java" line="25">

---

# CookieUtils Interface

The `CookieUtils` interface provides several methods for secure cookie handling. For instance, `shouldUseSecureCookieIfApplicable` checks whether to use a secure HTTPS cookie, `getCookieValue` retrieves the value of a specified cookie, and `setCookieValue` securely sets a cookie value. These methods help ensure secure handling of cookies in the application.

```java
public interface CookieUtils {
    String CUSTOMER_COOKIE_NAME = "customerId";

    /**
     * Checks <code>cookies.use.secure</code> System Property, which determines whether to use HTTPS cookie over
     * HTTPS connection or HTTP only.
     * 
     * @return value of <code>cookies.use.secure</code>
     */
    Boolean shouldUseSecureCookieIfApplicable();
    
    String getCookieValue(HttpServletRequest request, String cookieName);

    /**
     *  Uses a cookie value of "CookieInvalidationPlaceholderValue" because the later call to 
     *  {@link ESAPI#httpUtilities()#addHeader(HttpServletResponse, String, String)} 
     *  fails if the value is <code>null</code> or an empty String. If an empty cookie value is passed, 
     *  this is considered a request to remove the cookie and <code>maxAge</code> is set to 0 to force the removal.
     *  In addition, calls to {@link ESAPI#httpUtilities()#killCookie(HttpServletRequest, HttpServletResponse, String)} 
     *  have shown to be ineffective while this approach for removing cookies works.
     *  
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
