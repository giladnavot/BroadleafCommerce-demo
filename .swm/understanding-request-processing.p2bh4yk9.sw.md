---
title: Understanding Request Processing
---
Request processing in BroadleafCommerce-demo involves several components. The `doFilter` method in `RepeatSubmitProtectionFilter.java` is a key part of this process, checking if a session can be used and handling repeat requests. The `BroadleafWebRequestProcessor` interface defines the `process` method, which is used for processing requests from Servlet Filters or Spring interceptors. The `CustomerPaymentGatewayAbstractController` class has a `createCustomerPayment` method that initiates the creation of a saved payment token. The `TranslationRequestProcessor` class sets the translation context for the request. All these components work together to handle and process incoming requests in the application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/util/RepeatSubmitProtectionFilter.java" line="48">

---

# Filters

The `doFilter` method in `RepeatSubmitProtectionFilter` is a key part of the request processing flow. It checks if the request can use a session, and if so, it processes the request accordingly. If the request is already being processed, it sets the response status to `NO_CONTENT` and returns. Otherwise, it adds the request to the list of requests being processed and continues with the filter chain.

```java
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean useSession = true;
        if (BroadleafRequestContext.getBroadleafRequestContext() != null
                && BroadleafRequestContext.getBroadleafRequestContext().getWebRequest() != null) {
            if (!BLCRequestUtils.isOKtoUseSession(BroadleafRequestContext.getBroadleafRequestContext().getWebRequest())) {
                useSession = false;
            }
        } else if (!BLCRequestUtils.isOKtoUseSession(new ServletWebRequest((HttpServletRequest) request))) {
            useSession = false;
        }

        if (useSession) {
            String sessionId;
            String requestURI;
            synchronized(requests) {
                sessionId = ((HttpServletRequest) request).getSession().getId();
                requestURI = ((HttpServletRequest) request).getRequestURI();
                if (requests.containsKey(sessionId) && requests.get(sessionId).contains(requestURI)) {
                    //we are currently already processing this request
                    ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NO_CONTENT);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafWebRequestProcessor.java" line="63">

---

# Request Processors

The `BroadleafWebRequestProcessor` interface defines methods for processing web requests. The `process` method is used to process the current request, while the `postProcess` method is used for any work that needs to be done after the request has been processed.

```java
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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/BroadleafAbstractController.java" line="57">

---

# Controllers

The `BroadleafAbstractController` class provides convenience methods for controllers. For example, the `isAjaxRequest` method checks if a request was made via AJAX.

```java
    protected boolean isAjaxRequest(HttpServletRequest request) {
        return BroadleafControllerUtility.isAjaxRequest(request);       
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
